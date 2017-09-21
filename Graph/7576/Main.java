import java.util.*;
import java.io.*;

/**
 * problem 7576
 * 토마토
 * https://www.acmicpc.net/problem/7576
 * written by progresivoJS on 2017.09.21
 */
public class Main
{   
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int m, n;
    private static int totalTomato;

    public static void solve(int[][] tomato)
    {
        m = tomato.length;
        n = tomato[0].length;

        LinkedList<Point> startList = new LinkedList<>();
        totalTomato = 0;
        for (int i = 0; i < tomato.length; i++)
            for (int j = 0; j < tomato[i].length; j++)
            {
                if (tomato[i][j] != -1)
                    totalTomato++;
                if (tomato[i][j] == 1)
                    startList.add(new Point(i, j));
            }

        bfs(tomato, startList);
    }

    private static void bfs(int[][] tomato, LinkedList<Point> startList)
    {
        Queue<Point> q = new LinkedList<>();
        q.addAll(startList);

        int count = -1;
        int totalOfOne = startList.size();

        while (!q.isEmpty())
        {
            count ++;
            if (areRipened(totalOfOne))
            {
                System.out.println(count);
                return;
            }

            int size = q.size();
            while (size-- > 0)
            {
                Point v = q.poll();
                int row = v.x;
                int col = v.y;

                for (int i = 0; i < 4; i++)
                {
                    int newRow = row + dy[i];
                    int newCol = col + dx[i];

                    if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                        continue;
                    if (tomato[newRow][newCol] == -1)
                        continue;

                    if (tomato[newRow][newCol] == 0)
                    {
                        totalOfOne ++;
                        tomato[newRow][newCol] = 1;
                        q.add(new Point(newRow, newCol));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean areRipened(int totalOfOne)
    {
        return totalTomato == totalOfOne;
    }

    public static void main(String[] args)
    {
        In.init();
        int col = In.nextInt();
        int row = In.nextInt();
        int[][] tomato = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                tomato[i][j] = In.nextInt();
        solve(tomato);
    }

    private static class Point
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