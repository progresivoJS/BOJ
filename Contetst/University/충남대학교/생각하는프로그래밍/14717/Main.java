import java.util.*;
import java.io.*;

/**
 * problem 14717
 * 앉았다
 * https://www.acmicpc.net/problem/14717
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int a = In.nextInt();
        int b = In.nextInt();
        
        if (a == b)
        {
            double prob = 1 - ((18 - (a - 1) * 2) / 18.0) * (1.0 / ((10 - a) * 2 - 1));
            System.out.printf("%.3f", prob);
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