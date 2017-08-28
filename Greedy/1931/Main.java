import java.util.*;
import java.io.*;
public class Main
{
    public static int solve(Pair[] duration)
    {
        Arrays.sort(duration);
        int team = 0;
        int currentTime = 0;

        for (int i = 0; i < duration.length; i++)
            if (currentTime <= duration[i].left)
            {
                currentTime = duration[i].right;
                team++;
            }

        return team;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Pair[] duration = new Pair[n];
        for (int i = 0; i < n; i++)
            duration[i] = new Pair(In.nextInt(), In.nextInt());
        System.out.println(solve(duration));
    }

    private static class Pair implements Comparable<Pair>
    {
        private int left, right;

        public Pair(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Pair comp)
        {
            if (this.right == comp.right)
                return this.left - comp.left;
            return this.right - comp.right;
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
