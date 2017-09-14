import java.util.*;
import java.io.*;

/**
 * problem 1937
 * 욕심쟁이 판다
 * https://www.acmicpc.net/problem/1937
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static int n;
    private static int[][] board;
    private static int[][] cache;
    
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};
    
    public static void solve(int[][] board)
    {
        n = board.length;
        Main.board = board;
        cache = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
        
        int result = -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result = Math.max(result, eat(i, j));
        System.out.println(result);
    }
    
    private static int eat(int row, int col)
    {
        if (cache[row][col] != -1)
            return cache[row][col];
        
        int result = 1;
        for (int i = 0; i < 4; i++)
            if ((row + dy[i] >= 0 && row + dy[i] < n) && (col + dx[i] >= 0 && col + dx[i] < n))
                if (board[row][col] < board[row + dy[i]][col + dx[i]])
                    result = Math.max(result, 1 + eat(row + dy[i], col + dx[i]));
        
        return cache[row][col] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = In.nextInt();
        solve(board);
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
