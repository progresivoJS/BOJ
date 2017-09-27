import java.util.*;
import java.io.*;

/**
 * problem 10505
 * Wheels
 * https://www.acmicpc.net/problem/10505
 * written by progresivoJS on 2017.09.27
 */
public class Main
{
    private static int n;
    private static boolean[] isCounterClockWise;
    private static boolean[] marked;
    private static StringBuilder str;

    public static void solve(Circle[] circles)
    {
        n = circles.length;
        isCounterClockWise = new boolean[n];
        marked = new boolean[n];

        // touched[i][j] = i번째 휠과 j번째 휠이 외접 해 있습니까?
        boolean[][] touched = new boolean[n][n];

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                touched[i][j] = touched[j][i] = areOutTouched(circles[i], circles[j]);

        dfs(touched, 0);


        int totalToque = circles[0].r * 1;
        for (int i = 0; i < n; i++)
        {
            if (!marked[i])
            {
                str.append("not moving").append('\n');
            }
            else
            {
                int GCM = GCD(totalToque, circles[i].r);
                int denominator = circles[i].r / GCM; // 분모
                int numerator = totalToque / GCM; // 분자

                str.append(numerator);
                if (denominator != 1)
                    str.append('/').append(denominator);
                str.append(isCounterClockWise[i] ? " counterclockwise" : " clockwise");
                str.append('\n');
            }
        }
    }

    private static void dfs(boolean[][] touched, int v)
    {
        marked[v] = true;
        for (int w = 0; w < n; w++)
            if (touched[v][w])
            {
                if (!marked[w])
                {
                    isCounterClockWise[w] = !isCounterClockWise[v];
                    dfs(touched, w);
                }
            }
    }

    private static boolean areOutTouched(Circle a, Circle b)
    {
        int dx = Math.abs(a.x - b.x);
        int dy = Math.abs(a.y - b.y);

        return dx * dx + dy * dy == (a.r + b.r) * (a.r + b.r);
    }

    private static int GCD(int a, int b)
    {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }

    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();

        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            Circle[] circles = new Circle[n];

            for (int i = 0; i < n; i++)
                circles[i] = new Circle(In.nextInt(), In.nextInt(), In.nextInt());

            solve(circles);
        }

        System.out.println(str.toString());
    }

    public static class Circle
    {
        int x, y, r;
        public Circle(int x, int y, int r)
        {
            this.x = x;
            this.y = y;
            this.r = r;
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
