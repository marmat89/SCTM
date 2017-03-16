
public class DustSensor {
	private static SerialTest SerialCom;
	private static String request="getConcentration=";
	public static void getConcentration()
	{
		int i=0;
		while(i<10)
		{
		SerialCom.sendData(request);
		i++;
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialCom = new SerialTest();
		SerialCom.initialize();
		getConcentration();
	}

}
