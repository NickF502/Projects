#include <iostream>
using namespace std;

int main() {

    // Exercise 1

    int price;
    int total = 0;

    cout << "Enter the price of an item:" << endl << "> ";
    cin >> price;

    while (price != 0) {
        total += price;
        cout << "Enter the price of an item:" << endl << "> ";
        cin >> price;
    }

    cout << "Your checkout total is $" << total << endl;

    if (total > 100) {
        cout << "That's expensive!";
    }

    cout << endl << endl << endl;



    // Exercise 2

    int var1;
    int oddsum = 0;

    cout << "Enter a positive integer:" << endl << "> ";
    cin >> var1;

    while (var1 < 0) {
        cout << "Invalid input. Try again: " << endl << "> ";
        cin >> var1;
    }

    while (var1 != 0) {
        if (var1 % 2 != 0) {
            oddsum += (var1 % 10);
        }
        var1 /= 10;
    }

    cout << "The sum of the odd digits in the number is " << oddsum;

    cout << endl << endl << endl;



    // Exercise 3

    int num1;
    bool hasFive = false;

    cout << "Enter a positive integer: ";
    cin >> num1;
    cout << endl;

    while (num1 < 0) {
        cout << "Invalid input. Try again: ";
        cin >> num1;
        cout << endl;
    }

    while (num1 != 0) {
        if (num1 % 10 == 5) {
            hasFive = true;
        }
        num1 /= 10;
    }

    if (hasFive == true) {
        cout << "Number contains a 5.";
    }
    else {
        cout << "Number does not contain a 5.";
    }

    cout << endl << endl << endl;



    // Exercise 4

    int num2;
    int evencounter = 0;
    int oddcounter = 0;

    cout << "Enter a number:" << endl << "> ";
    cin >> num2;

    while (num2 < 0 || num2 > 100) {
        cout << "Invalid input. Try again:" << endl << "> ";
        cin >> num2;
    }

    while (num2 != 0) {
        if (num2 % 2 == 0) {
            evencounter++;
        }
        else {
            oddcounter++;
        }

        cout << "Enter a number:" << endl << "> ";
        cin >> num2;
    }

    cout << "Number of even inputs: " << evencounter << endl;
    cout << "Number of odd inputs: " << oddcounter << endl;

    cout << endl << endl;



    // Exercise 5

    int num3;
    int singlecounter = 0;
    int doublecounter = 0;
    int triplecounter = 0;

    cout << "Enter a number:" << endl << "> ";
    cin >> num3;

    while (num3 > 0 && num3 < 1000) {
        if (num3 % 100 != num3) {
            triplecounter++;
        }
        else if (num3 % 10 != num3) {
            doublecounter++;
        }
        else {
            singlecounter++;
        }

        cout << "Enter a number:" << endl << "> ";
        cin >> num3;
    }

    cout << "Number of single-digit integers: " << singlecounter << endl;
    cout << "Number of two-digit integers: " << doublecounter << endl;
    cout << "Number of three-digit integers: " << triplecounter << endl;

    cout << endl << endl;



    return 0;
}