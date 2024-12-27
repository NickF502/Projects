//Solve the 8 Queens problem with a 2D array, GoTos, and backtracking

#include <iostream>
using namespace std;

int main() {

	int solCounter = 0;
	int q[8][8] = { 0 };
	int c = 0;
	int r = 0;
	q[c][r] = 1;

NC: c++;
	if (c == 8) goto Print;
	r = -1;

NR: r++;
	if (r == 8) goto Backtracking;

	//rowTest
	for (int i = 0; i < c; i++) {
		if (q[r][i] == 1) goto NR;
	}

	//upDiagTest
	for (int i = 1; ((r - i) >= 0) && ((c - i) >= 0); i++) {
		if (q[r - i][c - i] == 1) goto NR;
	}

	//downDiagTest
	for (int i = 1; ((r + i) < 8) && ((c - i) >= 0); i++) {
		if (q[r + i][c - i] == 1) goto NR;
	}

	q[r][c] = 1;

	goto NC;

Backtracking: c--;
	if (c == -1) return 0;

	r = 0;
	while (q[r][c] != 1) r++;
	q[r][c] = 0;

	goto NR;

Print: cout << endl << "Solution # " << ++solCounter << endl;
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			cout << q[i][j] << "\t";
		}

		cout << endl;
	}

	goto Backtracking;
	return 0;
}
