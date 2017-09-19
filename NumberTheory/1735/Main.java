import java.util.*;
import java.io.*;

/**
 * problem 1735
 * 분수 합
 * https://www.acmicpc.net/problem/1735
 * written by progresivoJS on 2017.09.19
 */
public class Main
{
    public static void solve(int a, int b, int c, int d)
    {
        int numerator = a * d + b * c;
        int denominator =  b * d;
        
        int GCM = GCM(numerator, denominator);
        System.out.println(numerator / GCM + " " + denominator / GCM);
    }
    
    private static int GCM(int a, int b)
    {
        if (b == 0)
            return a;
        return GCM(b, a % b);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int a = In.nextInt();
        int b = In.nextInt();
        int c = In.nextInt();
        int d = In.nextInt();
        
        solve(a, b, c, d);
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