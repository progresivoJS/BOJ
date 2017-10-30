import java.util.*;
import java.io.*;

/**
 * problem 14723
 * 이산수학 과제
 * https://www.acmicpc.net/problem/14723
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        int sum = 0;
        int count = 1;
        while (n > sum)
            sum += (count++);
        count--;
        
        int order = n - (sum - count);
        System.out.println(1 + count - order + " " + order);
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