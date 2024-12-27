#include <iostream>
using namespace std;

int main() {

    // Exercise 1

    int nbig;
    int nsmall;

    cout << "Type a big integer n: ";
    cin >> nbig;
    cout << endl;

    for (int i = 1; i <= 4; i++) {
        cout << "Type a smaller value of n: ";
        cin >> nsmall;

        if (nsmall >= nbig) {
            cout << "Goodbye";
            exit(1);
        }

        nbig = nsmall;
    }

    cout << endl << endl << endl;



    // Exercise 2

    int n2;

    cout << "Enter an odd positive integer: ";
    cin >> n2;

    if (n2 % 2 == 0 || n2 <= 0) {
        cout << "Goodbye";
        exit(2);
    }

    for (int row = 1; row <= n2; row++) {
        for (int col = 1; col <= n2; col++) {

            if (col == row && row) {
                if (row == n2 / 2 + 1) {
                    cout << "*";
                }

                else if (row <= n2 / 2 + 1) {
                    cout << "+";
                }

                else {
                    cout << "x";
                }
            }

            else {
                cout << " ";
            }
        }

        cout << endl;
    }

    cout << endl << endl << endl;



    // Exercise 3

    int n3;

    cout << "Enter an odd positive integer: ";
    cin >> n3;

    if (n3 % 2 == 0 || 32 <= 0) {
        cout << "Goodbye";
        exit(3);
    }

    for (int row = (-n3 / 2); row <= (n3 / 2); row++) {
        for (int col = (-n3 / 2); col <= (n3 / 2); col++) {

            if (col + row == 0 || col == row) {

                if (col == 0) {
                    cout << "*";
                }

                else if (col < 0) {
                    cout << "+";
                }

                else {
                    cout << "x";
                }
            }

            else {
                cout << " ";
            }
        }

        cout << endl;
    }

    cout << endl << endl;



    // Exercise 4
    int n4;
    int wrongCounter = 1;

    cout << "Enter an integer between 1 and 20: ";
    cin >> n4;

    while (n4 < 1 || n4 > 20) {
        cout << "Out of range. Enter an integer between 1 and 20: ";
        cin >> n4;
        wrongCounter++;

        if (wrongCounter == 10) {
            n4 = 10;
            cout << "You have been assigned 10." << endl;
        }
    }

    cout << "The cube of your number is " << n4 * n4 * n4;

    cout << endl << endl << endl;



    return 0;
}