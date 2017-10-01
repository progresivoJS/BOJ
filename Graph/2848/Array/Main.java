import java.util.*;
import java.io.*;

/**
 * problem 2848
 * 알고스팟어
 * https://www.acmicpc.net/problem/2848
 * written by progresivoJS on 2017.10.01
 */
public class Main
{
    public static void solve(boolean[][] adj, int[] ind, boolean[] marked)
    {
        int n = adj.length;
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < n; i++)
            if (marked[i])
            {
                count ++;
                if (ind[i] == 0)
                    q.add(i);
            }
            
        int[] result = new int[count];
        boolean multiple = false;
        for (int i = 0; i < count; i++)
        {
            System.out.println("q size : " + q.size());
            if (q.isEmpty())
            {
                System.out.println("!");
                return;
            }
            if (q.size() > 1)
                multiple = true;
            
            int v = q.poll();
            result[i] = v + 'a';
            
            for (int w = 0; w < n; w++)
                if (adj[v][w] && --ind[w] == 0)
                    q.add(w);
        }
        
        if (multiple)
        {
            System.out.println("?");
            return;
        }
        
        StringBuilder str = new StringBuilder();
        for (int i : result)
            str.append((char)i);
        str.append('\n');
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        int n = In.nextInt();
        String[] words = new String[n];
        
        // marked[i] = i번째 알파벳이 사용되었는가?
        boolean[] marked = new boolean[26];
        
        for (int i = 0; i < n; i++)
        {
            words[i] = In.next();
            for (int j = 0; j < words[i].length(); j++)
                marked[words[i].charAt(j) -'a'] = true;
        }
        
        boolean[][] adj = new boolean[26][26];
        int[] ind = new int[26];
        
        for (int i = 0; i < n - 1; i++)
        {
            int min = Math.min(words[i].length(), words[i + 1].length());
            int j;
            for (j = 0; j < min; j++)
            {
                int a = words[i].charAt(j) - 'a';
                int b = words[i + 1].charAt(j) -'a';
                if (a == b) continue;
                if (adj[a][b]) break;
                adj[a][b] = true;
                ind[b] ++;
                break;
            }
        }
        
        solve(adj, ind, marked);
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