import java.util.*;
import java.io.*;

/**
 * problem 10989
 * 수 정렬하기 3
 * https://www.acmicpc.net/problem/10989
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        Arrays.sort(array);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
            str.append(array[i]).append('\n');
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