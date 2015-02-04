package it.unibo.util;

public class BooleanMeasure extends Measure{
	private Boolean isChange;
	public BooleanMeasure(boolean simulated, Boolean isChange) {
		super(simulated);
		this.isChange=isChange;
	}

	public Boolean isChanged() {
		return isChange;
	}


	@Override
	public Object getValue() {
		if (isChange)
		return 1;
		else 		return 0;

	}
	


}
