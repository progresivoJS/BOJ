import java.util.*;
import java.io.*;

/**
 * problem 11498
 * 홀수 싸이클
 * https://www.acmicpc.net/problem/11498
 * written by progresivoJS on 2017.10.19
 */
public class Main
{
    private static boolean[] marked;
    private static boolean[] finished;
    private static int[] edgeTo;
    private static Deque<Integer> cycle;

    private static StringBuilder str;

    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;

        marked = new boolean[n];
        finished = new boolean[n];
        edgeTo = new int[n];
        cycle = null;

        for (int v = 0; v < n; v++)
            if (!marked[v] && cycle == null)
                dfs(adj, v);

        if (cycle != null && cycle.size() % 2 == 1)
        {
            str.append(1).append('\n');
            str.append(cycle.size()).append('\n');
            for (int i : cycle)
                str.append(i+1).append('\n');
        }
        else
        {
            str.append(-1).append('\n');
        }
    }

    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;

        for (int w : adj[v])
        {
            if (cycle != null && cycle.size() % 2 == 1) return;
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(adj, w);
            }
            else if (!finished[w])
            {
                cycle = new ArrayDeque<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
            }
        }

        finished[v] = true;
    }

    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();

            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();

            while (m-- > 0)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;

                adj[from].add(to);
            }

            solve(adj);
        }
        System.out.println(str);
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
