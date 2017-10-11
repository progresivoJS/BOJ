import java.util.*;
import java.io.*;

/**
 * problem 1507
 * 궁금한 민호
 * https://www.acmicpc.net/problem/1507
 * written by progresivoJS on 2017.10.10
 */
public class Main
{
    public static void solve(int[][] dist)
    {
        int n = dist.length;
        int[][] bridge = new int[n][n];
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                {
                    if (dist[i][j] == dist[i][k] + dist[k][j])
                    {
                        bridge[i][k] = dist[i][k];
                        bridge[k][j] = dist[k][j];
                    }
                    else if (dist[i][j] > dist[i][k] + dist[k][j])
                    {
                        System.out.println(-1);
                        return;
                    }
                }
        
        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                sum += bridge[i][j];
        System.out.println(sum);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dist[i][j] = In.nextInt();
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