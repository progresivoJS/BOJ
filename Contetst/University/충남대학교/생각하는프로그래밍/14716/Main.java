import java.util.*;
import java.io.*;

/**
 * problem 14716
 * 현수막
 * https://www.acmicpc.net/problem/14716
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    private static int m, n;
    private static boolean[][] marked;
    
    private static final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    private static final int[] dy = {0, 1 ,1 ,1, 0, -1 ,-1 ,-1};
    
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        int count = 0;
        marked = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (!marked[i][j] && map[i][j] == 1)
                {
                    count++;
                    dfs(map, i, j);
                }
        
        System.out.println(count);
    }
    
    private static void dfs(int[][] map, int row, int col)
    {
        marked[row][col] = true;
        for (int i = 0; i < 8; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
                
            if (!marked[newRow][newCol] && map[row][col] == 1)
                dfs(map, newRow, newCol);
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = In.nextInt();
        solve(map);
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