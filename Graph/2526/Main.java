import java.util.*;
import java.io.*;

/**
 * problem 2526
 * 싸이클
 * https://www.acmicpc.net/problem/2526
 * written by progresivoJS on 2017.09.27
 */
public class Main
{
    private static boolean[] marked;
    private static boolean[] finished;
    private static int[] edgeTo;
    private static int count;

    public static void solve(int n, int p)
    {
        marked = new boolean[1001];
        finished = new boolean[1001];
        edgeTo = new int[p + 1];
        count = 0;

        dfs(n, n, p);
        System.out.println(count);
    }

    private static void dfs(int n, int v, int p)
    {
        marked[v] = true;

        int w = (n * v) % p;
        if (!marked[w])
        {
            edgeTo[w] = v;
            dfs(n, w, p);
        }
        else if (!finished[w])
        {
            for (int x = v; x != w; x = edgeTo[x])
                count ++;
            count ++;
        }

        finished[v] = true;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int p = In.nextInt();

        solve(n, p);
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
