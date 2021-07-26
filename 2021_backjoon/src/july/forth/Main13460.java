package july.forth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 구슬탈출
 */
public class Main13460 {

    private static String EMPTY = ".";
    private static String WALL = "#";
    private static String HOLE = "O";
    private static String RED_BALL = "R";
    private static String BLUE_BALL = "B";

    private static int MAX_COUNT = 10;

    private static int count = 0;

    private static String[][] map;

    public static void main(String[] args) throws Exception {
        input();
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] lineArray = br.readLine().split(" ");
        int x = Integer.parseInt(lineArray[0]);
        int y = Integer.parseInt(lineArray[1]);

        map = new String[x][y];
        for (int i = 0; i < map.length; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = line[j];
            }
        }
    }
}
