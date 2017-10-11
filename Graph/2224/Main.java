import java.util.*;
import java.io.*;

/**
 * problem 2224
 * 명제 증명
 * https://www.acmicpc.net/problem/2224
 * written by progresivoJS on 2017.10.10
 */
public class Main
{
    private static int INF = 987654321;
    
    public static void solve(HashMap<String, Integer> map, int[][] dist)
    {
        int n = 'z' - 'A' + 1;
        
        String[] dic = new String[n];
        for (String s : map.keySet())
            dic[map.get(s)] = s;

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k] == 1 && dist[k][j] == 1)
                        dist[i][j] = 1;
        
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (dist[i][j] != INF)
                {
                    if (dic[i] != null && dic[j] != null && dic[i] != dic[j])
                    {
                        str.append(dic[i]).append(" => ").append(dic[j]).append('\n');
                        count++;
                    }
                }
        System.out.println(count);
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[][] dist = new int['z' - 'A' + 1]['z' - 'A' + 1];
        for (int i = 0; i < dist.length; i++)
            Arrays.fill(dist[i], INF);
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        while (n-- > 0)
        {
            String from = In.next();
            In.next();
            String to = In.next();
            if (!map.containsKey(from))
                map.put(from, (int)from.charAt(0) - 'A');
            if (!map.containsKey(to))
                map.put(to, (int)to.charAt(0) - 'A');
            
            dist[map.get(from)][map.get(to)] = 1;
        }
        
        solve(map, dist);
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