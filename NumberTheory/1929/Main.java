import java.util.*;
import java.io.*;

public class Main
{
    private static boolean[] isPrime;
    private static int MAX;

    // slower
    public static boolean isPrime(int n)
    {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    // more faster
    public static void eratosthenes()
    {
        isPrime[0] = isPrime[1] = false;

        int sqrt = (int)Math.sqrt(MAX);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j <= MAX; j += i)
                    isPrime[j] = false;
    }

    public static void main(String[] args) throws IOException
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        MAX = n;

        isPrime = new boolean[MAX+1];
        Arrays.fill(isPrime, true);

        eratosthenes();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = m; i <= n; i++)
        {
            if (isPrime[i])
            {
                out.write(String.valueOf(i)+"");
                out.write("\n");
            }
        }

        out.close();
    }

    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;

        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}
