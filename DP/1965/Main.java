import java.util.*;
import java.io.*;

/**
 * problem 1965
 * 상자넣기
 * https://www.acmicpc.net/problem/1965
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static int[] boxes;
    private static int[] cache;
    public static int solve(int[] boxes)
    {
        Main.boxes = boxes;
        Main.n = boxes.length;
        
        cache = new int[n+1];
        Arrays.fill(cache, -1);

        return pushBox(-1);
    }

    private static int pushBox(int index)
    {
        int cacheIndex = index + 1;
        if (cache[cacheIndex] != -1)
            return cache[cacheIndex];
        int result = 0;
        for (int next = index + 1; next < n; next++)
            if (index == -1 || boxes[index] < boxes[next])
                result = Math.max(result, 1 + pushBox(next));

        return cache[cacheIndex] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] boxes = new int[n];
        for (int i = 0; i < n; i++)
            boxes[i] = In.nextInt();
        System.out.println(solve(boxes));
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