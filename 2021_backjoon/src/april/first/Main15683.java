package april.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

/**
 * 15683 감시
 * <p>
 * [input]
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 1 0 6 0
 * 0 0 0 0 0 0
 * <p>
 * [output]
 * 20
 */
public class Main15683 {
    private static int[][] map;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        ////////////////////// [input] //////////////////////
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(s1[0]);
        M = Integer.parseInt(s1[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] s2 = bufferedReader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(s2[j]);
            }
        }
        /////////////////////////////////////////////////////

        int x = 0;
        int y = 0;
        int[][] copyMap = map.clone();
        for (int[] mapX : map) {
            for (int mapXY : mapX) {
                int goLeftNum = goLeft(x, y, 0);
                map = copyMap;
                int goTopNum = goTop(x, y, 0);
                map = copyMap;
                int goRightNum = goRight(x, y, 0);
                map = copyMap;
                int goBottomNum = goBottom(x, y, 0);
                map = copyMap;

                switch (mapXY) {
                    case 1:
                        chooseMaxDir(goLeftNum, goTopNum, goRightNum, goBottomNum);
                        break;

                    case 2:
                        chooseMaxDir(goLeftNum+goRightNum, goTopNum+goBottomNum);
                        break;

                    case 3:
//                        chooseMaxDir(go);
                    case 4:
                    case 5:
                    case 6:
                }
                y++;
            }
            x++;
        }
    }

    private static void chooseMaxDir(int i, int i1) {
    }

    private static void chooseMaxDir(int goLeftNum, int goTopNum, int goRightNum, int goBottomNum) {

    }


    private static int goLeft(int x, int y, int cnt) {
        if (y <= 0 || map[x][y - 1] == 6) {
            return cnt;
        }

        map[x][y - 1] = -1;
        cnt++;
        goLeft(x, y - 1, cnt);

        return cnt;
    }

    private static int goTop(int x, int y, int cnt) {
        if (x <= 0 || map[x - 1][y] == 6) {
            return cnt;
        }

        map[x - 1][y] = -1;
        cnt++;
        goTop(x - 1, y, cnt);

        return cnt;
    }

    private static int goRight(int x, int y, int cnt) {
        if (y >= M - 1 || map[x][y + 1] == 6) {
            return cnt;
        }
        map[x][y + 1] = -1;
        cnt++;
        goRight(x, y + 1, cnt);

        return cnt;
    }

    private static int goBottom(int x, int y, int cnt) {
        if (x >= N - 1 || map[x + 1][y] == 6) {
            return cnt;
        }

        map[x + 1][y] = -1;
        cnt++;
        goBottom(x + 1, y, cnt);

        return cnt;
    }

}
