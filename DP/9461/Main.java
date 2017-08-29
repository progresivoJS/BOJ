import java.util.*;
import java.io.*;
public class Main
{
    private static long[] cache;

    public static void solve(int test)
    {
        cache = new long[101];
        Arrays.fill(cache, -1);

        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            System.out.println(padovan(n));
        }
    }

    private static long padovan(int n)
    {
        if (n <= 3)
            return 1;
        else if (n == 4)
            return 2;
        else if (n == 5)
            return 2;

        if (cache[n] != -1)
            return cache[n];

        return cache[n] = padovan(n-1) + padovan(n-5);
    }

    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        solve(test);
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
                }
                catch (IOException  e)
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
