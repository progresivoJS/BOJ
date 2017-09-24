import java.util.*;
import java.io.*;

/**
 * problem 1963
 * 소수 경로
 * https://www.acmicpc.net/problem/1963
 * written by progresivoJS on 2017.09.24
 */
public class Main
{
    private static boolean[] isPrime;
    
    public static void solve(int from, int to)
    {
        boolean[] marked = new boolean[10000];
        bfs(marked, from, to);
    }
    
    private static void bfs(boolean[] marked, int from, int to)
    {
        marked[from] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        
        int count = 0;
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size-- > 0)
            {
                int v = q.poll();
                if (v == to)
                {
                    System.out.println(count);
                    return;
                }
                
                // case 1 : 1000의 자리 숫자를 바꾼다.
                for (int i = 1; i <= 9; i++)
                {
                    int w = v % 1000;
                    w += i * 1000;
                    if (!marked[w] && isPrime[w])
                    {
                        q.add(w);
                        marked[w] = true;
                    }
                }
                
                // case 2 : 100의 자리 숫자를 바꾼다.
                for (int i = 0; i <= 9; i++)
                {
                    int w = (v / 1000) * 1000;
                    w += v % 100;
                    w += i * 100;
                    if (!marked[w] && isPrime[w])
                    {
                        q.add(w);
                        marked[w] = true;
                    }
                }
                
                // case 3 : 10의 자리 숫자를 바꾼다.
                for (int i = 0; i <= 9; i++)
                {
                    int w = (v / 100) * 100;
                    w += v % 10;
                    w += i * 10;
                    if (!marked[w] && isPrime[w])
                    {
                        q.add(w);
                        marked[w] = true;
                    }
                }
                
                // case 4 : 1의 자리 숫자를 바꾼다.
                for (int i = 0; i <= 9; i++)
                {
                    int w = (v / 10) * 10;
                    w += i;
                    if (!marked[w] && isPrime[w])
                    {
                        q.add(w);
                        marked[w] = true;
                    }
                }
            }
            
            count ++;
        }
        
        System.out.println("Impossible");
    }
    
    public static void main(String[] args)
    {
        In.init();
        eratosthenes();
        
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int from = In.nextInt();
            int to = In.nextInt();
            solve(from, to);
        }
    }
    
    public static void eratosthenes()
    {
        isPrime = new boolean[10000];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        
        int sqrt = (int) Math.sqrt(10000);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j < 10000; j += i)
                    isPrime[j] = false;
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