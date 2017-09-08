import java.util.*;
import java.io.*;

/**
 * problem 1720
 * Tile code 타일 코드
 * not solved.
 * https://www.acmicpc.net/problem/1720
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static int[] cache;
    
    public static int solve(int n)
    {
        Main.n = n;
        cache = new int[31];
        Arrays.fill(cache, -1);
        
        if (n == 1) return 1;
        if (n == 2) return 3;
        
        if (n % 2 == 1)
            return tiling(n) - tiling((n-1)/2) / 2;
        
        return tiling(n) - tiling(n/2)/2 - tiling((n-1)/2)/2;
    }
    
    private static int tiling(int index)
    {
        if (index == 0)
            return 1;
        if (index < 0)
            return 0;
        
        if (cache[index] != -1)
            return cache[index];
        
        int result = 0;
        result = tiling(index - 1) + 2 * tiling(index - 2);
        
        return cache[index] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        System.out.println(solve(n));
        
        for (int i = 1; i <= 30; i++)
        {
            System.out.println("i : " + i + " answer : " + solve(i));
        }
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