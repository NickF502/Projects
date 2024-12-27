//Solve the Towers of Hanoi problem

#include <iostream>
#include <vector>
using namespace std;



int main() {

	vector<int> t[3]; //three towers A,B,C represented as an array of 3 vectors

	int n; //number of rings
	int candidate; //the ring being moves (1-smallest)
	int to; //the number tower the ring is being moved to
	int from; //the number tower the ring is being moved from
	int move = 0; //counts the move number

	//Prompt user for number of rings n
	cout << "Please enter a number of rings to move: ";
	cin >> n;
	cout << endl;

	//Initialize the 3 towers.
	for (int i = n + 1; i >= 1; i--)
		t[0].push_back(i); //t[0] contains all numbers n+1 -> 1

	t[1].push_back(n + 1); //t[1] contains n+1
	t[2].push_back(n + 1); //t[2] contains n+1

	//Initialize variables for first move
	candidate = 1; //first move will take ring 1...
	from = 0; //...from tower 0 and place it on...
	if (n % 2 == 1) to = 1; //...tower 1 if n is odd
	else to = 2; //...tower 2 if n is even


	/* Note that the to tower changes because if n is odd, the rings are moved to the right, but
	* if n is even, the rings are moved to the left. The towers are thought to "wrap around",
	* meaning tower 2 is to the left of tower 0 and tower 0 is to the right of tower 2. This is
	* also why in the "get next to tower" section, we move right two towers if n is even: this
	* has the same effect of moving left one tower when taking the module with respect to 3
	*/

	while (t[1].size() < n + 1) { //While there are still rings to transfer to tower B...

	//Write the directions
		cout << "Move number " << ++move << ": Transfer ring " << candidate << " from tower "
			<< char(from + 65) << " to tower " << char(to + 65) << endl;

		//Move the disks around
		t[to].push_back(t[from].back()); // Push the top of the "from" tower to the "to" tower
		t[from].pop_back(); // Remove the ring from the "from" tower

		//Get the next "from tower"
		if (t[(to + 1) % 3].back() < t[(to + 2) % 3].back()) from = (to + 1) % 3;
		else from = (to + 2) % 3;

		//Get the next "to tower"
		if (n % 2 == 0) { //If there are an even number of rings
			if (t[from].back() < t[(from + 2) % 3].back()) to = (from + 2) % 3;
			else to = (from + 1) % 3;
		}
		else { //If there are an odd number of rings
			if (t[from].back() < t[(from + 1) % 3].back()) to = (from + 1) % 3;
			else to = (from + 2) % 3;
		}

		//Get the next candidate, which is on top of the new "from" tower
		candidate = t[from].back();
	}

	return 0;
}