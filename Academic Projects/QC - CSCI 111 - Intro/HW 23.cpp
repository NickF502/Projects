#include <iostream>
#include <ctime>
using namespace std;



void fillArray(int a[], int capacity) {
    for (int i = 0; i < capacity; i++) {
        a[i] = rand() % 100;
    }
}

void printArray(int a[], int capacity) {
    for (int i = 0; i < capacity; i++) {
        cout << a[i] << " ";
    }
    cout << endl;
}

void insertionSort(int a[], int capacity) {
    for (int newpos = 0; newpos < capacity; newpos++) {
        for (int current = 1; current < capacity; current++) {

            if (a[current] < a[current - 1]) {
                int temp = a[current];
                a[current] = a[current - 1];
                a[current - 1] = temp;
            }

        }
    }
}

void readInput(int a[], int capacity) {
    for (int i = 0; i < capacity; i++) {
        cin >> a[i];
    }
}

double arrayAverage(int a[], int capacity) {
    double sum = 0;
    for (int i = 0; i < capacity; i++) {
        sum += a[i];
    }

    return sum / capacity;
}

void greaterThanAverage(int a[], int capacity, double avg) {
    for (int i = 0; i < capacity; i++) {
        if (a[i] >= avg) {
            cout << a[i] << " ";
        }
    }
    cout << endl;
}

void printBoardCols(int a[], int capacity) {

    for (int i = 0; i < capacity; i++) {

        for (int c = 0; c < 8; c++) {

            if (a[i] == c) cout << "Q";
            else cout << ".";

        }

        cout << endl;

    }

}

void printBoardRows(int a[], int capacity) {

    for (int c = 0; c < 8; c++) {

        for (int i = 0; i < capacity; i++) {

            if (a[i] == c) cout << "Q";
            else cout << ".";

        }

        cout << endl;

    }

}

void fillWithDistinctNums(int a[], int capacity) {

    for (int i = 0; i < capacity; i++) {
        bool notUsed = false;

        while (notUsed == false) {
            a[i] = rand() % 20 + 1;
            notUsed = true;

            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) notUsed = false;
            }

        }


    }
}



int main() {
    srand(time(0));



    // Exercise 1

    int cap = 10;
    int nums[cap];
    fillArray(nums, cap); // fill array with random numbers
    printArray(nums, cap); // print array
    insertionSort(nums, cap); // sort elements in array
    printArray(nums, cap); // print sorted array

    cout << endl << endl << endl;



    // Exercise 2

    int nums1[10];
    double average;
    cout << "Enter ten numbers:\n";
    // read in numbers from user
    readInput(nums1, 10);
    // compute average of elements in array
    average = arrayAverage(nums1, 10);
    // print average value to monitor
    cout << average << endl;
    // print array elemnets that are greater than average
    greaterThanAverage(nums1, 10, average);

    cout << endl << endl << endl;


    // Exercise 3
    int queenColumns[8];
    cout << "Enter columns for queens:\n";
    readInput(queenColumns, 8);
    printBoardCols(queenColumns, 8);

    cout << endl << endl << endl;



    // Exercise 4
    int queenRows[8];
    cout << "Enter rows for queens:\n";
    readInput(queenRows, 8);
    printBoardRows(queenRows, 8);

    cout << endl << endl << endl;



    // Exercise 5
    int nums5[20];
    fillWithDistinctNums(nums5, 20);
    printArray(nums5, 20);

    cout << endl << endl << endl;



    return 0;
}