# Semestrální práce - RaceResults

## Abstrakt

Aplikace má simulovat správu datábáze a vyhodnocování závodníků například závodů ze seriálu **MotoGP**.

Aplikace v souborech uchovává seznam závodníků a přiřazuje jim data - umístění v daném závodě, maximální rychlost během závodu atd. Dále umožňuje vytvářet nové závody a upravovat data závodníků.

**Cílem** práce je uchovat data závodníků a přehledně je zobrazit-

Funkční specifikace – seznam funkcí z pohledu uživatele, které bude Váš program poskytovat např. formou větveného seznamu (stromu). Může sloužit následně jako podklad pro menu.

## Funkční specifikace
Aplikace běží v konzolovém menu:

### 1. zobrazit výsledky závodu
- načíst data data ze souboru
- zobrazit data
- uložit data 
### 2. zahájit nový závod
- vytvořit závod
    - přidat závodníky
    - upravit závodníky
    - smazat závodníky
- uložit závod do souboru
### 3. upravit v načteném závodě závodníka
### 4. vypsat detail závodníků
### 5. uložit načtené závodníky
### 6. uložit načtené závodníky do binárního souboru
### 7. načíst závodníky z binárního soubor



  



## Popis řešení

Cílem bylo co nejvíce oddělit logiku od zobrazovací vrstvy. Aplikace je rozdělena do několika vrstev. Do datové vrstvy, servisní a prezenční. K tomu jsou ještě nějaké pomocné utility (Db, Menu, IO, ...).

Jediný kdo komunikuje s databází, která se nachází na serveru (pavel-vacha.cz) je datová vrstva **Repository** přes databázový wrapper. Tyto data zpracovává pouze servisní vrstva **Service** a o tyto data si žádá **View**. (Proto se nejdná o MVC. V MVC View dostane data naservírované už od kontroleru a View generuje eventy na které kontroler reaguje).

Základem všech pohledu je abstraktní třída **View**, která má v sobě `display()` a `showMessage()`

O routování mezi View se stará třída **Navigator**. Má v sobě jakýsi zásobník a na základě zavolané metody se s ním pracuje... Více v kódu..

O výpis menu (i s korektně ošetřenými právy) se stará třída _Menu_

Databázový wrapper má vytvořený pool připojení a dynamicky je přidává a ubírá dokud není vyčerpáná interně zvolená kapacita. (Nebo server nepřestane odpovídat :)). Na serveru běží `10.3.27-MariaDB-0+deb10u1 - Debian 10`. Schéma lze najít níže

## Databázové schéma

  ![Databázové schéma](./images/semestral_project.png)

## Formát souborů

Vstupní soubor pro inspekci. Formát **.txt** první řádek jsou hlavičky a další soubory jsou oddělené mezerou. Datum musí být ve formátu **(dd/mm/yyyy)**

```
idDevice    idInspection    supplier        inspectionDate
3           2               Altier          25/5/2022
3           3               Caviar          26/5/2022
3           1               Savo            13/5/2022
```

Výstupní formát předmětů ze zdravotechniky. V hlavičce je ID, interní registrovací čísel, název a typ zařízení z číselníku. Vše je odděleno tabama. (Data se již neimportují zpět)

```
ID    IRN           NAME                  CPV_DEVICE_TYPE
4     X-2321011     Rentegonový přístroj  Radiodiagnostické doplnňky(33124210-0)
```

V profilu jsem využil exportu a importu do binárního souboru, abych splnil požadavky :). Formát:

```
    1. int - ID uživatele
    2. utf - jméno uživatele
    3. utf - heslo uživatele (zahashované)
    4. boolean - je admin?
```

## Class diagram

![Objektový návrh](./images/uml.png)

## Testování

Zdrojový kód k testům najdete v `package com.tul.vacha.semestralproject.ui.ConsoleApp;`. Otestoval jsem několik základních CRUD operací, limitních stavů a chybně zadaných vstupů od uživatele.

![Objektový návrh](./images/tests.png)

## Popis fungování externí knihovny

MySQL connector poskytuje připojení klientské aplikace v Jave k  MySQL a zastřešuje Java Database Connectivity (JDBC) API

Použití:

```java
import java.sql.*;
class MysqlCon {

    public static void main(String args[]) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "root");

            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from emp");

            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.

                    getString(3));
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
```

## ResultSetPropertiesSimplifyHelps

Dále bylo využito jakési knihovničky ResultSetPropertiesSimplifyHelps, která by měla být schopna automaticky mapovat ResultSet z JDBC do javovských objektů.

Po mírném zásahu a opravách v knihovně to bylo možné. Knihovnu jsem dále nezkoumal, je možné, že by mohla být i nebezpečná -> deserializace objektu všeobecně je nebezpečná, když je udělaná špatně.
