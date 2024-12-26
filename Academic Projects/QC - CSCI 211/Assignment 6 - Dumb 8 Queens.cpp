//Solve the 8 Queens problem by iterating through all possible solutions

#include <iostream>
#include <cmath>
using namespace std;



// Check to see if the board is valid if the queen is placed
bool checkBoard(int a[]) {
	for (int i = 0; i < 8; i++) {
		for (int j = i + 1; j < 8; j++) {
			if ((a[j] == a[i]) || (abs(a[j] - a[i])) == abs(j - i)) return false;
		}
	}

	return true;
}



// Print out the board
void printBoard(int array[], int solNum) {
	cout << "Solution #" << solNum << endl;

	for (int i = 0; i < 8; i++) {
		cout << array[i] << "\t"; // Print 1D Array
	}

	cout << endl << "----------------------------------------------------------" << endl; // Print line

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if (array[j] == i) cout << "1\t"; // Print 2D Board
			else cout << "0\t";
		}

		cout << endl;

	}

	cout << endl;

}



int main() {

	int solNum = 0;
	int c = 0;
	int q[8] = { 0 };

	for (int c0 = 0; c0 < 8; c0++) { // Loops to iterate through
		for (int c1 = 0; c1 < 8; c1++) { // all possible positions
			for (int c2 = 0; c2 < 8; c2++) { // of the board
				for (int c3 = 0; c3 < 8; c3++) {
					for (int c4 = 0; c4 < 8; c4++) {
						for (int c5 = 0; c5 < 8; c5++) {
							for (int c6 = 0; c6 < 8; c6++) {
								for (int c7 = 0; c7 < 8; c7++) {
									q[0] = c0;
									q[1] = c1;
									q[2] = c2;
									q[3] = c3; // Update array
									q[4] = c4;
									q[5] = c5;
									q[6] = c6;
									q[7] = c7;

									// Check if the board is solution in its new position. If so, print it
									if (checkBoard(q) == true) printBoard(q, ++solNum);
								}
							}
						}
					}
				}
			}
		}
	}

	return 0;
}
