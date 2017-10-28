import java.util.*;
import java.io.*;

/**
 * problem 11650
 * 좌표 정렬하기
 * https://www.acmicpc.net/problem/11650
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Point[] array = new Point[n];
        for (int i = 0; i < n; i++)
            array[i] = new Point(In.nextInt(), In.nextInt());
        Arrays.sort(array);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
            str.append(array[i].x).append(' ').append(array[i].y).append('\n');
        System.out.println(str);
    }
    
    private static class Point implements Comparable<Point>
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Point other)
        {
            if (this.x - other.x != 0)
                return this.x - other.x;
            else
                return this.y - other.y;
        }
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