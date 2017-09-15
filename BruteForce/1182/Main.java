import java.util.*;
import java.io.*;

/**
 * problem 1182
 * 부분집합의 합
 * https://www.acmicpc.net/problem/1182
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    private static int[] seq;
    private static int s, n;
    public static void solve(int[] seq, int s)
    {
        Main.seq = seq;
        Main.s = s;
        Main.n = seq.length;

        int result = 0;
        for (int i = 0; i < n; i++)
            result += count(i, seq[i]);

        System.out.println(result);
    }

    /**
     * 지금까지의 합이 sum일 때, (here, last] 에서 합이 S가 되는 부분집합의 수.
     */
    private static int count(int here, int sum)
    {
        int result = (sum == s) ? 1 : 0;
        for (int next = here + 1; next < n; next++)
            result += count(next, sum + seq[next]);

        return result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int s = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        solve(seq, s);
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
