#include <iostream>
using namespace std;

int main() {


    // Homework 2 Q3

    int nh1, nh2;

    cout << "Enter two positive integers: ";
    cin >> nh1 >> nh2;

    if (nh1 < 0 || nh2 < 0) {
        cout << "Invalid Input. Goodbye.";
        exit(3);
    }

    if (nh1 < nh2) {
        cout << nh1 << " is the smallest and " << nh2 << " is the largest." << endl;

        for (int i = 1; i < nh2; i++) {
            cout << "*";
        }

        for (int j = 0; j <= nh1; j++) {
            cout << "*" << endl;
        }


    }
    else {
        cout << nh2 << " is the smallest and " << nh1 << " is the largest." << endl;

        for (int i = 1; i < nh1; i++) {
            cout << "*";
        }

        for (int j = 0; j <= nh2; j++) {
            cout << "*" << endl;
        }
    }

    cout << endl << endl << endl;



    // Homework 2 Q4

    int inth2;
    int oddCounter = 0;
    int evenCounter = 0;

    cout << "Enter a positive integer with six digits or fewer: ";
    cin >> inth2;

    if (inth2 < 0 || inth2 / 1000000 != 0) {
        cout << "Invalid input. Goodbye.";
        exit(3);
    }

    while (inth2 != 0) {
        if (inth2 % 2 == 0) {
            evenCounter++;
        }
        else {
            oddCounter++;
        }
        
        inth2 /= 10;
    }

    for (int i = 1; i <= evenCounter; i++) {
        cout << "E";
    }

    cout << endl;

    for (int j = 1; j <= oddCounter; j++) {
        cout << "O";
    }

    cout << endl << endl << endl;



    // Homework 2 Q5

    int int5;
    int fdigit, ldigit;

    cout << "Enter a positive integer between two and five digits: ";
    cin >> int5;

    while (int5 < 100 || int5 >99999) {
        cout << "Invalid input. Enter a positive integer between two and five digits: ";
        cin >> int5;
    }

    ldigit = int5 % 10;

    while (int5 >= 10) {
        int5 /= 10;
    }

    fdigit = int5;

    cout << "The first digit of your input is " << fdigit << " and the last digit of your input is " << ldigit;

    cout << endl << endl << endl;



    // Homework 2 Q6

    int int6;
    int evenSum = 0;
    int oddSum = 0;

    cout << "Enter a positive integer: ";
    cin >> int6;

    while (int6 < 0) {
        cout << "Invalid input. Enter a positive integer: ";
        cin >> int6;
    }

    for (int i = 1; i <= int6; i++) {
        if (i % 2 == 0) {
            evenSum += i;
        }
        else {
            oddSum += i;
        }
    }

    cout << "The sum of all odd numbers from 1 through " << int6 << " is " << oddSum << endl;
    cout << "The sum of all even numbers from 1 through " << int6 << " is " << evenSum << endl;

    cout << endl << endl << endl;



    // Homework 2 Q7

    int num1, num2;

    cout << "Enter two integers. The second must be less than half the value of the first: ";
    cin >> num1 >> num2;
    cout << endl;

    if (num2 > (num1 / 2)) {
        cout << "Invalid input. Goodbye.";
        exit(3);
    }

    cout << "The numbers from 1 to " << num1 << " that are evenly divisible by " << num2 << " are: ";

    for (int j = 1; j <= num1; j++) {
        if (j % num2 == 0) {
            cout << j << " ";
        }
    }

    cout << endl << "The numbers from 1 to " << num1 << " that are not evenly divisible by " << num2 << " are: ";

    for (int k = 1; k <= num1; k++) {
        if (k % num2 != 0) {
            cout << k << " ";
        }
    }

    return 0;
}