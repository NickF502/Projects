//Place numbers in a cross such that no adjacent swuares contain consecutive integers

#include <cmath>
#include <iostream>
using namespace std;



void backtrack(int& c) { // Backtracking function
	c--;
	if (c == -1) exit(0);
}



// function to check if a value can be placed in a box of the cross
bool checkBoard(int a[], int col) {
	// helper array that is used to test for adjacency in the cross. -1 is the sentinel value that
	//is used to terminate the loop
		int helper[8][5] = { {-1}, {0, -1}, {0, -1}, {0, 1, 2, -1}, {0, 1, 3, -1}, {1, 4, -1}, {2, 3, 4, -1}, {3,
		4, 5, 6, -1} };

	for (int i = 0; i < col; ++i) {
		if (a[col] == a[i]) return false; // row test
	}

	for (int i = 0; helper[col][i] != -1; ++i) {
		if (abs(a[col] - a[helper[col][i]]) == 1) return false; // adjacency test
	}

	return true;
}



void printBoard(int col, int q[], int& solCounter) {
	cout << endl << "Solution # " << ++solCounter << endl;

	// I add 1 to every number because the main function uses numbers 0-7 instead of 1-8
	cout << " " << q[0] + 1 << q[1] + 1 << " " << endl;
	cout << q[2] + 1 << q[3] + 1 << q[4] + 1 << q[5] + 1 << endl;
	cout << " " << q[6] + 1 << q[7] + 1 << " " << endl;
}



// the main function is identical to the main function in the 8 queens without GOTOs problem
int main() {
	int solNum = 0;
	int q[8] = { 0 };
	q[0] = 0;
	int col = 1;
	bool fromBacktrack = false;

	while (true) {

		while (col < 8) {

			if (!fromBacktrack) {
				q[col] = -1;
				fromBacktrack = true;
			}

			while (q[col] < 8) {

				q[col]++;

				if (q[col] == 8) {
					bool fromBacktrack = true;
					backtrack(col);
					break;
				}

				if (checkBoard(q, col)) {
					fromBacktrack = false;
					col++;
					break;
				}
			}
		}

		printBoard(col, q, solNum);
		fromBacktrack = true;
		backtrack(col);

	}

	return 0;
}
