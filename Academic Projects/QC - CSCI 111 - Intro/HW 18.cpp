#include <iostream>
using namespace std;

// Exercise 1
int sumCubes(int x) {
    if (x <= 0) return 0;
    return sumCubes(x - 1) + x * x * x;
}

// Exercise 2
int twoPart(int a) {
    if (a % 2 == 1) return 1;
    if (a == 0) return 1;
    return(2 * twoPart(a / 2));
}

// Exercise 3
int biggestDigit(int a) {
    if (a % 10 == 9) return 9;
    if (a < 10) return a;

    if ((a / 10) % 10 >= a % 10) {
        return biggestDigit(a / 10);
    }
    else {
        int digit = a % 10;
        a /= 100;
        a = a * 10 + digit;
        return biggestDigit(a);
    }
}

// Exercise 4
int sumDigits(int a) {
    if (a == 0) return 0;
    return (a % 10 + sumDigits(a / 10));
}

// Exercise 5
int removeOddDigits(int n) {
    int digit = n % 10;
    if (digit % 2 == 1) digit = 0;
    if (n < 10) return digit;
    if (n % 2 == 1) return removeOddDigits(n / 10);
    else return removeOddDigits(n / 10) * 10 + digit;
}



int main() {

    // Exercise 1
    cout << sumCubes(2) << endl;
    cout << sumCubes(4) << endl;
    cout << sumCubes(6) << endl;

    cout << endl << endl << endl;



    // Exercise 2

    cout << twoPart(16) << endl; //prints 16
    cout << twoPart(666) << endl; //prints 2
    cout << twoPart(777) << endl; //prints 1

    cout << endl << endl << endl;



    // Exercise 3

    cout << biggestDigit(29) << endl; //prints 9
    cout << biggestDigit(31415) << endl; // prints 5
    cout << biggestDigit(7) << endl; // prints 7

    cout << endl << endl << endl;



    // Exercise 4
    cout << sumDigits(912) << endl; // prints 12
    cout << sumDigits(3275) << endl; // prints 17

    cout << endl << endl << endl;



    // Exercise 5
    cout << removeOddDigits(792) << endl; // prints 2
    cout << removeOddDigits(37) << endl; // prints 0
    cout << removeOddDigits(222) << endl;  // prints 222



    return 0;
}