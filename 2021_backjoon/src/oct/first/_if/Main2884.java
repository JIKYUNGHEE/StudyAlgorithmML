package oct.first._if;

import java.util.Scanner;

/**
 * 알람시계
 */
public class Main2884 {
    private static final int ALARM_TIME = 45;
    private static final int MIDNIGHT = 24;
    private static final int ADD_M = 60;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] hm = sc.nextLine().split(" ");

        int h = Integer.parseInt(hm[0]);
        int m = Integer.parseInt(hm[1]);

        System.out.println(getAlarmTime(h, m));
    }

    private static String getAlarmTime(int h, int m) {
        int alarmH = h;
        int alarmM = m - ALARM_TIME;

        if (alarmM < 0) {
            if(h == 0) {
                alarmH = MIDNIGHT;
            }
            --alarmH;
            alarmM += ADD_M;
        }

        return alarmH + " " + alarmM;
    }
}
