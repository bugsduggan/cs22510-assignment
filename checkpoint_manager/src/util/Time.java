package util;

/**
 * 
 * @author Tom Leaman (thl5@aber.ac.uk)
 *
 */
public class Time {

	private int hours;
	private int minutes;

	public Time(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	@Override
	public String toString() {
		return hours + ":" + minutes;
	}

}
