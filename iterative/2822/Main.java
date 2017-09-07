import java.util.*;
import java.io.*;

/**
 * problem 2822
 * 점수 계산
 * https://www.acmicpc.net/problem/2822
 * written by progresivoJS
 */
public class Main
{
    public static void solve(int[] scores)
    {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = 0; i < scores.length; i++)
            tree.put(scores[i], i);
        
        int sum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++)
        {
            int key = tree.lastKey();
            sum += key;
            list.add(tree.get(key) + 1);
            tree.remove(key);
        }
        
        System.out.println(sum);
        Integer[] indexes = list.toArray(new Integer[list.size()]);
        Arrays.sort(indexes);
        for (Integer number : indexes)
            System.out.print(number + " ");
    }
    
    public static void main(String[] args)
    {
        In.init();
        int[] scores = new int[8];
        for (int i = 0 ; i < 8; i++)
            scores[i] = In.nextInt();
        solve(scores);
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
