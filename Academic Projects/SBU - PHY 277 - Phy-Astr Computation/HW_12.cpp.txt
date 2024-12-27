//Name: Nicholas Farkash
//Purpose: To gain experience with C++ syntax and in using Newton-Raphson
//         iteration to find roots.
//Date: November 22nd, 2021
//Due:  November 30th, 2021 @ 6:00PM


//There are five roots in this function. The approximate values of these roots,
//along with some initial values that can be used to find them, are below:

//     -----------------------------------------------------------
//     |          Roots          |        Initial Guesses        |
//     -----------------------------------------------------------
//     |         2.61298         |   1.0   3.0   4.0   6.0       |
//     |         1.9175          |   2.0   5.0   7.0             |
//     |         0.0             |  -0.5   0.0   0.5             |
//     |        -1.9175          |  -7.0  -5.0  -2.0             |
//     |        -2.61298         |  -6.0  -4.0  -3.0  -1.0       |
//     -----------------------------------------------------------

#include <iostream>
#include <cmath>
using namespace std;
int main (){

  double xold;                     //Old estimate of root
  double xnew;                     //New estimate of root
  double fold;                     //Value of f(xold)
  double fpold;                    //Value of df/dx (xold)
  double epsilon = 1.0e-30;        //Determines if the value has converged

  int niter = 1;                   //Number of iterations
  const int N = 1000;              //Max number of iterations allowed
  bool converged;                  //True if value has converged, false if not

  cout << "Enter an initial guess for the root of the equation:" << endl;
  cin >> xold;

  while (niter <= N){
    fold = xold + 3.0*sin(2.0*xold);  //Function evaluated at old estimate
    fpold = 1.0 + 6.0*cos(2.0*xold);  //Derivative evaluated at old estimate

    if (abs(fpold) < 1.0e-20){             //If derivative is 0.0 ...
      cout << "The value of the derivative is approximately zero." << endl;
      cout << "Method Failed!" << endl;    //... this method dpesn't work
      return 2;
    }

    xnew = xold - fold/fpold;     //Make the new estimate from the old one
    
    if (xold != 0.0){        //Check for convergence if root is not 0.0
      converged = abs(xnew-xold) < epsilon*abs(xold);
    }
    else{                    //Check for convergence if root is 0.0
      converged = abs(xnew-xold) < epsilon;
    }

    if (converged){          //If the value converges, display the root
      cout << "Root is " << xnew << endl;
      return 0;
    }

    xold = xnew;        //Make the old guess the new one for the next pass
    niter = niter+1;    //Increase integer counter by one
      
  }          //End of while loop
    
  cout << "Method Failed: Too many iterations!" << endl;
  return 1;
             //Executes if the value does not converge
}
