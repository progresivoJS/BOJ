import java.util.*;
import java.io.*;

/**
 * problem 1992
 * QuadTree
 * https://www.acmicpc.net/problem/1992
 * written by progresivoJS
 */
public class Main
{
    private static int[][] board;
    public static String solve(int n, int[][] board)
    {
        Main.board = board;
        return DQ(n, 0, 0);
    }
    
    /**
     * row, col 은 sub box의 좌측상단 좌표.
     */
    private static String DQ(int n, int row, int col)
    {
        if (isSame(n, row, col))
            return String.valueOf(board[row][col]);
        
        StringBuilder s = new StringBuilder("(");
        
        int half = n / 2;
        s.append(DQ(half, row, col));
        s.append(DQ(half, row, col + half));
        s.append(DQ(half, row + half, col));
        s.append(DQ(half, row + half, col + half));
        
        s.append(")");
        return s.toString();
    }
    
    private static boolean isSame(int n, int row, int col)
    {
        int cmp = board[row][col];
        for (int i = row; i < row + n; i++)
            for (int j = col; j < col + n; j++)
                if (cmp != board[i][j])
                    return false;
        return true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            String line = In.next();
            for (int j = 0; j < n; j++)
                board[i][j] = line.charAt(j) - '0';
        }
        
        System.out.println(solve(n, board));
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
