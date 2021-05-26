package may.forth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16235_yeonhee {
    public static void main(String[] args) throws Exception {
        Main16235_yeonhee main = new Main16235_yeonhee();

        main.readInput();
        println(main.execute());
    }

    static int N, M, K;		// N : 땅 크기, M : 나무 수,  K : 반복 수
    static int[][] A;		// 각 칸에 추가되는 양분의 양

    static int[][] map;
    static List<Tree> trees = new LinkedList<Tree>();

    static final int INIT_FOOD_AMOUNT = 5;

    public int execute() {

        for(int n=0; n<K; n++) {
            // 봄 : 나무가 자람
            Tree.grow();

            // 여름 : 죽은 나무가 땅에 흡수됨.
            Tree.absorbed();

            // 가을 : 나무 번식
            Tree.reproduce();

            // 겨울 : 로봇이 양분 추가
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    map[r][c] += A[r][c];
                }
            }
        }

        return trees.size();
    }

    static class Tree {
        int x, y;
        int age;
        boolean isAlive = true;

        Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }

        private static Queue<Tree> deadList = new LinkedList<Tree>();
        private static Queue<Tree> reproduceList = new LinkedList<Tree>();

        static void grow() {

            for(int idx=0; idx<trees.size(); idx++) {
                Tree tree = trees.get(idx);

                if(tree.age <= map[tree.x][tree.y]) {
                    map[tree.x][tree.y] -= tree.age;
                    tree.age += 1;
                    if(tree.age % 5 == 0) reproduceList.add(tree);

                } else {
                    trees.remove(tree);
                    deadList.add(tree);
                    idx--;
                }
            }
        }

        static void absorbed() {
            while(!deadList.isEmpty()) {
                Tree tree = deadList.poll();
                map[tree.x][tree.y] += tree.age / 2;
            }
        }

        static void reproduce() {
            while(!reproduceList.isEmpty()) {
                Tree tree = reproduceList.poll();

                for(int a=-1; a<=1; a++) {
                    for(int b=-1; b<=1; b++) {
                        if(a==0 && b==0) continue;

                        try {
                            int t = map[tree.x + a][tree.y + b];
                            trees.add(0, new Tree(tree.x + a, tree.y + b, 1));
                        } catch(IndexOutOfBoundsException e) {}
                    }
                }
            }
        }
    }


    private void readInput() throws Exception {
        int[] line1 = toIntArray(br.readLine(), 3);
        N = line1[0];
        M = line1[1];
        K = line1[2];

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = INIT_FOOD_AMOUNT;
            }
        }

        A = readToIntArray(N, N);

        for(int i=0; i<M; i++) {
            int[] info = toIntArray(br.readLine(), 3);
            int x = info[0]; int y = info[1]; int age = info[2];
            trees.add(new Tree(x-1, y-1, age));
        }

        trees.sort((t1, t2) -> Integer.compare(t1.age, t2.age));

        closeReader();
    }


    protected static BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
    protected static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int toInt(String target) throws Exception {
        return Integer.parseInt(target);
    }
    public static int[] toIntArray(String target, int M) throws Exception {
        int[] result = new int[M];

        StringTokenizer st = new StringTokenizer(target);
        for(int j=0; j<M; j++) {
            result[j] = Integer.parseInt(st.nextToken());
        }

        return result;
    }
    public static int[][] readToIntArray(int N, int M) throws Exception{
        int[][] result = new int[N][M];

        for(int i=0; i<N; i++) {
            result[i] = toIntArray(br.readLine(), M);
        }
        return result;
    }

    public static void closeReader() throws Exception {
        if(br != null) {
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