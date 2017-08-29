import java.util.*;
import java.io.*;
public class Main
{
    private static int n;
    private static int[] digits;
    private static int[] cache;
    private static int M = 1000000;

    public static int solve(String number)
    {
        n = number.length();
        digits = new int[n];

        cache = new int[n];
        Arrays.fill(cache, -1);

        for (int i = 0; i < digits.length; i++)
            digits[i] = number.charAt(i) - '0';

        if (digits.length == 1 && digits[0] == 0)
            return 0;

        return code(0);
    }

    /**
     * index ~ n-1 까지의 string의 해석될 수 있는 경우의 수.
     */
    private static int code(int index)
    {
        if (index == n-1)
            return 1;
        if (index == n)
            return 1;

        if (cache[index] != -1)
            return cache[index];

        int result = 0;
        if (digits[index] > 0 && digits[index+1] > 0)
            result = (result + code(index + 1) % M) % M;

        if (index + 2 < n && digits[index + 2] == 0)
            return cache[index] = result;

        if (digits[index] * 10 + digits[index + 1] <= 26 && digits[index] * 10 + digits[index + 1] >= 10)
            result = (result + code(index + 2) % M) % M;

        return cache[index] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        String number = In.next();
        System.out.println(solve(number));
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
