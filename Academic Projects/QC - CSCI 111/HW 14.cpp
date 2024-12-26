#include <iostream>
#include <cmath>
using namespace std;

// Exercise 3
int numberOdd(int a, int b) {
    if (a % 2 == 1 && b % 2 == 1) {
        return 2;
    }
    else if (a % 2 == 1 || b % 2 == 1) {
        return 1;
    }
    else { return 0; }
}



int closest(double a) {
    return round(a);
}



int max(int a, int b, int c, int d) {
    if (a >= b && a >= c && a >= d) {
        return a;
    }
    else if (b >= a && b >= c && b >= d) {
        return b;
    }
    else if (c >= a && c >= b && c >= d) {
        return c;
    }
    else if (d >= a && d >= c && d >= b) {
        return d;
    }
}



int firstDigit(int a) {
    int val1;
    while (a != 0) {
        val1 = a;
        a /= 10;
    }
    return val1;
}



// Exercise 4
int oddLessEven(int a) {
    int oddSum = 0;
    int evenSum = 0;

    while (a != 0) {
        if (a % 2 == 0) {
            evenSum += a % 10;
        }
        else {
            oddSum += a % 10;
        }
        a /= 10;
    }

    return (oddSum - evenSum);
}



// Exercise 5
double sumRatios(int a, int b) {
    double ratioSum = 0.0;
    while (a != 0) {
        ratioSum += double(a % 10) / double(b % 10);
        a /= 10.0;
        b /= 10.0;
    }

    return ratioSum;
}



int main() {

    // Exercise 1

    /* Commented out for compilation

    string firstDigit(int a)
    int undouble(int b)
    string halfString(string c)
    string sort(string d, string e, string f, string g)
    string randomWord()

    */



    // Exercise 2

    /* Commented out for compilation

    int sumDigits(int a)
    bool isSmall(double b)
    char randomLetter()
    string sort3(int c, int d, int e)

    */



    // Exercise 3

    cout << numberOdd(7, 8) << endl;
    cout << closest(3.75) << endl;
    cout << max(3, 1, 4, 1) << endl;
    cout << firstDigit(19683) << endl;

    cout << endl << endl << endl;



    // Exercise 4
    cout << oddLessEven(23) << endl;
    cout << oddLessEven(1234) << endl;
    cout << oddLessEven(777) << endl;

    cout << endl << endl << endl;



    // Exercise 5
    cout << sumRatios(132, 568) << endl;
    cout << endl << endl << endl;



    return 0;
}