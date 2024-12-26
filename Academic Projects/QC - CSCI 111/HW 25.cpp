#include <iostream>
using namespace std;

void lowerCaseString(string& a) {
    for (int i = 0; i < a.length(); i++) {
        if (int(a[i]) <= 90 && int(a[i]) >= 65) {
            a[i] = char(int(a[i]) + 32);
        }
    }
}

string pigLatinConverter(string a) {
    char firstChar = a[0];
    string firstStr = a.substr(0, 1);

    if (int(a[0]) == int('a') || int(a[0]) == int('e') || int(a[0]) == int('i') || int(a[0]) == int('o') || int(a[0]) == int('u')) {
        a.append("way");
    }
    else {
        a.erase(0, 1);
        a.append(firstStr);
        a.append("ay");
    }

    return a;
}

void capitalizeFirstLetter(string& a) {
    a[0] = char(int(a[0]) - 32);
}

int main() {

    string firstName;
    string lastName;

    cout << "Enter your first name: ";
    cin >> firstName;

    cout << "Enter your last name: ";
    cin >> lastName;

    lowerCaseString(firstName);
    lowerCaseString(lastName);

    firstName = pigLatinConverter(firstName);
    lastName = pigLatinConverter(lastName);

    capitalizeFirstLetter(firstName);
    capitalizeFirstLetter(lastName);

    cout << "Your name in Pig Latin is " << firstName << " " << lastName << ".";

    cout << endl << endl << endl;



    return 0;
}

