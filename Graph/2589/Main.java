import java.util.*;
import java.io.*;

/**
 * problem 2589
 * 보물섬
 * https://www.acmicpc.net/problem/2589
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static final int LAND = 1;
    private static final int SEA  = 0;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static int m, n;
    public static void solve(int[][] map)
    {
        int row = map.length;
        int col = map[0].length;
        m = row;
        n = col;
        
        int distance = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
            {
                int[][] tempMap = new int[row][col];
                for (int k = 0; k < row; k++)
                    for (int l = 0; l < col; l++)
                        tempMap[k][l] = map[k][l];
                        
                if (tempMap[i][j] == LAND)
                    distance = Math.max(distance, bfs(tempMap, new Point(i, j)));
            }
            
        System.out.println(distance);
    }
    
    private static int bfs(int[][] map, Point s)
    {
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        map[s.row][s.col] = SEA;
        
        int count = -1;
        while (!q.isEmpty())
        {
            int size = q.size();
            
            while (size-- > 0)
            {
                Point v = q.poll();
                int row = v.row;
                int col = v.col;
                
                for (int i = 0; i < 4; i++)
                {
                    int newRow = row + dy[i];
                    int newCol = col + dx[i];
                    
                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    
                    if (map[newRow][newCol] == LAND)
                    {
                        map[newRow][newCol] = SEA;
                        q.add(new Point(newRow, newCol));
                    }
                }
            }
            count ++;
        }
        
        return count;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int row = In.nextInt();
        int col = In.nextInt();
        
        // map[i][j] = 1이면 육지, 0이면 바다.
        int[][] map = new int[row][col];
        
        for (int i = 0; i < row; i++)
        {
            String str = In.next();
            for (int j = 0; j < col; j++)
                map[i][j] = str.charAt(j) == 'L' ? LAND : SEA;
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