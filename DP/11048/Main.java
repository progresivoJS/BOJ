import java.util.*;
import java.io.*;
public class Main
{
    private static int n, m;
    private static int[][] candy;
    private static int[][] cache;

    private static int[] dx = {1, 0, 1};
    private static int[] dy = {0, 1, 1};

    public static int solve(int n, int m, int[][] candy)
    {
        Main.n = n;
        Main.m = m;
        Main.candy = candy;

        cache = new int[n][m];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);

        return move(0, 0);
    }

    private static int move(int row, int col)
    {
        if (row > n-1 || col > m-1)
            return 0;
        if (row == n-1 && col == m-1)
            return candy[row][col];

        if (cache[row][col] != -1)
            return cache[row][col];

        int result = -1;

        for (int i = 0; i < 3; i++)
        {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            result = Math.max(result, candy[row][col] + move(newRow, newCol));
        }

        return cache[row][col] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();

        int[][] candy = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                candy[i][j] = In.nextInt();
        System.out.println(solve(n, m, candy));
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
