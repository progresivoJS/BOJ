import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] matrix;
    private static int x, y;

    public static int solve(int[][] matrix, int i, int j, int x, int y)
    {
        Main.matrix = matrix;
        Main.x = x;
        Main.y = y;
        return sum(i, j);
    }

    private static void makeCumulative(int[][] matrix)
    {
        for (int i = 1; i < matrix.length; i++)
            for (int j = 1; j < matrix[i].length; j++)
            {
                if (j == 1)
                    matrix[i][1] += matrix[i-1][1];
                else if (i == 1)
                    matrix[1][j] += matrix[1][j-1];
                else
                    matrix[i][j] += matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
            }
    }

    private static int sum(int i, int j)
    {
        int result = matrix[x][y];
        result -= matrix[i-1][y];
        result -= matrix[x][j-1];
        result += matrix[i-1][j-1];
        return result;
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

        makeCumulative(matrix);

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

/**
 * use accumulative matrix.
 */
