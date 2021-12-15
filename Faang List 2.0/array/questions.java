// leetcode 925
public boolean isLongPressedName(String s, String t) {
    int ns = s.length();
    int nt = t.length();
    if(nt < ns){
        return false;
    }
    int i = 0;
    int j = 0;
    while(i<ns && j<nt){
        if(s.charAt(i) == t.charAt(j)){
            i++;
            j++;
        }
        else if(i>0 && s.charAt(i-1) == t.charAt(j)){
            j++;
        } else{
            return false;
        }
    }
    if(i<ns){
        return false;
    }
    while(j<nt){
        if(s.charAt(i-1) != t.charAt(j)) return false;
        j++;
    }
    return true;
}
// lintcode 903
public int[] getModifiedArray(int length, int[][] updates) { //lame method
    // Write your code here
    int[] ans = new int[length];
    for(int[] x : updates){
        for(int i=x[0] ; i<=x[1]; i++){
            ans[i]+=x[2];
        }
    }
    return ans;
}

public int[] getModifiedArray(int length, int[][] updates) {  //kaafi ajeeb
    // Write your code here
    int[] ans = new int[length];
    for(int[] x : updates){
        ans[x[0]] += x[2];
        if (x[1]+1 < length) ans[x[1]+1] -= x[2];
    }
    for(int i=1;i<length;i++){
        ans[i]+=ans[i-1];
    }
    return ans;
}

//leetcode 11
public int maxArea(int[] arr) {
    int n = arr.length;
    int i = 0;
    int j = n-1;
    int ans = 0;
    while(i<j){
        int w = j-i;
        ans = arr[i]<arr[j] ? Math.max(ans, arr[i++]*w) : Math.max(ans, arr[j--]*w);
    }
    return ans;
}

//leetcode 977
public int[] sortedSquares(int[] nums) {
    int[] ans = new int[nums.length];
    int ps = nums.length;
    for(int i=0;i<nums.length;i++){
        if(nums[i] > 0){
            ps = i;
            break;
        }
    }
    int j = ps-1;
    int k =0;
    while(k<nums.length && j>=0 && ps<nums.length){
        if(nums[j]*nums[j] < nums[ps]*nums[ps]){
            ans[k++] = nums[j]*nums[j];
            j--;
        }
        else{
            ans[k++] =  nums[ps]*nums[ps];
            ps++;
        }
    }
    while(ps<nums.length){
        ans[k++] = nums[ps]*nums[ps];
        ps++;
    }
    while(j>=0){
        ans[k++] = nums[j]*nums[j];
        j--;
    }
    return ans;
}

//leetcode 665
public boolean checkPossibility(int[] nums) {
    int n = nums.length;
    int wrong = 0;
    for(int i = n-1;i>0;i--){
        if(i-1 >=0 && nums[i] < nums[i-1]){
            wrong++;
             if(i-1>=0 && i+1 < n && nums[i+1] < nums[i-1]){
                 nums[i-1] = nums[i];
              }
            if( i+1 < n && nums[i+1] > nums[i-1]){
                nums[i] = nums[i-1];
            }
        }
        
    }
    if(wrong <= 1){
        return true;
    }
    return false;
}
// leetcode 169
public int majorityElement(int[] nums) {
    HashMap<Integer,Integer>map = new HashMap<>();
    int req = nums.length/2;
    for(int i=0;i<nums.length;i++){
        map.put(nums[i], map.getOrDefault(nums[i],0)+1);
    }
    for(int x : map.keySet()){
        if(map.get(x)>req){
            return x;
        }
    }
    return -1;
}
//leetcode 229
public List<Integer> majorityElement(int[] nums) {
    HashMap<Integer,Integer>map = new HashMap<>();
    List<Integer> ans = new ArrayList<>();
    int req = nums.length/3;
    for(int i=0;i<nums.length;i++){
        map.put(nums[i], map.getOrDefault(nums[i],0)+1);
    }
    for(int x : map.keySet()){
        if(map.get(x)>req){
            ans.add(x);
        }
    }
    return ans;
}
// leetcode 904
public int totalFruit(int[] tree) {
    int n = tree.length;
    int si = 0;
    int ei = 0;
    int count = 0;
    int len = 0;
    int[] freq = new int[100000 + 1];
    while(ei<n){
        if(freq[tree[ei++]]++ == 0){
            count++;
        }
        while(count > 2){
             if(freq[tree[si++]]-- == 1){
                count--;
            }
        }
        len = Math.max(len, ei-si);
    }
    return len;
}
// leetcode 930
public int atMostSum(int[] nums, int goal){
    int n = nums.length;
    int si=0;
    int ei=0;
    int sum = 0;
    int count=0;
    while(ei<n){
        sum+=nums[ei++];
        while(sum > goal){
            sum-=nums[si++];
        }
        count+=ei-si;
    }
    return count;
}
public int numSubarraysWithSum(int[] nums, int goal) {
    int required1 = atMostSum(nums, goal);
    int required2 = 0;
    
    if(goal>0){
        required2 = atMostSum(nums, goal-1);
    }
    return required1 - required2;
}

