public class _2800_Common_SuperSequence_of_3Strings {
    public String minimumString(String a, String b, String c) {
        /*
         WHY WE DON"T USE DP HERE ?
       1. 3 strings hai dp[][][]  3D Dp banana padta 
       2. Humein length nhi nikaalni poori string chhaiye toh dp ke andar
          backtraking bhi alag se krni padti hai string nikaalne ke liye 
          
          
         When Should We Use DP?
 You would use DP-based SCS when: ✅ The input has more than 3 strings.
 ✅ You need to find SCS for very long strings (N, M, L > 1000).
 ✅ You are asked for only the length of SCS (not the actual string).
 
 For 3 strings with small constraints, brute-force with merging is much better.
 
      
  */
 
            /*Key Observations: 
         1. For ex we have  s1=abc , s2=bca , s3=aaa 
         2. In total we can have 6 permutations of the string
             3! = 6
             to arrange these strings 
             There's no clear greedy approach to determine the best order in
             advance.
             Since 3! = 6 is small, brute-force checking all is reasonable.
             
         3. Generate all possible permutations 
         4. Then if we have the case 
          if (i == j) continue;
          This prevents selecting the same string twice for the first two
          positions.
          if (k == i || k == j) continue;
          Ensures that k is different from both i and j, meaning all three
          selected strings are distinct. 
         
         5. After these genrations we will merge 
             merge1 = s1 with s2 
             then their merge RESULT =  merge1 with s3
             
         6. We will change our FINAL = whenever
             i) our final is null OR
            ii) our final length is bigger than th result one's length
           iii) our final and result lengths are equal but result is
               lexicographically smaller than the final
               As these strings are objects we must use 
               obj1.compareTo( obj2() )   <>= 0
         */
 
 
         String[] allStr= { a ,b, c};
         String final_str=null;
 
 
         //Generating Permutations :
         for(int i=0;i<3;i++)
         {
             for(int j=0;j<3;j++)
             {
                 if(i==j)
                 continue;   //No duplicate
 
                 for(int k=0;k<3;k++)
                 {
                   if(k==i || k==j)
                   continue;    //No duplicate
                   
 
                   String merge1= merge( allStr[i] , allStr[j] );
                   String result= merge( merge1 , allStr[k] );  
                 
                 int len1=result.length();
 
                 int len2 = final_str!=null ? final_str.length() : 0;
                 if(final_str==null ||   len2>len1   || 
                      (len1==len2 && final_str.compareTo(result) > 0) )
                      {
                         final_str=result;
                      }
                 }
             }
         }
         return final_str;
     }
 
     private String merge(String s1 , String s2)
     {  
         /*Key Observations : 
         1. We will check if str1 contains s2 or s2 contains s1 if so return 
           the parent string 
           
         2. if not We will use Suffix-Prefix Concept that 
            at which index the suffix of string s1 will become the prefix of 
            string s2 
            for ex : abcd and cdf     suff of s1 and pre of s2
            index 0: abcd : cdf
            index 1: bcd : cdf 
            index 2: cd : cdf 
            Here the ending of s1 is starting of s2 
            so we will concatenate the 
                            part of s1 not in s2 + s2 
 
         3. If no overlapping part is present then we will return the whole
           concatenation s1+s2                    
           */
         if(s1.contains(s2))
         return s1;
 
         if(s2.contains(s1))
         return s2;
 
 
         for(int i=0;i<s1.length() ;i++)
         {
             if( s2.startsWith(s1.substring(i)) )
             {
                 return s1.substring(0 , i) + s2 ;
             }
         }
 
         return s1+s2 ;
 
     }
}
