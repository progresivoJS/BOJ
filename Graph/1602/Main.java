import java.util.*;
import java.io.*;

/**
 * problem 1602
 * 도망자 원숭이
 * https://www.acmicpc.net/problem/1602
 * written by progresivoJS on 2017.10.11
 */
public class Main
{
    private static int INF = 987654321;
    public static void solve(int[] teaseTime, int[][] dist, int q)
    {
        int n = dist.length;
        
        int[][] maxTeaseTime = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                maxTeaseTime[i][j] = Math.max(teaseTime[i], teaseTime[j]);
        
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    int max = Math.max(maxTeaseTime[i][j], teaseTime[k]);
                    if (maxTeaseTime[i][j] + dist[i][j] > max + dist[i][k] + dist[k][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        maxTeaseTime[i][j] = max;
                    }
                }
        
        StringBuilder str = new StringBuilder();
        while (q-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            
            int cost = dist[from][to] + maxTeaseTime[from][to];
            if (dist[from][to] == INF)
                str.append(-1);
            else
                str.append(cost);
            str.append('\n');
        }
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int q = In.nextInt();
        
        int[] teaseTime = new int[n];
        for (int i = 0; i < n; i++)
            teaseTime[i] = In.nextInt();
        
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            dist[from][to] = dist[to][from] = Math.min(dist[from][to], In.nextInt());
        }
        
        solve(teaseTime, dist, q);
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