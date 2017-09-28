import java.util.*;
import java.io.*;

/**
 * problem 4963
 * 섬의 개수
 * https://www.acmicpc.net/problem/4963
 * written by progresivoJS on 2017.09.28
 */
public class Main
{
    private static int m, n;
    private static boolean[][] marked;
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    private static StringBuilder str;
    public static void solve(int[][] map)
    {
        m = map.length;
        n = map[0].length;

        marked = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (!marked[i][j] && map[i][j] == 1)
                {
                    count ++;
                    dfs(map, i, j);
                }
        str.append(count).append('\n');
    }

    private static void dfs(int[][] map, int row, int col)
    {
        marked[row][col] = true;
        for (int i = 0; i < 8; i++)
        {
            int newRow = row + dy[i];
            int newCol = col + dx[i];

            if (newRow >= m || newRow < 0 || newCol >= n || newCol < 0)
                continue;

            if (map[newRow][newCol] == 1 && !marked[newRow][newCol])
                dfs(map, newRow, newCol);
        }
    }

    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            if (n == 0 && m == 0)
                break;
            int[][] map = new int[m][n];
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    map[i][j] = In.nextInt();

            solve(map);
        }

        System.out.println(str.toString());
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
