package march.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main1 {

    private final static String timeSeparator = "~";
    private final static String hhmmSeparator = ":";

    private static List<Integer> minArrTimes = new ArrayList<>();
    private static List<Integer> maxArrTimes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] times = br.readLine().trim().split(timeSeparator);
            for (int j = 0; j < times.length; j++) {
                String[] hhmmTime = times[j].trim().split(hhmmSeparator);
                if (j == 0) {
                    minArrTimes.add(Integer.parseInt(hhmmTime[0] + hhmmTime[1]));
                } else if (j == 1) {
                    maxArrTimes.add(Integer.parseInt(hhmmTime[0] + hhmmTime[1]));
                }
            }
        }

        Collections.sort(minArrTimes);
        Collections.sort(maxArrTimes);

        int maxMinArrTime = minArrTimes.get(minArrTimes.size() - 1);
        int minMaxArrTime = maxArrTimes.get(0);

        if (minMaxArrTime < maxMinArrTime) {
            System.out.print(-1);
        } else {
            String startTime = Integer.toString(maxMinArrTime);
            String endTime = Integer.toString(minMaxArrTime);

            startTime = addZero(startTime);
            endTime = addZero(endTime);

            System.out.print(startTime.substring(0, 2) + hhmmSeparator + startTime.substring(2, 4) +
                    " " + timeSeparator + " "
                    + endTime.substring(0, 2) + hhmmSeparator + endTime.substring(2, 4));
        }
    }

    private static String addZero(String time) {
        int length = time.length();
        if(length == 4) {
            return time;
        }

        StringBuilder timeBuilder = new StringBuilder();
        for(int i=0; i<4 - length; i++) {
            timeBuilder.append(0);
        }
        timeBuilder.append(time);
        return timeBuilder.toString();
    }
}
