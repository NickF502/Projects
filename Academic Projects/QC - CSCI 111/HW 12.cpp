#include <iostream>
using namespace std;

int main() {

    // Exercise 1

    int n1;
    int randint1;

    cout << "Enter an integer greater than or equal to 10: ";
    cin >> n1;

    while (n1 < 10) {
        cout << "Invalid input. Try again: ";
        cin >> n1;
    }

    randint1 = rand() % 11 + 1;

    cout << "Random number between 1 and " << n1 << " is " << randint1;

    cout << endl << endl << endl;



    // Exercise 2

    int n2a;
    int n2b;
    int modnum;
    int lowest;
    int randint2;

    cout << "Enter two positive integers with a difference of at least 10: ";
    cin >> n2a >> n2b;

    if (abs(n2a - n2b) < 10 || n2a < 0 || n2b < 0) {
        cout << "Invalid input. Goodbye. ";
        exit(1);
    }

    if (n2a < n2b) {
        lowest = n2a;
    }
    else {
        lowest = n2b;
    }

    modnum = abs(n2a - n2b);

    randint2 = (rand() % modnum) + lowest;

    cout << "Random number between " << n2a << " and " << n2b << " is " << randint2;

    cout << endl << endl << endl;



    // Exercise 3

    int numdicerolls;
    int roll;
    int onecounter = 0;

    cout << "Enter a number of dice rolls: ";
    cin >> numdicerolls;

    if (numdicerolls <= 0) {
        cout << "We are not playing games.";
        exit(2);
    }

    for (int i = 1; i <= numdicerolls; i++) {
        roll = (rand() % 6) + 1;
        if (roll == 1) { onecounter++; }
        cout << "Roll " << i << ": " << roll << endl;
    }

    cout << "Total number of ones rolled: " << onecounter;

    cout << endl << endl << endl;



    // Exercise 4

    int rand4 = (rand() % 99) + 1;
    int guess;
    int guesscounter = 1;

    cout << "Guess the number I'm thinking of between 1 and 100: ";
    cin >> guess;

    while (guess != rand4) {
        guesscounter++;

        if (guess > rand4) { cout << "Too big"; }
        else { cout << "Too small"; }
        cout << endl;

        cin >> guess;
    }

    cout << "Congratulations! You took " << guesscounter << " guesses.";

    cout << endl << endl << endl;



    // Exercise 5

    int coinflip = 0;
    double prob_heads = 0;
    double prob_tails = 0;
    int headscounter = 0;
    int tailscounter = 0;

    for (int i = 1; i <= 10; i++) {
        coinflip = rand() % 2;

        if (coinflip == 0) { tailscounter++; }
        else { headscounter++; }
    }

    prob_heads = double(headscounter) / 10;

    cout << "Probability of heads given 10 tosses = " << prob_heads << endl;
    cout << "Probability of tails given 10 tosses = " << 1.0 - prob_heads << endl << endl;

    headscounter = 0;
    tailscounter = 0;


    for (int i = 1; i <= 100; i++) {
        coinflip = rand() % 2;

        if (coinflip == 0) { tailscounter++; }
        else { headscounter++; }
    }

    prob_heads = double(headscounter) / 100;

    cout << "Probability of heads given 100 tosses = " << prob_heads << endl;
    cout << "Probability of tails given 100 tosses = " << 1.0 - prob_heads << endl << endl;



    headscounter = 0;
    tailscounter = 0;


    for (int i = 1; i <= 1000; i++) {
        coinflip = rand() % 2;

        if (coinflip == 0) { tailscounter++; }
        else { headscounter++; }
    }

    prob_heads = double(headscounter) / 1000;

    cout << "Probability of heads given 1000 tosses = " << prob_heads << endl;
    cout << "Probability of tails given 1000 tosses = " << 1.0 - prob_heads << endl << endl;


    headscounter = 0;
    tailscounter = 0;


    for (int i = 1; i <= 5000; i++) {
        coinflip = rand() % 2;

        if (coinflip == 0) { tailscounter++; }
        else { headscounter++; }
    }

    prob_heads = double(headscounter) / 5000;

    cout << "Probability of heads given 5000 tosses = " << prob_heads << endl;
    cout << "Probability of tails given 5000 tosses = " << 1.0 - prob_heads << endl << endl;


    return 0;
}