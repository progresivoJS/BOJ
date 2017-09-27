import java.util.*;
import java.io.*;

/**
 * problem 1926
 * 그림
 * https://www.acmicpc.net/problem/1926
 * written by progresivoJS on 2017.09.27
 */
public class Main
{
    private static int m, n, count;
    private static final int MARKED = -1;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        count = 0;
        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (map[i][j] == 1)
                {
                    count ++;
                    max = Math.max(max, dfs(map, i, j));
                }
        System.out.println(count);
        System.out.println(max);
    }
    
    private static int dfs(int[][] map, int row, int col)
    {
        map[row][col] = MARKED;
        int area = 1;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (map[newRow][newCol] == 1)
                area += dfs(map, newRow, newCol);
        }
        
        return area;
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