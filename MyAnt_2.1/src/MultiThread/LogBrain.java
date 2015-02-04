package MultiThread;

import java.util.Iterator;
import java.util.List;

public class LogBrain extends LinearBrain {

	@Override
	public int fun(int n) {
		double d=0;
		if(n>1){
		 d = Math.log((double) n);
		}else{
			d=n;
			
		}
		return (int) d;
	}


}
