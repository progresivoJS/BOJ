import java.util.*;
import java.io.*;

/**
 * problem 4195
 * 친구 네트워크
 * https://www.acmicpc.net/problem/4195
 * written by progresivoJS on 2017.10.02
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int edge = In.nextInt();
            
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