//leetcode 485
public int findMaxConsecutiveOnes(int[] nums) {
    int n = nums.length;
    int si = 0;
    int ei = 0;
    int count = 0;
    int len = 0;
    while(ei<n){
        if(nums[ei++] == 0){
            count++;
        }
        while(count > 0){
            if(nums[si++] == 0){
                count--;
            }
        }
        len = Math.max(len,ei-si);
    }
    return len;
}
//leetcode 487
public int findMaxConsecutiveOnes(int[] nums) {
    int n = nums.length;
   int si = 0;
   int ei = 0;
   int count = 0;
   int len = 0;
   while(ei<n){
       if(nums[ei++] == 0){
           count++;
       }
       while(count > 1){
           if(nums[si++] == 0){
               count--;
           }
       }
       len = Math.max(len,ei-si);
   }
   return len;
}
// leetcode 1004
public int longestOnes(int[] nums, int k) {
       
    int n = nums.length;
   int si = 0;
   int ei = 0;
   int count = 0;
   int len = 0;
   while(ei<n){
       if(nums[ei++] == 0){
           count++;
       }
       while(count > k){
           if(nums[si++] == 0){
               count--;
           }
       }
       len = Math.max(len,ei-si);
   }
   return len;

}
//leetcode 974
public int subarraysDivByK(int[] arr, int k) {
    int n = arr.length;
    int count = 0;
    int sum = 0;
    
    HashMap<Integer, Integer>map = new HashMap<>();
    map.put(0,1);
    int i = 0;
    while(i<n){
        sum+=arr[i++];
        int rem = (sum%k + k)%k;
        if(map.containsKey(rem)){
            count+=map.get(rem);
        }
      
        map.put(rem, map.getOrDefault(rem,0) + 1);
        
    }
    return count;
}
// https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1#
static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
         
        int count = 0;
        int sum = 0;
        
        HashMap<Integer, Integer>map = new HashMap<>();
        map.put(0,1);
        int i = 0;
        while(i<n){
            sum+=arr[i];
            if(arr[i]==0){
                sum-=1;
            }
            i++;
            if(map.containsKey(sum)){
                count+=map.get(sum);
            }
          
            map.put(sum, map.getOrDefault(sum,0) + 1);
            
        }
        return count;
    
    }
    // leetcode 525
    public int findMaxLength(int[] arr) {
        int n = arr.length;
        int count = 0;
        int sum = 0;
        
        HashMap<Integer, Integer>map = new HashMap<>();
        map.put(0,-1);
        int i = 0;
        while(i<n){
            sum+=arr[i];
            if(arr[i]==0){
                sum-=1;
            }
            
            map.putIfAbsent(sum, i);
           
            if(map.containsKey(sum)){
                count=Math.max(count, i-map.get(sum));
            }
           i++;
          
            
        }
        return count;
    }
    // leetcode 556
    public int nextGreaterElement(int n) {
        
        String s = Integer.toString(n);
        char[] arr = s.toCharArray();
        int i = arr.length-2;
        while(i>=0 && arr[i] >= arr[i+1]){
            i--;
        }
        if(i == -1){
            return -1;
        }
        int swapWith = -1;
        for(int j = arr.length-1;j>i;j--){
            if(arr[j] > arr[i]){
                swapWith = j;
                break;
            }
        }
        char temp = arr[i];
        arr[i] = arr[swapWith];
        arr[swapWith] = temp;
        
        String ans = "";
        for(int k=0;k<=i;k++){
            ans+=arr[k];
        }
        for(int l=arr.length-1;l>i;l--){
            ans+=arr[l];
        }
        long val = Long.valueOf(ans);
        return val <= Integer.MAX_VALUE ? (int) val : -1;
        
    }
    //leetcode 881
     public int numRescueBoats(int[] arr, int tar) {
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length - 1;
        int boatCount = 0;
        
        while(i<=j){
            int sum = arr[i] + arr[j];
            if(sum<=tar){
                boatCount++;
                i++;
                j--;
            }
            else if(sum>tar){
                boatCount++;
                j--;
            }
        }
        return boatCount;
    }