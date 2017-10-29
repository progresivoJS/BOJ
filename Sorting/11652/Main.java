import java.util.*;
import java.io.*;

/**
 * problem 11652
 * 카드
 * https://www.acmicpc.net/problem/11652
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        TreeMap<Long, Integer> map = new TreeMap<>();
        
        int n = In.nextInt();
        while (n-- > 0)
        {
            long key = Long.parseLong(In.next());
            if (!map.containsKey(key))
                map.put(key, 1);
            else
                map.put(key, map.get(key) + 1);
        }
        
        long index = -1;
        int max = 0;
        for (long key : map.keySet())
        {
            int value = map.get(key);
            if (value > max)
            {
                index = key;
                max = value;
            }
            else if (value == max)
            {
                index = index < key ? index : key;
            }
        }
        
        System.out.println(index);
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