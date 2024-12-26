#include <iostream>
using namespace std;
int main(){
   
    //Exercise 2.1.1
    cout << "Exercise 2.1.1" << endl;
   
    int num;
   
    cout << "Enter a number to see if it is divisible by 7: ";
    cin >> num;
   
    if(num % 7 == 0){
        cout << num << " is divisible by 7.";
    }
    else{
        cout << num << " is not divisible by 7.";
    }
   
    cout << endl;
    cout << endl;
    cout << endl;
   
   
   
    //Exercise 2.2.1
    cout << "Exercise 2.2.1" << endl;

    int n1;
    int n2;
   
    cout << "Enter an upper limit: ";
    cin >> n1;
   
    cout << "Enter a lower limit: ";
    cin >> n2;
   
    if(n2 > n1){
        cout << "Error: Lower limit is higher than upper limit!";
    }
   
    while(n2 <= n1){
        cout << n2 << " ";
        ++n2;
    }
   
    cout << endl;
    cout << endl;
    cout << endl;
   
   
   
    //Exercise 2.2.2
    cout << "Exercise 2.2.2" << endl;
   
    int x;
   
    cout << "Enter a number to count down to 1 from: ";
    cin >> x;
   
    if (x <= 0){
        cout << "Error: Number is less than 1!";
    }
   
    while(x >= 1){
        cout << x << " ";
        --x;
    }
   
    cout << endl;
    cout << endl;
    cout << endl;
   
   
   
    //Exercise 2.2.3
    cout << "Exercise 2.2.3" << endl;
   
    int y1;
    int y2;
   
    cout << "Enter an upper limit: ";
    cin >> y1;
   
    cout << "Enter a lower limit: ";
    cin >> y2;
   
    if(y2 > y1){
        cout << "Error: Lower limit is higher than upper limit!";
    }
   
    while(y2 <= y1){
        if(y2 % 2 == 0){
            cout << y2 << " ";
        }
        ++y2;
    }
   
    cout << endl;
    cout << endl;
    cout << endl;
   
    return 0;
}

