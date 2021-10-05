public class Test {
    static class Pos {
        int x;
        int y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    public static void main(String[] args) {
        Pos pos = new Pos(1,1);
        System.out.println(pos);

        Pos nullPos = null;
        System.out.println(nullPos);
    }
}
