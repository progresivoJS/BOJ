import java.util.*;
import java.io.*;
public class Main
{
    private static int[] seq;
    public static int solve(int k, int[] seq)
    {
        Main.seq = seq;
        Arrays.sort(seq);
        return seq[k-1];
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();

        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();

        System.out.println(solve(k, seq));
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
