import java.util.*;
import java.io.*;

/**
 * problem 1613
 * 역사
 * https://www.acmicpc.net/problem/1613
 * written by progresivoJS on 2017.09.27
 */
public class Main
{
    private static boolean[][] marked;
    private static int n;
    public static void solve(LinkedList<Integer>[] adj)
    {
        n = adj.length;

        // marked[i][j] = i에서 j로 가는 길이 있는가?
        marked = new boolean[n][n];

        for (int s = 0; s < n; s++)
            dfs(adj, s, s);
    }

    private static void dfs(LinkedList<Integer>[] adj, int s, int v)
    {
        marked[s][v] = true;
        for (int w : adj[v])
            if (!marked[s][w])
                dfs(adj, s, w);
    }

    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();

        int n = In.nextInt(); // <= 400
        int k = In.nextInt(); // <= 50000

        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();

        for (int i = 0; i < k; i++)
            adj[In.nextInt() - 1].add(In.nextInt() - 1);

        solve(adj);

        int s = In.nextInt();
        for (int i = 0; i < s; i++)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            if (marked[from][to])
                str.append(-1).append('\n');
            else if (marked[to][from])
                str.append(1).append('\n');
            else
                str.append(0).append('\n');
        }

        System.out.println(str.toString());
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
