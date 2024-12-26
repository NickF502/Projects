//Solve the Stable Marriage problem

#include <cmath>
#include <iostream>
using namespace std;

#define numPpl 3 // Set number of people to be a constant

int mp[numPpl][numPpl] = { {0, 2, 1}, {0, 2, 1}, {1, 2, 0} }; // Array of the men's preferences
int wp[numPpl][numPpl] = { {2, 1, 0}, {0, 1, 2}, {2, 0, 1} }; // Array of the women's preferences
int solNum = 0; // Number of solutions that have been found



// If we reach q[c] = numPpl, we must go back to the previous column
void backtrack(int& c) {
	c--;
	if (c == -1) exit(0);
}



// Check if the current matchings are acceptable
bool checkMarriage(int q[], int col) {
	// If a woman is already taken, return false
	for (int i = 0; i < col; i++) {
		if (q[col] == q[i]) return false;
	}

	// Check if the current man prefers the new woman to his partner and
	// if the new woman prefers the current man to her partner, return false
	for (int i = 0; i < col; i++) {
		if (mp[i][col] < mp[i][q[i]] && wp[col][i] < wp[col][q[col]]) return false;
	}

	// Check if the current woman prefers the new man to her partner and
	// if the new man prefers the current woman to his partner, return false
	for (int i = col - 1; i >= 0; i--) {
		if (mp[q[col]][i] < mp[q[col]][col] && wp[i][q[col]] < wp[i][q[i]]) return false;
	}

	return true; // If all testing passes, return true
}



// Print the stable marriage that has been found
void printMarriages(int q[]) {
	cout << "Solution #" << ++solNum << endl;
	cout << "-------------" << endl;

	for (int i = 0; i < numPpl; i++) {
		cout << q[i] << " "; // Print the array in one row, such that the first
		// number represents the woman that is paired
	} // with man one, the second number is the woman paired to man two, etc.

	cout << endl << endl;
}



// Same body as in 8 Queens and 8 in a Cross, but the 8 is replaced with numPpl
int main() {

	int q[numPpl] = { 0 };
	int col = 1;
	bool fromBacktrack = false;

	while (true) {

		while (col < numPpl) {

			if (!fromBacktrack) {
				q[col] = -1;
				fromBacktrack = true;
			}

			while (q[col] < numPpl) {

				q[col]++;
				if (q[col] == numPpl) {
					bool fromBacktrack = true;
					backtrack(col);
					break;
				}

				if (checkMarriage(q, col)) {
					fromBacktrack = false;
					col++;
					break;
				}
			}
		}

		printMarriages(q);
		fromBacktrack = true;
		backtrack(col);
	}

	return 0;
}
