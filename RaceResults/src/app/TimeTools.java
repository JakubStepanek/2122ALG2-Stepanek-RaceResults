package app;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *hipityhopityuco
 * @author kubin
 */
public class TimeTools {

    private int hours;
    private int minutes;
    private int seconds;

    // constructor mask: XX XX XXXX
    public TimeTools(String time) {
        String[] arrOfTime = time.split(" ");
        int[] arrInt = new int[3];
        for (int i : arrInt) {
            arrInt[i] = Integer.parseInt(arrOfTime[i]);
        }
        this.hours = arrInt[0];
        this.minutes = arrInt[1];
        this.seconds = arrInt[2];
    }

    public TimeTools(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public static String secondsToTimeString(int seconds) {
        // TO-DO: StringBuilder sb = new StringBuilder();
        int h, m, s, sLeft;
        h = seconds / 3600;
        sLeft = seconds - h * 3600;
        m = sLeft / 60;
        s = sLeft - m * 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public int[] secondsToTimeArray(int seconds) {
        int[] ret = new int[3];
        int sLeft;
        ret[0] = seconds / 3600;
        sLeft = seconds - ret[0] * 3600;
        ret[1] = sLeft / 60;
        ret[2] = sLeft - ret[1] * 60;
        return ret;
    }

    public static int timeCompare(int startTimeSeconds, int endTimeSeconds) {
        return endTimeSeconds - startTimeSeconds;
    }

    public static int timeToSeconds(int hours, int minutes, int seconds) {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static int timeToSeconds(String seconds) {
        String arr[] = seconds.split("'", 2);
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
