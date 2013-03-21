/*
 * main.cpp
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <iostream>

int show_menu() {
	using namespace std;

	cout << endl;
	cout << "Menu" << endl;
	cout << endl;
	cout << "\t1. Create new event" << endl;
	cout << "\t2. Add entrant" << endl;
	cout << "\t3. Add course" << endl;
	cout << "\t4. Quit" << endl;

	cout << endl;
	cout << ">> ";
	int result;
	cin >> result;
	return result;
}

int main(int argc, char* argv[]) {
	using namespace std;

	cout << "Event Creator" << endl;

	bool running = true;
	while (running) {
		int input = show_menu();
		switch (input) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				running = false;
				break;
			default:
				// invalid option
				// do nothing
				break;
		}
	}

	return 0;
}
