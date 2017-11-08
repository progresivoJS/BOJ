import java.util.*;
import java.io.*;

/**
 * problem 6589
 * Heavy Cargo
 * https://www.acmicpc.net/problem/6589
 * written by progresivoJS on 2017.11.08
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        while (true)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            
            while (m-- > 0)
            {
                
            }
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