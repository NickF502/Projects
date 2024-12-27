#include <iostream>
using namespace std;

int main() {



    // Exercise 1

    int num1;

    cout << "Enter a positive number: ";
    cin >> num1;

    while (num1 < 0) {
        cout << "Invalid input. Enter a positive number: ";
        cin >> num1;
    }

    for (int i = 1; i <= num1; i++) {
        if (i % 2 == 0) {
            cout << -i;
        }
        else {
            cout << i;
        }
        cout << " ";
    }

    cout << endl << endl << endl;



    // Exercise 2

    int n;
    int tempint;
    int oddCounter = 0;

    cout << "Enter a positive number: ";
    cin >> n;

    while (n < 0) {
        cout << "Invalid input. Enter a positive number: ";
        cin >> n;
    }

    cout << "Now enter " << n << " more integers: ";

    for (int j = 1; j <= n; j++) {
        cin >> tempint;

        if (tempint % 2 != 0) {
            oddCounter++;
        }
    }

    cout << oddCounter << " were odd.";

    cout << endl << endl << endl;



    // Exercise 3

    int num3a, num3b, lowint;

    cout << "Enter two positive integers: ";
    cin >> num3a >> num3b;

    while (num3a < 0 || num3b < 0) {
        cout << "Invalid input. Enter two positive number: ";
        cin >> num3a >> num3b;
    }

    if (num3a < num3b) {
        lowint = num3a;
    }
    else {
        lowint = num3b;
    }

    for (int k = 1; k <= lowint; k++) {
        cout << "X";
    }

    cout << endl << endl << endl;



    // Exercise 4

    int num4;

    cout << "Enter a positive integer greater than 10: ";
    cin >> num4;

    while (num4 <= 10) {
        cout << "Invalid input. Enter a positive integer greater than 10: ";
        cin >> num4;
    }

    for (int l = 1; l <= num4; l++) {
        if (l % 12 == 0) {
            cout << "FuzzyCat ";
        }
        else if (l % 4 == 0) {
            cout << "Cat ";
        }
        else if (l % 3 == 0) {
            cout << "Fuzzy ";
        }
        else {
            cout << l << " ";
        }
    }

    cout << endl << endl << endl;



    // Exercise 5

    int num5a, num5b;

    cout << "Enter two integers.  The second must be at least 20 larger than the first: ";
    cin >> num5a >> num5b;

    while (num5b < num5a + 20) {
        cout << "Invalid input. Enter two integers.  The second must be at least 20 larger than the first: ";
        cin >> num5a >> num5b;
    }

    cout << "The numbers between " << num5a << " and " << num5b << " that are evenly divisible by 2 but not by 3 are: ";

    for (int m = num5a; m <= num5b; m++) {
        if (m % 2 == 0 && m % 3 != 0) {
            cout << m << " ";
        }
    }

    cout << endl << endl << endl;



    return 0;
}