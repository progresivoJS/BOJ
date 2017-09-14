import java.util.*;
import java.io.*;

/**
 * problem 1065
 * 한수
 * https://www.acmicpc.net/problem/1065
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    public static void solve(int n) throws IOException
    {
       int count = 0;
        for (int i = 1; i <= n; i++)
            if (isEqualDifference(String.valueOf(i)))
                count ++;
        System.out.println(count);
    }
    
    private static boolean isEqualDifference(String n)
    {
        if (n.length() <= 2)
            return true;
        int dif = n.charAt(0) - n.charAt(1);
        
        for (int i = 1; i < n.length() - 1; i++)
        {
            int nextDif = n.charAt(i) - n.charAt(i + 1);
            if (dif != nextDif)
                return false;
            else
                dif = nextDif;
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        
        solve(n);
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
