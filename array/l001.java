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