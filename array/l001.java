import java.util.*;
import java.util.io;

public int lengthOfLongestSubstring(String s) {
    if(s.length() <= 1){
        return s.length();
    }
    int si=0;
    int ei =0;
    int len = 0;
    int count = 0;
    int n = s.length();
    int[]freq = new int[128];
    while(ei<n){
        if(freq[s.charAt(ei++)]++ > 0){
            count++;
        }
        while(count>0){
             if(freq[s.charAt(si++)]-- > 1){
                count--;
            }
        }
        len = Math.max(len, (ei-si));
    }
    return len;
}

public int lengthOfLongestSubstringTwoDistinct(String s) {
    // Write your code here
      if(s.length() <= 2){
        return s.length();
    }
    int si=0;
    int ei =0;
    int len = 0;
    int count = 0;
    int n = s.length();
    int[]freq = new int[128];
    while(ei<n){
        if(freq[s.charAt(ei++)]++ == 0){
            count++;
        }
        while(count > 2){
             if(freq[s.charAt(si++)]-- == 1){
            count--;
        }
            
        }
        len = Math.max(len, (ei-si));
    }
    return len;
}

public String minWindow(String s, String t) {
    int ns = s.length();
     int nt = t.length();
     if(ns < nt){
         return "";
     }
     int si=0;
     int ei =0;
     int len = (int)1e9;
     int count = nt;
     int gsi = 0;
     int[]freq = new int[128];
     for(int i=0;i<nt;i++){
         freq[t.charAt(i)]++;
     }
     while(ei<ns){
         if(freq[s.charAt(ei++)]-- > 0){   //+ve - required!
             count--;
         }
         while(count == 0){
             if(len > ei-si){
                 len = ei-si;
                 gsi = si;
             }
             if(freq[s.charAt(si++)]++ == 0){ //jb koi exact amount m h, pr firr bhi we are dropping that
               count++;
             }
             
         }
         
     }
     return len == (int)1e9 ? "" : s.substring(gsi,gsi+len);
 
 }

 public String findSubString( String str) {
    // Your code goes 
    int count = 0;
    int[] freq = new int[128];
    for(int i=0;i<str.length();i++){
        if(freq[str.charAt(i)]==0){
            freq[str.charAt(i)] = 1;
            count++;
        }
    }
    
    int si = 0;
    int ei = 0;
    int len = (int)1e9;
    int gsi = 0;
    int n = str.length();
    while(ei<n){
        if(freq[str.charAt(ei++)]-- > 0){
            count--;
        }
        while(count == 0){
            if(ei - si < len){
                len = ei-si;
                gsi = si;
            }
            if(freq[str.charAt(si++)]++ == 0){
                count++;
            }
        }
    }
    if(len == (int)1e9){
        return "";
    }
    return str.substring(gsi, gsi+len);
}

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    // write your code here
    int n = s.length();
    int si = 0;
    int ei = 0;
    int count = 0;
    int len = -(int)1e9;
    int[] freq = new int[128];
    while(ei<n){
        if(freq[s.charAt(ei++)]++ == 0){
            count++;
        }
        while(count>k){
            if(freq[s.charAt(si++)]-- == 1){
                count--;
            }
        }
        len = Math.max(len,(ei-si));
    }
    if(len == -(int)1e9){
        return 0;
    }
    return len;
}
public boolean isVowel(char s){
    if(s == 'a' || s == 'e' || s == 'i' || s =='o' || s == 'u'){
        return true;
    }
    return false;
}
public int maxVowels(String s, int k) {
    int n = s.length();
    int si = 0;
    int ei = 0;
    int vowelCount = 0;
    int maxVowelCount = 0;
    while(ei<n){
        if(isVowel(s.charAt(ei++))){
            vowelCount++;
        }
        if(ei - si > k){
          if(isVowel(s.charAt(si++))){
                vowelCount--;
            }  
        }
       maxVowelCount = Math.max(maxVowelCount, vowelCount); 
    }
    return maxVowelCount;
}

public int withAtmostK(int[] arr, int k){
    int n = arr.length;
    int si = 0;
    int ei = 0;
    int count = 0;
    int len = 0;
    int[] freq = new int[20001];
    while(ei<n){
        if(freq[arr[ei++]]++ == 0){
            count++;
        }
        while(count > k){
           if(freq[arr[si++]]-- == 1){
                count--;
            }  
        }
        len+=(ei-si);
    }
    return len;
}
public int subarraysWithKDistinct(int[] arr, int k) {
    int atmostK = withAtmostK(arr, k);
    int atmostKm1 = withAtmostK(arr, k-1);
    return atmostK - atmostKm1;
}

public int AtMostKOdd(int[] arr, int k){
    int n = arr.length;
    int si = 0;
    int ei = 0;
    int len = 0;
    int count = 0;
   
    while(ei<n){
        if(arr[ei++] % 2 != 0){
            count++;
        }
        while(count > k){
            if(arr[si++] % 2 != 0){
                count--;
            }
        }
        len+=(ei-si);
    }
    return len;
}
public int numberOfSubarrays(int[] arr, int k) {
    int needed1 = AtMostKOdd(arr, k);
    int needed2 = AtMostKOdd(arr,k-1);
    return needed1-needed2;
}