package MultiThread;

import java.util.Iterator;
import java.util.List;

public class ExponentialBrain extends LinearBrain {

	@Override
	public int fun(int n) {
		return (((int) Math.pow(n, 2)));
}
}