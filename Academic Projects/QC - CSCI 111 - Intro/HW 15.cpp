#include <iostream>
#include <cmath>
using namespace std;

// Exercise 1
bool areVeryDifferent(int a, int b) {
    if (abs(a - b) >= 10) {
        return true;
    }
    else { return false; }
}

// Exercise 2
double countChange(double q, double d, double n, double p) {
    q *= 0.25;
    d *= 0.1;
    n *= 0.05;
    p *= 0.01;
    return(q + d + n + p);
}

// Exercise 3
double quadratic(double a, double b, double c, double x) {
    return (a * x * x + b * x + c);
}

// Exercise 4
int toSeconds(int d, int h, int m) {
    d *= 60 * 60 * 24;
    h *= 60 * 60;
    m *= 60;
    return d + h + m;
}

// Exercise 5
void minMaxMath(int a, int b, int c) {
    cout << "The sum is " << a + b + c << endl;
    cout << "The produceis " << a * b * c << endl;

    cout << "The largest number is ";
    if (a >= b && a >= c) {
        cout << a;
    }
    else if (b >= a && b >= c) {
        cout << b;
    }
    else { cout << c; }
    cout << endl;

    cout << "The smallest number is ";
    if (a <= b && a <= c) {
        cout << a;
    }
    else if (b <= a && b <= c) {
        cout << b;
    }
    else { cout << c; }
    cout << endl;
}

int main() {

    // Exercise 1

    int x1 = 4, y1 = 10, z1 = -4;
    if (areVeryDifferent(x1, y1)) cout << "x and y are very different" << endl;
    if (areVeryDifferent(x1, z1)) cout << "x and z are very different" << endl;
    if (areVeryDifferent(y1, z1)) cout << "y and z are very different" << endl;

    cout << endl << endl << endl;



    // Exercise 2

    int q2 = 10, d2 = 5, n2 = 1, p2 = 2;
    double a2 = countChange(q2, d2, n2, p2);
    cout << "You have $" << a2 << endl;

    cout << endl << endl << endl;



    // Exercise 3

    double a3 = 1.0, b3 = 2.2, c3 = 1.21, x3 = 0.1;
    cout << quadratic(a3, b3, c3, x3) << endl;

    cout << endl << endl << endl;



    // Exercise 4
    int d4 = 5, h4 = 12, m4 = 30;
    cout << toSeconds(d4, h4, m4) << endl;

    cout << endl << endl << endl;



    // Exercise 5
    int a5 = 7, b5 = 2, c5 = 4;
    minMaxMath(a5, b5, c5);

    return 0;
}