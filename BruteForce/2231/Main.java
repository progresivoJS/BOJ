import java.util.*;
import java.io.*;

/**
 * problem 2231
 * 분해합
 * https://www.acmicpc.net/problem/2231
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    public static void solve(int n)
    {
        int length = String.valueOf(n).length();
        for (int i = n - length * 9; i < n; i++)
            if (decompositionSum(i) == n)
            {
                System.out.println(i);
                return;
            }

        System.out.println(0);
    }

    private static int decompositionSum(int i)
    {
        int result = i;
        while (i > 0)
        {
            result += i % 10;
            i /= 10;
        }
        return result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();

        solve(n);
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
