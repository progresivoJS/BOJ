import java.util.*;
import java.io.*;

/**
 * problem 2098
 * TSP
 * DP version
 * https://www.acmicpc.net/problem/2098
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static int[][] distance;

    private static int[][] cache;

    public static int solve(int n, int[][] distance)
    {
        Main.n = n;
        Main.distance = distance;

        cache = new int[n][(1 << n)];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                cache[i][j] = -1;

        return shortestPath(0, 1);
    }

    // here 부터 남은 point를 다 도는데 걸리는 길이의 최솟값.
    private static int shortestPath(int here, int visited)
    {
        if (visited == (1 << n) - 1)
            return distance[here][0];

        if (cache[here][visited] != -1)
            return cache[here][visited];

        int result = 987654321;
        for (int next = 0; next < n; next++)
        {
            if (((visited & (1 << next)) == 0) && distance[here][next] != 0)
            {
                int candidate = distance[here][next] + shortestPath(next, visited + (1 << next));
                result = Math.min(result, candidate);
            }
        }

        return cache[here][visited] = result;
    }

    public static void main(String[] args)
    {
        In.init();

        int n = In.nextInt();

        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                distance[i][j] = In.nextInt();

        System.out.println(solve(n, distance));
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
