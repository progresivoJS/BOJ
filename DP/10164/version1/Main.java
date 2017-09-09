import java.util.*;
import java.io.*;

/**
 * problem 10164
 * 격자상의 경로
 * https://www.acmicpc.net/problem/10164
 * written by progresivoJS
 */
public class Main
{
    private static int n, m, k;
    private static int[][][] cache;
    
    public static int solve(int n, int m, int k)
    {
        Main.n = n;
        Main.m = m;
        Main.k = k;
        
        cache = new int[n + 1][m + 1][n * m + 1];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);
            
        if (k == 0)
            return jump(1, 1, n * m);
        return jump(1, 1, k) * jump(k % m == 0 ? k / m : k / m + 1, k % m == 0 ? m : k % m, n * m);
    }
    
    /**
     * row >= 1, col >=1 , 1 < destination < n*m 
     */
    private static int jump(int row, int col, int destination)
    {
        if (row > n || col > m)
            return 0;
            
        if ((row-1) * m + col  == destination)
            return 1;
        
        if (cache[row][col][destination] != -1)
            return cache[row][col][destination];
            
        int result = 0;
        
        result += jump(row + 1, col, destination);
        
            
        result += jump(row, col + 1, destination);
        
        
        return cache[row][col][destination] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int k = In.nextInt();
        
        System.out.println(solve(n, m, k));
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