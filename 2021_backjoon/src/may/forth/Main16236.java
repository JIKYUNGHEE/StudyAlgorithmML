package may.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 아기상어
 */
public class Main16236 {

    private static int N;
    private static int M;
    private static int[][] space;
    private static BabyShark babyShark;

    private static Queue<Node> fishQueue = new LinkedList<>();
    private static boolean[][] visited;

    private final static int FLAG_OF_BABY_SHARK = 9;

    //위, 왼, 아, 오
    private final static int[] dx = {-1, 0, 1, 0};
    private final static int[] dy = {0, -1, 0, 1};

    static class Node {
        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        int x;
        int y;
        int depth;
    }

    static class BabyShark {
        int x;
        int y;
        int size;

        BabyShark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
        }

        void grow() {
            size++;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();

        System.out.print(sayMomShark());
    }

    private static int sayMomShark() {
        int time = 0;

        while (true) {
            if (!findEatenFish()) {
                break;
            }

            //자기보다 작은 물고기 -> canEat
            eatFish();    //물고기가 있는 칸으로 이동, 원래 칸은 빈 칸이 된다.
        }

        return time;
    }

    private static boolean findEatenFish() {
        boolean isFindEatenFish = false;

        visited[0][0] = true;
        //BFS - 먹을 수 있는 물고기가 있는 지 확인
        //자기보다 큰 물고기가 가고 싶은 위치에 있을 경우 -> false;
        bfs(0, 0);  // 계속 Queue 에 넣는다.

        return isFindEatenFish;
    }

    private static void dfs() {

    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new LinkedList();
        //최초 노드 삽입
        queue.offer(new Node(x, y, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            visited[node.x][node.y] = true;

            //상하좌우 이동여부를 판단해본다.

        }

    }

    private static void eatFish() {
        //DFS - 먹을 물고기 찾음

        //먹을 물고기가 많으면,
        // 1. 가장 가까운 물고기를 먹는다.(지나가는 칸의 최소값)
        // 2. 가장 왼쪽에 있는 물고기를 먹는다.
    }

    private static void input() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int index = 0;
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(line[index]);
                space[i][j] = num;

                if (num == FLAG_OF_BABY_SHARK) {    //상어위치 표시
                    babyShark = new BabyShark(i, j);
                }
                index++;
            }
        }
    }
}


