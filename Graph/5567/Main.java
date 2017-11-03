import java.util.*;
import java.io.*;

/**
 * problem 5567
 * 결혼식
 * https://www.acmicpc.net/problem/5567
 * written by progresivoJS on 2017.11.02
 */
public class Main
{
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        boolean[] marked = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        dist[0] = 0;
        
        while (!q.isEmpty())
        {
            int v = q.poll();
            if (dist[v] == 2)
                break;
            for (int w : adj[v])
                if (!marked[w])
                {
                    q.add(w);
                    marked[w] = true;
                    dist[w] = dist[v] + 1;
                }
        }
        
        int count = 0;
        for (int i = 1; i < n; i++)
            if (dist[i] <= 2)
                count++;
        System.out.println(count);
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
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from].add(to);
            adj[to].add(from);
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
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
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