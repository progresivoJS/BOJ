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