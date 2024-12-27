#include <iostream>
using namespace std;



void readInput(string a[], int capacity) {
    for (int i = 0; i < capacity; i++) {
        cin >> a[i];
    }
}



void getPrefixes(string w[], string p[], int capacity) {
    for (int i = 0; i < capacity; i++) {
        p[i] = w[i].substr(0, 3);
    }
}



string concatenateStrings(string p[], int capacity) {
    string catedString;
    for (int i = 0; i < capacity; i++) {
        catedString.append(p[i].substr(0, 3));
    }
    return catedString;
}



string bookEndString(string& s, int n) {
    if (s.substr(0, 1) == s.substr(s.length() - 1, 1)) {
        string letter = s.substr(0, 1);
        for (int i = 1; i <= n; i++) {
            s.insert(0, letter);
            s.insert(s.length() - 1, letter);
        }
    }
    else {
        for (int i = 1; i <= n; i++) {
            s.insert(0, "Z");
            s.insert(s.length(), "Z");
        }

    }

    return s;
}



void swapFirstLetters(string& a, string& b) {
    string firstA = a.substr(0, 1);
    string firstB = b.substr(0, 1);
    a.replace(0, 1, firstB);
    b.replace(0, 1, firstA);
}



int longestString(string a, string b) {
    if (a.length() > b.length()) {
        cout << a << " is the longest string.";
        return a.length();
    }
    else {
        cout << b << " is the longest string.";
        return b.length();
    }
}



void repeatHello(int length) {
    for (int i = 1; i <= length; i++) {
        cout << "Hello";
    }
}



void fillCharArray(char a[2][4], int rCap, int cCap) {
    for (int r = 0; r < rCap; r++) {
        for (int c = 0; c < cCap; c++) {
            a[r][c] = char((rand() % (122 - 97 + 1) + 97));

        }
    }
}



void printArray(char a[2][4], int rCap, int cCap) {
    for (int r = 0; r < rCap; r++) {
        for (int c = 0; c < cCap; c++) {
            cout << a[r][c] << "\t";

        }
        cout << endl;
    }
}



void vowelsPerRow(char a[2][4], int rCap, int cCap) {
    int vowelRowCounter = 0;
    for (int r = 0; r < rCap; r++) {
        for (int c = 0; c < cCap; c++) {
            vowelRowCounter = 0;
            if (a[r][c] == int('a') || a[r][c] == int('e') || a[r][c] == int('i') || a[r][c] == int('o') || a[r][c] == int('u')) {
                vowelRowCounter++;
            }
            if (vowelRowCounter > 0) {
                cout << "Row " << r << " contains at least one vowel" << endl;
                break;
            }
        }
    }
}



void vowelsPerCol(char a[2][4], int rCap, int cCap) {
    int vowelColCounter = 0;
    for (int c = 0; c < cCap; c++) {
        for (int r = 0; r < rCap; r++) {
            vowelColCounter = 0;
            if (a[r][c] == int('a') || a[r][c] == int('e') || a[r][c] == int('i') || a[r][c] == int('o') || a[r][c] == int('u')) {
                vowelColCounter++;
            }
            if (vowelColCounter > 0) {
                cout << "Column " << c << " contains at least one vowel" << endl;
                break;
            }
        }
    }
}



void fillCharArray(char a[][3], int capacity) {
    for (int r = 0; r < capacity; r++) {
        for (int c = 0; c < capacity; c++) {
            a[r][c] = char((rand() % (122 - 97 + 1) + 97));

        }
    }
}



void printArray(char a[][3], int capacity) {
    for (int r = 0; r < capacity; r++) {
        for (int c = 0; c < capacity; c++) {
            cout << a[r][c] << "\t";

        }
        cout << endl;
    }
}



void countVowels(char a[][3], int capacity) {
    int vowelCounter = 0;
    for (int r = 0; r < capacity; r++) {
        for (int c = 0; c < capacity; c++) {
            if (a[r][c] == int('a') || a[r][c] == int('e') || a[r][c] == int('i') || a[r][c] == int('o') || a[r][c] == int('u')) {
                vowelCounter++;
            }
        }
    }
    cout << "There are " << vowelCounter << " vowels in this array.";
}



int main() {

    // HW 24 Exercise 1
    string words[5];
    string prefixes[5];

    cout << "Enter five words: ";

    readInput(words, 5);
    getPrefixes(words, prefixes, 5);

    string s1 = concatenateStrings(prefixes, 5);

    cout << "Concatenated string is " << s1 << endl;



    cout << endl << endl << endl;



    // HW 24 Exercise 2
    string s2;
    int num;

    cout << "Enter a word with at least three letters: ";
    cin >> s2;

    while (s2.length() < 3) {
        cout << "Try again: Enter a word with at least three letters: ";
        cin >> s2;
    }

    cout << "Enter a number greater than 0: ";
    cin >> num;

    while (num <= 0) {
        cout << "Try again: Enter a number greater than 0: ";
        cin >> num;
    }

    bookEndString(s2, num);

    cout << "The modified word is: " << s2 << endl;



    cout << endl << endl << endl;



    // HW 4 Exercise 3
    string fName, lName;
    int length;

    cout << "Please enter your first name: ";
    cin >> fName;

    cout << "Please enter your last name: ";
    cin >> lName;

    swapFirstLetters(fName, lName);

    cout << "Your new first name is " << fName << endl;
    cout << "Your new last name is " << lName << endl;

    length = longestString(fName, lName);
    cout << endl;
    repeatHello(length);



    cout << endl << endl << endl;



    // HW 4 Exercise 3
    srand(time(0));

    char arr[2][4];

    fillCharArray(arr, 2, 4);
    printArray(arr, 2, 4);

    vowelsPerRow(arr, 2, 4);

    vowelsPerCol(arr, 2, 4);



    cout << endl << endl << endl;



    // HW 4 Exercise 2
    srand(time(0));

    char cArray[3][3];

    fillCharArray(cArray, 3);
    printArray(cArray, 3);

    countVowels(cArray, 3);



    cout << endl << endl << endl;



    return 0;
}