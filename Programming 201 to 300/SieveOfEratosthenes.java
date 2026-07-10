import java.util.Arrays;

public class SieveOfEratosthenes {
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, 2, n + 1, true);
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        boolean[] primes = sieve(30);
        for (int i = 2; i < primes.length; i++)
            if (primes[i])
                System.out.print(i + " ");
    }
}