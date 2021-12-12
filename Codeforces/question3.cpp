// https://codeforces.com/problemset/problem/266/B
#include<iostream>
#include<string>
#include<algorithm>
using namespace std;
string solve(int n, int t, string s){
    while(t>0){
        int i =0;
        while(i<n-1){
            if(s[i]=='B' && s[i+1]=='G'){
                s[i] = 'G';
                s[i+1] = 'B'; 
                i++;
            }
            i++;
        }
        t--;
    }
    return s;
}
int main(){
    int n,t;
    cin>>n>>t;
    string s;
    cin>>s;
    cout<<solve(n,t,s)<<endl;
    return 0;
}