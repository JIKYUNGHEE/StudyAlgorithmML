package march.thrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main14500_pre {

    static int n;
    static int m;
    static int[][] map;
    static List<Tetromino> tetrominos = new ArrayList<>();

    static List<Integer> sums = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        initTetrominos();

        for (Tetromino tetromino : tetrominos) {
            while (true) {
                if (tetromino.maxX > n - 1) {
                    break;
                }

                int sum = map[tetromino.x1][tetromino.y1] + map[tetromino.x2][tetromino.y2] + map[tetromino.x3][tetromino.y3] + map[tetromino.x4][tetromino.y4];
                sums.add(sum);

//                moveXY(tetromino, true);
                //maxY가 m-1 보다 크면
                if (!(tetromino.maxY >= m - 1)) { //오른쪽으로 1칸 이동
                    tetromino.y1 += 1;
                    tetromino.y2 += 1;
                    tetromino.y3 += 1;
                    tetromino.y4 += 1;
                    tetromino.maxY +=1;
                } else {    //아래로 1칸 이동
                    tetromino.x1 += 1;
                    tetromino.x2 += 1;
                    tetromino.x3 += 1;
                    tetromino.x4 += 1;
                    tetromino.maxX += 1;
                }
            }
        }

        //sumList에 있는 sum중 가장 큰 걸 구한다.
        int max = 0;
        for (Integer sum : sums) {
            if(sum > max) {
                max = sum;
            }
        }

        System.out.print(max);
    }

    private static void moveXY(Tetromino tetromino, boolean moveY) {
        if (tetromino.maxX > n - 1) {
            return;
        }

        if (moveY) { //오른쪽으로 1칸 이동
            tetromino.y1 += 1;
            tetromino.y2 += 1;
            tetromino.y3 += 1;
            tetromino.y4 += 1;
        } else {    //아래로 1칸 이동
            tetromino.x1 += 1;
            tetromino.x2 += 1;
            tetromino.x3 += 1;
            tetromino.x4 += 1;

            moveY = true;
        }

        int sum = map[tetromino.x1][tetromino.y1] + map[tetromino.x2][tetromino.y2] + map[tetromino.x3][tetromino.y3] + map[tetromino.x4][tetromino.y4];
        sums.add(sum);

        if (tetromino.maxY > m - 1) {
            moveY = false;
        }
    }

    private static void initTetrominos() {
        tetrominos.add(new Tetromino(0, 0, 0, 1, 0, 2, 0, 3, 0, 3));
        tetrominos.add(new Tetromino(0, 0, 1, 0, 2, 0, 3, 0, 3, 0));
        tetrominos.add(new Tetromino(0, 0, 0, 1, 1, 0, 1, 1, 1, 1));
        tetrominos.add(new Tetromino(0, 1, 1, 1, 2, 0, 2, 1, 2, 1));
        tetrominos.add(new Tetromino(0, 0, 1, 0, 2, 0, 2, 1, 2, 1));
        tetrominos.add(new Tetromino(0, 0, 0, 1, 0, 2, 1, 0, 1, 2));
        tetrominos.add(new Tetromino(0, 0, 0, 1, 0, 2, 1, 2, 1, 2));
        tetrominos.add(new Tetromino(0, 0, 1, 0, 1, 1, 1, 2, 1, 2));
        tetrominos.add(new Tetromino(0, 1, 1, 0, 1, 1, 2, 0, 2, 1));
        tetrominos.add(new Tetromino(0, 0, 0, 1, 1, 1, 1, 2, 1, 2));
        tetrominos.add(new Tetromino(0, 1, 0, 2, 1, 0, 1, 1, 1, 2));
        tetrominos.add(new Tetromino(1, 0, 0, 1, 1, 1, 1, 2, 1, 2));
        tetrominos.add(new Tetromino(0, 0, 0, 1, 0, 2, 1, 1, 1, 2));
        tetrominos.add(new Tetromino(0, 1, 1, 0, 1, 1, 2, 1, 2, 1));
        tetrominos.add(new Tetromino(0, 0, 1, 0, 2, 0, 1, 1, 2, 1));
    }

    private static class Tetromino {
        int x1;
        int y1;

        int x2;
        int y2;

        int x3;
        int y3;

        int x4;
        int y4;

        int maxX;
        int maxY;

        public Tetromino(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int maxX, int maxY) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
            this.x4 = x4;
            this.y4 = y4;
            this.maxX = maxX;
            this.maxY = maxY;
        }
    }
}
