import java.util.*;
import java.io.*;

/**
 * problem 1915
 * 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 * written by progresivoJS
 */
public class Main
{
    private static int n, m;
    private static int[][] board;
    private static int[][] cache;
    
    public static int solve(int n, int m, int[][] board)
    {
        Main.n = n;
        Main.m = m;
        Main.board = board;
        
        cache = new int[n][m];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        return computeArea(0, 0);
    }
    
    /**
     * row, col 이 좌측 상단인 정사각형의 최대 넓이
     */
    private static int computeArea(int row, int col)
    {
        if (row >= n || col >= m)
            return 0;
        
        if (cache[row][col] != -1)
            return cache[row][col];
        
        int side = Math.min(n - row, m - col);
        int area = 0;
        for (int i = 1; i <= side; i++)
            if (isSquare(i, row, col))
                area = Math.max(area, i * i);
            else
                break;
                
        if (area == side * side)
            return cache[row][col] = area;
        
        area = Math.max(area, computeArea(row + 1, col));
        area = Math.max(area, computeArea(row, col + 1));
        
        return cache[row][col] = area;
    }
    
    private static boolean isSquare(int side, int row, int col)
    {
        for (int i = 0; i < side; i++)
            for (int j = 0; j < side; j++)
                if (board[row + i][col + j] != 1)
                    return false;
        return true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int[][] board = new int[n][m];
        
        for (int i = 0; i < n; i++)
        {
            String line = In.next();
            for (int j = 0; j < m; j++)
                board[i][j] = line.charAt(j) - '0';
        }
        
        System.out.println(solve(n, m, board));
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