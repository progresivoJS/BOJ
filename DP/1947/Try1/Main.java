import java.util.*;
import java.io.*;

/**
 * problem 1947
 * 선물 전달
 * 이 방식은 TSP 풀 때 n!을 n * 2^n 으로 줄이는 방법..
 * 이 문제는 n이 100만까지이므로 적합하지않다.
 * https://www.acmicpc.net/problem/1947
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static int[][] cache;
    private static int M = 1000000000;
    
    public static int solve(int n)
    {
        Main.n = n;
        cache = new int[n][1 << n];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        return give(0, 0);
    }
    
    private static int give(int index, int isGiven)
    {
        if (isGiven == (1 << n) - 1)
            return 1;
            
        if (cache[index][isGiven] != -1)
            return cache[index][isGiven];
        
        int result = 0;
        for (int i = 0; i < n; i++)
            if (index != i && ((isGiven & (1 << i)) == 0))
            {
                isGiven += (1 << i);
                result = (result + give(index + 1, isGiven)) % M;
                isGiven -= (1 << i);
            }
        
        return cache[index][isGiven] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        // int n = In.nextInt();
        // System.out.println(solve(n));
        
        for (int i = 1; i < 100; i++)
            System.out.println(solve(i));
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
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}