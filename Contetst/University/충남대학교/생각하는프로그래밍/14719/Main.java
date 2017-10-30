import java.util.*;
import java.io.*;

/**
 * problem 14719
 * 빗물
 * https://www.acmicpc.net/problem/14719
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int H = In.nextInt();
        int W = In.nextInt();
        int[] height = new int[W];
        for (int i = 0; i < W; i++)
            height[i] = In.nextInt();
        
        int drop = 0;
        int pivot = 0;
        int min = height[pivot];
        for (int i = 1; i < W; i++)
        {
            drop += (i - pivot - 1) * (Math.min(height[i], height[pivot]) - min);
            min = Math.min(height[pivot], height[i]);
            if (height[pivot] < height[i])
                pivot = i;
        }
        
        System.out.println(drop);
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