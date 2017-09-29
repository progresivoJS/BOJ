import java.util.*;
import java.io.*;

/**
 * problem 2252
 * 줄 세우기
 * https://www.acmicpc.net/problem/2252
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    public static void solve(LinkedList<Integer>[] adj, int[] ind)
    {
        int n = adj.length;

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                q.add(i);

        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            result[i] = v;
            for (int w : adj[v])
                if (--ind[w] == 0)
                    q.add(w);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
            str.append(result[i] + 1).append(' ');
        System.out.println(str.toString());
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();

        int[] ind = new int[n];
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();

        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from].add(to);
            ind[to] ++;
        }

        solve(adj, ind);
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
