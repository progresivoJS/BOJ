import java.util.*;
import java.io.*;

public class Main
{
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

    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        for (int i = m; i <= n; i++)
            if (isPrime(i))
                System.out.println(i);
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
