import java.util.*;
import java.io.*;

/**
 * problem 1715
 * 카드 정렬하기
 * https://www.acmicpc.net/problem/1715
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = In.nextInt();
        for (int i = 0; i < n; i++)
            pq.add(In.nextInt());

        int result = 0;
        while (!pq.isEmpty())
        {
            if (pq.size() == 1)
                break;
            int newDeck = pq.poll() + pq.poll();
            result += newDeck;
            pq.add(newDeck);
        }

        System.out.println(result);
    }

    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
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
