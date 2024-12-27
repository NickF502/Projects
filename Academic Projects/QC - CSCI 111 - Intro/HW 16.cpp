#include <iostream>
using namespace std;



int reverse(int b) {
    int ans = 0;

    while (b > 0) {
        int rem = b % 10;
        ans = ans * 10 + rem;
        b /= 10;
    }

    return ans;
}



int changeThrees(int a) {

    if (a == 3)
        return 2;

    else {
        int b = 0;

        while (a > 0) {

            int digit = a % 10;

            if (digit == 3) {
                cout << "Removed a 3" << endl;
                digit = 2;
            }

            b = b * 10 + digit;
            a /= 10;
        }

        return reverse(b);
    }
}



int recursiveSequence(int a) {
    if (a == 0) {
        return 0;
    }
    if (a == 1) {
        return 3;
    }
    return (2 * recursiveSequence(a - 1) + 3);
}



int getFirstThreeSum(int a) {

    if (a >= 1000) {
        a /= 10;
        getFirstThreeSum(a);
    }

    return (a % 10 + (a / 10) % 10 + (a / 100) % 10);
}



void printRange(int r) {
    if (r % 2 == 0) {
        for (int i = 0; i <= r; i++) {
            cout << i << " ";
        }
    }
    else {
        for (int i = 1; i <= r; i++) {
            cout << i << " ";
        }
    }
}



void applyCurve(int& a, int& b, int& c, int curve) {
    a += curve;
    b += curve;
    c += curve;
}



void swap(int& a, int& b) {
    int temp;
    temp = a;
    a = b;
    b = temp;
}



void updateAscending(int& a, int& b, int& c) {

    if (a > c) {
        swap(a, c);
    }
    if (a > b) {
        swap(a, b);
    }
    if (b > c) {
        swap(b, c);
    }
}



void updateDescending(int& a, int& b, int& c) {

    if (a < c) {
        swap(a, c);
    }
    if (a < b) {
        swap(a, b);
    }
    if (b < c) {
        swap(b, c);
    }
}



int main() {

    int n8, new_n8;

    cout << "Enter a positive integer greater than or equal to 3: ";
    cin >> n8;

    new_n8 = changeThrees(n8);

    cout << new_n8 << endl << "I don't like numbers with 3's in them";



    cout << endl << endl << endl;



    int num7;

    cout << "Enter a number between 5 and 20: ";
    cin >> num7;

    if (num7 < 5 || num7 > 20) {
        cout << endl << "Goodbye";
        exit(7);
    }

    for (int i = 0; i < num7; i++) {
        cout << " " << recursiveSequence(i);
    }



    cout << endl << endl << endl;



    int int6;
    int range;

    cout << "Enter a number greater than or equal to 100: ";
    cin >> int6;

    while (int6 < 100) {
        cout << "Invalid input. Try again: ";
        cin >> int6;
    }

    range = getFirstThreeSum(int6);

    cout << "The sum of the first three digits is " << range << endl;

    printRange(range);



    cout << endl << endl << endl;



    int score1, score2, score3;
    int curve;

    cout << "Enter three score values out of 100: ";
    cin >> score1 >> score2 >> score3;

    while (score1 > 100 || score2 > 100 || score3 > 100 || score1 < 0 || score2 < 0 || score3 < 0) {
        cout << "Scores must be between 0 and 100. Try again: ";
        cin >> score1, score2, score3;
    }

    if (score1 >= score2 && score1 >= score3) {
        curve = 100 - score1;
    }
    else if (score2 >= score1 && score2 >= score3) {
        curve = 100 - score2;
    }
    else if (score3 >= score2 && score3 >= score1) {
        curve = 100 - score3;
    }

    cout << endl << "The curve is " << curve;

    applyCurve(score1, score2, score3, curve);

    cout << endl << "The scores after applying the curve are " << score1 << " " << score2 << " " << score3;



    cout << endl << endl << endl;



    int a, b, c;
    string order;

    cout << "Enter three integers: ";
    cin >> a >> b >> c;

    cout << endl << "Would you like these numbers sorted in ascending or descending order?: ";
    cin >> order;

    if (order == "ascending") {
        updateAscending(a, b, c);
    }
    else if (order == "descending") {
        updateDescending(a, b, c);
    }

    cout << "The sorted variables now store a = " << a << ", b = " << b << ", c = " << c;



    cout << endl << endl << endl;



    return 0;
}