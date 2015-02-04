package MultiThread;

import java.util.Iterator;
import java.util.List;

public class FunBrain extends LinearBrain {

	@Override
	public int fun(int n) {
		// return (int)1+n/4;
		double d = 0;
		if (n > 1) {
			d = Math.log10((double) ((int) Math.pow(n, 10000)));
		} else {
			d = n;

		}
		return (int) d % 100;
	}
}
