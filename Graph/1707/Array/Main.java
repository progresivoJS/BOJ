import java.util.*;
import java.io.*;

/**
 * problem 1707
 * 이분 그래프
 * https://www.acmicpc.net/problem/1707
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int n;
    private static boolean[] marked;
    private static boolean[] color;
    private static boolean bipartite;
    public static void solve(boolean[][] areConnected)
    {
        n = areConnected.length;
        marked = new boolean[n];
        color = new boolean[n];
        bipartite = true;
        
        for (int i = 0; i < n; i++)
            if (!marked[i])
            {
                dfs(areConnected, i);
                if (!bipartite)
                    break;
            }
                
        if (bipartite)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
    
    private static void dfs(boolean[][] areConnected, int v)
    {
        marked[v] = true;
        for (int w = 0; w < n; w++)
        {
            if (!bipartite)
                return;
            if (areConnected[v][w])
            {
                if (!marked[w])
                {
                    color[w] = !color[v];
                    dfs(areConnected, w);
                }
                else if (color[v] == color[w])
                {
                    bipartite = false;
                }
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
            boolean[][] areConnected = new boolean[vertex][vertex];
            for (int j = 0; j < edge; j++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                areConnected[from][to] = areConnected[to][from] = true;
            }
            
            solve(areConnected);
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