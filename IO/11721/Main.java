import java.util.*;
import java.io.*;

/**
 * problem 11721
 * 열개씩 끊어 출력하기
 * https://www.acmicpc.net/problem/11721
 * written by progresivoJS
 */
public class Main
{
    public static void solve(String str)
    {
        int length = str.length();
        
        for (int i = 0; i < length; i += 10)
            if (i + 10 < length)
                System.out.println(str.substring(i, i + 10));
            else
                System.out.println(str.substring(i));
    }
    
    public static void main(String[] args)
    {
        In.init();
        String str = In.next();
        
        solve(str);
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