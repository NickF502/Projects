#include <iostream>
using namespace std;

int main() {

    // Exercise 1

    int x;

    cout << "How many words? (Enter a positive integer value no greater than 100): ";
    cin >> x;

    if (x > 100 || x < 0) exit(1);

    cout << "Enter your words" << endl;

    string word1[x];

    for (int i = 0; i < x; i++) {
        cin >> word1[i];
    }

    for (int i = x - 1; i >= 0; i--) {
        cout << word1[i] << " ";
    }

    cout << endl << endl << endl;



    // Exercise 2

    int r;

    cout << "How many rows? (Enter a positive integer value no greater than 100): ";
    cin >> r;

    if (r > 100 || r < 0) exit(2);

    int rowLengths[r];

    cout << "Enter " << r << " row lengths: ";
    for (int i = 0; i < r; i++) cin >> rowLengths[i];

    for (int i = 0; i < r; i++) {

        for (int j = 0; j < rowLengths[i]; j++) {
            cout << "*";
        }

        cout << endl;
    }

    cout << endl << endl << endl;



    // Exercise 3

    int n;

    cout << "Enter a positive integer value no greater than 100: ";
    cin >> n;

    int num3[n];

    cout << "Enter " << n << " integers: ";

    for (int i = 0; i < n; i++) cin >> num3[i];

    for (int i = 0; i < n; i++) {
        if (num3[i] < 0) cout << num3[i] << " ";
    }

    cout << endl;

    for (int i = n - 1; i >= 0; i--) {
        if (num3[i] > 0) cout << num3[i] << " ";
    }

    cout << endl << endl << endl;



    return 0;
}