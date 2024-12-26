#include <iostream>
using namespace std;

int main() {
   
   
    //Exercise 1
    cout << "Exercise 1:" << endl;
   
    int x;
    int y;
   
    cout << "Enter a value for x: ";
    cin >> x;
   
    cout << "Enter a value for y: ";
    cin >> y;

    cout << "The sum of " << x << " and "<< y << " is " << x+y << endl;

    cout << y << " subtracted from " << x << " is " << y-x << endl;
   
    cout << "The product of " << x <<  " and " << y << " is " << x*y << endl;
   
    cout << "The average of " << x <<  " and " << y << " is " << (x+y)/2.0 << endl;
   
    cout << "The remainder when " << x << " is divided by " << y << " is " << x % y << endl;

    cout << endl << endl << endl;
   
   
    //Exercise 2
    cout << "Exercise 2:" << endl;
   
    double length = 8.5;
    double width = 11.0;
   
    double area;
    double perimeter;
   
    area = length * width;
    perimeter = 2.0 * length + 2.0 * width;
   
    cout << "The area of a letter-size sheet of paper is " << area << endl;
   
    cout << "The perimeter of a letter-size sheet of paper is " << perimeter << endl;
   
    cout << endl << endl << endl;
   
   
    //Exercise 3
    cout << "Exercise 3:" << endl;
   
    string num1;
   
    cout << "Enter a four-digit number: ";
    cin >> num1;
   
    cout << "Your number in reverse is ";
    cout << num1[3];
    cout << num1[2];
    cout << num1[1];
    cout << num1[0];
 
    cout << endl << endl << endl;
   
   
    //Exercise 4
    cout << "Exercise 4:" << endl;
   
    string name;
    int height_in;
    double height_cm;
   
    cout << "Enter your name: ";
    cin >> name;
   
    cout << "Enter your height in inches: ";
    cin >> height_in;
   
    height_cm = height_in * 2.54;
   
    cout << "Hi " << name << ". You are " << height_cm << " centimeters tall.";
   
    cout << endl << endl << endl;
   
   
    //Exercise 5
    cout << "Exercise 5:" << endl;
   
    int three_pointers;
    int two_pointers;
    int total_points;

    cout << "Enter the number of 3-pointers scored: ";
    cin >> three_pointers;
   
    cout << "Enter the number of 2-pointers scored: ";
    cin >> two_pointers;
   
    total_points = three_pointers * 3 + two_pointers * 2;
   
    cout << "Total points scored: " << total_points << endl;
   
    cout << endl << endl << endl;


    return 0;
}