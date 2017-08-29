import java.util.*;
import java.io.*;
public class Main
{
    private static int[][][] cache;
    private static int[][] matrix;
    private static int n;

    public static void solve(int[][] matrix)
    {
        Main.matrix = matrix;
        n = matrix.length;

        cache = new int[n][3][2];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);

        int min = 987654321;
        int max = -1;

        for (int i = 0; i < 3; i++)
        {
            min = Math.min(min, jump(0, i, 0));
            max = Math.max(max, jump(0, i, 1));
        }

        System.out.println(max + " " + min);
    }

    private static int jump(int row, int col, int minMax)
    {
        if (row == n)
            return 0;

        if (cache[row][col][minMax] != -1)
            return cache[row][col][minMax];

        int result = -987654321;

        // min
        if (minMax == 0)
        {
            result = 987654321;

            // straight jump
            result = Math.min(result, matrix[row][col] + jump(row + 1, col, minMax));

            if (col == 0)
            {
                result = Math.min(result, matrix[row][col] + jump(row + 1, 1, minMax));
            }
            else if (col == 1)
            {
                result = Math.min(result, matrix[row][col] + jump(row + 1, 0, minMax));
                result = Math.min(result, matrix[row][col] + jump(row + 1, 2, minMax));
            }
            else if (col == 2)
            {
                result = Math.min(result, matrix[row][col] + jump(row + 1, 1, minMax));
            }
        }
        // max
        else
        {
            result = -1;

            // straight jump
            result = Math.max(result, matrix[row][col] + jump(row + 1, col, minMax));

            if (col == 0)
            {
                result = Math.max(result, matrix[row][col] + jump(row + 1, 1, minMax));
            }
            else if (col == 1)
            {
                result = Math.max(result, matrix[row][col] + jump(row + 1, 0, minMax));
                result = Math.max(result, matrix[row][col] + jump(row + 1, 2, minMax));
            }
            else if (col == 2)
            {
                result = Math.max(result, matrix[row][col] + jump(row + 1, 1, minMax));
            }
        }

        return cache[row][col][minMax] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] matrix = new int[n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                matrix[i][j] = In.nextInt();
        solve(matrix);
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
