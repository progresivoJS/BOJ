import java.util.*;
import java.io.*;

/**
 * problem 10448
 * 유레카 이론
 * https://www.acmicpc.net/problem/10448
 * written by progresivoJS
 */
public class Main
{
    private static int[] triangle;

    public static int solve(int n)
    {
        for (int i = 0; i < triangle.length; i++)
            for (int j = 0; j < triangle.length; j++)
            {
                if (Arrays.binarySearch(triangle, n - triangle[i] - triangle[j]) >= 0)
                    return 1;
            }

        return 0;
    }

    public static void triangle()
    {
        triangle = new int[44];
        for (int i = 0; i < triangle.length; i++)
            triangle[i] = (i + 1) * (i + 2) / 2;
    }

    public static void main(String[] args) throws IOException
    {
        In.init();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        triangle();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            out.write(String.valueOf(solve(In.nextInt())));
            out.write("\n");
        }
        out.close();
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
