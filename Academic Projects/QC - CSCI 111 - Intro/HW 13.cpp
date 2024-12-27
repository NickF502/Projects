#include <iostream>
#include <cmath>
#include <ctime>
using namespace std;

int main() {

    // Exercise 1

    double l1, l2, l3;

    cout << "Enter the lengths of three sides of a triangle:" << endl;
    cin >> l1 >> l2 >> l3;

    double p = (l1 + l2 + l3) / 2.0;

    double area = sqrt(p * (p - l1) * (p - l2) * (p - l3));

    cout << "Area of triangle: " << area;

    cout << endl << endl << endl;



    // Exercise 2

    srand(time(0));
    int n2;

    cout << "Enter a number between 5 and 15: ";
    cin >> n2;

    while (n2 < 5 || n2 > 15) {
        cout << "Invalid input. Try again: ";
        cin >> n2;
    }

    int rand1 = rand() % 10;
    int rand2 = rand() % 10;

    while (rand2 == rand1) {
        rand2 = rand() % 10;
    }

    for (int r = 1; r <= n2; r++) {
        for (int c = 1; c <= 2 * n2; c++) {

            if (r == 1 || r == n2 || c == 1 || c == 2 * n2) {
                cout << "*";
            }
            else if (c % 2 == 1) {
                cout << rand1;
            }
            else if (c % 2 == 0) {
                cout << rand2;
            }


        }

        cout << endl;
    }

    cout << endl << endl << endl;



    // Exercise 3

    srand(time(0));

    int low = (rand() % 151) + 50;
    int high = (rand() % 2301) + 700;

    cout << "The perfect squares between " << low << " and " << high << " are:" << endl;

    for (int i = low; i <= high; i++) {
        if (pow(floor(sqrt(i)), 2) == i) {
            cout << i << endl;
        }
    }

    cout << endl << endl << endl;



    //Exercise 4

    srand(time(0));

    int rand4 = rand() % 3001 + 2000;

    for (int i = 1000; i <= rand4; i++) {
        if (i * i % 100 == 56) {
            cout << i << " squared is " << i * i << endl;
        }
    }

    cout << endl << endl << endl;



    // Exercise 5

    srand(time(0));
    int wrongCounter = 0;

    for (int i = 1; i <= 5; i++) {

        int ans;

        int rand1 = rand() % 100 + 1;
        int rand2 = rand() % 100 + 1;

        cout << rand1 << " + " << rand2 << " = ";
        cin >> ans;

        if (ans != (rand1 + rand2)) {
            cout << "Wrong! The answer was " << rand1 + rand2 << endl;
            wrongCounter++;
        }

        cout << endl;


    }

    cout << "You got " << 5 - wrongCounter << " correct, " << wrongCounter << " wrong.";



    return 0;
}