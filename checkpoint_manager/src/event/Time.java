package event;

public class Time {

	private int hours;
	private int minutes;

	private Time(int minutes) {
		setMinutes(minutes % 60);
		setHours(minutes / 60);
	}

	public Time(int hrs, int mins) {
		setHours(hrs);
		setMinutes(mins);
	}

	public int getHours() {
		return hours;
	}

	private void setHours(int hrs) {
		hours = hrs;
	}

	public int getMinutes() {
		return minutes;
	}

	private void setMinutes(int mins) {
		minutes = mins;
	}

	public Time getDelta(Time comp) {
		return new Time(asMinutes() - comp.asMinutes());
	}

	public int asMinutes() {
		return (getHours() * 60) + getMinutes();
	}

}
