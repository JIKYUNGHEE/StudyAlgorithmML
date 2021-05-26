package may.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main16235 {
    private static int N;
    private static int M;
    private static int K;

    private static int[][] r;           //1*1 양분 배열
    private static List<Area> areaList = new ArrayList<>(); //1*1 땅-나무 리스트

    static class Area {
        int x;
        int y;
        int energy; //양분
        List<Tree> trees;  //나무들

        public Area(int x, int y, int energy) {
            this.x = x;
            this.y = y;
            this.energy = energy;
            this.trees = new ArrayList<>();
        }

        public boolean isSameArea(int x, int y) {
            return (this.x == x && this.y == y);
        }
    }

    static class Tree implements Comparable<Tree> {
        int age;
        private boolean alive;

        public Tree(int age) {
            this.age = age;
            this.alive = true;
        }

        public void die() {
            this.alive = false;
        }

        @Override
        public int compareTo(Tree tree) {
            if (this.age < tree.age) {
                return -1;
            } else if (this.age > tree.age) {
                return 1;
            }
            return 0;
        }

        public void grow() {
            this.age += 1;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        int year = K;
        System.out.print(afterYear(year));
    }

    private static int afterYear(int year) {
        for (int i = 0; i < year; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        int treeNum = 0;
        for (Area area : areaList) {
            for (Tree tree : area.trees) {
                if (tree.alive) {
                    treeNum++;
                }
            }
        }

        return treeNum;
    }

    private static void spring() {
        for (Area area : areaList) {
            Collections.sort(area.trees);       //가장 작은 나이부터 정렬
            for (Tree tree : area.trees) {
                if (tree.age > area.energy) {   //땅에 양분이 모자라면
                    tree.die();
                } else {
                    area.energy -= tree.age;  //나이만큼 양분을 먹고
                    tree.grow();
                }
            }
        }
    }

    private static void summer() {
        for (Area area : areaList) {
            List<Integer> removeIndexList = new ArrayList<>();
            int index = 0;
            for (Tree tree : area.trees) {  //나무삭제
                if (!tree.alive) {   //나무가 죽었으면
                    area.energy += tree.age / 2;
                }
                removeIndexList.add(index);
                index++;
            }
            for (Integer i : removeIndexList) {
                area.trees.remove(i);
            }
        }
    }

    private static void fall() {
        for (Area area : areaList) {
            for (int i = 0; i < area.trees.size(); i++) {
                Tree tree = area.trees.get(i);
                if (tree.alive && tree.age % 5 == 0) {
                    bear(area.x, area.y);   //인접 8칸에 newTree
                }
            }
        }
    }

    private static void bear(int x, int y) {
        int[] dx = {-1, 0, 1};
        int[] dy = {-1, 0, 1};

        for (int i = 0; i < dx.length; i++) {
            for (int j = 0; j < dy.length; j++) {
                if (dx[i] == 0 && dx[j] == 0) {
                    continue;
                }

                int nearX = x + dx[i];
                int nearY = y + dy[i];

                if (nearX <= 0 || nearX >= N + 1 || nearY <= 0 || nearY >= N + 1) {
                    continue;
                }

                boolean isSameArea = false;
                for (Area area : areaList) {
                    if (area.isSameArea(nearX, nearY)) { //이미 리스트에 있을 경우
                        area.trees.add(new Tree(1));
                        isSameArea = true;
                    }
                }
                if (!isSameArea) {
                    Area area = new Area(nearX, nearY, 0);
                    area.trees.add(new Tree(1));
                }
            }
        }
    }

    private static void winter() {
        for (int[] xs : r) {
            for (int y : xs) {
                for (Area area : areaList) {
                    area.energy += y;
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        N = Integer.parseInt(line1[0]);
        M = Integer.parseInt(line1[1]);
        K = Integer.parseInt(line1[2]);

        r = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            String[] line = br.readLine().split(" ");
            int index = 0;
            for (int j = 1; j < N + 1; j++) {
                int energy = Integer.parseInt(line[index]);
                r[i][j] = energy;
                index++;
            }
        }

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);

            Area area = new Area(x, y, 5);
            area.trees.add(new Tree(Integer.parseInt(line[2])));
            areaList.add(area);
        }
    }
}