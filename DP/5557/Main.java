import java.util.*;
import java.io.*;

/**
 * problem 5557
 * 1학년
 * https://www.acmicpc.net/problem/5557
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    private static int[] seq;
    private static int n;
    private static long[][] cache;
    
    public static void solve(int[] seq)
    {
        Main.seq = seq;
        n = seq.length;
        
        cache = new long[n][21];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        long result = calculate(0, seq[0]);
        System.out.println(result);
    }
    
    /**
     * [0, index]까지의 계산 값이 middleValue일 때, 상근이가 만들 수 있는 올바른 등식의 개수.
     */
    private static long calculate(int index, int middleValue)
    {
        if (middleValue < 0 || middleValue > 20)
            return 0;
        if (index == n-2)
        {
            if (middleValue == seq[n-1])
                return 1;
            else
                return 0;
        }
        
        if (cache[index][middleValue] != -1)
            return cache[index][middleValue];
            
        long result = 0;
        result += calculate(index + 1, middleValue + seq[index + 1]);
        result += calculate(index + 1, middleValue - seq[index + 1]);
        
        return cache[index][middleValue] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        solve(seq);
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