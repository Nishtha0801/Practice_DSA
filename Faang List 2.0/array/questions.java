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