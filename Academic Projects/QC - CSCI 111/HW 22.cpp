#include <iostream>
#include <ctime>
using namespace std;



// Exercise 2
void arrayFun(int a[], int i) {
    double sum1 = 0;

    for (int j = 0; j < i; j++) {
        sum1 += a[j];
    }

    cout << "Sum of array entries is " << sum1 << endl;
    cout << "Average of array entries is " << sum1 / i << endl;

    int max = a[0];
    int min = a[0];

    for (int j = 0; j < i; j++) {
        if (a[j] > max) max = a[j];
        if (a[j] < min) min = a[j];
    }

    cout << "Max array entry is " << max << endl << "Min array entry is " << min;
}



// Exercise 3
string allOdd(int x[][2], int r, int c) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (x[i][j] % 2 == 0) return "Not All Odd";
        }
    }
    return "All Odd";
}



// Exercise 4
bool increases(int x[], int i) {
    for (int a = 0; a < i - 1; a++) {
        if (x[a + 1] < x[a]) return false;
    }
    return true;
}



int main() {

    // Exercise 1
    int n1;
    int baseArray[10] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    cout << "Enter a number no greater than 10: ";
    cin >> n1;

    int userInts[n1];

    cout << "Enter " << n1 << " single digit integers: ";

    for (int i = 0; i < n1; i++) {
        cin >> userInts[i];
    }

    for (int j = 0; j < 10; j++) {
        for (int k = 0; k < n1; k++) {
            if (userInts[k] == baseArray[j]) {
                baseArray[j] = 20;
            }
        }

        if (baseArray[j] != 20) cout << baseArray[j] << " ";
    }

    cout << endl << endl << endl;



    // Exercise 2
    srand(time(0));

    int n2;

    cout << "Enter an array length: ";
    cin >> n2;

    int randArray[n2];

    for (int i = 0; i < n2;i++) {
        randArray[i] = rand() % 10;
        cout << randArray[i] << " ";
    }

    cout << endl;

    arrayFun(randArray, n2);

    cout << endl << endl << endl;



    // Exercise 3
    int x[2][2] = { {1, 3}, {5, 7} };

    cout << allOdd(x, 2, 2) << endl;
    // prints All Odd

    cout << endl << endl << endl;



    // Exercise 4
    int x1[8] = { 1, 3, 5, 7, 9 };
    if (increases(x1, 5)) {
        cout << "It increases\n"; // prints: It increases
    }
    else {
        cout << "It does not increase\n";
    }

    cout << endl << endl << endl;



    return 0;
}