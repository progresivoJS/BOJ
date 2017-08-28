import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] cache;
    private static int[][] stickers;
    private static int n;
    public static int solve(int[][] stickers)
    {
        Main.stickers = stickers;
        n = stickers[0].length;
        cache = new int[2][n];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);

        return Math.max(cut(0, 0), cut(1, 0));
    }

    private static int cut(int row, int col)
    {
        if (row >= 2)
            return 0;
        if (col == n-1)
            return stickers[row][col];
        if (col > n-1)
            return 0;

        if (cache[row][col] != -1)
            return cache[row][col];

        int result = 0;
        if (row == 0)
        {
            result = Math.max(result, stickers[row][col] + cut(1, col + 1));
            result = Math.max(result, stickers[row][col] + cut(1, col + 2));
            result = Math.max(result, stickers[row][col] + cut(0, col + 2));
        }
        else
        {
            result = Math.max(result, stickers[row][col] + cut(0, col + 1));
            result = Math.max(result, stickers[row][col] + cut(0, col + 2));
            result = Math.max(result, stickers[row][col] + cut(1, col + 2));
        }

        return cache[row][col] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int[][] stickers = new int[2][n];
            for (int row = 0; row < 2; row++)
                for (int col = 0; col < n; col++)
                    stickers[row][col] = In.nextInt();
            System.out.println(solve(stickers));
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

