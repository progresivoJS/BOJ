import java.util.*;
import java.io.*;

/**
 * problem 14790
 * Tidy Numbers (Small)
 * https://www.acmicpc.net/problem/14790
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        StringBuilder str = new StringBuilder();
        for (int testCase = 1; testCase <= test; testCase++)
        {
            String s = In.next();
            char[] array = s.toCharArray();
            int n = array.length;
            for (int i = n - 1; i >= 1; i--)
            {
                if (array[i] < array[i - 1])
                {
                    for (int j = i; j < n; j++)
                        array[j] = '9';
                    array[i - 1] -= 1;
                }
            }
            
            str.append("Case #").append(testCase).append(": ");
            if (array[0] != '0')
                str.append(array[0]);
            for (int i = 1; i < n; i++)
                str.append(array[i]);
            str.append('\n');
        }
        
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