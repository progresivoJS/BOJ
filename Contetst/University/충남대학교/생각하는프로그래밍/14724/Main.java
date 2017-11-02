import java.util.*;
import java.io.*;

/**
 * problem 14724
 * 관리자는 누구?
 * https://www.acmicpc.net/problem/14724
 * written by progresivoJS on 2017.11.02
 */
public class Main
{
    private static String[] circles = {"PROBRAIN", "GROW", "ARGOS", "ADMIN",
                                        "ANT", "MOTION", "SPG", "COMON", "ALMIGHTY"};
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int max = 0;
        int index = -1;
        for (int i = 0; i < circles.length; i++)
            for (int j = 0; j < n; j++)
            {
                int value = In.nextInt();
                if (max < value)
                {
                    max = value;
                    index = i;
                }
            }
        System.out.println(circles[index]);
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