import java.util.*;
import java.io.*;

/**
 * problem 11562
 * 백양로 브레이크
 * https://www.acmicpc.net/problem/11562
 * written by progresivoJS on 2017.10.10
 */
public class Main
{
    private static int INF = 987654321;
    
    public static void solve(int[][] dist)
    {
        int n = dist.length;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        int q = In.nextInt();
        StringBuilder str = new StringBuilder();
        while (q-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            str.append(dist[from][to]).append('\n');
        }
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                // dist[i][j] = INF;
                if (i == j) dist[i][j] = 0;
                else        dist[i][j] = INF;
        
        int m = In.nextInt();
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int direction = In.nextInt();
            if (direction == 0)
            {
                dist[to][from] = 1;
                dist[from][to] = 0;
            }
            else
                dist[from][to] = dist[to][from] = 0;
        }
        
        solve(dist);
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