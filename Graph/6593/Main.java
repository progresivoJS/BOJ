import java.util.*;
import java.io.*;

/**
 * problem 6593
 * 상범 빌딩
 * https://www.acmicpc.net/problem/6593
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static final int INF = 987654321;
    private static final int PASSABLE = 1;
    private static final int IMPASSABLE = 2;
    
    private static final int[] dx = {1, 0, -1, 0, 0, 0};
    private static final int[] dy = {0, 1, 0, -1, 0, 0};
    private static final int[] dz = {0, 0, 0, 0, 1, -1};
    
    public static void solve(int H, int R, int C, Point start, Point end, int[][][] map)
    {
        int[][][] dist = new int[H][R][C];
        for (int i = 0; i < H; i++)
            for (int j = 0; j < R; j++)
                Arrays.fill(dist[i][j], INF);
        
        boolean[][][] marked = new boolean[H][R][C];
        // marked[start.height][start.row][start.col] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        
        int time = 0;
        
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                if (v.height == end.height && v.row == end.row && v.col == end.col)
                {
                    System.out.println("Escaped in " + time + " minute(s).");
                    return;
                }
                
                for (int i = 0; i < 6; i++)
                {
                    int newHeight = v.height + dz[i];
                    int newRow = v.row + dy[i];
                    int newCol = v.col + dx[i];
                    
                    if (newHeight >= H || newHeight < 0 || newRow >= R || newRow < 0 || newCol >= C || newCol < 0)
                        continue;
                    
                    if (map[newHeight][newRow][newCol] == PASSABLE)
                    {
                        q.add(new Point(newHeight, newRow, newCol));
                        map[newHeight][newRow][newCol] = IMPASSABLE;
                    }
                }
            }
            time++;
        }
        
        System.out.println("Trapped!");
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        while (true)
        {
            int H = In.nextInt();
            int R = In.nextInt();
            int C = In.nextInt();
            
            if (H == 0 && R == 0 && C == 0) break;
            
            int[][][] map = new int[H][R][C];
            
            Point start = null;
            Point end = null;
            
            for (int i = 0; i < H; i++)
                for (int j = 0; j < R; j++)
                {
                    String str = In.next();
                    for (int k = 0; k < C; k++)
                    {
                        char c = str.charAt(k);
                        if (c == 'S')
                        {
                            start = new Point(i, j, k);
                            map[i][j][k] = PASSABLE;
                        }
                        else if (c == 'E')
                        {
                            end = new Point(i, j, k);
                            map[i][j][k] = PASSABLE;
                        }
                        else if (c == '#')
                        {
                            map[i][j][k] = IMPASSABLE;
                        }
                        else if (c == '.')
                        {
                            map[i][j][k] = PASSABLE;
                        }
                    }
                }
                
            solve(H, R, C, start, end, map);
        }
    }
    
    private static class Point
    {
        int row, col, height;
        public Point(int height, int row, int col)
        {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    
    private static class Node
    {
        
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