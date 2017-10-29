import java.util.*;
import java.io.*;

/**
 * problem 2959
 * 거북이
 * https://www.acmicpc.net/problem/2959
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int[] array = new int[4];
        array[0] = In.nextInt();
        array[1] = In.nextInt();
        array[2] = In.nextInt();
        array[3] = In.nextInt();
        
        Arrays.sort(array);
        
        System.out.println(array[0] * array[2]);
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