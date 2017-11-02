import java.util.*;
import java.io.*;

/**
 * problem 14722
 * 우유 도시
 * https://www.acmicpc.net/problem/14722
 * written by progresivoJS on 2017.11.01
 */
public class Main
{
    private static int[][] map;
    private static int[][][] cache;
    private static int n;

    private static void solve(int[][] map)
    {
        Main.map = map;
        Main.n = map.length;

        cache = new int[n][n][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(cache[i][j], -1);

        System.out.println(drink(0, 0, 0));
    }

    // index 우유를 마셔야할 때, row, col 에서 시작 할 때 마실 수 있는 최대 우유 수.
    private static int drink(int row, int col, int index)
    {
        if (row >= n || col >= n)
            return 0;

        if (cache[row][col][index] != -1)
            return cache[row][col][index];

        int result = 0;
        // 우유를 마실 수 있음.
        if (map[row][col] == index)
        {
            // 우유를 사마심
            result = Math.max(result, 1 + drink(row + 1, col, (index + 1) % 3));
            result = Math.max(result, 1 + drink(row, col + 1, (index + 1) % 3));

            // 우유를 안사마심
            result = Math.max(result, drink(row + 1, col, index));
            result = Math.max(result, drink(row, col + 1, index));
        }
        // 우유를 못마실 떄.
        else
        {
            result = Math.max(result, drink(row + 1, col, index));
            result = Math.max(result, drink(row, col + 1, index));
        }

        return cache[row][col][index] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map[i][j] = In.nextInt();

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
