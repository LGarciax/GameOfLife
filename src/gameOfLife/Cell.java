package gameOfLife;

public class Cell {

	private int maxAge;
	
	private Status currentStatus;
	private Status nextStatus;
	private int age;
	
	public Cell(Status status, int maxAge) {
		this.currentStatus = status;
		this.nextStatus = status;
		this.age = 1;
		this.maxAge = maxAge;
	}
	
	public void age() {
		this.age += 1;
	}
	
	
	public Status getStatus() {
		return this.currentStatus;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void update() {
		
		
		if(nextStatus == Status.DEAD) {
			this.currentStatus = Status.DEAD;
		}
		if(currentStatus == Status.DEAD && nextStatus == Status.ALIVE) {
			this.currentStatus = Status.ALIVE;
			this.age = 1;
		}
		if(this.age >= this.maxAge) {
			this.currentStatus = Status.DEAD;
		}

		this.age();
	}
	
	
	
	public void setNextStatus(Status status) {
		this.nextStatus = status;
	}
	
	public boolean isAlive() {
		if(this.currentStatus == Status.ALIVE) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		
		if(getStatus() == Status.ALIVE) {
			return "1";
		}
		return "0";
		
	}
	
}
