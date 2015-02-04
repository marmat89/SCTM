package MultiThread;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Standard.Formica;
import Standard.NodoE;

public class AntTH implements Runnable {
	//TODO
	public Thread findEat;
	public AntM ant;
	public AnthillM hill;
	public int steps;
	private int TIME;
	private Brain brain;
	private Configurator c;

	public void ThreadCreation(AntM ant, AnthillM hill,Brain br) {
		this.hill=hill;
		this.ant=ant;
		c = new Configurator();
		TIME=c.getTagValueINT("THREAD_TIME");
		steps=hill.allNodes.getSize();
		ant.setStart(hill.allNodes.getSize());
		//THREAD finder Start
		findEat = new Thread(this, ant.getName());
		brain=br;
		findEat.start();
	}
	public void run() {
		while ((ant.getPosition() != hill.food && !(ant.dieAnt()))) {
			try {
				ant.moveStep();
				NodeM temp = ant.getPosition();
				//used for Montecarlo 
				hill.allNodes.bubbleSort(ant.getPosition());
				NodeM nextStep = brain.moveCalc(ant.getPosition(),ant.getLastPosition());
				ant.setLastPosition(temp);
				int sleep = (int)brain.calcSleep(ant.getPosition(),nextStep,TIME);
				System.out.println("AntM:" + ant.getName() + "go_to"
						+ nextStep.getName() + "_wait_"
						+ (sleep)+ "_ms"+ "++LP: "
								+ant.getLifePoints());
				ant.setPosition(nextStep);
				findEat.sleep(sleep);
			} catch (InterruptedException e) {}
		}
		if (ant.getPosition() == hill.food && !(ant.dieAnt())) {
			ant.getBuffer().add(ant.getPosition());
			System.err.println("Buffer:" + ant.getBuffer());
			final Lock mutex = new ReentrantLock(true);

			mutex.lock();

			brain.updateRoute(ant.getBuffer());

			mutex.unlock(); 
			System.err.println("La_formica:_" + ant.getName()
					+ "_ha_trovato_da_mangiare");
			synchronized (hill.antManager) {
				hill.finderCount--;
		        hill.antManager.notify();
			}		
		}else{
			synchronized (hill.antManager) {
				hill.finderCount--;
		        hill.antManager.notify();
			}	
			System.err.println("La_formica:_" + ant.getName()
					+ "_muore_di_fame");

			}
	}
}