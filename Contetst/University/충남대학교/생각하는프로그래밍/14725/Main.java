import java.util.*;
import java.io.*;

/**
 * problem 14725
 * 개미굴
 * https://www.acmicpc.net/problem/14725
 * written by progresivoJS on 2017.11.02
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        TreeMap<String, PriorityQueue<String>> map = new HashMap<>();
        
        for (int i = 0; i < n; i++)
        {
            int m = In.nextInt();
            String key = In.next();
            
            for (int j = 0; j < m - 1; j++)
            {
                String value = In.next();
                if (!map.contains(key))
                    map.put(key, new PriorityQueue<String>());
                map.get(key).add(value);
                
                key = value;
            }
        }
        
        for ()
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