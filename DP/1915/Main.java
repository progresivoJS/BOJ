import java.util.*;
import java.io.*;

/**
 * problem 1915
 * 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 * written by progresivoJS on 2017.11.06
 */
public class Main
{
    private static int n, m;
    private static int[][] board;
    private static int[][] cache;
    
    public static void solve(int n, int m, int[][] board)
    {
        Main.n = n;
        Main.m = m;
        Main.board = board;
        
        cache = new int[n][m];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                result = Math.max(result, computeArea(i, j));
        System.out.println(result * result);
    }
    
    /**
     * row, col 이 좌측 상단인 정사각형의 한변의 길이
     */
    private static int computeArea(int row, int col)
    {
        if (row >= n || col >= m)
            return 0;
        if (row == n - 1 || col == m - 1)
            return board[row][col];
        
        if (cache[row][col] != -1)
            return cache[row][col];
        
        int result = board[row][col];
        if (board[row][col] == 1 && board[row][col + 1] == 1 && board[row + 1][col] == 1 && board[row + 1][col + 1] == 1)
            result = 1 + Math.min(computeArea(row + 1, col + 1), Math.min(computeArea(row + 1, col), computeArea(row, col + 1)));
        
        return cache[row][col] = result;
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
        
        solve(n, m, board);
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
