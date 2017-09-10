import java.util.*;
import java.io.*;

/**
 * problem 1456
 * 거의 소수
 * https://www.acmicpc.net/problem/1456
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    
    public static int solve(long n, long m)
    {
        int count = 0;
        
        int sqrt = (int) Math.sqrt(m);
        for (int i = 2; i <= sqrt; i++)
        {
            if (isPrime[i])
            {
                double value = i;
                while (true)
                {
                    value *= i;
                    if (value <= 0)
                        break;
                    else if (value < n)
                        continue;
                    else if (value > m)
                        break;
                    else
                    {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    public static void eratosthenes()
    {
        int M = 10000001;
        isPrime = new boolean[M];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        
        int sqrt = (int) Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
        {
            if (isPrime[i])
            {
                for (int j = i * i; j < M; j += i)
                {
                    isPrime[j] = false;
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        eratosthenes();
        
        long n = In.nextLong();
        long m = In.nextLong();
        System.out.println(solve(n, m));
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