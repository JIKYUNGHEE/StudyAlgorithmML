package may.forth;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main16235_hojoon {
    static int N;
    static int M;
    static int K;
    static int[][] map;
    static int[][] fuel;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    static LinkedList<Tree> trees;
    static Queue<Tree> dead;
    static int year;

    static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N+1][N+1];
        fuel = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                map[i][j] = 5;
                fuel[i][j] = sc.nextInt();
            }
        }

        trees = new LinkedList<Tree>();
        dead = new LinkedList<Tree>();
        for(int i=0; i<M; i++) {
            trees.add(new Tree(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        year = 0;

        System.out.println(trees.size());
    }

    public void solution() {
        while(year<=K) {
            //봄
            Iterator<Tree> itr = trees.iterator();
            while(itr.hasNext()) {
                Tree tree = itr.next();
                int x = tree.x;
                int y = tree.y;
                int age = tree.age;
                if(map[x][y] - age <0) {
                    dead.add(tree);
                    itr.remove();
                } else {
                    tree.age += 1;
                    map[x][y] -= age;
                }
            }
            //여름
            while(!dead.isEmpty()) {
                Tree tree = dead.poll();
                map[tree.x][tree.y] += tree.age/2;
            }
            //가을
            LinkedList<Tree> babyTrees = new LinkedList<Tree>();
            for(Tree tree : trees) {
                int x = tree.x;
                int y = tree.y;
                if(tree.age % 5 == 0) {
                    for(int i=0; i<8; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                        babyTrees.add(new Tree(nx, ny, 1));
                    }
                }
            }
            trees.addAll(0, babyTrees);
            //겨울
            year++;
        }
    }
}
