import java.util.*;
import java.io.*;

/**
 * problem 1780
 * 종이의 개수
 * https://www.acmicpc.net/problem/1780
 * written by progresivoJS
 */
public class Main
{
    private static int[][] board;
    private static int[] frequency;
    public static void solve(int n, int[][] board) throws IOException
    {
        Main.board = board;
        frequency = new int[3];
        DQ(n, 0, 0);
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        out.write(String.valueOf(frequency[0]));
        out.write("\n");
        out.write(String.valueOf(frequency[1]));
        out.write("\n");
        out.write(String.valueOf(frequency[2]));
        out.write("\n");
        out.close();
    }
    
    /**
     * row, col 은 sub board의 좌측 상단
     */
    private static void DQ(int n, int row, int col)
    {
        if (isSame(n, row, col))
        {
            frequency[board[row][col]] ++;
            return;
        }
        
        int oneThird = n / 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                DQ(oneThird, row + oneThird * i, col + oneThird * j);
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
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = In.nextInt() + 1;
        solve(n, board);
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
