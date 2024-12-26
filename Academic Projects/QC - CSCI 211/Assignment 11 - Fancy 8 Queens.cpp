//Solve the 8 Queens problem and print out a chessboard for each solution

#include<iostream>
using namespace std;

int i, j, k, l;
typedef char box[4][7];
box bb, wb, bq, wq, * board[8][8];



// If we reach q[col] = 8, we must go back to the previous column
void backtrack(int& c) {
	c--;
	if (c == -1) exit(1);
}



// Check if the board is legal in its current state; return true if so
bool checkBoard(int a[], int col) {
	for (int i = 0; i < col; i++) {
		if (a[col] == a[i] || abs(a[col] - a[i]) == abs(col - i)) return false;
	}

	return true;
}



void printBoard(int q[], int solNum, box* board[8][8]) {
	//print the board via the pointers in array board
	cout << endl << "\tSolution #" << solNum << "\t";

	for (i = 0; i < 8;i++) cout << q[i] << " ";
	cout << endl;

	//first print upper border
	cout << " ";
	for (i = 0; i < 7 * 8; i++)
		cout << char(196);
	cout << endl;

	//now print the board
	for (i = 0; i < 8; i++)
		for (k = 0; k < 4; k++) {

			cout << " " << char(179); //print left border

			for (j = 0; j < 8; j++)
				for (l = 0; l < 7; l++) {

					//check if queen
					if (q[j] == i) //if queen
						if (i % 2 == j % 2) cout << wq[k][l];
						else cout << bq[k][l];
					else cout << (*board[i][j])[k][l];
				}

			cout << char(179) << endl; // at end of line bring bar and then new line
		}


	//before exiting print lower border
	cout << " ";
	for (i = 0; i < 7 * 8; i++)
		cout << char(196);
	cout << endl << endl;

}



int main() {

	//fill in bb=black box and wb=white box
	for (i = 0; i < 4; i++) {
		for (j = 0; j < 7; j++) {
			wb[i][j] = ' ';
			bb[i][j] = char(219);
		}
	}

	//fill in bq=black queen and wq=white queen
	for (int r = 0; r < 4; r++) {
		for (int c = 0; c < 7; c++) {
			if (r == 2 && (c > 0 && c < 6)) {
				wq[r][c] = char(219);
				bq[r][c] = ' ';
			}
			else if (r == 1 && c % 2 == 1) {
				wq[r][c] = char(219);
				bq[r][c] = ' ';
			}
			else {
				wq[r][c] = ' ';
				bq[r][c] = char(219);
			}
		}
	}

	//fill board with pointers to bb and wb in alternate positions
	for (i = 0; i < 8; i++) {
		for (j = 0; j < 8; j++) {
			if ((i + j) % 2 == 0) board[i][j] = &wb;
			else board[i][j] = &bb;
		}
	}

	//beginning of 8 queens solver
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

				//allTests
				if (checkBoard(q, col)) { // If board is legal, move to next col
					fromBacktrack = false;
					col++;
					break;
				}
			}
		}

		printBoard(q, ++solNum, board);
		fromBacktrack = true; // We are going to have to backtrack, so update boolean
		backtrack(col); // Backtrack and loop back to top of while(true)

	}

	return 0;
}
