import java.util.*;
import java.io.*;

/**
 * problem 2468
 * 안전 영역
 * https://www.acmicpc.net/problem/2468
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int n;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static void solve(int[][] map)
    {
        n = map.length;
        int max = 0;
        int min = 100;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        
        int safeRegion = 0;
        for (int rain = min - 1; rain < max; rain++)
        {
            boolean[][] isFlooded = new boolean[n][n];
            
            // check flooded region.
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (map[i][j] <= rain)
                        isFlooded[i][j] = true;
            
            // count safe region.
            int count = 0;
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (!isFlooded[i][j])
                    {
                        count ++;
                        dfs(isFlooded, i, j);
                    }
            
            safeRegion = Math.max(safeRegion, count);
        }
        
        System.out.println(safeRegion);
    }
    
    private static void dfs(boolean[][] isFlooded, int row, int col)
    {
        isFlooded[row][col] = true;
        
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (!isFlooded[newRow][newCol])
                dfs(isFlooded, newRow, newCol);
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++)
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