package april.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main15684 {
    public final static int VERTICAL_DIR = 0;
    public static int HORIZONTAL_DIR = -1;    //<-: -1, ->: +1

    public static int N;
    public static int M;
    public static int H;
    public static int FINAL;

    public static boolean[][] map;

    public static void main(String[] args) throws IOException {
        ////////////////////// [input] //////////////////////
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        N = Integer.parseInt(line1[0]);
        M = Integer.parseInt(line1[1]);
        H = Integer.parseInt(line1[2]);
        FINAL = M > H ? M : H;

        map = new boolean[N + 1][M + 1];

        for (int i = 0; i < M; i++) {
            String[] infoLine = br.readLine().split(" ");
            int x = Integer.parseInt(infoLine[0]);
            int y = Integer.parseInt(infoLine[1]);
            map[x][y] = true;
        }

        for (boolean[] x : map) {
            for (boolean y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        /////////////////////////////////////////////////////

        //가로 인덱스가 어디서 끝나는 지 구한다.
        int index = 1;
        int nowX = 1;
        int nowY = 1;

        while (true) {
            if (nowY >= FINAL) {
                break;
            }

            goNext(index, nowX, nowY);
        }

    }

    private static void goNext(int index, int nowX, int nowY) {
        if(nowY == FINAL) {
            return;
        }

        boolean nowDir = map[nowX][nowY];
        if (nowDir) {
            HORIZONTAL_DIR = -HORIZONTAL_DIR;
        } else {
            boolean nextDir = map[nowX][nowY + HORIZONTAL_DIR];
            if (nextDir) {
                goNext(index, nowX, nowY + HORIZONTAL_DIR);
                return; //??????
            }
        }

        goNext(index, nowX + 1, nowY);
    }
}
