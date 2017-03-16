
public class DustMeasure {
	
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String lowpulseoccupancy;
	public String concentration;
	/**
	 * @param id
	 * @param lowpulseoccupancy
	 * @param lastName
	 * @param gender
	 * @param age
	 */
	public DustMeasure(String lowpulseoccupancy, String concentration) {
		super();
		this.lowpulseoccupancy = lowpulseoccupancy;
		this.concentration = concentration;
	}
	
	@Override
	public String toString() {
		return "Student [lowpulseoccupancy=" + lowpulseoccupancy
				 + ", concentration=" + concentration + "]";
	}
}