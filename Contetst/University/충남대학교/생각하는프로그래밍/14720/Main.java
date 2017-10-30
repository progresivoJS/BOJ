import java.util.*;
import java.io.*;

/**
 * problem 14720
 * 우유 축제
 * https://www.acmicpc.net/problem/14720
 * written by progresivoJS on 2017.10.30
 */
public class Main
{
    private static int n;
    private static int[][] cache;
    
    private static void solve(int[] store)
    {
        n = store.length;
        
        cache = new int[n][3];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
        
        int result = drink(store, 0, 0);
        System.out.println(result);
    }
    
    // index 번 가게부터 milkToDrink 번 우유를 먹어야 할 때,
    // 영학이가 먹을 수 있는 우유의 최대 갯수.
    private static int drink(int[] store, int index, int milkToDrink)
    {
        if (index >= n)
            return 0;
        
        if (cache[index][milkToDrink] != -1)
            return cache[index][milkToDrink];
        
        int result = 0;
        
        // 사먹을 수 있는 경우
        if (store[index] == milkToDrink)
        {
            // 사먹는 경우
            result = Math.max(result, 1 + drink(store, index + 1, (milkToDrink + 1) % 3));
            // 사먹지 않는 경우
            result = Math.max(result, drink(store, index + 1, milkToDrink));
        }
        // 못 사먹는 경우
        else
        {
            result = drink(store, index + 1, milkToDrink);
        }
        
        return cache[index][milkToDrink] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] store = new int[n];
        for (int i = 0; i < n; i++)
            store[i] = In.nextInt();
        solve(store);
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