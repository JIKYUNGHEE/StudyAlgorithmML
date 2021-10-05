package jun.forth;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17143_yeounhee extends MainUtils {

    static int R, C;
    static int M;

    static Shark[][] map;

    public static void main(String[] args) throws Exception {
        readInput();

        while (Person.move() <= C) {        // 오른쪽으로 한 칸 이동

            // 낚시왕이 낚시를 한다.
            Person.doFishing();

            // 상어가 이동한다.
            Shark.moveAll();
        }

        println(Person.totalAmount);
    }

    public static void readInput() throws Exception {
        int[] line1 = toIntArray(br.readLine(), 3);
        R = line1[0];
        C = line1[1];
        M = line1[2];

        map = new Shark[R + 1][C + 1];

        for (int i = 1; i <= M; i++) {
            int[] sharkInfo = toIntArray(br.readLine(), 5);
            map[sharkInfo[0]][sharkInfo[1]] = new Shark(sharkInfo);
        }

        br.close();
    }

    static class Person {
        static int position = 0;
        static int totalAmount = 0;

        static int move() {
            return ++position;
        }

        static void doFishing() {
            for (int i = 1; i <= R; i++) {
                if (map[i][position] != null) {
                    totalAmount += map[i][position].size;
                    map[i][position] = null;
                    return;
                }
            }
        }
    }


    static final int[][] DIRECTIONS = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Shark {
        int r, c;
        int size;
        int speed;
        int directX, directY;

        Shark(int[] info) {
            r = info[0];
            c = info[1];
            speed = info[2];
            directX = DIRECTIONS[info[3]][0];
            directY = DIRECTIONS[info[3]][1];
            size = info[4];
        }

        static void moveAll() {
            Object[] sharkArr = Arrays.stream(map)
                    .flatMap(Arrays::stream)
                    .filter(shark -> shark != null)
                    .toArray();

            // map 초기화
            map = new Shark[R + 1][C + 1];

            for (Object obj : sharkArr) {
                Shark shark = (Shark) obj;
                shark.move();

                // 이동할 위치에 다른 상어 있는지 확인
                Shark competitor = map[shark.r][shark.c];
                if (competitor == null                     // 경쟁자 없거나
                        || shark.size > competitor.size) {     // 경쟁자보다 큰 경우
                    map[shark.r][shark.c] = shark;

                } else {                                   // 경쟁자보다 작은 경우
                    // 잡아먹힘..
                }
            }
        }

        void move() {
            for (int i = 0; i < this.speed; i++) {
                if (this.directX == 1 && this.r == R
                        || this.directX == -1 && this.r == 1
                        || this.directY == 1 && this.c == C
                        || this.directY == -1 && this.c == 1) {

                    turn();
                }
                this.r += this.directX;
                this.c += this.directY;
            }
        }

        void turn() {
            this.directX *= -1;
            this.directY *= -1;
        }
    }
}


////////
class MainUtils {
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