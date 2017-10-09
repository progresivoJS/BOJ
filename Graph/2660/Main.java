import java.util.*;
import java.io.*;

/**
 * problem 2660
 * 회장뽑기
 * https://www.acmicpc.net/problem/2660
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
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        
        int min = INF;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++)
        {
            int score = 0;
            for (int j = 0; j < n; j++)
                if (dist[i][j] != INF)
                    score = Math.max(score, dist[i][j]);
                
            if (score == min)
                list.add(i);
            else if (score < min)
            {
                min = score;
                list.clear();
                list.add(i);
            }
        }
        
        System.out.println(min + " " + list.size());
        for (int i : list)
            System.out.print(i + 1 + " ");
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
        
        while (true)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            if (from == -2 && to == -2)
                break;
            
            dist[from][to] = dist[to][from] = 1;
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