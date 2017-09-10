import java.util.*;
import java.io.*;

/**
 * problem 1016
 * 제곱 ㄴㄴ 수
 * https://www.acmicpc.net/problem/1016
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    private static boolean[] isCounted;
    
    /**
     * 제곱 ㅇㅇ수 를 찾고, 여집합으로 빼자.
     */
    public static int solve(long min, long max)
    {
        isCounted = new boolean[(int)(max - min) + 1];
        
        int count = 0;
        int count2 = 0;
        for (int i = 2; i <= 1000000; i++)
        {
            if (isPrime[i])
            {
                long sqare = i * i;
                // long j;
                // for (j = min; j <= max; j++)
                //     if (j % sqare == 0)
                //         break;
                long j = ((min - 1)/sqare + 1) * sqare;
                if (j < min)
                    System.out.println(j);
                
                for (; j <= max; j += sqare)
                {
                    isCounted[(int)(j - min)] = true;
                }
            }
        }
        
        for (int i = 0; i < isCounted.length; i++)
            if (isCounted[i])
                count ++;
        
        return (int)(max - min + 1) - count;
    }
    
    public static void eratosthenes()
    {
        int M = 1000000;
        isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        int sqrt = (int) Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j <= M; j += i)
                    isPrime[j] = false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        long min = In.nextLong();
        long max = In.nextLong();
        
        eratosthenes();
        
        System.out.println(solve(min, max));
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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
    
        public static long nextLong()
        {
            return Long.parseLong(next());
        }
    }
}