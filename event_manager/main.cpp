#include <iostream>

int show_menu() {
	using namespace std;

	int response;

	cout << "Main menu:" << endl;
	cout << "\t1. Count competitors waiting to start" << endl;
	cout << "\t2. Count competitors running" << endl;
	cout << "\t3. Count competitors finished" << endl;
	cout << "\t4. Count competitors excluded" << endl;
	cout << "\t5. List all competitors' status" << endl;
	cout << "\t6. Quit" << endl;
	cout << endl;

	cout << ">> ";
	cin >> response;

	return response;
}

int main(int argc, char* argv[]) {
	using namespace std;

	int input = -1;

	cout << "Event manager" << endl;
	cout << endl;

	while (input != 6) {
		input = show_menu();
		cout << input << endl;
	}

	cout << "Program terminated" << endl;

	return 0;
}
