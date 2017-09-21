import java.util.*;
import java.io.*;

/**
 * problem 7562
 * 나이트의 이동
 * Point y, x 순서 잘 생각하자.
 * https://www.acmicpc.net/problem/7562
 * written by progresivoJS on 2017.09.21
 */
public class Main
{
    private static final int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private static final int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static void solve(int n, Point from, Point to)
    {
        boolean[][] marked = new boolean[n][n];
        marked[from.y][from.x] = true;
        
        bfs(marked, from, to);
    }
    
    private static void bfs(boolean[][] marked, Point from, Point to)
    {
        int n = marked.length;
        Queue<Point> q = new LinkedList<>();
        q.add(from);
        
        int count = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                
                if (v.x == to.x && v.y == to.y)
                {
                    System.out.println(count);
                    return;
                }
            
                for (int i = 0; i < 8; i++)
                {
                    int newRow = v.y + dy[i];
                    int newCol = v.x + dx[i];
                    if (newRow >= n || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    if (!marked[newRow][newCol])
                    {
                        marked[newRow][newCol] = true;
                        q.add(new Point(newCol, newRow));
                    }
                }
            }
            
            count ++;
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int I = In.nextInt();
            Point current = new Point(In.nextInt(), In.nextInt());
            Point destination = new Point(In.nextInt(), In.nextInt());
            
            solve(I, current, destination);
        }
    }
    
    public static class Point
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
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