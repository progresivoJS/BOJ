import java.util.*;
import java.io.*;

/**
 * problem 2146
 * 다리 만들기
 * https://www.acmicpc.net/problem/2146
 * written by progresivoJS on 2017.09.25
 */
public class Main
{
    private static int n;
    private static int[][] id;
    private static int count;
    
    private static final int LAND = 1;
    private static final int SEA = 0;
    
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    public static void solve(int[][] map)
    {
        n = map.length;
        
        boolean[][] marked = new boolean[n][n];
        id = new int[n][n]; // id는 1부터 매겨진다. 0은 바다.
        
        // check id.
        count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (!marked[i][j] && map[i][j] == LAND)
                {
                    dfs(map, marked, i, j);
                    count++;
                }
        
        // 각 섬에서 시작한다고 가정했을 때,
        // 각 섬의 간척 length를 구하고 그 중에서 최솟값을 구한다.
        int length = 987654321;
        for (int i = 1; i <= count; i++)
        {
            length = Math.min(length, bfs(map, i));
        }
        
        System.out.println(length);
    }
    
    private static int bfs(int[][] map, int regionId)
    {
        Queue<Point> q = new LinkedList<Point>();
        boolean[][] marked = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (id[i][j] == regionId)
                {
                    q.add(new Point(i, j));
                    marked[i][j] = true;
                }
        
        int bridge = -1;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                if (id[v.row][v.col] != regionId && id[v.row][v.col] != 0)
                {
                    return bridge;
                }
                
                for (int i = 0; i < 4; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];
                    
                    if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    
                    if (!marked[newRow][newCol])
                    {
                        q.add(new Point(newRow, newCol));
                        marked[newRow][newCol] = true;
                    }
                }
            }
            
            bridge ++;
        }
        
        return 987654321;
    }
    
    private static void dfs(int[][] map, boolean[][] marked, int row, int col)
    {
        marked[row][col] = true;
        id[row][col] = count + 1;
        
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            
            if (!marked[newRow][newCol] && map[newRow][newCol] == LAND)
                dfs(map, marked, newRow, newCol);
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
    
    public static class Point
    {
        int row, col;
        public Point(int row, int col)
        {
            this.row = row;
            this.col = col;
        }
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