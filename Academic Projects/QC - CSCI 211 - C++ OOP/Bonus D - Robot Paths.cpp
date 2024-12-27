//Using memoization to calculate the number of paths to a grid space

#include <iostream>
#include <vector>
using namespace std;

int paths(int i, int j) {
    static vector<vector<int>> memo;
    
    // Check if the memo vector has enough rows
    if (memo.size() <= i) {
        memo.resize(i + 1, vector<int>());
    }
    
    // Check if the memo vector has enough columns
    if (memo[i].size() <= j) {
        memo[i].resize(j + 1, -1);
    }
    
    // Check if the result is already computed and stored in the cache
    if (memo[i][j] != -1) {
        return memo[i][j];
    }
    
    // Base case: when i or j is 0, there is only one path
    if (i == 0 || j == 0) {
        return 1;
    }
    
    // Compute the result recursively
    int result = paths(i - 1, j) + paths(i, j - 1);
    
    // Store the computed result in the cache
    memo[i][j] = result;
    
    return result;
}

int main() {
    cout << paths(4, 4) << endl;
  
    return 0;
}


