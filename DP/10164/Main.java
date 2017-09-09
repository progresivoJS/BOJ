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
    private static int[][][][] cache;
    
    public static int solve(int n, int m, int k)
    {
        Main.n = n;
        Main.m = m;
        Main.k = k;
        
        cache = new int[n + 1][m + 1][n + 1][m + 1];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                for (int l = 0; l < cache[i][j].length; l++)
                    Arrays.fill(cache[i][j][l], -1);
            
        if (k == 0)
            return jump(0, 0, n-1, m-1);
        
        int i = (k - 1) / m;
        int j = (k - 1) % m;
        return jump(0, 0, i, j) * jump(i, j, n-1, m-1);
    }
    
    /**
     * row >= 1, col >=1 , 1 < destination < n*m 
     */
    private static int jump(int row, int col, int destRow, int destCol)
    {
        if (row >= n || col >= m)
            return 0;
            
        if (row == destRow && col == destCol)
            return 1;
        
        if (cache[row][col][destRow][destCol] != -1)
            return cache[row][col][destRow][destCol];
            
        int result = 0;
        result += jump(row + 1, col, destRow, destCol);
        result += jump(row, col + 1, destRow, destCol);
        return cache[row][col][destRow][destCol] = result;
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