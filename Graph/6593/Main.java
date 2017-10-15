import java.util.*;
import java.io.*;

/**
 * problem 6593
 * 상범 빌딩
 * https://www.acmicpc.net/problem/6593
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    public static void solve(int H, int R, int C, Point start, Point end, int[][][] map)
    {
        
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        while (true)
        {
            int H = In.nextInt();
            int R = In.nextInt();
            int C = In.nextInt();
            
            if (H == 0 && R == 0 && C == 0) break;
            
            int[][][] map = new int[R][C][H];
            
            Point start, end;
            
            for (int i = 0; i < H; i++)
                for (int j = 0; j < R; i++)
                {
                    String str = In.next();
                    for (int k = 0; k < C; k++)
                    {
                        char c = str.charAt(k);
                        if (c == 'S')
                        {
                            start = new Point(j, k, i);
                            map[j][k][i] = 0;
                        }
                        else if (c == 'E')
                        {
                            end = new Point(j, k, i);
                            map[j][k][i] = 1;
                        }
                        else if (c == '#')
                        {
                            map[j][k][i] = INF;
                        }
                        else// if (c == '.')
                        {
                            map[j][k][i] = 1;
                        }
                    }
                }
                
            solve(H, R, C, start, end, map);
        }
    }
    
    private static class Point
    {
        int row, col, height;
        public Point(int row, int col, int height)
        {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    
    private static class Node
    {
        
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