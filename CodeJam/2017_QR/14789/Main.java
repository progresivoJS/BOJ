import java.util.*;
import java.io.*;

/**
 * problem 14789
 * Oversized Pancake Flipper (Large)
 * https://www.acmicpc.net/problem/14789
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    private static final int INF = 987654321;
    private static int k;
    private static int n;
    
    private static StringBuilder str;
    
    private static int[] cache;
    
    private static void solve(String row, int k, int caseNumber)
    {
        Main.k = k;
        
        n = row.length();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++)
            if (row.charAt(i) == '+')
                array[i] = true;
                
        int result = flip(array);
        str.append("Case #").append(caseNumber).append(": ").append(result == INF ? "IMPOSSIBLE" : result).append('\n');
    }
    
    private static int flip(boolean[] array)
    {
        int n = array.length;
        
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            if (!array[i])
            {
                if (i + k - 1 >= n)
                    return INF;
                
                for (int index = i; index < i + k; index++)
                    array[index] = !array[index];
                count++;
            }
        }
        
        return count;
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