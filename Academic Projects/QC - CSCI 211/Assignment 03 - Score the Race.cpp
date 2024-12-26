//Determine the winner of a team race

#include <iostream>
using namespace std;

int main() {

	string raceResults = "";

	while (raceResults != "DONE") {

	LoopTop:
		cout << "Enter the race results (Ex. ZZAZAA). Enter 'DONE' when you wish to quit: ";
		cin >> raceResults;

		if (raceResults == "DONE") break;

		// If letters are not all capital, try again
		for (int i = 0; i < raceResults.length(); i++) {
			if (raceResults[i] < 'A' or raceResults[i] > 'Z') {
				cout << "All letters must be capital. Try again." << endl;
				goto LoopTop;
			}
		}

		// Determine number of teams
		string teamNames = raceResults;
		int numMembersOnTeam = 1;

		for (int i = 0; i < teamNames.size(); i++) {
			for (int j = i + 1; j < teamNames.size(); j++) {
				if (teamNames[j] == teamNames[i]) {
					if (teamNames[j] == teamNames[0]) numMembersOnTeam++;
					teamNames.erase(j, 1);
					j--;
				}
			}
		}

		int numTeams = teamNames.size();

		// Sort teamNames array alphabetically
		for (int i = 0; i < numTeams; i++) {
			for (int j = 0; j < numTeams; j++) {
				if (teamNames[j] > teamNames[i]) {
					char temp = teamNames[i];
					teamNames[i] = teamNames[j];
					teamNames[j] = temp;
				}
			}
		}

		// Check that all teams have same number of racers
		for (int i = 0; i < numTeams; i++) {
			int teamMemberCounter = 0;

			for (int j = 0; j < raceResults.size(); j++) {
				if (raceResults[j] == teamNames[i]) {
					teamMemberCounter += 1;
				}
			}

			if (teamMemberCounter != numMembersOnTeam) {
				cout << "Error! Not all teams have the same number of runners!" << endl << endl;
				goto LoopTop;
			}
		}

		// Output number of teams and the members
		cout << "There are " << numTeams << " teams in the race." << endl;
		cout << "Each team has " << numMembersOnTeam << " runners in the race." << endl;
		
		// Compute the scores for each team
		float teamScores[numTeams];
		
		
		for (int i = 0; i < numTeams; i++) {
			int scoreCounter = 0;

			for (int j = 0; j < raceResults.size(); j++) {
				if (raceResults[j] == teamNames[i]) {
					scoreCounter += (j + 1);
				}
			}

			teamScores[i] = float(scoreCounter) / numMembersOnTeam;
		}

		// Output the names alphabetically with team score
		for (int i = 0; i < numTeams; i++) {
			cout << "Team " << teamNames[i] << ": " << teamScores[i] << endl;
		}

		// Outpute name and score of winning team
		int min = 0;
		for (int i = 0; i < numTeams; i++) {
			if (teamScores[i] < teamScores[min]) {
				min = i;
			}
		}

		cout << "The winning team is Team " << teamNames[min] << " with a score of " <<
			teamScores[min] << endl << endl;

	}

	return 0;
}