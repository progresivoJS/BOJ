import java.util.*;
import java.io.*;

/**
 * problem 11931
 * 수 정렬하기 4
 * https://www.acmicpc.net/problem/11931
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int range = 1000000;
        int[] array = new int[2 * range + 1];
        
        int n = In.nextInt();
        for (int i = 0; i < n; i++)
            array[In.nextInt() + range]++;
        
        StringBuilder str = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 1)
                str.append(i - range + "\n");
        System.out.println(str);
        
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