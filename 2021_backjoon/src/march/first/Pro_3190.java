package march.first;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.Queue;

import java.util.Scanner;


public class Pro_3190 {

    static int N, K, L;
    static int[][] map;

    // 0 북, 1:동, 2:남, 3:서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static String second;
    static String dirCode;

    static int curRow;
    static int curCol;

    static int dir = 1;

    static ArrayList<String[]> moveInfo = new ArrayList<String[]>();

    static boolean isEnd;
    static int count;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        map = new int[N + 1][N + 1];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 0;
            }
        }

        K = sc.nextInt();

        for (int i = 0; i < K; i++) {
            int apple_row = sc.nextInt();
            int apple_col = sc.nextInt();
            map[apple_row][apple_col] = 2;
        }


        L = sc.nextInt();

        for (int i = 0; i < L; i++) {
            second = sc.next();
            dirCode = sc.next();
            moveInfo.add(new String[]{second, dirCode});        // 초, 방향  (L:왼쪽, D:오른쪽)
        }


        // 시작 전 setting

        count = 0;
        curRow = 1;
        curCol = 1;

        map[curRow][curCol] = 1;
        isEnd = false;

        queue.add(new int[]{curRow, curCol});

        for (int i = 0; i < L; i++) {
            second = moveInfo.get(i)[0];
            dirCode = moveInfo.get(i)[1];

            if (!move()) {
                break;
            }


            if (dirCode.equals("L")) {
                dir = (dir + 3) % 4;
            } else if (dirCode.equals("D")) {
                dir = (dir + 1) % 4;
            }
        }

        while (!isEnd) {
            count++;
            int nextRow = curRow + dx[dir];
            int nextCol = curCol + dy[dir];

            if (nextRow <= 0 || nextRow > N || nextCol <= 0 || nextCol > N || map[nextRow][nextCol] == 1) {
                isEnd = true;
            }

            curRow = nextRow;
            curCol = nextCol;
        }


        System.out.println(count);
        sc.close();
    }


    public static boolean move() {

        while (count < Integer.parseInt(second)) {
            count++;

            int nextRow = curRow + dx[dir];
            int nextCol = curCol + dy[dir];

            if (nextRow <= 0 || nextRow > N || nextCol <= 0 || nextCol > N || map[nextRow][nextCol] == 1) {
                isEnd = true;
                return false;
            }


            if (map[nextRow][nextCol] == 0) {        // 사과 없을때
                int[] a = queue.poll();
                map[a[0]][a[1]] = 0;
            }

            map[nextRow][nextCol] = 1;
            queue.add(new int[]{nextRow, nextCol});
            curRow = nextRow;
            curCol = nextCol;
        }

        return true;
    }
}