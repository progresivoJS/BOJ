import java.util.*;
import java.io.*;

/**
 * problem 1181
 * 단어 정렬
 * https://www.acmicpc.net/problem/1181
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        String[] array = new String[n];
        for (int i = 0; i < n; i++)
            array[i] = In.next();
        Arrays.sort(array, new Comparator<String>() {
            public int compare(String a, String b)
            {
                int diff = a.length() - b.length();
                if (diff == 0)
                    return a.compareTo(b);
                else
                    return diff;
            }
        });
        
        StringBuilder str = new StringBuilder();
        String temp = "";
        for (int i = 0; i < n; i++)
        {
            if (!temp.equals(array[i]))
                str.append(array[i] +"\n");
            temp = array[i];
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