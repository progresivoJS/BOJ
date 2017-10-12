import java.util.*;
import java.io.*;

/**
 * problem 1238
 * 파티
 * https://www.acmicpc.net/problem/1238
 * written by progresivoJS on 2017.10.09
 */
public class Main
{
    private static int INF = 987654321;
    public static void solve(int[][] dist, int x)
    {
        int n = dist.length;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dist[i][x] + dist[x][i]);
        
        System.out.println(max);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int x = In.nextInt() - 1;
        
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (i == j) dist[i][j] = 0;
                else        dist[i][j] = INF;
            }
            
        while(m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            dist[from][to] = In.nextInt();
        }
        
        solve(dist, x);
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