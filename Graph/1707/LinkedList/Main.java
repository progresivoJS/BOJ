import java.util.*;
import java.io.*;

/**
 * problem 1707
 * 이분 그래프
 * https://www.acmicpc.net/problem/1707
 * V*V에 비해 edge수가 굉장히 적기 때문에 LinkedList가 훨씬 빠르다.
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int n;
    private static boolean[] marked;
    private static boolean[] color;
    private static boolean isBipartite;
    public static void solve(LinkedList<Integer>[] adj)
    {
        n = adj.length;
        marked = new boolean[n];
        color = new boolean[n];
        isBipartite = true;
        
        for (int v = 0; v < n; v++)
            if (!marked[v])
            {
                dfs(adj, v);
                if (!isBipartite)
                    break;
            }
                
        if (isBipartite)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
        {
            if (!isBipartite)
                return;
                
            if (!marked[w])
            {
                color[w] = !color[v];
                dfs(adj, w);
            }
            else if (color[v] == color[w])
            {
                isBipartite = false;
            }
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int vertex = In.nextInt();
            int edge = In.nextInt();
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[vertex];
            for (int j = 0; j < adj.length; j++)
                adj[j] = new LinkedList<Integer>();
            for (int j = 0; j < edge; j++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                adj[from].add(to);
                adj[to].add(from);
            }
            
            solve(adj);
        }
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