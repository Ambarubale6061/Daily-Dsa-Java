// 494_BishopMoves.java
public class BishopMoves {
    public static int minBishopMoves(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) return 0;
        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) return 1;
        if ((x1 + y1) % 2 != (x2 + y2) % 2) return -1;
        return 2;
    }
}