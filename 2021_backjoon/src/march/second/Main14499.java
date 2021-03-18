package march.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14499 {
    static int map[][]; //지도
    static int dice[] = new int[7];  //주사위(-, t, tt, r, l, tb, b)
    static int x;   //주사위 좌표 x
    static int y;   //주사위 좌표 y
    static int k;   //이동횟수

    final static int EAST = 1;  //동
    final static int WEST = 2;  //서
    final static int NORTH = 3; //
    final static int SOUTH = 4; //남

    static Queue<Integer> kQueue = new LinkedList<>();  //이동횟수 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            kQueue.add(Integer.parseInt(st2.nextToken()));
        }

        while (!kQueue.isEmpty()) {
            int dir = kQueue.poll();

            int[] originDice = dice.clone();
            int originX = x;
            int originY = y;

            moveDice(dir); //주사위 굴리기
            moveXY(dir);    //좌표 옮기기

            //이동을 못할경우 아무것도 X
            if (!(x >= 0 && x <= n - 1) && (y >= 0 && y <= n - 1)) {
                dice = originDice;
                x = originX;
                y = originY;
                continue;
            }

            int number = map[x][y]; //현재 바닥의 숫자
            if (number == 0) {
                map[x][y] = dice[6];
            } else {
                dice[6] = map[x][y];
                map[x][y] = 0;
            }
            System.out.print(dice[1]);
            System.out.println();
        }
    }

    private static void moveDice(int dir) {
        int[] diceCopy = dice.clone();
        switch (dir) {
            case EAST:
                dice[1] = diceCopy[3];
                dice[3] = diceCopy[6];
                dice[4] = diceCopy[1];
                dice[6] = diceCopy[4];
                break;
            case WEST:
                dice[1] = diceCopy[4];
                dice[3] = diceCopy[1];
                dice[4] = diceCopy[6];
                dice[6] = diceCopy[3];
                break;
            case NORTH:
                dice[1] = diceCopy[2];
                dice[2] = diceCopy[6];
                dice[5] = diceCopy[1];
                dice[6] = diceCopy[5];
                break;
            case SOUTH:
                dice[1] = diceCopy[5];
                dice[2] = diceCopy[1];
                dice[5] = diceCopy[6];
                dice[6] = diceCopy[2];
                break;
        }
    }

    public static void moveXY(int dir) {
        switch (dir) {
            case EAST:
                y += 1;
                break;
            case WEST:
                y -= 1;
                break;
            case NORTH:
                x -= 1;
                break;
            case SOUTH:
                x += 1;
                break;
        }
    }
}
