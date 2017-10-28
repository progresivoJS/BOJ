import java.util.*;
import java.io.*;

/**
 * problem 1427
 * 소트인사이드
 * https://www.acmicpc.net/problem/1427
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        String s = In.next();
        char[] array = s.toCharArray();
        Arrays.sort(array);
        
        for (int i = array.length - 1; i >= 0; i--)
            System.out.print(array[i]);
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