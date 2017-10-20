import java.util.*;
import java.io.*;

/**
 * problem 1325
 * 효율적인 해킹
 * https://www.acmicpc.net/problem/1325
 * written by progresivoJS on 2017.10.20
 */
public class Main
{
    private static boolean[] marked;
    private static int count;

    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        int[] infectedCount = new int[n];

        for (int v = 0; v < n; v++)
        {
            marked = new boolean[n];
            count = 1;
            dfs(adj, v);
            infectedCount[v] = count;
        }

        // find max
        int max = 0;
        int lastIndex = -1;
        for (int i = 0; i < n; i++)
        {
            if (infectedCount[i] >= max)
            {
                max = infectedCount[i];
                lastIndex = i;
            }
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= lastIndex; i++)
            if (infectedCount[i] == max)
                str.append(i + 1).append(' ');
        System.out.println(str);
    }

    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;

        for (int w : adj[v])
            if (!marked[w])
            {
                count++;
                dfs(adj, w);
            }
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();

        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();

        while (m-- > 0)
        {
            int to = In.nextInt() - 1;
            int from = In.nextInt() - 1;
            adj[from].add(to);
        }

        solve(adj);
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
