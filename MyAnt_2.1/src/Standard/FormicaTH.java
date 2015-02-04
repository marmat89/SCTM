package Standard;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FormicaTH implements Runnable {
	private Formica io;
	private NodoE lastPosition;
	private char target;
	private NodoE position;
	public Thread findEat;
	
	public void ThreadCreation(Formica formica) {
		io=formica;
		lastPosition=formica.getHome();
		position=formica.getHome();
		findEat = new Thread(this, io.getName());
		System.out.println("Thread creato: " + findEat);
	}
	
	public int moveCalc(){
		List<NodoE> choice = new ArrayList(position.getAdiac()
				.keySet());
		List<Integer> dist = new ArrayList(position.getAdiac()
				.values());				
		List<Integer> smells = new ArrayList(position.getSmell()
				.values());
		bubbleSort(smells);
		return 0;	
	}
	public void bubbleSort(List<Integer> array) {

        for(int i = 0; i < array.size(); i++) {
            boolean flag = false;
            for(int j = 0; j < array.size()-1; j++) {
            	
                //Se l' elemento j e maggiore del successivo allora
                //scambiamo i valori
                if((array.get(j))>(array.get(j+1))) {
                    int k = array.get(j);
                    array.add(j,array.get(j+1));
                    array.add(j+1 , k);
                    System.out.println("Scambio_"+array.get(j)+"_con_"+array.get(j+1));
                    flag=true; //Lo setto a true per indicare che é avvenuto uno scambio
                }
                

            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poiché l' array risulta ordinato
        }
    }

	public void run() {
		Random random = new Random();
		while ((position.getName() != "E" )) {
			try {
				List<NodoE> choice = new ArrayList(position.getAdiac()
						.keySet());
				List<Integer> dist = new ArrayList(position.getAdiac()
						.values());		
				int i=moveCalc();
				int r = random.nextInt(choice.size());
				position.getSmell();
				position=(choice.get(r));
				
				
				
				Thread.sleep(dist.get(r) * 1000);
				System.out.println("wake up_" + io.getName() + "_actin_goto_"
						+ position.getName()+"_need_attend_"+ dist.get(r) * 1000+"_seconds");
				
			} catch (InterruptedException e) {
				System.out.println("Gesù_decide_che_La_Formica_è_MORTA");
			}
		}
		System.err.println(io.getName() + "La_formica_ha_trovato_da_mangiare");
	}

}