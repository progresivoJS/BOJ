import java.util.*;
import java.io.*;

/**
 * problem 14717
 * 앉았다
 * https://www.acmicpc.net/problem/14717
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int a = In.nextInt();
        int b = In.nextInt();
        Pair myPair = new Pair(a, b);
        
        // 내가 이기는 경우의 수.
        int count = 0;
        
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
        {
            deck.add(i);
            deck.add(i);
        }
        deck.remove(new Integer(a));
        deck.remove(new Integer(b));
        
        for (int i = 0; i < deck.size(); i++)
            for (int j = i + 1; j < deck.size(); j++)
            {
                Pair otherPair = new Pair(deck.get(i), deck.get(j));
                if (myPair.compareTo(otherPair) > 0)
                    count++;
            }
        
        System.out.printf("%.3f", count / 153.0);
    }
    
    private static class Pair implements Comparable<Pair>
    {
        private int a, b;
        public Pair(int a, int b)
        {
            this.a = a;
            this.b = b;
        }
        
        public int compareTo(Pair other)
        {
            if (this.a == this.b)
            {
                if (other.a == other.b)
                    return this.a - other.a;
                else
                    return 1;
            }
            else
            {
                if (other.a == other.b)
                    return -1;
                else
                    return (this.a + this.b) % 10 - (other.a + other.b) % 10;
            }
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