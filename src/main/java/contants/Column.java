package contants;

public enum Column {
	NUM(1), ID(2);
	private int value;

	private Column(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
