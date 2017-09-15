import java.util.*;
import java.io.*;

/**
 * problem 6679
 * 싱기한 네자리 숫자
 * https://www.acmicpc.net/problem/6679
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        In.init();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1000; i <= 9999; i++)
            if (isInteresting(i))
            {
                out.write(String.valueOf(i));
                out.write("\n");
            }
            
        out.close();
    }
    
    public static boolean isInteresting(int number)
    {
        int ten = 0;
        String str10 = String.valueOf(number);
        for (int i = 0; i < str10.length(); i++)
            ten += str10.charAt(i) - '0';
        
        int twelve = 0;
        String str12 = Integer.toString(number, 12);
        for (int i = 0; i < str12.length(); i++)
        {
            if (str12.charAt(i) >= 'a')
                twelve += str12.charAt(i) - 'a' + 10;
            else
                twelve += str12.charAt(i) - '0';
        }
        
        int sixteen = 0;
        String str16 = Integer.toString(number, 16);
        for (int i = 0; i < str16.length(); i++)
        {
            if (str16.charAt(i) >= 'a')
                sixteen += str16.charAt(i) - 'a' + 10;
            else
                sixteen += str16.charAt(i) - '0';
        }
        
        if (ten == twelve && twelve == sixteen)
            return true;
        return false;
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