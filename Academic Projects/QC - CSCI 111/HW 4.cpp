#include <iostream>
using namespace std;

int main() {


    int total = 0, n = 0;
    int max_sub;


    cout << "Welcome to NIM. Pick a starting total: ";
    cin >> total;

    // Exercise 2.5.2
    cout << endl << "Enter a maximum number to subtract from the total: ";
    cin >> max_sub;

    cout << endl << "To quit at any time, enter anything outside the established range." << endl;

    // Exercise 2.5.1
    while (total < 0) {
        cout << "Total cannot be negative. Enter a positive number: " << endl;
        cin >> total;
    }

    while (true) {
        if ((total % max_sub + 1) == 2) {
            total = total - max_sub;
            cout << "I am subtracting " << max_sub << "." << endl;
        }
        else {
            total--;
            cout << "I am subtracting 1." << endl;
        }

        cout << "New total is " << total << endl;
        if (total <= 0) {
            cout << "I win!" << endl;
            break;
        }

        cout << "Enter num to subtract (1 -  " << max_sub << "): ";
        cin >> n;
        cout << endl;

        // Exercise 2.5.3
        while (n <= 0 || n > max_sub) {
            cout << "Input must be between 1 and " << max_sub << " . If you want to quit, enter something outside this range." << endl;
            cout << "Re-enter: ";
            cin >> n;
            cout << endl;

            if (n <= 0 || n > max_sub) {
                cout << "Quitting...";
                exit(1);
            }
        }

        total = total - n;

        cout << "New total is " << total << endl;

        if (total <= 0) {
            cout << "You win!" << endl;
            break;
        }
    }


    cout << endl << endl << endl;

    // Lab 4 Homework 1, Questions 5, 6, 7

    // Lab 4 - Question 5

    int num1, num2, num3;

    cout << "Enter three integers: ";
    cin >> num1 >> num2 >> num3;
    cout << endl;

    cout << "3 * " << num1 << " = " << 3 * num1 << endl;

    cout << "3 * " << num1 << " + " << num2 << " = " << 3 * num1 + num2 << endl;

    cout << "(" << num1 << " + " << num2 << ") / 7 = " << (float(num1 + num2)) / 7.0 << endl;;

    cout << "3 * " << num1 << " + " << num2 << " / (" << num3 << " + 2) = " << float((3 * float(num1) + float(num2)) / (float(num3) + 2)) << endl;

    cout << endl << endl << endl;


    // Lab 4 - Question 6

    int hour, minute;
    string ampm;

    cout << "Enter the current time." << endl << "Hour: ";
    cin >> hour;
    cout << endl << "Minute: ";
    cin >> minute;
    cout << endl << "AM or PM? ";
    cin >> ampm;
    cout << endl;

    if (hour == 9 && minute == 0 && ampm == "AM") {
        cout << "It is time for breakfast." << endl;
    }
    else if (hour == 5 && minute == 30 && ampm == "PM") {
        cout << "It is time for dinner." << endl;
    }
    else if (hour >= 8 && ampm == "PM") {
        cout << "It is time for an evening snack." << endl;
    }
    else {
        cout << "It is not time to eat yet." << endl;
    }

    cout << endl << endl << endl;


    // Lab 4 - Question 7
    int month, day, year;
    
    cout << "Enter your birth month: ";
    cin >> month;
    cout << endl << "Enter your birth day: ";
    cin >> day;
    cout << endl << "Enter your birth year: ";
    cin >> year;
    cout << endl;

    if (month == 9 && day >= 16 || month >= 10) {
        cout << "You were born in Fall or Winter";
    }
    else if (month <= 2 || month == 3 && day <= 15) {
        cout << "You were born in Fall or Winter";
    }
    else if (month == 3 && day >= 16 || month >= 4 && month <= 8) {
        cout << "You were born in Spring or Summer";
    }
    else if (month == 9 && day <= 15) {
        cout << "You were born in Spring or Summer";
    }

    cout << endl;

    if (year % 4 == 0 && year % 100 != 0) {
        cout << "You were born in a leap year.";
    }
    else {
        cout << "You were not born in a leap year.";
    }
    cout << endl;

    return 0;
}