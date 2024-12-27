#include <iostream>
#include <ctime>
using namespace std;

int main() {
    srand(time(0));

    // Exercise 1

    int orig1[5];
    int sorted1[5];

    cout << "Enter 5 numbers: \t";
    for (int i = 0; i < 5; i++) {
        cin >> orig1[i];
    }

    cout << "Shifted array: \t\t";
    for (int i = 0; i < 5; i++) {
        sorted1[i] = orig1[(i + 4) % 5];
        cout << sorted1[i] << " ";
    }

    cout << endl << endl << endl;



    // Exercise 2

    int input2[10];
    int output2[10];

    cout << "Input array values:" << endl;

    for (int i = 0; i < 10; i++) {
        input2[i] = rand() % 9 + 1;
        cout << input2[i] << " ";
    }

    cout << endl << "Output array values:" << endl;

    for (int i = 0; i < 10; i++) {
        if (input2[i] % 3 == 0) output2[i] = 3;
        else output2[i] = 0;
        cout << output2[i] << " ";
    }

    cout << endl << endl << endl;



    // Exercise 3

    int a3[15];
    int negCounter = 0;

    for (int i = 0; i < 15; i++) {
        a3[i] = rand() % 21 - 10;
        cout << a3[i] << " ";

        if (a3[i] < 0) negCounter++;
    }

    cout << endl << "Percentage of negative entries: " << double(negCounter) / 15 * 100 << "%";

    cout << endl << endl << endl;



    // Exercise 4

    int n4;
    int a4[5];

    cout << "Enter a five-digit integer: ";
    cin >> n4;
    if (n4 >= 100000 || n4 <= 9999) exit(4);

    for (int i = 4; i > -1; i--) {
        a4[i] = n4 % 10;
        n4 /= 10;
    }

    cout << endl << "The odd digits from left to right are ";
    for (int i = 0; i < 5; i++) {
        if (a4[i] % 2 == 1) cout << a4[i] << " ";
    }

    cout << endl << "The even diits from right to left are ";
    for (int i = 4; i >= 0; i--) {
        if (a4[i] % 2 == 0) cout << a4[i] << " ";
    }

    cout << endl << endl << endl;



    // Exercise 5

    int a5[2][5] = { {-1, -2, 1, -3, 5}, {-5, -6, 4, -7, -8} };

    int posEntriesInRow[2];

    for (int r = 0; r < 2; r++) {
        int posCounter = 0;
        for (int c = 0; c < 5; c++) {
            if (a5[r][c] > 0) posCounter++;
        }
        posEntriesInRow[r] = posCounter;
    }

    int rowMostPos = 0;
    for (int i = 0; i < 2; i++) {
        if (posEntriesInRow[i] > posEntriesInRow[rowMostPos]) {
            rowMostPos = i;
        }
    }

    cout << "The row with the most positive entries is row " << rowMostPos;

    cout << endl << endl << endl;



    return 0;
}