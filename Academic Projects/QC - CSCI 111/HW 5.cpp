#include <iostream>
using namespace std;
int main() {

	// Q1

	int num1, num2, num3;

	cout << "Enter three numbers: ";
	cin >> num1 >> num2 >> num3;
	cout << endl;

	if (num1 > num2 && num2 > num3) {
		cout << "Decreasing" << endl;
	}
	else if (num1 < num2 && num2 < num3) {
		cout << "Increasing" << endl;
	}
	else {
		cout << "Neither" << endl;
	}

	cout << endl << endl << endl;

	// Q2
	int month;

	cout << "Enter a month's integer: ";
	cin >> month;
	cout << endl;

	if (month == 2) {
		cout << "28 or 29 days";
	}
	else if (month == 9 || month == 4 || month == 6 || month == 11) {
		cout << "30 days";
	}
	else {
		cout << "31 days";
	}
	cout << endl << endl << endl;

	// Q4

	int angle1, angle2, angle3;

	cout << "Enter an angle in degrees: ";
	cin >> angle1;
	cout << endl << "Enter an angle in degrees: ";
	cin >> angle2;
	cout << endl << "Enter an angle in degrees: ";
	cin >> angle3;
	cout << endl;

	if (angle1 + angle2 + angle3 == 180) {
		cout << "A triangle can be formed from these angles." << endl;

		if (angle1 == 60 && angle2 == 60 && angle3 == 60) {
			cout << "The triangle is an equilateral triangle." << endl;
		}
		else if (angle1 == 90 || angle2 == 90 || angle3 == 90) {
			cout << "The triangle is a right triangle." << endl;
		}
		else {
			cout << "The triangle is neither equilateral nor right." << endl;
		}

	}
	else {
		cout << "A triangle cannot be formed from these angles." << endl;
	}


	return 0;
}