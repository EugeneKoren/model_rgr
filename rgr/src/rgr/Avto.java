package rgr;

import java.util.function.BooleanSupplier;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import process.Store;
import rnd.Randomable;
import stat.Histo;

public class Avto extends Actor {

	private double finishTime;
	private Randomable rnd;
	private int cnt_container;
	private int load_container;
	private QueueForTransactions<Avto> queueToZavantagAvto;
	private QueueForTransactions<Avto> queueToRoad;
	private BooleanSupplier isBodyFull;
	private Histo avtoHisto;
	private Store areagruz;
	private Store areados;
	private int maxsize;
	
	public Avto(String string, UserInterface gui, Model model) {
		finishTime = gui.getChooseChasMod().getDouble();
		rnd = gui.getChooseRandomChasPerebAvtoInRoad();
		cnt_container = gui.getChooseMistcistAvto().getInt();
		queueToZavantagAvto = model.getQueueToZavantagAvto();
		//queueToRoad = model.getQueueToRoad();
		load_container = 0;
		avtoHisto = model.getHistoAvto();
		areagruz = model.getAreagruz();
		maxsize= gui.getChooseSizePlo().getInt();
		//setHistoForActorWaitingTime(model.getQueueToZavantagAvto());
		areados = model.getAreados();
	}


	
	private void initCondition(){
		this.isBodyFull = ()->isFull();
	}
	@Override
	protected void rule() {
		initCondition();
		while(getDispatcher().getCurrentTime()<=finishTime){
			getDispatcher().printToProtocol("Авто відправляється до порту");
			while(areagruz.getSize()> 0&& !this.isFull()) {
				this.addContainer();
				areagruz.remove(1);
			}
			if(!this.isFull()) {
				queueToZavantagAvto.addLast(this);	
				try {
					waitForCondition(isBodyFull, " машина має бути заповнена");
				} catch (DispatcherFinishException e) {
					return;
				}
				//queueToZavantagAvto.removeFirst();
				System.out.println("+++");
			}
			if(this.isFull()) {
			areados.add(1);
			System.out.println("---");
			holdForTime(rnd.next());
			load_container=0;
			}
			
//			holdForTime(rnd.next());
//			queueToRoad.remove(this);
//			getDispatcher().printToProtocol("Авто прибуло до порту");
//			queueToZavantagAvto.addLast(this);
//			double current_time = getDispatcher().getCurrentTime();
//			try {
//				waitForCondition(isBodyFull, " машина має бути заповнена");
//			} catch (DispatcherFinishException e) {
//				return;
//			}
//			double time = getDispatcher().getCurrentTime();
//			avtoHisto.add(time-current_time);
//			getDispatcher().printToProtocol(getNameForProtocol()+" поїхав розвантажуватись");
//			queueToRoad.addLast(this);
//			holdForTime(rnd.next());
//			queueToRoad.remove(this);
//			getDispatcher().printToProtocol(getNameForProtocol()+" розвантажується");
//			load_container=0;
		}
	}
	public void addContainer(){
		load_container++;
		getDispatcher().printToProtocol(getNameForProtocol()+" у кузові стало "+load_container);
	}
	public boolean isFull() {
		return load_container>=cnt_container;
	}
	public void setFinishTime(double arg0) {
		finishTime = arg0;
	}
}