import java.util.*;

public class GrayCode {
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(grayCode(2)); // [0,1,3,2]
    }
}