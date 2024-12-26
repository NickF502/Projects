#include <iostream>
using namespace std;

int main() {

    // Exercise 1

    string name;

    cout << "Enter a name and I will repeat it back to you. Type END when you wish to quit." << endl << "> ";
    cin >> name;

    while (name != "END") {
        cout << name << endl << "> ";
        cin >> name;
    }

    cout << "I am done.";

    cout << endl << endl << endl;



    // Exercise 2

    int num1;

    cout << "Enter a number and I will tell you if it is even or odd. Enter a negative number to stop:" << endl << "> ";
    cin >> num1;
    cout << endl;

    while (num1 >= 0) {
        if (num1 % 2 == 0) {
            cout << "Even";
        }
        else {
            cout << "Odd";
        }
        cout << endl << "> ";
        cin >> num1;
    }

    cout << "Goodbye.";

    cout << endl << endl << endl;



    // Exercise 3

    double salary = 0.01;
    int day = 1;

    cout << "Day    Salary" << endl;
    cout << day << "      " << salary << endl;

    while (salary <= 10000) {
        salary *= 2;
        day++;
        cout << day << "      " << salary << endl;
    }

    cout << endl << endl << endl;



    // Exercise 4

    int num2;

    cout << "Enter a positive integer: " << endl << "> ";
    cin >> num2;

    while (num2 < 0) {
        cout << "Invalid input! Try again:" << endl << "> ";
        cin >> num2;
    }

    cout << "Your number in reverse binary is:" << endl;

    while (num2 != 0) {
        cout << num2 % 2 << endl;
        num2 /= 2;
    }

    cout << endl << endl << endl;



    // Exercise 5

    int num3;
    int counter = 1;

    cout << "Enter a positive integer: " << endl << "> ";
    cin >> num3;

    while (num3 < 0) {
        cout << "Invalid input! Try again:" << endl << "> ";
        cin >> num3;
    }

    cout << "The factors of " << num3 << " are ";

    while (counter <= num3) {
        if (num3 % counter == 0) {
            cout << counter << " ";
        }
        counter++;
    }

    cout << endl << endl << endl;



    return 0;
}