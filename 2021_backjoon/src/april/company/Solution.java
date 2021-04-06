package april.company;

class Solution {
    //서, 남, 동, 북
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};
    private static int[][] map;
    private static boolean[][] visited;

    public static int[] solution(int rows, int columns, int[][] queries) {
        initMap(rows, columns);
        int min = Integer.MAX_VALUE;



        for (int[] query : queries) {
            int nowX = query[0];
            int nowY = query[1];
            int dir = 0; //움직이는 방향
            rotate(nowX, nowY, queries[0], dir, min);
        }


        int[] answer = {};
        return answer;
    }

    private static void rotate(int nowX, int nowY, int[] query, int dir, int min) {
        if (map[nowX][nowY] == 0) {
            return; // TODO. 조건 다시 생각하기 (맨 처음 시작한 곳은 0으로 둔다.)
        }

        int nowValue = map[nowX][nowY];

        //1. 움직이는 방향을 정한다.
        if (nowX == query[0]) {
            dir = 0;
        } else if (nowY == query[4]) {
            dir = 1;
        } else if (nowX == query[3]) {
            dir = 2;
        } else if (nowY == query[1]) {
            dir = 3;
        }

        //2. 움직인다.
        int nextX = nowX + dx[dir];
        int nextY = nowY + dy[dir];
        //3. 그 좌표의 값을 map 에서 꺼낸다.(nextValue)
        int nextValue = map[nextX][nextY];

        //4. 그 좌표에 지금 값(nowValue) 를 넣는다.
        map[nextX][nextY] = nowValue;

        //5. min 값을 비교한다.
        if (min >= nowValue) {
            min = nowValue;
        }

        rotate(nextX, nextY, query, dir, min);
    }

    private static void initMap(int rows, int columns) {
        map = new int[rows][columns];
        visited =new boolean[rows][columns];
        int mapValue = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = ++mapValue;
            }
        }
    }

    public static void main(String[] args) {
        int[][] queries = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        int[] answer = solution(6, 6, queries);
        System.out.println(answer);
    }
}