package march.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 뱀
 */
public class Main3190 {

    private static final String L = "L";

    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    //위, 아래, 왼, 오른
    private static final int dx[] = {0, 0, -1, 1};
    private static final int dy[] = {-1, 1, 0, 0};

    private static int n;
    private static int k;

    private static int[][] map;
    private static Map<Integer, String> changeDirMap = new HashMap<>();
    private static Deque<SnailDot> snail = new ArrayDeque<>();

    static class SnailDot {
        int x;
        int y;

        public SnailDot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n을 입력받는다.
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];

        //사과의 개수(K)를 입력받는다.
        k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < k; i++) { //사과의 좌표를 입력받는다.(for)
            st = new StringTokenizer(br.readLine());
            int ax = Integer.parseInt(st.nextToken());
            int ay = Integer.parseInt(st.nextToken());
            map[ax][ay] = 1;
        }

        //방향 변환 횟수(L)을 입력받는다.
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) { //x초, L/D (for)
            st = new StringTokenizer(br.readLine());
            changeDirMap.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        //Queue 에 Snail 정보를 넣는다.
        SnailDot head = new SnailDot(1, 1);
        snail.add(head);
        map[1][1] = 2;

        move(0, RIGHT);
    }

    private static void move(int time, int dir) {
        //움직인다.
        String changeDir = changeDirMap.get(time);  //L(완쪽) 또는 D(오른쪽)
        if (changeDir != null) { //방향을 틀경우
            dir = calculateDir(dir, changeDir);
        }

        for (SnailDot snailInfo : snail) { //전체적으로 이동 후,
            snailInfo.x += dx[dir];
            snailInfo.y += dy[dir];
        }

        SnailDot head = snail.getFirst();  //Snail 머리 정보를 뽑는다.
        //머리가 벽에 부딫히면 return
        if (head.y > n || head.y <= 0 || head.x > n || head.x <= 0) {
            System.out.println(time + 1);
            return;
        }

        //자기자신의 몸에 부딫히면 return
        if (map[head.x][head.y] == 2) {
            System.out.println(time + 1);
            return;
        }

        if (map[head.x][head.y] == 1) {  //사과일 경우
            map[head.x][head.y] = 2;
            snail.addFirst(new SnailDot(head.x, head.y));
        } else if (map[head.x][head.y] == 0) {   //사과가 아닐 경우
            map[head.x][head.y] = 2;
            snail.addFirst(new SnailDot(head.x, head.y));

            SnailDot tail = snail.removeLast();
            map[tail.x][tail.y] = 0;
        }

        move(time + 1, dir);
    }

    private static int calculateDir(int dir, String changeDir) {
        switch (dir) {
            case TOP:
                return (changeDir.equals(L)) ? LEFT : RIGHT;
            case BOTTOM:
                return (changeDir.equals(L)) ? RIGHT : LEFT;
            case LEFT:
                return (changeDir.equals(L)) ? BOTTOM : TOP;
            case RIGHT:
                return (changeDir.equals(L)) ? TOP : BOTTOM;
        }
        return 0;
    }
}
