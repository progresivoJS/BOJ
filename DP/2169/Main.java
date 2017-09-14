import java.util.*;
import java.io.*;

/**
 * problem 2169
 * 로봇 조종하기
 * https://www.acmicpc.net/problem/2169
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static final int down = 0;
    private static final int left = 1;
    private static final int right = 2;
    
    private static int n, m;
    private static int[][] map;
    private static int[][][] cache;
    
    private static int INF = 987654321;
    
    public static void solve(int n, int m, int[][] map)
    {
        Main.n = n;
        Main.m = m;
        Main.map = map;
        
        cache = new int[n][m][3];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);
        
        System.out.println(Math.max(search(0, 0, down), search(0, 0, right)));
    }
    
    // 현재 row, col 에 있고, direction 방향으로 가려고 할 때 가치의 최댓값.
    private static int search(int row, int col, int direction)
    {
        if (row == n-1 && col == m-1)
            return map[row][col];
        
        if (row >= n || row < 0 || col >= m || col < 0)
            return -INF;
        
        if (cache[row][col][direction] != -1)
            return cache[row][col][direction];
        
        int result = -INF;
        
        if (direction == right)
        {
            result = Math.max(result, map[row][col] + search(row, col + 1, right));
            result = Math.max(result, map[row][col] + search(row, col + 1, down));
        }
        else if (direction == left)
        {
            result = Math.max(result, map[row][col] + search(row, col - 1, left));
            result = Math.max(result, map[row][col] + search(row, col - 1, down));
        }
        else if (direction == down)
        {
            result = Math.max(result, map[row][col] + search(row + 1, col, down));
            result = Math.max(result, map[row][col] + search(row + 1, col, right));
            result = Math.max(result, map[row][col] + search(row + 1, col, left));
        }
        
        return cache[row][col][direction] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        
        int n = In.nextInt();
        int m = In.nextInt();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                map[i][j] = In.nextInt();
        solve(n, m, map);
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