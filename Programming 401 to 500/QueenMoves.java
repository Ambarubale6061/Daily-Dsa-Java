// 496_QueenMoves.java
public class QueenMoves {
    public static int minQueenMoves(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) return 0;
        if (x1 == x2 || y1 == y2 || Math.abs(x1 - x2) == Math.abs(y1 - y2)) return 1;
        return 2;
    }
}