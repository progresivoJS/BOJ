import java.util.*;
import java.io.*;

/**
 * problem 6598
 * Arbitrage
 * https://www.acmicpc.net/problem/6598
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    private static final double INF = Double.MAX_VALUE;
    private static StringBuilder str;

    public static void solve(double[][] dist, int testCase)
    {
        int n = dist.length;
        
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }

        str.append("Case ").append(testCase).append(": ");
        for (int i = 0; i < n; i++)
            if (dist[i][i] < 0)
            {
                str.append("Yes").append('\n');
                return;
            }

        str.append("No").append('\n');
    }

    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();

        int testCase = 1;
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
                double weight = -Math.log(Double.parseDouble(In.next()));
                int to = map.get(In.next());
                dist[from][to] = weight;
            }

            solve(dist, testCase++);
        }

        System.out.println(str);
    }
    
    private static class Edge
    {
        int from, to;
        double weight;
        public Edge(int from, int to, double weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
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
