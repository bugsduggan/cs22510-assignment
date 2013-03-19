#include <iostream>

int show_menu() {
	using namespace std;

	int response;

	cout << "Main menu:" << endl;
	cout << "\t1. Query individual competitor" << endl;
	cout << "\t2. Count competitors waiting to start" << endl;
	cout << "\t3. Count competitors running" << endl;
	cout << "\t4. Count competitors finished" << endl;
	cout << "\t5. Count competitors excluded" << endl;
	cout << "\t6. List all competitors' status" << endl;
	cout << "\t7. Quit" << endl;
	cout << endl;

	cout << ">> ";
	cin >> response;

	return response;
}

int main(int argc, char* argv[]) {
	using namespace std;

	int input = -1;
	bool running = true;

	cout << "Event manager" << endl;
	cout << endl;

	while (running) {
		input = show_menu();
		switch (input) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				running = false;
				break;
			default:
				// invalid input, do nothing
				break;
		}
	}

	cout << "Program terminated" << endl;

	return 0;
}
