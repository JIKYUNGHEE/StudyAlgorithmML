package jun.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 아기상어
 */
public class Main16236 {

    private static int N;
    private static int[][] space;
    private static BabyShark babyShark;

    private static List<Node> candidateFishList = new ArrayList<>();
    private static Node candidateFish;
    private static int eatFishNum = 0;

    private final static int FLAG_BABY_SHARK = 9;

    //상, 하, 좌, 우
    private final static int[] dx = {-1, 1, 0, 0};
    private final static int[] dy = {0, 0, -1, 1};

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
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        input();

        System.out.print(sayMomShark());
    }

    private static int sayMomShark() {
        int time = 0;

        while (true) {
            if (!bfs(babyShark.x, babyShark.y)) {
                break;
            }

            eatFish();

            grow();

            time += candidateFish.depth;
        }

        return time;
    }

    private static void grow() {
        if (eatFishNum == babyShark.size) {
            babyShark.size++;
            eatFishNum = 0;
        }
    }

    private static void eatFish() {
        // 여러개의 candidate 중에서 고른다.
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Node fish : candidateFishList) {
            if (fish.x < minX) {
                candidateFish = fish;
                if (fish.y < minY) {
                    candidateFish = fish;
                }
            }
        }

        babyShark.x = candidateFish.x;
        babyShark.y = candidateFish.y;
        space[babyShark.x][babyShark.y] = 0;
        eatFishNum++;
    }

    private static boolean bfs(int x, int y) {
        boolean isFindCandidateFish = false;
        boolean[][] visited = new boolean[N][N];
        Queue<Node> queue = new LinkedList();
        visited[0][0] = true;
        queue.offer(new Node(x, y, 0)); //최초 노드 삽입

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int size = space[currentNode.x][currentNode.y];
            if (size > 0 && size < babyShark.size) {
                candidateFishList.add(currentNode);
                isFindCandidateFish = true;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > N - 1) continue;
                if (space[nextX][nextY] > babyShark.size) continue;
                if (visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;
                queue.add(new Node(nextX, nextY, currentNode.depth + 1));
            }
        }

        return isFindCandidateFish;
    }

    private static void input() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int index = 0;
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(line[index]);
                space[i][j] = num;

                if (num == FLAG_BABY_SHARK) {    //상어위치 표시
                    babyShark = new BabyShark(i, j);
                }
                index++;
            }
        }
    }
}