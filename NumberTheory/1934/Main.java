import java.util.*;
import java.io.*;

/**
 * problem 1934
 * 최소공배수
 * https://www.acmicpc.net/problem/1934
 * written by progresivoJS on 2017.09.19
 */
public class Main
{
    public static int LCM(int a, int b)
    {
        return a * b / GCM(a, b);
    }
    
    public static int GCM(int a, int b)
    {
        if (b == 0)
            return a;
        return GCM(b, a % b);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < test; i++)
        {
            int a = In.nextInt();
            int b = In.nextInt();
            str.append(LCM(a, b)).append('\n');
        }
        
        System.out.println(str.toString());
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