package rgr;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;
import stat.Histo;

public class BargeGenerator extends Actor {
	private String name;
	private Model model;
	private double finishTime;
	private Histo histoBarg;
	private QueueForTransactions<Barge> QueueToRozvan;
	private Randomable rnd;
	private UserInterface interf;
	
	public BargeGenerator(String name, Model model, UserInterface interf) {
		this.name = name;
		this.model = model;
		this.interf = interf;
		setNameForProtocol(name);
		finishTime = interf.getChooseChasMod().getDouble();
		rnd = interf.getChooseRandomPoivaBar();
	}
	
	
	@Override
	protected void rule() throws DispatcherFinishException {
		while (getDispatcher().getCurrentTime() <= finishTime) {
			holdForTime(rnd.next());
			getDispatcher().printToProtocol(
					"  " + getNameForProtocol() + " створює транзакцію.");
			Barge transaction = new Barge(model);
			dispatcher.addStartingActor(transaction);
		}
	}
	}
	
	

