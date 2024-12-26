#include <iostream>
using namespace std;

int main() {

    // Exercise 2

    int n1;

    cout << "Enter an odd integer between 5 and 25: ";
    cin >> n1;

    while (n1 % 2 == 0 || n1 < 5 || n1 > 25) {
        cout << "Invalid input. Enter an odd integer between 5 and 25: ";
        cin >> n1;
    }

    for (int r = 1; r <= n1; r += 2) {
        for (int c = 1; c <= n1; c += 2) {
            cout << r * c << "\t";
        }
        cout << endl;
    }

    cout << endl << endl << endl;



    // Exercise 3

    int n2;
    int counter1 = 0;
    int runningsum1 = 0;

    cout << "Enter a positive integer: ";
    cin >> n2;

    if (n2 < 0) {
        cout << "Goodbye";
        exit(1);
    }

    for (int i = 1; i <= n2; i++) {
        counter1 = 1;
        runningsum1 = 0;

        while (counter1 <= i) {
            cout << counter1 << " ";
            runningsum1 += counter1;
            counter1++;
        }

        cout << "the sum is " << runningsum1 << endl;
    }

    cout << endl << endl << endl;



    // Exercise 4

    int counter2 = 0;
    int runningsum2 = 0;

    for (int i = 1; i <= 10; i++) {
        counter2 = i;
        runningsum2 = 0;

        while (counter2 <= i * i) {
            cout << counter2 << " ";
            runningsum2 += counter2;
            counter2++;
        }

        cout << "the sum is " << runningsum2 << endl;
    }

    cout << endl << endl << endl;



    // Exercise 5

    int rows, columns;

    cout << "Enter number of rows between 2 and 10: ";
    cin >> rows;
    cout << "Enter number of columns between 2 and 10: ";
    cin >> columns;

    while (rows <= 0 || columns <= 0) {
        cout << "Both must be positive integers between 2 and 10";
        cout << "Enter number of rows between 2 and 10: ";
        cin >> rows;
        cout << "Enter number of columns between 2 and 10:";
        cin >> columns;
    }

    for (int r = 1; r <= rows; r++) {
        for (int c = 1; c <= columns; c++) {
            cout << c << "x" << r << "=" << r * c << "\t";
        }

        cout << endl;
    }

    cout << endl << endl << endl;



    return 0;
}