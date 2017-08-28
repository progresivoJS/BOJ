import java.util.*;
import java.io.*;
public class Main
{
    public static int solve(int[] rope)
    {
        Arrays.sort(rope);

        int weight;
        int result = 0;
        for (int i = rope.length-1; i >= 0; i--)
        {
            weight = rope[i];
            result = Math.max(result, weight * (rope.length - i));
        }

        return result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] rope = new int[n];
        for (int i = 0; i < n; i++)
            rope[i] = In.nextInt();

        System.out.println(solve(rope));
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
