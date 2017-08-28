import java.util.*;
import java.io.*;
public class Main
{
    public static int solve(int n, int sixValueMin, int eachValueMin)
    {
        int result = 0;
        while (n > 0)
        {
            if (n >= 6)
                result += Math.min(sixValueMin, eachValueMin * 6);
            else
                result += Math.min(sixValueMin, eachValueMin * n);

            n -= 6;
        }

        return result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();

        int sixValueMin = 1000;
        int eachValueMin = 1000;
        for (int i = 0; i < m; i++)
        {
            sixValueMin = Math.min(sixValueMin, In.nextInt());
            eachValueMin = Math.min(eachValueMin, In.nextInt());
        }

        System.out.println(solve(n, sixValueMin, eachValueMin));
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
