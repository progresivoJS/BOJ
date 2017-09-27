import java.util.*;
import java.io.*;

/**
 * problem 1600
 * 말이 되고픈 원숭이
 * https://www.acmicpc.net/problem/1600
 * written by progresivoJS on 2017.09.27
 */
public class Main
{
    private static int m, n;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    private static final int[] dxHorse = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] dyHorse = {-2, -1, 1, 2, 2, 1, -1, -2};
    
    public static void solve(int k, int[][] map)
    {
        m = map.length;
        n = map[0].length;
        
        bfs(k, map);
    }
    
    private static void bfs(int k, int[][] map)
    {
        // marked[i][j][k] = 말처럼 i번 행동했을 때, j행 k열을 방문했는가?
        boolean[][][] marked = new boolean[k + 1][m][n];
        marked[k][0][0] = true;
        
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, k));
        
        int action = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                if (v.row == m - 1 && v.col == n - 1)
                {
                    System.out.println(action);
                    return;
                }
                
                for (int i = 0; i < 4; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];
                    
                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    
                    if (!marked[v.jumpCount][newRow][newCol] && map[newRow][newCol] == 0)
                    {
                        marked[v.jumpCount][newRow][newCol] = true;
                        q.add(new Point(newRow, newCol, v.jumpCount));
                    }
                }
                
                if (v.jumpCount > 0)
                {
                    for (int i = 0; i < 8; i++)
                    {
                        int newRow = v.row + dyHorse[i];
                        int newCol = v.col + dxHorse[i];
                        
                        if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                            continue;
                        
                        if (!marked[v.jumpCount - 1][newRow][newCol] && map[newRow][newCol] == 0)
                        {
                            marked[v.jumpCount - 1][newRow][newCol] = true;
                            q.add(new Point(newRow, newCol, v.jumpCount - 1));
                        }
                    }
                }
            }
            action ++;
        }
        System.out.println(-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int k = In.nextInt();
        int n = In.nextInt();
        int m = In.nextInt();
        int[][] map = new int[m][n]; // map[i][j] = 1이면 장애물, 0 이면 평지
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = In.nextInt();
        solve(k, map);
    }
    
    private static class Point
    {
        int row, col;
        int jumpCount;
        public Point(int row, int col, int jumpCount)
        {
            this.row = row;
            this.col = col;
            this.jumpCount = jumpCount;
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