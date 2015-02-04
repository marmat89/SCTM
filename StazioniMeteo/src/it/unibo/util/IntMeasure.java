package it.unibo.util;

public class IntMeasure extends Measure{
	private int percentage;
	public IntMeasure(boolean simulated, int percentage) {
		super(simulated);
		this.percentage=percentage;
	}

@Override
	public Object getValue() {
		return percentage;
	}


}
