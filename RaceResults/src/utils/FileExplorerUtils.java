package utils;

import java.io.File;

/**
 * @author Jakub Štěpánek
 */
public final class FileExplorerUtils {
    private FileExplorerUtils() {

    }

    public static String showPathFile(File path, int level) {
        File[] files = path.listFiles();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < files.length; i++) {
            sb.append("\t".repeat(level));
            if (!(files[i].getName().startsWith("."))) {
                sb.append(files[i].getName() + "\n");
            }
            if (files[i].isDirectory()) {
                sb.append(showPathFile(files[i], level + 1));
            }
        }
        return sb.toString();
    }

    /**
     * Method to show Race menu, finds files in data folder
     */
    public static void showRaceMenu() {
        String actualDir = System.getProperty("user.dir") + File.separator + "Data";
        File file = new File(actualDir);
        System.out.println(FileExplorerUtils.showPathFile(file, 0));
        System.out.print("Zadejte soubor se závody: ");
    }

}