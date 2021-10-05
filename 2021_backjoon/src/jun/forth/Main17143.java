package jun.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 낚시왕
 */
public class Main17143 {
    private static int R;   //전체 행
    private static int C;   //전체 열
    private static int M;   //상어의 개수

    private static int[][] map;
    private static List<Shark> sharksList = new ArrayList<>();

    enum Direction {
        NONE, TOP, BOTTOM, LEFT, RIGHT;

        static Direction getDirection(int d) {
            for (Direction value : Direction.values()) {
                if (value.ordinal() == d) {
                    return value;
                }
            }
            return NONE;
        }

        Direction oppositeSide(Direction dir) {
            switch (dir) {
                case TOP:
                    return BOTTOM;
                case BOTTOM:
                    return TOP;
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
                default:
                    return NONE;
            }
        }
    }

    static class Position {
        int r;
        int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Position position = (Position) o;
            return r == position.r && c == position.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static class Shark {
        int r;  //행
        int c;  //열
        int s;  //스피드
        Direction d;  //방향
        int z;  //사이즈

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = Direction.getDirection(d);
            this.z = z;
        }

        public void move() {
            int moveBlocks = 0;   //갈 수 있는 블록의 개수
            int remainBlocks = s;

            while (true) {
                map[r][c]--;

                switch (d) {
                    case TOP:
                        moveBlocks = r - 1;
                        break;
                    case BOTTOM:
                        moveBlocks = R - r;
                        break;
                    case LEFT:
                        moveBlocks = c - 1;
                        break;
                    case RIGHT:
                        moveBlocks = C - c;
                        break;
                }

                if (moveBlocks >= remainBlocks) {
                    moveBlocks = remainBlocks;
                    remainBlocks = 0;
                }

                justMove(moveBlocks);
                map[r][c]++;

                if (remainBlocks == 0) {
                    break;
                }

                d = d.oppositeSide(d);
                remainBlocks -= moveBlocks;
            }
        }

        private void justMove(int blocks) {
            if (blocks == 0) {
                return;
            }

            switch (d) {
                case TOP:
                    r -= blocks;
                    break;
                case BOTTOM:
                    r += blocks;
                    break;
                case LEFT:
                    c -= blocks;
                    break;
                case RIGHT:
                    c += blocks;
                    break;
            }
        }
    }

    static class KingOfFisher {
        int c;  //열
        List<Shark> catchSharkList;

        KingOfFisher() {
            c = 0;
            catchSharkList = new ArrayList<>();
        }

        public void move() {
            c = c + 1;
        }

        public void fish() {
            Shark theShark = findNearestShark(); //가장 가까운 상어를 찾는다.
            catchShark(theShark);       //그 상어를 잡는다.
        }

        private Shark findNearestShark() {
            Shark theShark = null;
            for (Shark shark : sharksList) {
                if (shark.c == c) {
                    if (theShark != null) {
                        theShark = (shark.r < theShark.r) ? shark : theShark;
                    } else {
                        theShark = shark;
                    }
                }
            }
            return theShark;
        }


        private void catchShark(Shark theShark) {
            if (theShark != null) {
                catchSharkList.add(theShark);
                map[theShark.r][theShark.c]--;
                sharksList.remove(theShark);
            }
        }

        public int getTotalSizeOfCaughtSharks() {
            int totalSizeOfCaughtSharks = 0;
            if (catchSharkList != null) {
                for (Shark shark : catchSharkList) {
                    totalSizeOfCaughtSharks += shark.z;
                }
            }
            return totalSizeOfCaughtSharks;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        KingOfFisher kingOfFisher = new KingOfFisher();
        for (int i = 0; i < C; i++) {
            kingOfFisher.move();            //낚시왕이 오른쪽으로 한칸 이동한다.
            kingOfFisher.fish();             //낚시왕이 자신과 제일 가까운 상어를 잡는다.

            for (Shark shark : sharksList) {
                shark.move();             //상어가 이동한다.
            }

            //상어가 상어를 잡아먹는다.
            eatSharks();
        }

        System.out.println(kingOfFisher.getTotalSizeOfCaughtSharks());
    }

    private static void putTheBiggestSharks() {

    }

    private static void eatSharks() {
        for (int x = 0; x < map.length; x++) {         //한 칸의 상어가 2마리 이상일 때
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] > 1) {
                    eatShark(x, y);
                }
            }
        }
    }

    private static void eatShark(int x, int y) {
        List<Shark> sameDirSharkList = new LinkedList<>();
        Shark biggestShark = null;
        for (int i = 0; i < sharksList.size(); i++) {
            Shark shark = sharksList.get(i);
            if(shark.r == x && shark.c == y) {
                if(biggestShark != null) {
                    biggestShark = (shark.z > biggestShark.z) ? shark : biggestShark;
                } else {
                    biggestShark = shark;
                }
                sameDirSharkList.add(shark);
            }
        }

        for (Shark shark : sameDirSharkList) {
            sharksList.remove(shark);
        }

        map[x][y] = 1;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행
        C = Integer.parseInt(st.nextToken()); // 열
        M = Integer.parseInt(st.nextToken()); // 상어의 개수
        map = new int[R + 1][C + 1];

        // shark 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharksList.add(new Shark(r, c, s, d, z));
            map[r][c] = 1;
        }
    }
}
