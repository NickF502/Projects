#include <iostream>
#include <ctime>
using namespace std;

int main() {

    // Exercise 1
    int array1[5];

    cout << "Enter five integers: ";

    for (int i = 0; i < 5; i++) {
        cin >> array1[i];
    }

    for (int i = 0; i < 5; i++) {
        if (array1[i] % 2 == 1) cout << array1[i] << " ";
    }

    cout << endl;

    for (int i = 0; i < 5; i++) {
        if (array1[i] % 2 == 0) cout << array1[i] << " ";
    }

    cout << endl;

    int sum1 = 0;
    for (int i = 0; i < 5; i++) {
        sum1 += array1[i];
    }

    cout << "Sum of all numbers on screen: " << 2 * sum1;

    cout << endl << endl << endl;



    // Exercise 2
    srand(time(0));
    int array2[10];

    for (int i = 0; i < 10; i++) {
        array2[i] = rand() % 20 + 1;
    }

    for (int i = 0; i < 10; i++) {
        cout << array2[i] << "\t";
    }

    cout << endl;

    for (int i = 0; i < 10; i++) {
        cout << array2[i] + 2 << "\t";
    }

    cout << endl << endl << endl;



    // Exercise 3
    int array3[50];

    for (int i = 0; i < 50; i++) {
        array3[i] = rand() % 100 + 1;
    }

    cout << endl;

    for (int i = 0; i < 50; i++) {
        cout << array3[i] << "\t";
    }

    cout << endl;

    int max = array3[0];
    for (int i = 0; i < 50; i++) {
        if (array3[i] > max) max = array3[i];
    }

    int min = array3[0];
    for (int i = 0; i < 50; i++) {
        if (array3[i] < min) min = array3[i];
    }

    cout << "The largest number in the array is " << max << endl << "The smallest number in the array is " << min;

    cout << endl << endl << endl;



    // Exercise 4
    int array4[10];

    cout << "Enter ten integers: ";

    for (int i = 0; i < 10; i++) {
        cin >> array4[i];
    }

    int sum4 = 0;
    for (int i = 0; i < 10; i++) {
        sum4 += array4[i];
    }

    double average = double(sum4) / 10;

    cout << endl << "Average of entries in array: " << average << endl;

    int greatersum = 0;
    int greatercounter = 0;

    for (int i = 0; i < 10; i++) {
        if (array4[i] >= average) {
            greatersum += array4[i];
            greatercounter++;
        }
    }

    double greateraverage = double(greatersum) / greatercounter;

    cout << "Average of entries greater than overall average: " << greateraverage;

    cout << endl << endl << endl;



    // Exercise 5

    string names5[3] = { "Nick", "Alice", "Bob" };
    int scores5[3] = { 100, 90, 80 };

    for (int i = 0; i < 3; i++) {
        cout << names5[i] << "'s score is " << scores5[i] << endl;
    }



    return 0;
}