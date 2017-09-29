import java.util.*;
import java.io.*;

/**
 * problem 1516
 * 게임개발
 * https://www.acmicpc.net/problem/1516
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    private static int n;

    private static Queue<Integer> q;
    private static int[] result;
    private static int[] time;
    private static int[] indegree;

    public static void solve(LinkedList<Integer>[] adj)
    {
        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            for (int next : adj[v])
            {
                result[next] = Math.max(result[next], result[v] + time[next]);
                if (--indegree[next] == 0) q.add(next);
            }
        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++)
            str.append(result[i]).append(' ');
        System.out.println(str.toString());
    }

    public static void main(String[] args)
    {
        In.init();
        n = In.nextInt();

        q = new LinkedList<>();
        indegree = new int[n];
        time = new int[n];
        result = new int[n];

        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();


        for (int i = 0; i < n; i++)
        {
            time[i] = In.nextInt();

            int tech;
            while ((tech = In.nextInt()) != -1)
            {
                adj[tech - 1].add(i);
                indegree[i]++;
            }

            if (indegree[i] == 0)
            {
                result[i] = time[i];
                q.add(i);
            }
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
            try
            {
                br = new BufferedReader(new FileReader("/Users/rokaf/Desktop/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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
