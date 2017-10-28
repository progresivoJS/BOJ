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
        int[] array = new int[10001];
        for (int i = 0; i < n; i++)
            array[In.nextInt()]++;
        
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= 10000; i++)
            while (array[i]-- > 0)
                str.append(i + "\n");
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