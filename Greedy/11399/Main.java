import java.util.*;
import java.io.*;
public class Main
{
    /**
     * 가장 시간이 적게 걸리는 사람부터 먼저 choice.
     */
    public static int solve(int[] delay)
    {
        Arrays.sort(delay);
        int n = delay.length;
        int time = 0;
        int totalTime = 0;
        for (int i = 0; i < n; i++)
        {
            time += delay[i];
            totalTime += time;
        }
        return totalTime;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] delay = new int[n];
        for (int i = 0; i < n; i++)
            delay[i] = In.nextInt();
        System.out.println(solve(delay));
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            // try
            // {
            //     br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            // }
            // catch(Exception e)
            // {
            //     e.printStackTrace();
            // }
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