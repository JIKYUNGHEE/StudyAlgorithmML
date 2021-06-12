package jun.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 낚시왕
 */
public class Main17143 {
    private static int R;
    private static int C;
    private static int M;   //상어의

    private static int[][] map;

    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};

    private static Queue<Shark> sharksQueue = new LinkedList<>();
    private static int totalSizeOfShark = 0;

    static class Shark {
        int r;
        int c;
        int x;  //스피드
        int d;  //방향
        int z;  //사이즈

        Shark(int r, int c, int x, int d, int z) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        for (int i = 0; i < R; i++) {
            //낚시왕이 오른쪽으로 한칸 이동한다.
            moveFishMan();

            //낚시왕이 자신과 제일 가까운 상어를 잡는다.
            catchShark();

            //상어가 이동한다.
            moveSharks();

            //상어가 상어를 잡아먹는다.
            eatSharks();
        }

        System.out.println(totalSizeOfShark);
    }

    private static void moveFishMan() {
    }

    private static void catchShark() {
        //bfs 이용

    }

    private static void moveSharks() {

    }

    private static void eatSharks() {
        //한 칸의 상어가 2마리 이상일 때
            //크기가 큰 상어가 남는다.

    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 초
        map = new int[R][C];

        // shark 정보 저장
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int x  = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z  = Integer.parseInt(st.nextToken());

            sharksQueue.add(new Shark(r, c, x, d, z));
        }
    }
}
