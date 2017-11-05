import java.util.*;
import java.io.*;

/**
 * problem 5214
 * 환승
 * https://www.acmicpc.net/problem/5214
 * written by progresivoJS on 2017.11.04
 */
public class Main
{
    public static void solve(ArrayList<Integer>[] adj, int m)
    {
        int n = adj.length;
        boolean[] marked = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        marked[0] = true;
        q.add(0);
        
        int dist = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                int v = q.poll();
                if (v == n - m - 1)
                {
                    System.out.println(dist / 2 + 1);
                    return;
                }
                
                for (int w : adj[v])
                    if (!marked[w])
                    {
                        q.add(w);
                        marked[w] = true;
                    }
            }
            dist++;
        }
        
        System.out.println(-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();
        int m = In.nextInt();
        
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n + m];
        for (int i = 0; i < n + m; i++)
            adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < k; j++)
            {
                int from = n + i;
                int to = In.nextInt() - 1;
                adj[from].add(to);
                adj[to].add(from);
            }
        
        solve(adj, m);
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