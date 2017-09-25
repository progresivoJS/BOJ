import java.util.*;
import java.io.*;

/**
 * problem 2573
 * 빙산
 * https://www.acmicpc.net/problem/2573
 * written by progresivoJS on 2017.09.25
 */
public class Main
{
    private static final int SEA = -987654321;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    private static boolean[][] marked;
    private static int m, n;
    
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        int day = 0;
        while (true)
        {
            int count = 0;
            marked = new boolean[m][n];
            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++)
                    // 땅이면서 아직 간 적이 없을 때.
                    if (!marked[i][j] && map[i][j] != SEA)
                    {
                        dfs(map, i, j);
                        count ++;
                    }
                    
            if (count == 0)
            {
                System.out.println(0);
                return;
            }
            else if (count >= 2)
            {
                System.out.println(day);
                return;
            }
            
            day ++;
        }
    }
    
    private static void dfs(int[][] map, int row, int col)
    {
        marked[row][col] = true;
        
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            // 바다면 현재 땅의 높이를 1만큼 줄인다.
            if (map[newRow][newCol] == SEA)
                map[row][col]--;
        }
        
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (!marked[newRow][newCol] && map[newRow][newCol] != SEA)
                dfs(map, newRow, newCol);
        }
        
        if (map[row][col] <= 0)
            map[row][col] = SEA;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || i == m-1 || j == 0 || j == n-1)
                {
                    In.nextInt();
                    map[i][j] = SEA;
                    continue;
                }
                
                map[i][j] = In.nextInt();
                if (map[i][j] == 0)
                    map[i][j] = SEA;
            }
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