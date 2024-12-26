//Using memoization to calculate fibonacci numbers

#include <iostream>
#include <vector>
using namespace std;
    
vector<int> memo(110, -1);

int fib(int n){
    
    if(memo[n] != -1) return memo[n];	// Check if the value has already been calculated
    
    if(n==0 || n==1) return 1;		// Base case
   
    int result = fib(n-1) + fib(n-2);	// Compute the result and store it in the cache
    memo[n] = result;			
    return result;			// Return the result

}
    
    
int main(){

    for(int i=0; i<=100; i++){
        cout << i << "\t" << fib(i) << endl;
    }    

    return 0;
}




