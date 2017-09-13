import java.util.*;
import java.io.*;

/**
 * problem 4883
 * 삼각 그래프
 * https://www.acmicpc.net/problem/4883
 * written by progresivoJS on 2017.09.12
 */
public class Main
{
    private static int n;
    private static int[][] board;
    private static int[][] cache;
    private static int INF = 987654321;
    
    private static int[] dx = {1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1};
    
    private static BufferedWriter out;
    
    public static void solve(int n, int[][] board) throws IOException
    {
        Main.n = n;
        Main.board = board;
        
        cache = new int[n][3];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = goDown(0, 1);
        out.write(String.valueOf(result));
        out.write("\n");
    }
    
    /**
     * row, col 에서 마지막행 2열까지 갈 때, 경로의 비용
     */
    private static int goDown(int row, int col)
    {
        if (row == n-1 && col == 1)
            return board[row][col];
        if (row >= n || col > 2 || col < 0)
            return INF;
            
        if (cache[row][col] != -1)
            return cache[row][col];
            
        int cost = INF;
        for (int i = 0; i < 4; i++)
            cost = Math.min(cost, board[row][col] + goDown(row + dy[i], col + dx[i]));
        return cache[row][col] = cost;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int count = 1;
        while (true)
        {
            int n = In.nextInt();
            if (n == 0)
                break;
            int[][] board = new int[n][3];
            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board[i].length; j++)
                    board[i][j] = In.nextInt();
            out.write(String.valueOf(count++));
            out.write(". ");
            solve(n, board);
        }
        
        out.close();
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