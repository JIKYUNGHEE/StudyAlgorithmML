package jun.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//아기상어
public class Main16236_yeonhee {

    static final int BABYSHARK_INIT_SIZE = 2;

    static int N;
    static int[][] map;

    static BabyShark babyShark;

    public static void main(String[] args) throws Exception {
        readInput();

        int time = 0;
        Node targetNode = null;

        while ((targetNode = babyShark.findPrey()) != null) {
            babyShark.moveTo(targetNode);
            time += targetNode.distance;

            babyShark.eat();
            map[targetNode.x][targetNode.y] = 0;

//			 System.out.println("time : " + time + " (" + babyShark.x + ", " + babyShark.y + ")" + babyShark.size);
//			 for(int i=0; i<N; i++) {
//					for(int j=0; j<N; j++) {
//						System.out.print(((i == babyShark.x && j == babyShark.y)? 9 : map[i][j]) + " ");
//					}
//				System.out.println();
//			}
        }

        println(time);
    }

    private static int[] moveX = {-1, 0, 0, 1};
    private static int[] moveY = {0, -1, 1, 0};

    static class BabyShark {
        int x, y;
        int size = BABYSHARK_INIT_SIZE;

        int unDigestedFishCount = 0;        // 먹은 물고기 카운트 : 크기 커지면 0으로 reset

        BabyShark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 먹이 search
        Node findPrey() {
            boolean[][] visited = new boolean[N][N];
            Queue<Node> q = new LinkedList<Node>();

            q.add(new Node(this.x, this.y, 0));
            visited[this.x][this.y] = true;

            while (!q.isEmpty()) {
                Node current = q.poll();

                for (int i = 0; i < moveX.length; i++) {
                    try {
                        Node next = new Node(current.x + moveX[i], current.y + moveY[i], current.distance + 1);

                        if (!visited[next.x][next.y]) {

                            if (map[next.x][next.y] == 0 || map[next.x][next.y] == this.size) {
                                q.add(next);
                                visited[next.x][next.y] = true;

                            } else if (map[next.x][next.y] < this.size) {
                                return next;

                            } else if (map[next.x][next.y] > this.size) {
                                visited[next.x][next.y] = true;
                                continue;
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
            return null;
        }

        // 이동
        void moveTo(Node targetNode) {
            this.x = targetNode.x;
            this.y = targetNode.y;
        }

        void eat() {
            // 성장
            if (++unDigestedFishCount >= this.size) {
                this.size += 1;
                unDigestedFishCount = 0;
            }
        }
    }

    static class Node {
        int x, y;
        int distance;

        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static void readInput() throws Exception {
        N = toInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int s = Integer.parseInt(st.nextToken());

                if (s == 9) babyShark = new BabyShark(i, j);
                else map[i][j] = s;
            }
        }

        closeReader();
    }


    /////// Comm Function ///////
    protected static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    protected static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int toInt(String target) throws Exception {
        return Integer.parseInt(target);
    }

    public static int[] toIntArray(String target, int M) throws Exception {
        int[] result = new int[M];

        StringTokenizer st = new StringTokenizer(target);
        for (int j = 0; j < M; j++) {
            result[j] = Integer.parseInt(st.nextToken());
        }

        return result;
    }

    public static int[][] readToIntArray(int N, int M) throws Exception {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; i++) {
            result[i] = toIntArray(br.readLine(), M);
        }
        return result;
    }

    public static void closeReader() throws Exception {
        if (br != null) {
            br.close();
        }
    }

    public static void println(Object message) throws Exception {
        bw.write(String.valueOf(message));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
