import java.util.*;
import java.io.*;

/**
 * problem 1743
 * 음식물 피하기
 * https://www.acmicpc.net/problem/1743
 * written by progresivoJS on 2017.10.20
 */
public class Main
{
    private static final int EXIST = 1;
    private static final int MARKED = 2;

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};
    
    private static int m, n;

    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;

        int max = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (map[i][j] == EXIST)
                    max = Math.max(max, dfs(map, i, j));
        System.out.println(max);
    }

    private static int dfs(int[][] map, int row, int col)
    {
        map[row][col] = MARKED;
        int size = 1;
        for (int i = 0; i < 4; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];

            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;
            if (map[newRow][newCol] == EXIST)
                size += dfs(map, newRow, newCol);
        }

        return size;
    }

    public static void main(String[] args)
    {
        In.init();
        int m = In.nextInt();
        int n = In.nextInt();

        int[][] map = new int[m][n];

        int k = In.nextInt();
        while (k-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            map[from][to] = EXIST;
        }

        solve(map);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
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
