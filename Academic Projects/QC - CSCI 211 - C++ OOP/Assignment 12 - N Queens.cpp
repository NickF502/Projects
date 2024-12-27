//Calculate the number of solutions to the "n" Queens problem

#include <iostream>
#include<cmath>
using namespace std;

// Check if the board is legal in its current state; return true if so
inline bool checkBoard(int a[], int col) {
	for (int i = 0; i < col; i++) {
		if (a[col] == a[i] || abs(a[col] - a[i]) == abs(col - i)) return false;
	}

	return true;
}



// The entire 1D 8 queens problem, modified to calculate for any n queens
int eightQueens(int n) {

	// Allocate dynamic memory for whatever size array is needed
	int* q = new int[n];

	q[0] = 0;
	int solNum = 0;
	int col = 1;

	/* We need a boolean to check whether we came from a backtrack.
	* If we did, then we should NOT reset q[col], as we want to continue */
	bool fromBacktrack = false;

	while (true) { // continuous loop, will never be exited

		while (col < n) { // when this loop resets, it means a solution is found

			if (!fromBacktrack) { // Reset q[col] if we didn't come from backtrack
				q[col] = -1;
				fromBacktrack = true;
			}

			while (q[col] < n) { // Loop over all row values
				q[col]++;

				if (q[col] == n) { // If row = n, no solution found; backtrack
					bool fromBacktrack = true;

					// The backtrack function, which returns solNum when all solutions are found
					col--;
					if (col == -1) { // All solutions have been found if true
						delete[] q; // Delete the memory associated with the array
						return(solNum); // Backtrack and loop back to top of while(true)
					}

					break;
				}

				if (checkBoard(q, col)) { // If board is legal, move to next col
					fromBacktrack = false;
					col++;
					break;
				}
			}
		}

		solNum++; // Solution has been found. Increment counter
		fromBacktrack = true; // We are going to backtrack, so update boolean
		// The backtrack function, which returns solNum when all solutions are found
		col--;

		if (col == -1) { // All solutions have been found if true
			delete[] q; // Delete the memory associated with the array
			return(solNum); // Backtrack and loop back to top of while(true)
		}
	}

	return 0; // Will never actually be reached. Program terminates in backtrack()
}



int main() {
	int n = 8;

	// Print the number of solutions for all ixi boards for i <= n
	for (int i = 1; i <= n; i++) {
		cout << i << ". There are " << eightQueens(i) << " solutions to the " << i << " queens problem." << endl;
	}
}