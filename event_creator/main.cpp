/*
 * main.cpp
 *
 * Tom Leaman (thl5@aber.ac.uk)
 */

#include <fstream>
#include <iostream>
#include <string>
#include <vector>

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
	cin.ignore();
	getline(cin, name);

	cout << "Please enter date" << endl;
	cout << ">> ";
	string date;
	cin.ignore();
	getline(cin, date);

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

void write_course(std::string filename, char id, int num_nodes, std::vector<int> nodes) {
	using namespace std;

	ofstream file;
	file.open(filename.c_str(), ios::app);

	file << id << " " << num_nodes;
	for (int i = 0; i < num_nodes; i++) {
		file << " " << nodes[i];
	}
	file << "\n";

	file.close();
}

void add_course() {
	using namespace std;

	cout << "Please enter course id" << endl;
	cout << ">> ";
	char id;
	cin >> id;

	cout << "Please enter the number of nodes" << endl;
	cout << ">> ";
	int num_nodes;
	cin >> num_nodes;

	vector<int> nodes(num_nodes);
	for (int i = 0; i < num_nodes; i++) {
		cout << "Please enter node " << (i+1) << endl;
		cout << ">> ";
		cin >> nodes[i];
	}

	cout << "Please enter course file" << endl;
	cout << ">> ";
	string filename;
	cin >> filename;

	write_course(filename, id, num_nodes, nodes);
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
				add_course();
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
