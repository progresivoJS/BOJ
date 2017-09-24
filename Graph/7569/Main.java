import java.util.*;
import java.io.*;

/**
 * problem 7569
 * 토마토
 * https://www.acmicpc.net/problem/7569
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static final int NO_TOMATO = -1;
    private static final int RIPENED = 1;
    private static final int NOT_RIPENED = 0;
    
    private static final int[] dx = {1, 0, -1, 0, 0, 0};
    private static final int[] dy = {0, 1, 0, -1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};
    
    private static int row, col, height;
    
    public static void solve(int[][][] tomato)
    {
        Main.row = tomato.length;
        Main.col = tomato[0].length;
        Main.height = tomato[0][0].length;
        
        int totalTomato = 0;
        for (int i = 0; i < tomato.length; i++)
            for (int j = 0; j < tomato[i].length; j++)
                for (int k = 0; k < tomato[i][j].length; k++)
                    if (tomato[i][j][k] != -1)
                        totalTomato++;
        bfs(tomato, totalTomato);
    }
    
    private static void bfs(int[][][] tomato, int totalTomato)
    {
        int ripenedTomato = 0;
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < tomato.length; i++)
            for (int j = 0; j < tomato[i].length; j++)
                for (int k = 0; k < tomato[i][j].length; k++)
                    if (tomato[i][j][k] == RIPENED)
                    {
                        q.add(new Point(i, j, k));
                        ripenedTomato ++;
                    }
        
        int day = 0;
        while (!q.isEmpty())
        {
            if (ripenedTomato == totalTomato)
            {
                System.out.println(day);
                return;
            }
            
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                
                for (int i = 0; i < 6; i++)
                {
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];
                    int newHeight = v.height + dz[i];
                    
                    if (newRow >= row || newRow < 0)
                        continue;
                    if (newCol >= col || newCol < 0)
                        continue;
                    if (newHeight >= height || newHeight < 0)
                        continue;
                        
                    if (tomato[newRow][newCol][newHeight] == NOT_RIPENED)
                    {
                        ripenedTomato++;
                        tomato[newRow][newCol][newHeight] = RIPENED;
                        q.add(new Point(newRow, newCol, newHeight));
                    }
                }
            }
            
            day ++;
        }
        
        System.out.println(-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt(); // col
        int n = In.nextInt(); // row
        int h = In.nextInt(); // height
        
        // tomato[i][j][k] = row : i, col : j, height : k
        int[][][] tomato = new int[n][m][h];
        
        for (int k = 0; k < h; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    tomato[i][j][k] = In.nextInt();
        solve(tomato);
    }
    
    public static class Point
    {
        int row, col, height;
        public Point(int row, int col, int height)
        {
            this.row = row;
            this.col = col;
            this.height = height;
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