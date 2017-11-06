import java.util.*;
import java.io.*;

/**
 * problem 6623
 * Arbitrage?
 * https://www.acmicpc.net/problem/6623
 * written by progresivoJS on 2017.11.06
 */
public class Main
{
    private static double INF = Double.MAX_VALUE;
    private static StringBuilder str;
    public static void solve(double[][] dist)
    {
        int n = dist.length;
        
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        boolean isArbitrage = false;
        for (int i = 0; i < n; i++)
            if (dist[i][i] < 0)
            {
                isArbitrage = true;
                break;
            }
        
        str.append(isArbitrage ? "Arbitrage" : "Ok").append('\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
            double[][] dist = new double[n][n];
            for (int i = 0; i < n; i++)
            {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }
            
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++)
                map.put(In.next(), i);
            
            int m = In.nextInt();
            for (int i = 0; i < m; i++)
            {
                int from = map.get(In.next());
                int to = map.get(In.next());
                String[] s = In.next().split(":");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                
                dist[from][to] = -Math.log((double)b/a);
            }
            
            solve(dist);
        }
        System.out.println(str);
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