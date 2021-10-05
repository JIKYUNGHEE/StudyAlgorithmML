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

    //위, 왼, 아래, 오
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int MAX_COUNT = 10;

    private static int count = 0;

    private static String[][] map;

    private static Ball redBall;
    private static Ball blueBall;
    private static int holeX;
    private static int holeY;

    static class Ball {
        int x;
        int y;

        Ball(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //index 방향으로 굴릴 수 있는 데 까지 굴린다.
        public void roll(int index) {

        }
    }

    public static void main(String[] args) throws Exception {
        input();

        if (redBall != null && blueBall != null) {
            for (int i = 0; i < 4; i++) {
                //
                redBall.roll(i);
                blueBall.roll(i);
            }
        }
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
                String word = line[j];
                map[i][j] = word;
                if (word != null) {
                    if (word.equalsIgnoreCase(RED_BALL)) {
                        redBall = new Ball(i, j);
                    }

                    if (word.equalsIgnoreCase(BLUE_BALL)) {
                        blueBall = new Ball(i, j);
                    }

                    if (word.equalsIgnoreCase(HOLE)) {
                        holeX = i;
                        holeY = j;
                    }
                }
            }
        }
    }
}
