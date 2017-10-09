import java.util.*;
import java.io.*;

/**
 * problem 11403
 * 경로 찾기
 * https://www.acmicpc.net/problem/11403
 * written by progresivoJS on 2017.09.28
 */
public class Main
{
    private static int n;
    private static boolean[][] marked;
    public static void solve(boolean[][] adj)
    {
        n = adj.length;
        
        // marked[i][j] = i에서 출발했을 때, j까지 도달하는가?
        marked = new boolean[n][n];
        
        for (int s = 0; s < n; s++)
            for (int v = 0; v < n; v++)
                if (!marked[s][v] && adj[s][v])
                    dfs(adj, s, v);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                str.append(marked[i][j] ? 1 : 0).append(' ');
            str.append('\n');
        }
        
        System.out.println(str.toString());
    }
    
    private static void dfs(boolean[][] adj, int s, int v)
    {
        marked[s][v] = true;
        for (int w = 0; w < n; w++)
            if (adj[v][w] && !marked[s][w])
                dfs(adj, s, w);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        boolean[][] adj = new boolean[n][n]; // adj[i][j] = i에서 j로 가는 방향 edge가 있는가?
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adj[i][j] = In.nextInt() == 1;
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