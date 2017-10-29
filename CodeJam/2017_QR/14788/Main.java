import java.util.*;
import java.io.*;

/**
 * problem 14788
 * Oversized Pancake Flipper (Small)
 * https://www.acmicpc.net/problem/14788
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    private static final int INF = 987654321;
    private static int k;
    private static int n;
    
    private static StringBuilder str;
    
    private static void solve(String row, int k, int caseNumber)
    {
        Main.k = k;
        
        n = row.length();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++)
            if (row.charAt(i) == '+')
                array[i] = true;
        
        int result = flip(array, 0);
        str.append("Case #").append(caseNumber).append(": ").append(result == INF ? "IMPOSSIBLE" : result).append('\n');
    }
    
    private static int flip(boolean[] array, int start)
    {
        if (areHappy(array))
            return 0;
            
        if (start + k - 1 >= n)
            return INF;
        
        int result = INF;
        
        result = Math.min(result, flip(array, start + 1));
        
        for (int i = start; i < start + k; i++)
            array[i] = !array[i];
        result = Math.min(result, 1 + flip(array, start + 1));
        for (int i = start; i < start + k; i++)
            array[i] = !array[i];
        
        return result;
    }
    
    private static boolean areHappy(boolean[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (!array[i])
                return false;
        }
        return true;
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        
        int test = In.nextInt();
        for (int testNumber = 1; testNumber <= test; testNumber++)
        {
            String row = In.next();
            int k = In.nextInt();
            
            solve(row, k, testNumber);
        }
        
        System.out.println(str);
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