/*
 * main.cpp
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <fstream>
#include <iostream>
#include <string>

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

void write_event(std::string filename, std::string name, std::string date, std::string time) {
	using namespace std;

	ofstream file;
	file.open(filename.c_str());

	file << name << "\n";
	file << date << "\n";
	file << time << "\n";

	file.close();
}

void create_event() {
	using namespace std;

	cout << "Please enter event name" << endl;
	cout << ">> ";
	string name;
	cin >> name;

	cout << "Please enter date" << endl;
	cout << ">> ";
	string date;
	cin >> date;

	cout << "Please enter start time" << endl;
	cout << ">> ";
	string time;
	cin >> time;

	cout << "Please enter file name to save data" << endl;
	cout << ">> ";
	string filename;
	cin >> filename;

	write_event(filename, name, date, time);
}

void write_entrant(std::string filename, int id, char course, std::string name) {
	using namespace std;

	ofstream file;
	file.open(filename.c_str(), ios::app);

	file << id << " " << course << " " << name << "\n";

	file.close();
}

void add_entrant() {
	using namespace std;

	cout << "Please enter entrant id" << endl;
	cout << ">> ";
	int id;
	cin >> id;

	cout << "Please enter course id" << endl;
	cout << ">> ";
	char course;
	cin >> course;

	cout << "Please enter entrant name" << endl;
	cout << ">> ";
	string name;
	cin.ignore();
	getline(cin, name);

	cout << "Please enter entrant file" << endl;
	cout << ">> ";
	string filename;
	cin >> filename;

	write_entrant(filename, id, course, name);
}

int main(int argc, char* argv[]) {
	using namespace std;

	cout << "Event Creator" << endl;

	bool running = true;
	while (running) {
		int input = show_menu();
		switch (input) {
			case 1:
				create_event();
				break;
			case 2:
				add_entrant();
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
