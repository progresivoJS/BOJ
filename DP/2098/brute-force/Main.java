import java.util.*;
import java.io.*;

/**
 * problem 2098
 * TSP
 * brute-force version
 * https://www.acmicpc.net/problem/2098
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static boolean[] visited;
    private static int[][] distance;
    private static LinkedList<Integer> path;

    public static int solve(int n, int[][] distance)
    {
        Main.n = n;
        visited = new boolean[n];
        Main.distance = distance;

        path = new LinkedList<>();

        path.add(0);
        visited[0] = true;

        return shortestPath(path, distance, visited, 0);
    }

    private static int shortestPath(LinkedList<Integer> path, int[][] distance, boolean[] visited, int currentLength)
    {
        if (path.size() == n)
            return currentLength + distance[path.getLast()][path.getFirst()];

        int result = 987654321;
        for (int next = 0; next < n; next++)
        {
            if (!visited[next])
            {
                int here = path.getLast();

                path.add(next);
                visited[next] = true;

                int cand = shortestPath(path, distance, visited, currentLength + distance[here][next]);
                result = Math.min(result, cand);

                visited[next] = false;
                path.removeLast();
            }
        }

        return result;
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
