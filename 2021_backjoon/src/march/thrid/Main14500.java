package march.thrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 테트로미노
 */
public class Main14500 {

    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, -1, 0, 1};

    final static int WEST = 0;
    final static int NORTH = 1;
    final static int EAST = 2;
    final static int SOUTH = 3;

    final static int[] westX = {0, 1, 1, 2};
    final static int[] westY = {0, 0, 1, 0};
    final static int[] northX = {0, 1, 1, 2};
    final static int[] northY = {1, 0, 1, 1};
    final static int[] eastX = {1, 1, 0, 1};
    final static int[] eastY = {0, 1, 1, 2};
    final static int[] southX = {0, 0, 1, 0};
    final static int[] southY = {0, 1, 1, 2};

    final static List<int[]> specials = Arrays.asList(westX, westY, northX, northY, eastX, eastY, southX, southY);

    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;

    static List<Integer> sums = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //0.입력 값 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 0, 0);          //DFS 로 만들 수 있는 테트로미노
                special(i, j);      //DFS 로 만들 수 없는 테트로미노
            }
        }

        sums.sort(Comparator.reverseOrder());
        System.out.print(sums.get(sums.size() - 1));
    }

    private static void dfs(int nowX, int nowY, int length, int sum) {
        //1. 길이가 4면, sums 에 add
        if (length == 4) {
            sums.add(sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];

            //2. 진행 여부 판단
            if (nextX < 0 || nextX > n - 1 || nextY < 0 || nextY > m - 1) {
                continue;
            }

            //3. 이미 방문했으면
            if (visited[nextX][nextY]) {
                continue;
            }

            //4. 이동
            visited[nextX][nextY] = true;
            dfs(nextX, nextY, length + 1, sum + map[nextX][nextY]); //1방향의 테트로미노 다 돌았으니까 -> 5. 초기화 해줘야 한다.

            //5. 다시 초기화
            visited[nextX][nextY] = false;
        }
    }

    private static void special(int nowX, int nowY) {
        for (int dir = 0; dir < 4; dir++) {
            special(nowX, nowY, dir);
        }
    }

    private static void special(int nowX, int nowY, int dir) {
        int[] specialXs = specials.get(dir * 2);
        int[] specialYs = specials.get(dir * 2 + 1);

        int[] sortSpecialXs = specialXs.
        Arrays.sort(specialXs.clone());
        Arrays.sort(specialYs.clone());

        //1. 범위에 안맞을 경우, return
        if (nowX + specialXs[0] < 0 || nowX + specialXs[3] > n - 1 || nowY + specialYs[0] < 0 || nowY + specialYs[3] > m - 1)
            return;

        int sum = 0;

    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
