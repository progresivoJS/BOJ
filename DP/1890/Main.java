import java.util.*;
import java.io.*;

/**
 * problem 1890
 * Jump
 * https://www.acmicpc.net/problem/1890
 * written by progresivoJS
 */
public class Main
{
    private static int[][] board;
    private static long[][] cache;
    private static int n;
    public static long solve(int[][] board)
    {
        Main.board = board;
        Main.n = board.length;
        cache = new long[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);

        return jump(0, 0);
    }

    private static long jump(int row, int col)
    {
        if (row >= n || col >= n)
            return 0;
        if (board[row][col] == 0)
            if (row == n-1 && col == n-1)
                return 1;
            else
                return 0;
                
        if (cache[row][col] != -1)
            return cache[row][col];

        long result = 0;
        
        result = jump(row + board[row][col], col) + jump(row, col + board[row][col]);
        return cache[row][col] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = In.nextInt();
        System.out.println(solve(board));
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