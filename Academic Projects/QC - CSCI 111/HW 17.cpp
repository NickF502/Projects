#include <iostream>
using namespace std;

// Exercise 1
int thirdDigit(int x) {
    if (x <= 9) return x;

    if (x <= 999 && x >= 100) return x % 10;
    else thirdDigit(x / 10);
}

// Exercise 2
int useRecursion(int x) {
    if (x < 10) return x;
    if (x < 100 && x > 9) return x % 10 + (x / 10) % 10;
    else return useRecursion(x / 10);
}

// Exercise 3
bool unlucky(int x) {
    if (x <= 9) return false;
    if (x >= 100) return unlucky(x / 10);

    if (x <= 99 && x >= 10)
        if ((x % 10 + (x / 10) % 10) == 13) return true;
        else return false;
}



int main() {

    // Exercise 1
    cout << thirdDigit(347) << " " << thirdDigit(2048) << " " << thirdDigit(560125);

    cout << endl << endl << endl;



    // Exercise 2
    cout << useRecursion(567982) << endl;
    cout << useRecursion(107982) << endl;
    cout << useRecursion(7) << endl;

    cout << endl << endl << endl;



    // Exercise 3
    int x = 6789;
    if (unlucky(x)) cout << x << " is Unlucky!\n"; // prints 6789 is Unlucky!
    x = 6889;
    if (unlucky(x)) cout << x << " is Unlucky!\n"; // prints
    x = 6;
    if (unlucky(x)) cout << x << " is Unlucky!\n"; // prints
    x = 49;
    if (unlucky(x)) cout << x << " is Unlucky!\n"; // prints 49 is Unlucky!

    cout << endl << endl << endl;



    return 0;
}