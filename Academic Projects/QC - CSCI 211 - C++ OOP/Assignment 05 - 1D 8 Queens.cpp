//Solve the 8 Queens problem with a 1D array and backtracking

#include <iostream>
#include<cmath>
using namespace std;

// If we reach q[col] = 8, we must go back to the previous column
void backtrack(int& c) {
	c--;
	if (c == -1) exit(0); // If we backtrack past the first col, all solutions
	// have been found. Program should terminate
}



// Check if the board is legal in its current state; return true if so
bool checkBoard(int a[], int col) {
	for (int i = 0; i < col; i++) {
		if (a[col] == a[i] || abs(a[col] - a[i]) == abs(col - i)) return false;
	}

	return true;
}



// Print out a solution of the board
void printBoard(int col, int q[], int& solCounter) {
	cout << endl << "Solution # " << ++solCounter << endl;

	for (int i = 0; i < 8; i++) {
		cout << q[i] << " ";
	}

	cout << endl << "----------------" << endl;

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (q[j] == i) cout << "1 ";
			else cout << "0 ";
		}

		cout << endl;
	}

	cout << endl;
}



int main() {
	int solNum = 0;
	int q[8] = { 0 };
	q[0] = 0;
	int col = 1;

	// We need a boolean to check whether we came from a backtrack.
	// If we did, then we should NOT reset q[col], as we want to continue
	bool fromBacktrack = false;

	while (true) { // continuous loop, will never be exited

		while (col < 8) { // when this loop resets, it means a solution is found

			if (!fromBacktrack) { // Reset q[col] if we didn't come from backtrack
				q[col] = -1;
				fromBacktrack = true;
			}

			while (q[col] < 8) { // Loop over all row values
				q[col]++;
				if (q[col] == 8) { // If row = 8, no solution found; backtrack
					bool fromBacktrack = true;
					backtrack(col);
					break;
				}

				if (checkBoard(q, col)) { // If board is legal, move to next col
					fromBacktrack = false;
					col++;
					break;
				}

			}
		}

		printBoard(col, q, solNum); // Solution has been found. Print board
		fromBacktrack = true; // We are going to backtrack, so update boolean
		backtrack(col); // Backtrack and loop back to top of while(true)

	}

	return 0; // Will never actually be reached. Program terminates in backtrack()

}