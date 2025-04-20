package gameOfLife;

import java.util.Random;

public enum Status {
	DEAD,
	ALIVE;

private static final Random rand = new Random();
	public static Status randomStatus() {
		Status[] status = values();
		return status[rand.nextInt(status.length)];
	}
}




