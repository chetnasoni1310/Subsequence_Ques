public class _1092_SCS {
         /* 🚨 Why Prefix-Suffix Fails?

Prefix-suffix merging only checks for continuous matching parts, but "cabac" has "b" inserted in the middle, which prefix-suffix cannot detect.
The correct answer requires interleaving characters that don’t necessarily appear in a direct prefix-suffix manner. */

  public String shortestCommonSupersequence(String s1, String s2) {
    /*Key Observations :
 
    2. If the string chr matches then we will add it once only
    3. If the char don't match then 

      i)char at s1 might be char at s2 ka next
       add the s2 char and +1 it

     ii)char at s2 might be char at s1 ka next 
      add the s1 char and +1 it 

     toh we will have these cases 
       */


/****************************RECURSION************************** 
   return  SCS_Recursive(s1 , s2 , 0 , 0);
     */
  
 /*
 /****************************MEMOIZATION************************** 
  String dp[][] = new String[s1.length() +1][s2.length()+1];
  return  SCS_Memoization_TopDown(s1 , s2 , 0 , 0 ,dp);
  */


/***************************TABULATION*************************** */
  
  int n=s1.length();
  int m=s2.length();

  int[][] dp=new int[n+1][m+1];
  
  for(int i=1;i<=n;i++)
  {
    for(int j=1;j<=m;j++)
    {
       if(s1.charAt(i-1) == s2.charAt(j-1))
       {
          dp[i][j] = 1 +  dp[i-1][j-1]; 
       }
       else
       {
        dp[i][j] = Math.max(dp[i][j-1] ,  dp[i-1][j] ); 
       }
    }
  }
    StringBuilder sb=new StringBuilder();
    int i=n;
    int j=m;

    while(i>0 && j>0)
    {
        if( s1.charAt(i-1) == s2.charAt(j-1) )
        {
            sb.append( s1.charAt(i-1) );
            i--;
            j--;
        }
        else if(dp[i-1][j] > dp[i][j-1])
        {
         sb.append( s1.charAt(i-1) );
         i--;
        }
        else
        {
            sb.append( s2.charAt(j-1) );
            j--;
        }
    }
          // Add remaining characters
        while (i > 0) sb.append(s1.charAt(--i));
        while (j > 0) sb.append(s2.charAt(--j));

        return sb.reverse().toString();
  }



/****************************************************** */


 private String SCS_Memoization_TopDown(String s1, String s2,int n,int m,
 String dp[][] )
   { 
     if(n==s1.length() && m==s2.length())
     return "";

    if(dp[n][m] != null)
    {
        return dp[n][m];
    }  

     if(n==s1.length())
    {
     return dp[n][m]= s2.substring(m);
    }

    if(m==s2.length())
    {
    return dp[n][m] = s1.substring(n);
    }
    
    if( s1.charAt(n) == s2.charAt(m))
    {  
        dp[n][m]= s1.charAt(n) + SCS_Memoization_TopDown(s1 ,s2 ,n+1 ,m+1,dp);
        return  dp[n][m];
    }

String option1 = s2.charAt(m) + SCS_Memoization_TopDown(s1, s2, n, m + 1,dp);  

String option2 = s1.charAt(n) + SCS_Memoization_TopDown(s1, s2, n + 1, m,dp); 

      if (option1.length() == option2.length()) {
        dp[n][m]=option1.compareTo(option2) < 0 ? option1 : option2;
            return  dp[n][m];
        }

    dp[n][m]=(option1.length() < option2.length()) ? option1 : option2;    
    return  dp[n][m];

   }

/****************************************************** */

   private String SCS_Recursive(String s1, String s2,int n,int m )
   { 
     if(n==s1.length() && m==s2.length())
     return "";

     if(n==s1.length())
    {
     return s2.substring(m);
    }

    if(m==s2.length())
    {
    return s1.substring(n);
    }
    
    if( s1.charAt(n) == s2.charAt(m))
    {
        return s1.charAt(n) + SCS_Recursive(s1 ,s2 ,n+1 ,m+1);
    }

    String option1 = s2.charAt(m) + SCS_Recursive(s1, s2, n, m + 1);  // Take from s2
    String option2 = s1.charAt(n) + SCS_Recursive(s1, s2, n + 1, m);  // Take from s1

      if (option1.length() == option2.length()) {
            return option1.compareTo(option2) < 0 ? option1 : option2;
        }
    return (option1.length() < option2.length()) ? option1 : option2;

   }


    public String SCS_Prefix_Suffix_Approach(String s1, String s2) {
     /*Key Observations:
     1. If s1 contains s2 or vice versa return the parent string
     2. Use the suffix-prefix method to check 
       if any suffix of s1 becomes the prefix of s2
       
        */   
     
     //Edge case:
     if(s1.contains(s2))
     return s1;

     if(s2.contains(s1))
     return s2;
     
     for(int i=0;i<s1.length() ;i++)
     {
        if(s2.startsWith(s1.substring(i)))
        {
            return s1.substring(0,i) + s2;
        }
     }  
     return s1+s2;
    }
}
