#include <iostream>
#include <cmath>

using namespace std;
int main() {



//Exercise 1.3.1
double ctemp;
double ftemp;

cout << "Input a temperatue in Fahrenheit: ";
cin >> ftemp;

ctemp = (ftemp - 32.0) / 1.8;

cout << "Temperature in Celcius is: " << ctemp;

cout << endl;
cout << endl;
cout << endl;



//Exercise 1.3.2
double f_temp;

cout << "Input a temperatue in Fahrenheit: ";
cin >> f_temp;

f_temp = (f_temp - 32.0) / 1.8;

cout << "Temperature in Celcius is: " << f_temp;

cout << endl;
cout << endl;
cout << endl;



//Exercise 1.3.3
double x;

cout << "Input a value, x, to be cubed: x=";
cin >> x;

x = pow(x, 3);

cout << "The cube is " << x;

cout << endl;

return 0;
}

