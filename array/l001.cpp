#include <iostream>
#include <vector>
#include<algorithm>
using namespace std;

void reverse(vector<int>&arr, int i, int j){
    while(i<j){
        swap(arr[i++], arr[j--]);
    }
    return;
}
void rotateArraybyK(vector<int> &arr, int r)
{
    int n = arr.size();
    r = (r % n + n) % n;

    reverse(arr, 0, n - 1);
    reverse(arr, n - r, n - 1);
    reverse(arr, 0, n - r - 1);
    return;
}

void segregateNegativeAndPositive(vector<int>& arr){
    int p = -1;
    int itr = 0;
    int n = arr.size();
    while(itr < n){
        if(arr[itr]<0){
            swap(arr[++p], arr[itr]);
        }
        itr++;
    }
}

void segregateZeroAndOne(vector<int>& arr){
    int p = -1;
    int itr = 0;
    int n = arr.size();
    while(itr < n){
        if(arr[itr]==0){
            swap(arr[++p], arr[itr]);
        }
        itr++;
    }
}

void segregateZeroOneAndTwo(vector<int>& arr){
    int p1 = -1;
    int itr = 0;
    int n = arr.size();
    int p2 = n-1;
    
    while(itr <= p2){
        if(arr[itr] == 0){
            swap(arr[++p1], arr[itr++]);
        }
        else if(arr[itr] == 2){
            swap(arr[p2--], arr[itr]);
        }
        else{
            itr++;
        }
        
    }
    return;
}

int maxSumbyRotation(vector<int>&arr){
    int n = arr.size();
    int sum = 0;
    int sumWithIndex = 0;
   
    for(int i=0;i<arr.size();i++){
        sum+=arr[i];
        sumWithIndex+= arr[i]*i;
    }
     int maxSum = sumWithIndex;
    for(int i=1;i<n;i++){
        
        sumWithIndex = (sumWithIndex - sum) + arr[i-1]*n;
        maxSum = max(maxSum, sumWithIndex);
    }
    return maxSum;
}

int containerWithMaxWater(vector<int>&arr){ // submitted on leetcode!
    int n = arr.size();
    int i = 0;
    int j = n-1;
    int maxWater = 0;
    while(i<j){
        int w = j-i;
        maxWater = arr[i] < arr[j] ? max(maxWater, arr[i++]*w) : max(maxWater,arr[j--]*w);
    }
    return maxWater;
}

int main(){
    // vector<int> arr = {9,7,2,8,6,3};
    // vector<int>arr = {3,5,-9,6,-8,-5,9,10,-4};
    //vector<int>arr = {0,1,2,1,0,0,2,1,2,0};
    vector<int> arr = {8, 3, 1, 2};
    // int r = 3;
    // rotateArraybyK(arr,r);
    // segregateNegativeAndPositive(arr);
   // segregateZeroAndOne(arr);
   // segregateZeroOneAndTwo(arr);
   cout<<maxSumbyRotation(arr)<<endl;
    for(int i=0;i<arr.size();i++){
        cout<<arr[i]<<" ";
    }
}