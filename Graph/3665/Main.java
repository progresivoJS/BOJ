import java.util.*;
import java.io.*;

/**
 * problem 3665
 * 최종 순위
 * https://www.acmicpc.net/problem/3665
 * written by progresivoJS on 2017.10.01
 */
public class Main
{
    private static StringBuilder str;
    public static void solve(boolean[][] adj, int[] record, int[] ind)
    {
        int n = adj.length;
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                q.add(i);
        
        int[] result = new int[n];
        boolean multiple = false;
        for (int i = 0; i < n; i++)
        {
            if (q.isEmpty())
            {
                str.append("IMPOSSIBLE").append('\n');
                return;
            }
            
            if (q.size() > 1)
                multiple = true;
            
            int v = q.poll();
            result[i] = v + 1;
            
            for (int w = 0; w < n; w++)
                if (adj[v][w] && --ind[w] == 0)
                    q.add(w);
        }
        
        if (multiple)
        {
            str.append('?').append('\n');
            return;
        }
        
        for (int i = 0; i < n; i++)
            str.append(result[i]).append(' ');
        str.append('\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int test = In.nextInt();
        
        while (test-- > 0)
        {
            int n = In.nextInt(); // 팀 수
            
            int[] record = new int[n];
            for (int i = 0; i < n; i++)
                record[i] = In.nextInt() - 1;
            
            boolean[][] adj = new boolean[n][n];
            int[] ind = new int[n];
            
            for (int i = 0; i < n; i++)
                for (int j = 0; j < i; j++)
                {
                    adj[record[j]][record[i]] = true;
                    ind[record[i]]++;
                }
            
            int m = In.nextInt();
            while (m-- > 0)
            {
                int a = In.nextInt() - 1;
                int b = In.nextInt() - 1;
                if (adj[a][b])
                {
                    adj[a][b] = false;
                    adj[b][a] = true;
                    ind[b]--;
                    ind[a]++;
                }
                else
                {
                    adj[b][a] = false;
                    adj[a][b] = true;
                    ind[a]--;
                    ind[b]++;
                }
            }
            
            solve(adj, record, ind);
        }
        
        System.out.println(str.toString());
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