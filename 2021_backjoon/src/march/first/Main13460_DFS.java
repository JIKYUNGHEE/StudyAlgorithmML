package march.first;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 구슬탈출2(13460)
 */
public class Main13460_DFS {
    //위, 아래, 오, 왼
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int TOP = 3;

    public static final char RED = 'R';
    public static final char BLUE = 'B';
    public static final char HOLE = 'O';
    public static final char WALL = '#';
    public static final char ROAD = '.';

    public static final int FAIL = -1;
    public static final int LIMIT = 10;
    public static final int MAX = 999;

    public static char[][] map; //입력받은 값
    public static boolean[][][][] visited;  //빨간, 파란공의 방문 여부

    public static int N;
    public static int M;

    public static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = MAX;
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        Ball now = new Ball();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char here = line.charAt(j);
                if (here == BLUE) {
                    now.bx = i;
                    now.by = j;
                } else if (here == RED) {
                    now.rx = i;
                    now.ry = j;
                } else {
                    map[i][j] = here;
                }
            }
        }
        visited[now.rx][now.ry][now.bx][now.by] = true;

        move(now, 1, -1);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result == MAX ? -1 : result));
        bw.flush();
        bw.close();
    }

    private static void move(Ball now, int cnt, int dir) {
        if (cnt > LIMIT) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (isSameDirection(i, dir)) continue;

            Ball next = new Ball();
            int bx = now.bx;
            int by = now.by;
            int rx = now.rx;
            int ry = now.ry;

            int bcnt = 0;
            int rcnt = 0;

            //파란볼 먼저 이동
            while (map[bx + dx[i]][by + dy[i]] != WALL) {
                bx += dx[i];
                by += dy[i];
                bcnt++;
                if (map[bx][by] == HOLE)
                    break;
            }
            next.bx = bx;
            next.by = by;

            //빨간볼 먼저 이동
            while (map[rx + dx[i]][ry + dy[i]] != WALL) {
                rx += dx[i];
                ry += dy[i];
                rcnt++;
                if (map[rx][ry] == HOLE)
                    break;
            }

            next.rx = rx;
            next.ry = ry;

            //파란볼이 구멍에 들어가면 무효
            if (map[next.bx][next.by] == HOLE) {
                continue;
            }

            //빨간볼이 구멍에 들어가면 결과
            if (map[next.rx][next.ry] == HOLE) {
                result = Math.min(cnt, result);
                break;
            }

            //둘 다 그자리면 이 이상 갈 수 없음
            if (now.rx == next.rx && now.ry == next.ry && now.bx == next.bx && now.by == next.by)
                continue;

            //같은 칸이면 많이 움직인 구슬을 이전 칸으로 이동
            if (next.bx == next.rx && next.by == next.ry) {
                if (bcnt > rcnt) {
                    next.bx -= dx[i];
                    next.by -= dy[i];
                } else {
                    next.rx -= dx[i];
                    next.ry -= dy[i];
                }
            }

            //방문 했으면 안감
            if (visited[next.rx][next.ry][next.bx][next.by]) continue;

            visited[next.rx][next.ry][next.bx][next.by] = true;
            move(next, cnt + 1, i);
            visited[next.rx][next.ry][next.bx][next.by] = false;
        }
    }

    private static boolean isSameDirection(int now, int before) {
        if (before == now) return true;
        else {
            switch (before) {
                case LEFT:
                    if (now == RIGHT) return true;
                    break;
                case RIGHT:
                    if (now == LEFT) return true;
                    break;
                case BOTTOM:
                    if (now == TOP) return true;
                    break;
                case TOP:
                    if (now == BOTTOM) return true;
                    break;
                default:
                    return false;
            }
        }
        return false;
    }

    static class Ball {
        int rx;
        int ry;
        int bx;
        int by;

        public Ball(int rx, int ry, int bx, int by) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
        }

        public Ball() {
        }
    }
}

