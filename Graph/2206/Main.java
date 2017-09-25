import java.util.*;
import java.io.*;

/**
 * problem 2206
 * 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static int m, n;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static final int MARKED = -1;
    private static final int BLOCKED = 1;
    private static final int NOT_BLOCKED = 0;
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        bfs(map);
    }
    
    private static void bfs(int[][] map)
    {
        Queue<Point> q = new LinkedList<Point>();
        q.add(new Point(0, 0, false));
        map[0][0] = MARKED;
        
        int distance = 1;
        
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                System.out.println("row : " + v.row + " col : " + v.col + " " + v.didBreakWall);
                
                if (v.row == m - 1 && v.col == n - 1)
                {
                    System.out.println(distance);
                    return;
                }
                
                for (int i = 0; i < 4; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];
                    
                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    
                    // 안부술때.
                    if (map[newRow][newCol] == NOT_BLOCKED)
                    {
                        q.add(new Point(newRow, newCol, v.didBreakWall));
                        map[newRow][newCol] = MARKED;
                    }
                    
                    // 부술때.
                    if (!v.didBreakWall && map[newRow][newCol] == BLOCKED)
                    {
                        q.add(new Point(newRow, newCol, true));
                        map[newRow][newCol] = MARKED;
                    }
                }
            }
            System.out.println();
            
            distance ++;
        }
        
        System.out.println(-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();
        
        // map[i] = 1이면 벽, 0이면 이동할 수 있는 공간.
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++)
        {
            String str = In.next();
            for (int j = 0; j < n; j++)
                map[i][j] = str.charAt(j) - '0';
        }
        
        solve(map);
    }
    
    public static class Point
    {
        int row, col;
        boolean didBreakWall;
        public Point(int row, int col, boolean didBreakWall)
        {
            this.row = row;
            this.col = col;
            this.didBreakWall = didBreakWall;
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