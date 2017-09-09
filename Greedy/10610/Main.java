import java.util.*;
import java.io.*;

/**
 * problem 10610
 * 30
 * https://www.acmicpc.net/problem/10610
 * written by progresivoJS
 */
public class Main
{
    public static void solve(String n) throws IOException
    {
        char[] digits = n.toCharArray();
        
        int digitSum = 0;
        for (int i = 0; i < digits.length; i++)
            digitSum += digits[i] - '0';
            
        int i;
        for (i = 0; i < digits.length; i++)
            if (digits[i] == '0')
                break;
        
        if (i == digits.length || (digitSum % 3 != 0))
        {
            System.out.println(-1);
            return;
        }
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        Arrays.sort(digits);
        for (int j = digits.length - 1; j >= 0; j--)
            out.write(digits[j]);
        out.close();
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        String n = In.next();
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