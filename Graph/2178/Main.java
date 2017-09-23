import java.util.*;
import java.io.*;

/**
 * problem 2178
 * 미로탐색
 * https://www.acmicpc.net/problem/2178
 * written by progresivoJS on 2017.09.23
 */
public class Main
{
    private static int n, m;
    private static final int POSSIBLE = 1;
    private static final int IMPOSSIBLE = 0;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    public static void solve(int[][] map)
    {
        n = map.length;
        m = map[0].length;
        
        bfs(map);
    }
    
    private static void bfs(int[][] map)
    {
        map[0][0] = IMPOSSIBLE;
        Point s = new Point(0, 0);
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        
        int count = 1;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size -- > 0)
            {
                Point v = q.poll();
                int row = v.row;
                int col = v.col;
                
                if (row == n - 1 && col == m - 1)
                {
                    System.out.println(count);
                    return;
                }
                
                for (int i = 0; i < 4; i++)
                {
                    int newRow = row + dx[i];
                    int newCol = col + dy[i];
                    
                    if (newRow >= n || newRow < 0 || newCol >= m || newCol < 0)
                        continue;
                    if (map[newRow][newCol] == POSSIBLE)
                    {
                        map[newRow][newCol] = IMPOSSIBLE;
                        q.add(new Point(newRow, newCol));
                    }
                }
            }
            
            count ++;
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int row = In.nextInt();
        int col = In.nextInt();
        int[][] map = new int[row][col];
        for (int i = 0; i < row; i++)
        {
            String str = In.next();
            for (int j = 0; j < col; j++)
                map[i][j] = str.charAt(j) - '0';
        }
        
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