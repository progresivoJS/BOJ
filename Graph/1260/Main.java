import java.util.*;
import java.io.*;

/**
 * problem 1260
 * DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 * written by progresivoJS on 2017.09.19
 */
public class Main
{
    private static boolean[] marked;
    private static StringBuilder str;
    public static void solve(int[][] adj, int s)
    {
        int n = adj.length;
        
        marked = new boolean[n];
        dfs(adj, s);
        str.append('\n');
        
        Arrays.fill(marked, false);
        bfs(adj, s);
    }
    
    private static void dfs(int[][] adj, int v)
    {
        marked[v] = true;
        str.append(v + 1).append(' ');
        for (int w = 0; w < adj[v].length; w++)
            if (!marked[w] && adj[v][w] == 1)
            {
                marked[w] = true;
                dfs(adj, w);
            }
    }
    
    private static void dfsIterative(int[][] adj, int s)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        
        while (!stack.isEmpty())
        {
            int v = stack.pop();
            if (marked[v]) continue;
            marked[v] = true;
            str.append(v + 1).append(' ');
            for (int w = adj[v].length - 1; w >= 0; w--)
                if (adj[v][w] == 1)
                    stack.push(w);
        }
    }
    
    private static void bfs(int[][] adj, int s)
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        marked[s] = true;
        
        while (!queue.isEmpty())
        {
            int v = queue.remove();
            str.append(v + 1).append(' ');
            for (int w = 0; w < adj[v].length; w++)
                if (!marked[w] && adj[v][w] == 1)
                {
                    marked[w] = true;
                    queue.add(w);
                }
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int n = In.nextInt();
        int m = In.nextInt();
        int s = In.nextInt() - 1;
        int[][] adj = new int[n][n];
        for (int i = 0; i < m; i++)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from][to] = adj[to][from] = 1;
        }
        
        solve(adj, s);
        System.out.println(str.toString());
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