import java.util.*;
import java.io.*;

/**
 * problem 2240
 * 자두나무
 * https://www.acmicpc.net/problem/2240
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static int t, w;
    private static int[] dropPosition;
    private static int[][][] cache;
    
    public static void solve(int t, int w, int[] dropPosition)
    {
        Main.t = t;
        Main.w = w;
        Main.dropPosition = dropPosition;
        
        cache = new int[t][2][w + 1];
        for (int i = 0; i < cache.length; i++)
            for (int j = 0; j < cache[i].length; j++)
                Arrays.fill(cache[i][j], -1);
        System.out.println(Math.max(eat(0, 0, 0), eat(0, 1, 1)));
    }
    
    /**
     * time 초 때, 자두가 position 에 있고, moveCount 번 움직였을 때, 자두가 먹을 수 있는 자두의 최대 개수.
     */
    private static int eat(int time, int position, int moveCount)
    {
        if (moveCount > w)
            return 0;
        if (time == t - 1)
            return dropPosition[time] == position ? 1 : 0;
        
        if (cache[time][position][moveCount] != -1)
            return cache[time][position][moveCount];
        
        int result = 0;
        int canEatNow = dropPosition[time] == position ? 1 : 0;
        
        // no move
        result = Math.max(result, canEatNow + eat(time + 1, position, moveCount));
        // move
        result = Math.max(result, canEatNow + eat(time + 1, (position == 0) ? 1 : 0, moveCount + 1));
        
        return cache[time][position][moveCount] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int t = In.nextInt();
        int w = In.nextInt();
        int[] dropPosition = new int[t];
        for (int i = 0; i < t; i++)
            dropPosition[i] = In.nextInt() - 1;
        
        solve(t, w, dropPosition);
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
