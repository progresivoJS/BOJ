import java.util.*;
import java.io.*;

/**
 * problem 4485
 * 녹색 옷 입은 애가 젤다지?
 * https://www.acmicpc.net/problem/4485
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static final int INF = 987654321;
    private static PriorityQueue<Point> pq;
    private static int[][] dist;
    
    private static StringBuilder str;
    
    public static void solve(int count, int[][] map)
    {
        int n = map.length;
        pq = new PriorityQueue<>();
        dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);
        
        dist[0][0] = map[0][0];
        pq.add(new Point(0, 0, dist[0][0]));
        
        while (!pq.isEmpty())
        {
            Point top = pq.poll();
            int row = top.row;
            int col = top.col;
            int cost = top.dist;
            
            if (dist[row][col] < cost) continue;
            
            for (int i = 0; i < 4; i++)
            {
                int newRow = row + dy[i];
                int newCol = col + dx[i];
                
                if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                    continue;
                
                if (dist[newRow][newCol] > dist[row][col] + map[newRow][newCol])
                {
                    dist[newRow][newCol] = dist[row][col] + map[newRow][newCol];
                    pq.add(new Point(newRow, newCol, dist[newRow][newCol]));
                }
            }
        }
        
        str.append("Problem " + count +": " + dist[n-1][n-1] + '\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int count = 1;
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
            
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    map[i][j] = In.nextInt();
            
            solve(count++, map);
        }
        
        System.out.println(str.toString());
    }
    
    private static class Point implements Comparable<Point>
    {
        int row, col;
        int dist;
        
        public Point(int row, int col, int dist)
        {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
        
        public int compareTo(Point other)
        {
            return this.dist - other.dist;
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