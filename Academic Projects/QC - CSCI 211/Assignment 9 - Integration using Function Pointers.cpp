//Use function pointers to integrate different mathematical functions

#include <iostream>
using namespace std;

typedef double (*FUNC)(double);



double integrate(FUNC f, double a, double b) {

	double sum = 0;
	for (double i = b; i > a; i -= 0.0001) {
		sum += 0.0001 * f(i);
	}

	return sum;
}



double line(double x) { // this is the function y=x
	return x;
}



double square(double x) { // this is the function y=x^2
	return x * x;
}



double cube(double x) { // this is the function y=x^3
	return x * x * x;
}



int main() {

	cout << "The integral of f(x)=x^1 between 1 and 5 is: " << integrate(line, 1, 5) << endl;
	cout << "The integral of f(x)=x^2 between 1 and 5 is: " << integrate(square, 1, 5) << endl;
	cout << "The integral of f(x)=x^3 between 1 and 5 is: " << integrate(cube, 1, 5) << endl;

	return 0;
}
