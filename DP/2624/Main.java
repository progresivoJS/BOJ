import java.util.*;
import java.io.*;
public class Main
{
    private static int remain, k;
    private static int[] coinValue, coinNumber;

    private static int[][] cache;

    public static int solve(int remain, int k, int[] coinValue, int[] coinNumber)
    {
        Main.remain = remain;
        Main.k = k;
        Main.coinValue = coinValue;
        Main.coinNumber = coinNumber;

        cache = new int[k][remain+1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);

        return exchange(0, remain);
    }

    private static int exchange(int index, int remain)
    {
        if (remain == 0)
            return 1;

        if (remain < 0)
            return 0;

        if (index == k)
            return 0;

        if (cache[index][remain] != -1)
            return cache[index][remain];

        int result = 0;
        for (int i = 0; i <= coinNumber[index]; i++)
            if (remain >= coinValue[index] * i)
                result += exchange(index + 1, remain - coinValue[index] * i);

        return cache[index][remain] = result;
    }

    public static void main(String[] args)
    {
        In.init();

        int remain = In.nextInt();
        int k = In.nextInt();
        int[] coinValue = new int[k];
        int[] coinNumber = new int[k];

        for (int i = 0; i < k; i++)
        {
            coinValue[i] = In.nextInt();
            coinNumber[i] = In.nextInt();
        }

        System.out.println(solve(remain, k, coinValue, coinNumber));
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
