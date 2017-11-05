import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int a = In.nextInt();
        int b = In.nextInt();
        int c = In.nextInt();
        int d = In.nextInt();
        int start = In.nextInt();
        int len = In.nextInt();
        
        int[][] event = new int[n][2];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 2; j++)
                event[i][j] = In.nextInt();
        }
        
        int max = event[n - 1][0];
        
        boolean bestExist = false;
        int bestTime = -987654321;
        for (int i = 0; i < n + 1; i++)
        // for (int startTime = 0; startTime <= max + 1; startTime++)
        {
            int startTime;
            if (i == n)
                startTime = max + 1;
            else
                startTime = event[i][0] - len + 1;
            startTime = startTime < 0 ? 0 : startTime;
            int result = start;
            for (int j = 0; j < n; j++)
            {
                // talk show의 영향을 받음.
                if (event[j][0] < startTime + len && event[j][0] >= startTime)
                {
                    // photo shoot
                    if (event[j][1] == 1)
                        result += c;
                    // fashion show
                    else
                        result -= d;
                }
                // talk show의 영향을 받지 않음.
                else
                {
                    // photo shoot
                    if (event[j][1] == 1)
                        result += a;
                    // fashion show
                    else
                        result -= b;
                }
                
                
                if (result < 0)
                {
                    bestExist = false;
                    break;
                }
                else
                {
                    bestExist = true;
                }
            }
            
            if (bestExist)
            {
                bestTime = startTime;
                break;
            }
        }
        
        if (!bestExist)
            System.out.println(-1);
        else
            System.out.println(bestTime);
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