import java.util.*;
import java.io.*;

/**
 * problem 2960
 * RESETO 에라토스테네스의 체
 * https://www.acmicpc.net/problem/2960
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    public static int solve(int n, int k)
    {
        isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // 오리지널 에라토스테네스의 체와 조금 다르므로 이부분도 없어져야함.
        // 왜냐면 그 자신도 지워야하므로.
//        int sqrt = (int) Math.sqrt(n); 
        for (int i = 2; i <= n; i++)
        {
            if (isPrime[i])
            {
                k--;
                if (k == 0)
                    return i;

                for (int j = i * i; j <= n; j += i)
                {
                    // 이부분 체크해야만 한다.
                    // 원래의 에라토스테네스의 체에선 isPrime 값을 결정하는 것이 중요해서
                    // 신경 쓸 필요 없었지만, 여기선 k의 값을 체크해야 하므로 반드시 체크.
                    if (isPrime[j])
                    {
                        k--;
                        isPrime[j] = false;
                        if (k == 0)
                            return j;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();

        System.out.println(solve(n, k));
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
