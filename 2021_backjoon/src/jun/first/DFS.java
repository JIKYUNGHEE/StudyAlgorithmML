package jun.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DFS {
    //시계방향 - 위, 오, 아래, 왼
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited; //방문 표시할

    private static final int LAST_INDEX = 3;

    public static void main(String[] args) throws IOException {
        input();

        dfs(0, 0, 0);
//        print();
    }

    private static void dfs(int x, int y, int dir) {
        System.out.print(map[x][y] + "-> ");

        //4지점을 모두 방문했을 때 return
        boolean top = (x + dx[0] < 0) || (visited[x + dx[0]][y + dy[0]]);
        boolean right = (y + dy[1] > M - 1) || visited[x + dx[1]][y + dy[1]];
        boolean bottom = (x + dx[2] > N - 1) || visited[x + dx[2]][y + dy[2]];
        boolean left = (y + dy[3] < 0) || visited[x + dx[3]][y + dy[3]];
        if (top && right && bottom && left) {
            System.out.println("DFS 끝!");
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > M - 1) { //map 을 벗어날 경우
                continue;
            }

            if (visited[nextX][nextY]) {
                continue;
            }

            visited[nextX][nextY] = true;
            dfs(nextX, nextY, i);
            visited[nextX][nextY] = false;
        }
    }

    private static void print() {
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
