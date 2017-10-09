import java.util.*;
import java.io.*;

/**
 * problem 2458
 * 키 순서
 * https://www.acmicpc.net/problem/2458
 * written by progresivoJS on 2017.10.09
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
                    if (dist[i][k] == 1 && dist[k][j] == 1)
                        dist[i][j] = 1;
                    else if (dist[i][k] == -1 && dist[k][j] == -1)
                        dist[i][j] = -1;
        
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            int j;
            for (j = 0; j < n; j++)
                if (dist[i][j] == INF)
                    break;
            if (j == n) count++;
        }
        
        System.out.println(count);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                if (i == j) dist[i][j] = 0;
                else        dist[i][j] = INF;
            }
                
        int m = In.nextInt();
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            dist[from][to] = 1;
            dist[to][from] = -1;
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