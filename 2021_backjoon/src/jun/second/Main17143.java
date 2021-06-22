package jun.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 낚시왕
 */
public class Main17143 {
    private static int R;
    private static int C;
    private static int M;   //상어의 개수

    private static int[][] map;
    private static List<Shark> sharksInTheMap = new ArrayList<>();

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

    static class Shark {
        int r;
        int c;
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
            int remainBlocks = -1;   //더 가야하는 블록 개수(속도가 남은 개수보다 빨라서, 더 가야하는 개수)

            while (remainBlocks == 0) {
                map[r][c]--;

                switch (d) {
                    case TOP:
                    case BOTTOM:
                        if (s > R - r) {
                            remainBlocks = (s - (R - r));
                        } else {
                            remainBlocks = 0;
                        }
                        break;
                    case LEFT:
                    case RIGHT:
                        if (s > C - c) {
                            remainBlocks = (s - (C - c));
                        } else {
                            remainBlocks = 0;
                        }
                        break;
                }

                justMove(s - remainBlocks);
                d = d.oppositeSide(d);
                justMove(remainBlocks); //while 돌려야

                map[r][c]++;
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
        int c;
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
            for (Shark shark : sharksInTheMap) {
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
                sharksInTheMap.remove(theShark);
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

            for (Shark shark : sharksInTheMap) {
                shark.move();             //상어가 이동한다.
            }

            //상어가 상어를 잡아먹는다.
            eatSharks();
        }

        System.out.println(kingOfFisher.getTotalSizeOfCaughtSharks());
    }

    private static void eatSharks() {
        //한 칸의 상어가 2마리 이상일 때
        //크기가 큰 상어가 남는다.
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] > 1) {
                    map[x][y] = 1;
                }
            }
        }
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

            sharksInTheMap.add(new Shark(r, c, s, d, z));
            map[r][c] = 1;
        }
    }
}
