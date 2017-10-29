import java.util.*;
import java.io.*;

/**
 * problem 3047
 * ABC
 * https://www.acmicpc.net/problem/3047
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int[] array = new int[3];
        
        array[0] = In.nextInt();
        array[1] = In.nextInt();
        array[2] = In.nextInt();
        
        Arrays.sort(array);
        
        String word = In.next();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++)
            sb.append(array[word.charAt(i) - 'A']).append(' ');
        System.out.println(sb);
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