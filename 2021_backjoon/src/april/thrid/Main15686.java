package april.thrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 치킨 배달
 * <p>
 * 5 3
 * 0 0 1 0 0
 * 0 0 2 0 1
 * 0 1 2 0 0
 * 0 0 1 0 0
 * 0 0 0 0 2
 */
public class Main15686 {

    public static int N;
    public static int M;
    public static int[][] map;
    public static List<int[][]> candidateMapList = new ArrayList<>();
    public static List<Point> storeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        N = Integer.parseInt(line1[0]);
        M = Integer.parseInt(line1[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] lineMap = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(lineMap[j]);
                if (map[i][j] == 2) {
                    storeList.add(new Point(i, j));
                }
            }
        }

        logMap(map);

        createCandidateMapList();
    }

    private static void createCandidateMapList() {
        int storeNum = 0;

        //치킨집 개수를 구한다.
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 2) {
                    storeNum++;
                }
            }
        }

        //치킨집 개수가 M이면, 경우의 수는 1개
        if (storeNum == M) {
            candidateMapList.add(map);
            candidateMapList.forEach(candidate -> logMap(candidate));
            return;
        }

        //치킨집 개수가 M이 아니면, 경우의 수를 구한다.
        int storeIndex = 0;

        while (true) {
            if (storeIndex == storeList.size()) {
                break;
            }

            int[][] copyMap = createCopyMap();
            int pickNum = 0;
            Point startStore = storeList.get(storeIndex);

            for (int i = startStore.x; i < N; i++) {
                for (int j = startStore.y; j < N; j++) {
                    if (map[i][j] == 2) {
                        copyMap[i][j] = 2;
                        pickNum++;
                    }

                    if (pickNum == M) {
                        candidateMapList.add(copyMap);
                        copyMap[i][j] = 0;
                        pickNum--;
                    }
                }
            }

            storeIndex++;
        }

        candidateMapList.forEach(candidate -> logMap(candidate));
    }

    private static int[][] createCopyMap() {
        int[][] copyMap = map.clone();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }
        return copyMap;
    }

    private static void logMap(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + ", ");
            }
            System.out.println();
        }
        System.out.println();
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
