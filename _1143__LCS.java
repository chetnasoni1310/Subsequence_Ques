public class _1143__LCS
{
    public int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length();
        int m=text2.length();

        //METHOD 1:  return LCS_Recursive(text1  , text2 , n ,m);
        

       /*METHOD 2:
        int[][] dp= new int[n+1][m+1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        Zero nahi le skte hai kyunki kya pata kahin dp[i][j]=0 hi nikle
        return LCS_TopDown_Memoization(text1  , text2 , n ,m , dp);
         */

       return LCS_BottomUp_Tabulation(text1  , text2 , n ,m );
    }
    private int LCS_BottomUp_Tabulation(String s1  ,String s2 ,int n , int m)
    {
         int[][] dp= new int[n+1][m+1];
  

  //Base Case to preComputed Results 
        for(int i=0;i<=n;i++)
       { dp[i][0]= 0;}
        for(int i=0;i<=m;i++)
        {dp[0][i]= 0;}

  //Dp table filling
        for(int i=1;i<=n;i++)
        {
          for(int j=1;j<=m;j++)
          { 
            if(s1.charAt(i-1) == s2.charAt(j-1))
            {
                dp[i][j]= 1 +dp[i-1][j-1];
            }
            else
            dp[i][j]=Math.max( dp[i-1][j] , dp[i][j-1]);

          }
        }
        return dp[n][m];
    }


    private int LCS_TopDown_Memoization(String s1  ,String s2 ,int n , int m,
    int[][] dp)
    {
       //Base Condition : 
       if(n==0 || m==0)
       {
        return 0;
       }


       // DP Table check : 
       if(dp[n][m] !=-1)
       {
        return dp[n][m];
       }


       //Choice Diagram : 
       char last_s1=s1.charAt(n-1);
       char last_s2=s2.charAt(m-1);



       if(last_s2==last_s1)
       {
        dp[n][m]=  1 +  LCS_TopDown_Memoization(s1, s2, n-1 , m-1, dp);
       }
       else
       {

       int s1_full=LCS_TopDown_Memoization(s1 , s2, n , m-1, dp);
       int s2_full=LCS_TopDown_Memoization(s1, s2 , n-1 , m, dp);
      
       dp[n][m] = Math.max(s1_full , s2_full); 
       }
     
     return dp[n][m];
    }
    private int LCS_Recursive(String s1  ,String s2 ,int n , int m)
    {
       //Base Condition : 
       if(n==0 || m==0)
       {
        return 0;
       }

       //Choice Diagram : 
       char last_s1=s1.charAt(n-1);
       char last_s2=s2.charAt(m-1);

       if(last_s2==last_s1)
       {
        return  1 +  LCS_Recursive(s1, s2, n-1 , m-1);
       }
       else
       {
       int s1_full=LCS_Recursive(s1 , s2, n , m-1);
       int s2_full=LCS_Recursive(s1, s2 , n-1 , m);
       return Math.max(s1_full , s2_full); 
       }

    }
}