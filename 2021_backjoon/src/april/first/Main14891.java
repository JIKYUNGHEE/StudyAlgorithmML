package april.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 14891 톱니바퀴
 * <p>
 * [input]
 * 10101111
 * 01111101
 * 11001110
 * 00000010
 * 2
 * 3 -1
 * 1 1
 */
public class Main14891 {
    private static final int LEFT = -1;
    private static final int RIGHT = 1;

    private static Map<Integer, String> gearsMap = new HashMap<>();
    private static Queue<String> rotateQueue = new LinkedList<>();
    private static boolean[] visited = new boolean[4];
    private static int[] moveDirs = new int[4];

    public static void main(String[] args) throws IOException {
        ////////////////////// [input] //////////////////////
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            gearsMap.put(i, line);
        }

        int rotateCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotateCnt; i++) {
            String info = br.readLine();
            rotateQueue.add(info);
        }
        /////////////////////////////////////////////////////

        while (!rotateQueue.isEmpty()) {
            String info = rotateQueue.poll();
            int rotateNumber = Character.getNumericValue(info.charAt(0)) - 1;
            int rotateDir = Character.getNumericValue(info.charAt(2));

            //방향을 설정한다.
            configureMoveDir(rotateNumber, rotateDir);

            //배열을 옮긴다.(굴린다)
            int index = 0;
            for (int moveDir : moveDirs) {
                moveGear(index, moveDir);
                index++;
            }

            moveDirs = new int[4];
            visited = new boolean[4];
        }

//        gearsMap.forEach((key, value) -> System.out.println("key: " + key + ", value: " + value));
        System.out.print(answer());
    }

    private static int answer() {
        final int[] answer = {0};
        gearsMap.forEach((key, value) -> {
            int num0 = Character.getNumericValue(value.charAt(0));
            answer[0] += (num0 == 0) ? 0 : Math.pow(2, key);
        });
        return answer[0];
    }

    private static void configureMoveDir(int rotateNumber, int rotateDir) {
        if (visited[rotateNumber]) { //이미 방향 세팅이 되어 있으면,
            return;
        }

        String gear = gearsMap.get(rotateNumber);
        moveDirs[rotateNumber] = rotateDir;
        visited[rotateNumber] = true;

        int number2 = Character.getNumericValue(gear.charAt(2));
        int number6 = Character.getNumericValue(gear.charAt(6));
        int nextRotateDir = (rotateDir == LEFT) ? RIGHT : LEFT;

        if (rotateNumber < 3) {  //다음번호를 뽑는다.
            String nextGear = gearsMap.get(rotateNumber + 1);
            int nextNumber6 = Character.getNumericValue(nextGear.charAt(6));
            //다음 번호의 방향을 세팅한다.
            nextRotateDir = (number2 == nextNumber6) ? 0 : nextRotateDir;
            configureMoveDir(rotateNumber + 1, nextRotateDir);
        }

        if (rotateNumber > 0) { //이전번호를 뽑는다.
            String preGear = gearsMap.get(rotateNumber - 1);
            int preNumber2 = Character.getNumericValue(preGear.charAt(2));
            nextRotateDir = (number6 == preNumber2) ? 0 : nextRotateDir;
            //이전 번호의 방향을 세팅한다.
            configureMoveDir(rotateNumber - 1, nextRotateDir);
        }
    }

    private static void moveGear(int rotateNumber, int rotateDir) {
        String gear = gearsMap.get(rotateNumber);
        StringBuilder toGearBuilder = new StringBuilder();
        if (rotateDir == LEFT) {
            toGearBuilder.append(gear, 1, 8);
            toGearBuilder.append(gear.charAt(0));
        }

        if (rotateDir == RIGHT) {
            toGearBuilder.append(gear.charAt(7));
            toGearBuilder.append(gear, 0, 7);
        }

        if (rotateDir == 0) {
            toGearBuilder = new StringBuilder(gear);
        }

        gearsMap.put(rotateNumber, toGearBuilder.toString());
    }
}
