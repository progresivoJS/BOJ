import java.util.*;
import java.io.*;

/**
 * problem 2750
 * 수 정렬하기
 * https://www.acmicpc.net/problem/2750
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        In.init();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = In.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = In.nextInt();
        
        for (int i : a)
        {
            out.write(String.valueOf(i));
            out.newLine();
        }
        out.close();
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