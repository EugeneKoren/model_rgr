package rgr;

import java.util.function.BooleanSupplier;

import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;

public class Workers extends process.Actor {
	


	private String name;
	private QueueForTransactions<Barge> queue;
	private Randomable rnd;
	private double finishTime;
	private UserInterface gui;
	private QueueForTransactions<Avto> queueToZavantagAvto;
	
	
	public Workers(String name,UserInterface gui,Model model) {
		this.gui=gui;
		this.name = name;
		finishTime = gui.getChooseChasMod().getDouble();
		rnd = gui.getChooseRandomChasZavAvto();
		queue = model.getQueueToRozvantag();
		queueToZavantagAvto = model.getQueueToZavantagAvto();
	}
	
	
	public void rule() throws DispatcherFinishException {
		// Створюємо умову, виконання якої буде чекати актор
				BooleanSupplier queueSize = () -> queue.size() > 0;
				// цикл виконання правил дії
				while (getDispatcher().getCurrentTime() <= finishTime) {
					// Перевірка наявності транзакції та чекання на її появу
					waitForCondition(queueSize, "у черзі має з'явиться транзакція");
					waitForCondition(()->queueToZavantagAvto.size()>0, "у черзі має з'явиться транзакція");
					Barge transaction = queue.removeFirst();
					// Імітація обробки транзакції
					//holdForTime(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					for(int i=0;i != gui.getChooseKilkistKonteiner().getInt();i++){
						if(queueToZavantagAvto.size()!=0) {
						Avto avto = queueToZavantagAvto.removeFirst();
						avto.addContainer();
						holdForTime(rnd.next());
						queueToZavantagAvto.add(avto);
						}else {
							//queue.add(transaction);
						}
						}
					//System.out.println(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					transaction.setServiceDone(true);
				}
			}

		
	}

