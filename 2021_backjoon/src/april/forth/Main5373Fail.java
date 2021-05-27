package april.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main5373Fail {
    public static final String U = "U";
    public static final String D = "D";
    public static final String F = "F";
    public static final String B = "B";
    public static final String L = "L";
    public static final String R = "R";

    public static final String PLUS = "+";
    public static final String MINUS = "-";

    public static Cube cube = new Cube();

    public static int n;
    public static Queue<List<String>> inputQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        //[입력 시작]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            List<String> inputs = new ArrayList<>(Integer.parseInt(br.readLine()));
            inputs.add(br.readLine().replaceAll(" ", ""));
            inputQueue.add(inputs);
        }
        //[입력 끝]

        while (!inputQueue.isEmpty()) {
            List<String> commands = inputQueue.poll();
            rotateCubes(commands);
        }
    }

    private static void rotateCubes(List<String> commands) {
        commands.forEach(command -> {
            String[] commandArr = command.split("");
            cube.rotate(commandArr[0], commandArr[1]);
        });

    }

    private static class Cube {
        public static List<String[][]> cubePlanes = new ArrayList<>(6);
        public static String[][] arrU = new String[3][3];
        public static String[][] arrD = new String[3][3];
        public static String[][] arrF = new String[3][3];
        public static String[][] arrB = new String[3][3];
        public static String[][] arrL = new String[3][3];
        public static String[][] arrR = new String[3][3];

        Cube() {
            for (int i = 0; i < 3; i++) {
                Arrays.fill(arrU[i], "w");
                Arrays.fill(arrD[i], "y");
                Arrays.fill(arrF[i], "r");
                Arrays.fill(arrB[i], "o");
                Arrays.fill(arrL[i], "g");
                Arrays.fill(arrR[i], "b");
            }

            cubePlanes.add(arrU);
            cubePlanes.add(arrD);
            cubePlanes.add(arrF);
            cubePlanes.add(arrB);
            cubePlanes.add(arrL);
            cubePlanes.add(arrR);
        }

        public void rotate(String commandLoc, String commandDir) {
            int targetX = 0;
            int targetY = 0;
            switch (commandLoc) {
                case F:
                    targetX = 2;
                    break;
                case B:
                    targetX = 0;
                    break;
                case L:
                    targetY = 0;
                    break;
                case R:
                    targetY = 2;
                    break;
                case U:
                case D:
                default:
                    break;
            }

            if (targetX == 0 && targetY == 0) {
                //Up 평면은 회전하지 않는다.
            } else {
                //회전 순서: U -> D -> F -> B -> L
                for (String[][] cubePlane : cubePlanes) {
                    if (targetX != 0) {
                        String[] temp = cubePlane[targetX];

                    }
                    if (targetY != 0) {
                        String[] temp = new String[3];
                        int i = 0;
                        for (String[] item : cubePlane) {
                            temp[i] = item[targetY];
                            i++;
                        }
                    }
                }
            }
        }
    }
}