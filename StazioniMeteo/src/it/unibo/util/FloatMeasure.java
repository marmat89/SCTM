package it.unibo.util;

public class FloatMeasure extends Measure{
	private float value;
	public FloatMeasure(boolean simulated, float value) {
		super(simulated);
		this.value=value;
	}

	public Object getValue() {
		return value;
	}
}
