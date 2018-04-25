package rgr;

import java.util.function.BooleanSupplier;

import process.DispatcherFinishException;
import process.QueueForTransactions;
import process.Store;
import rnd.Randomable;
import stat.Histo;

public class Workers extends process.Actor {
	


	private String name;
	private QueueForTransactions<Barge> queue;
	private Randomable rnd;
	private double finishTime;
	private UserInterface gui;
	private QueueForTransactions<Avto> queueToZavantagAvto;
	private Store areagruz;
	private int maxsize;
	private Histo histoavto;
	
	public Workers(String name,UserInterface gui,Model model) {
		this.gui=gui;
		this.name = name;
		finishTime = gui.getChooseChasMod().getDouble();
		rnd = gui.getChooseRandomChasZavAvto();
		queue = model.getQueueToRozvantag();
		queueToZavantagAvto = model.getQueueToZavantagAvto();
		areagruz = model.getAreagruz();
		maxsize= gui.getChooseSizePlo().getInt();
		
	}
	
	
	public void rule() throws DispatcherFinishException {
		// Створюємо умову, виконання якої буде чекати актор
				BooleanSupplier queueSize = () -> queue.size() > 0;
				BooleanSupplier qu = ()->((queueToZavantagAvto.size()>0) || (areagruz.getSize()<maxsize));
				// цикл виконання правил дії
				
				while (getDispatcher().getCurrentTime() <= finishTime) {
					// Перевірка наявності транзакції та чекання на її появу
					waitForCondition(queueSize, "у черзі має з'явиться транзакція");
					waitForCondition(qu  , "у черзі має з'явиться транзакція");
					Barge transaction = queue.removeFirst();
					// Імітація обробки транзакції
					//holdForTime(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					for(int i=0;i != gui.getChooseKilkistKonteiner().getInt();i++){
						
						holdForTime(rnd.next());
						System.out.println(queueToZavantagAvto.size());
						if( queueToZavantagAvto.size() >0) {							
						Avto avto = queueToZavantagAvto.removeFirst();
						System.out.println("  "+queueToZavantagAvto.size());
						avto.addContainer();
						if(!avto.isFull()) {
							queueToZavantagAvto.add(avto);
						}
						//	
						//areagruz.add(1);
						}else {
						if(queueToZavantagAvto.size() == 0 && areagruz.getSize()<maxsize) {					
							areagruz.add(1);
							System.out.println("sd");	
						}else {
							waitForCondition(qu  , "у черзі має з'явиться транзакція");
						}
							
						}
						
						}
					//System.out.println(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					transaction.setServiceDone(true);
				}
			}

		
	}

