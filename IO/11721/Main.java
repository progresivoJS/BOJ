import java.util.*;
import java.io.*;

/**
 * problem 11720
 * 숫자의 합
 * https://www.acmicpc.net/problem/11720
 * written by progresivoJS
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        String number = In.next();
        
        int result = 0;
        for (int i = 0; i < n; i++)
            result += number.charAt(i) - '0';
            
        System.out.println(result);
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