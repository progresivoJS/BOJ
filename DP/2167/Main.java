import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] matrix;
    private static int x, y;
    private static int[][] cache;

    public static int solve(int[][] matrix, int i, int j, int x, int y)
    {
        Main.matrix = matrix;
        Main.x = x;
        Main.y = y;

        int row = matrix.length;
        int col = matrix[0].length;

        cache = new int[row][col];
        for (int m = 0; m < cache.length; m++)
            Arrays.fill(cache[m], -1);

        return sum(i, j);
    }

    private static int sum(int i, int j)
    {
        if (i == x && j == y)
            return matrix[i][j];
        if (i > x || j > y)
            return 0;

        if (cache[i][j] != -1)
            return cache[i][j];

        int result = 0;
        result += matrix[i][j];
        for (int row = i + 1; row <= x; row++)
            result += matrix[row][j];
        for (int col = j + 1; col <= y; col++)
            result += matrix[i][col];
        result += sum(i+1, j+1);
        return cache[i][j] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();

        int[][] matrix = new int[n+1][m+1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                matrix[i][j] = In.nextInt();

        int k = In.nextInt();
        for (int l = 0; l < k; l++)
        {
            int i, j, x, y;
            i = In.nextInt();
            j = In.nextInt();
            x = In.nextInt();
            y = In.nextInt();

            System.out.println(solve(matrix, i, j, x, y));
        }
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
