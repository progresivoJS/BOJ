import java.util.*;
import java.io.*;

/**
 * problem 1261
 * 알고스팟
 * https://www.acmicpc.net/problem/1261
 * written by progresivoJS on 2017.10.15
 */
public class Main
{
    private static int INF = 987654321;
    private static int[][] dist;
    private static PriorityQueue<Point> pq;
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};
    
    private static int R, C;
    
    public static void solve(int[][] map)
    {
        R = map.length;
        C = map[0].length;
        
        pq = new PriorityQueue<>();
        dist = new int[R][C];
        for (int i = 0; i < R; i++)
            Arrays.fill(dist[i], INF);
        
        dist[0][0] = 0;
        pq.add(new Point(0, 0, dist[0][0]));
        
        while (!pq.isEmpty())
        {
            Point top = pq.poll();
            int row = top.row;
            int col = top.col;
            int cost = top.key;
            
            if (dist[row][col] < cost) continue;
            
            relax(map, row, col);
        }
        
        System.out.println(dist[R-1][C-1]);
    }
    
    private static void relax(int[][] map, int row, int col)
    {
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];
            
            if (newRow >= R || newRow < 0 || newCol >= C || newCol < 0)
                continue;
                
            if (dist[newRow][newCol] > dist[row][col] + map[newRow][newCol])
            {
                dist[newRow][newCol] = dist[row][col] + map[newRow][newCol];
                pq.add(new Point(newRow, newCol, dist[newRow][newCol]));
            }
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt(); // 가로
        int n = In.nextInt(); // 세로
        
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++)
        {
            String str = In.next();
            for (int j = 0; j < m; j++)
                map[i][j] = str.charAt(j) -'0';
        }
        
        solve(map);
    }
    
    private static class Point implements Comparable<Point>
    {
        int row, col, key;
        public Point(int row, int col, int key)
        {
            this.row = row;
            this.col = col;
            this.key = key;
        }
        
        public int compareTo(Point other)
        {
            return this.key - other.key;
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