package april.forth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main5373 {
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
        for (String command : commands) {
            String[] commandArr = command.split("");
            rotateCube(commandArr[0], commandArr[1]);
        }
        printCube();    //U 의 상황을 프린트한다.
    }

    private static void rotateCube(String commandLoc, String commandDir) {
        //
        Plane plane = new Plane(commandLoc);
        //plane 을 회전시킨다.
        plane.rotatePlane(commandDir);

        //wings 를 회전시킨다.
        plane.rotateWings(commandDir);

        //옮긴 배열을 cube에 넣는다.
        cube.changePlane(plane, commandLoc);
    }

    private static void printCube() {
        for (String[] arr : cube.arrU) {
            for (String s : arr) {
                System.out.print(s);
            }
            System.out.println();
        }
    }
    public static String[] reverseArray(String[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[array.length - i - 1];
        }
        return result;
    }

    public static String[] getXArray(String[][] arr, int y) {
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i][y];
        }
        return result;
    }

    private static class Cube {
        public static Map<String, String[][]> cubePlanes = new HashMap(6);

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

            updateAllCube();
        }

        public void changePlane(Plane plane, String commandLoc) {
            setPlaneToCube(plane.plane, commandLoc);
            setWingsToCube(plane.wings, commandLoc);

            updateAllCube();
        }

        private void setPlaneToCube(String[][] plane, String commandLoc) {
            switch (commandLoc) {
                case U:
                    arrU = plane;
                    break;
                case D:
                    arrD = plane;
                    break;
                case F:
                    arrF = plane;
                    break;
                case B:
                    arrB = plane;
                    break;
                case L:
                    arrL = plane;
                    break;
                case R:
                    arrR = plane;
                    break;
            }
        }

        private void updateAllCube() {
            cubePlanes.put(U, arrU);
            cubePlanes.put(D, arrD);
            cubePlanes.put(F, arrF);
            cubePlanes.put(B, arrB);
            cubePlanes.put(L, arrL);
            cubePlanes.put(R, arrR);
        }

        private void setWingsToCube(String[] wings, String commandLoc) {
            String[] upWing = {wings[0], wings[1], wings[2]};
            String[] rightWing = {wings[3], wings[4], wings[5]};
            String[] downWing = {wings[6], wings[7], wings[8]};
            String[] leftWing = {wings[9], wings[10], wings[11]};
            switch (commandLoc) {
                case U:
                    cube.arrB[0] = upWing;
                    cube.arrR[0] = reverseArray(rightWing);
                    cube.arrF[0] = reverseArray(downWing);
                    cube.arrL[0] = reverseArray(leftWing);
                    break;
                case D:
                    cube.arrB[2] = upWing;
                    cube.arrR[2] = reverseArray(rightWing);
                    cube.arrF[2] = reverseArray(downWing);
                    cube.arrL[2] = reverseArray(leftWing);
                    break;
                case F:
                    cube.arrU[2] = upWing;
                    setXArray(cube.arrR, rightWing, 0);
                    cube.arrD[2] = downWing;
                    setXArray(cube.arrL, reverseArray(leftWing), 2);
                    break;
                case B:
                    cube.arrU[0] = upWing;
                    setXArray(cube.arrR, rightWing, 2);
                    cube.arrD[0] = reverseArray(downWing);
                    setXArray(cube.arrL, reverseArray(leftWing), 0);
                    break;
                case L:
                    setXArray(cube.arrU, upWing, 0);
                    setXArray(cube.arrF, rightWing, 0);
                    setXArray(cube.arrD, reverseArray(downWing), 0);
                    setXArray(cube.arrB, reverseArray(downWing), 0);
                    break;
                case R:
                    setXArray(cube.arrU, reverseArray(upWing), 2);
                    setXArray(cube.arrB, rightWing, 2);
                    setXArray(cube.arrD, downWing, 2);
                    setXArray(cube.arrF, reverseArray(leftWing), 2);
                    break;
            }
        }

        private void setXArray(String[][] targetArr, String[] copyArr, int y) {
            for (int i = 0; i < copyArr.length; i++) {
                targetArr[i][y] = copyArr[i];
            }
        }
    }

    private static class Plane {
        String[][] plane = new String[3][3];
        String[] wings = new String[12];

        public Plane(String commandLoc) {
            createPlane(commandLoc);
            createWings(commandLoc);
        }

        private void createWings(String commandLoc) {
            String[] upWing = new String[3];
            String[] rightWing = new String[3];
            String[] downWing = new String[3];
            String[] leftWing = new String[3];
            switch (commandLoc) {
                case U:
                    upWing = cube.arrB[0].clone();
                    rightWing = reverseArray(cube.arrR[0]);
                    downWing = reverseArray(cube.arrF[0]);
                    leftWing = reverseArray(cube.arrL[0]);
                    break;
                case D:
                    upWing = cube.arrB[2].clone();
                    rightWing = reverseArray(cube.arrR[2]);
                    downWing = reverseArray(cube.arrF[2]);
                    leftWing = reverseArray(cube.arrL[2]);
                    break;
                case F:
                    upWing = cube.arrU[2].clone();
                    rightWing = getXArray(cube.arrR, 0);
                    downWing = reverseArray(cube.arrD[2]);
                    leftWing = reverseArray(getXArray(cube.arrL, 2));
                    break;
                case B:
                    upWing = cube.arrU[0].clone();
                    rightWing = getXArray(cube.arrR, 2);
                    downWing = reverseArray(cube.arrD[0]);
                    leftWing = reverseArray(getXArray(cube.arrL, 0));
                    break;
                case L:
                    upWing = getXArray(cube.arrU, 0);
                    rightWing = getXArray(cube.arrF, 0);
                    downWing = reverseArray(getXArray(cube.arrD, 0));
                    leftWing = reverseArray(getXArray(cube.arrB, 0));
                    break;
                case R:
                    upWing = reverseArray(getXArray(cube.arrU, 2));
                    rightWing = getXArray(cube.arrB, 2);
                    downWing = getXArray(cube.arrD, 2);
                    leftWing = reverseArray(getXArray(cube.arrF, 2));
                    break;
            }

            for (int i = 0; i < wings.length; i++) {
                if (i >= 0 && i < 3) {
                    wings[i] = upWing[i];
                }
                if (i >= 3 && i < 6) {
                    wings[i] = rightWing[i - 3];
                }
                if (i >= 6 && i < 9) {
                    wings[i] = downWing[i - 6];
                }
                if (i >= 9 && i < 12) {
                    wings[i] = leftWing[i - 9];
                }
            }
        }

        private void createPlane(String commandLoc) {
            switch (commandLoc) {
                case U:
                    plane = cube.arrU.clone();
                    break;
                case D:
                    plane = cube.arrD.clone();
                    break;
                case F:
                    plane = cube.arrF.clone();
                    break;
                case B:
                    plane = cube.arrB.clone();
                    break;
                case L:
                    plane = cube.arrL.clone();
                    break;
                case R:
                    plane = cube.arrR.clone();
                    break;
            }
        }

        public void rotatePlane(String commandDir) {
            String[][] copyPlane = new String[3][3];
            String[] line0 = plane[0];
            String[] line1 = getXArray(plane, 2);
            String[] line2 = plane[2];
            String[] line3 = getXArray(plane, 0);
            if (commandDir.equals(PLUS)) {
                copyPlane[0] = line1;
                copyPlane[1] = new String[]{line1[1], plane[1][1], line2[1]};
                copyPlane[2] = line2;
            }
            if (commandDir.equals(MINUS)) {
                copyPlane[0] = reverseArray(line3);
                copyPlane[1] = new String[]{line2[1], plane[1][1], line1[1]};
                copyPlane[2] = reverseArray(line0);
            }
            plane = copyPlane;
        }

        public void rotateWings(String commandDir) {
            String[] copyWings = new String[12];
            if (commandDir.equals(PLUS)) {
                for (int i = 0; i < 3; i++) {
                    copyWings[i] = wings[wings.length - 3 + i];
                }
                for (int i = 3; i < wings.length - 3; i++) {
                    copyWings[i] = wings[i - 3];
                }
            }

            if (commandDir.equals(MINUS)) {
                for (int i = 0; i < copyWings.length - 3; i++) {
                    copyWings[i] = wings[i + 3];
                }

                for (int i = 9; i < wings.length; i++) {
                    copyWings[i] = wings[i - 9];
                }
            }
            wings = copyWings;
        }
    }
}
