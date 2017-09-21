import java.util.*;
import java.io.*;

/**
 * problem 11403
 * 경로 찾기
 * https://www.acmicpc.net/problem/11403
 * written by progresivoJS on 2017.09.20
 */
public class Main
{
    private static int count;
    private static boolean[] marked;
    private static boolean[][] hasPathTo;
    public static void dfs(boolean[][] adj)
    {
        int n = adj.length;
        marked = new boolean[n];
        hasPathTo = new boolean[n][n];
        
        for (int v = 0; v < n; v++)
            if (!marked[v])
            {
                dfs(adj, v);
            }
            
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
                System.out.print((hasPathTo[row][col] ? 1 : 0) + " ");
            System.out.println();
        }
    }
    
    private static void dfs(boolean[][] adj, int v)
    {
        marked[v] = true;
        for (int w = 0; w < adj.length; w++)
            if (!marked[w] && adj[v][w])
            {
                hasPathTo[v][w] = true;
                dfs(adj, w);
            }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt(); // vertex 수
        boolean[][] adj = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adj[i][j] = In.nextInt() == 1;
                
        dfs(adj);
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