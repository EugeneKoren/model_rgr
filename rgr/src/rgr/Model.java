package rgr;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import process.Store;
import qusystem.GetPutDevice;
import stat.DiscretHisto;
import stat.Histo;

public class Model {
	private Avto avto;
	private Workers workers;
	private QueueForTransactions<Barge> queueToRozvantag;
	private QueueForTransactions<Avto> queueToZavantagAvto;// черга на завантаження авто 

	private QueueForTransactions<Avto> queueToRoad;// чергаавто в дорозі
	private Store areagruz;
	private Histo histoarea;

	private DiscretHisto histoForQueueToRozvantag;
	private DiscretHisto histoForQueueToZavantagAvto;
	public DiscretHisto getHistoForQueueToZavantagAvto() {
		if(histoForQueueToZavantagAvto==null) {
			histoForQueueToZavantagAvto= new DiscretHisto();
		}
		return histoForQueueToZavantagAvto;
	}
	private Histo histoBargeInQue;//длявремени в черзи 
	public Histo gethistoBargeInQue() {
		return histoBargeInQue;
	}
	private Histo histoServirsBarg;//для розвантаження 
	public Histo getHistoServirsBarg() {
		return histoServirsBarg;
	}
	private Histo histoWorker;//для часу чекання  
	
	private Histo histoAvto;//для часу авто

	private Histo histoArea;
	protected MultiActor brigadOfWorkers;
	protected MultiActor Avtos;
	private Dispatcher dispatcher;
	private UserInterface interfase;
	private BargeGenerator bargegenerator;
	
	public static void main(String[] args) {
		System.out.println("sad");
	}
	public QueueForTransactions<Avto> getQueueToZavantagAvto() {
		if(queueToZavantagAvto==null) {
			queueToZavantagAvto= new  QueueForTransactions<>("queueToZavantagAvto",dispatcher,getHistoForQueueToZavantagAvto());
		}
		return queueToZavantagAvto;
	}
	
	public Model(Dispatcher d, UserInterface g) {
		if (d == null || g == null) {
			System.out.println("Не визначено диспетчера або GUI для Model");
			System.out.println("Подальша робота неможлива");
			System.exit(0);
		}
		dispatcher = d;
		interfase = g;
		//Передаємо акторів до стартового списку диспетчераы
		componentsToStartList();
	}
	public void componentsToStartList() {
		// Передаємо акторів диспетчеру
		dispatcher.addStartingActor(getBargegenerator());
		dispatcher.addStartingActor(getMultiBrigadOfWorkers());
		dispatcher.addStartingActor(getAvtos());
	}
	public BargeGenerator getBargegenerator() {
		if (bargegenerator == null) {
		bargegenerator = new BargeGenerator("Баржа", this , interfase);
		}
		return bargegenerator;
	}
	public Workers getWorkers() {
		if(workers==null) {
			workers=new Workers("workers",interfase,this);
			workers.setHistoForActorWaitingTime(getHistoWorker());
		}
		return workers;
	}
	
	public Avto getAvto() {
		if(avto==null) {
			avto= new Avto("avto",interfase,this);
			avto.setHistoForActorWaitingTime(getHistoAvto());
		}
		return avto;
	}
	public MultiActor getMultiBrigadOfWorkers() {
		if(brigadOfWorkers==null) {
			brigadOfWorkers= new MultiActor();
			brigadOfWorkers.setNameForProtocol("brigadOfWorkers");
			brigadOfWorkers.setOriginal(getWorkers());
			brigadOfWorkers.setNumberOfClones(interfase.getChooseKilkistBrig().getInt());
		}
		return brigadOfWorkers;
	}
	
	public MultiActor getAvtos() {
		if(Avtos==null) {
			Avtos= new MultiActor();
			Avtos.setNameForProtocol("Avtos");
			Avtos.setOriginal(getAvto());
			Avtos.setNumberOfClones(interfase.getChooseKilkistAvto().getInt());
		}
		return Avtos;
	}
	public void initForTest() {
		getAreagruz().setPainter(interfase.getDiagramPlo().getPainter());
		getQueueToRozvantag().setPainter(interfase.getDiagramBarg().getPainter());
		getQueueToZavantagAvto().setPainter(interfase.getDiagramAvto().getPainter());
		if(interfase.getChckbxNewCheckBox().isSelected()) {
			dispatcher.setProtocolFileName("Console");
		}else {
			dispatcher.setProtocolFileName("");
		}
	}
	public QueueForTransactions<Barge> getQueueToRozvantag() {
		if(queueToRozvantag==null) {
			queueToRozvantag = new QueueForTransactions<>("queueToRozvantag",dispatcher,getHistoForQueueToRozvantag());
		}
		return queueToRozvantag;
	}
	public DiscretHisto getHistoForQueueToRozvantag() {
		if(histoForQueueToRozvantag==null) {
			histoForQueueToRozvantag=new DiscretHisto();
		}
		return histoForQueueToRozvantag;
	}
	public Histo getHistoWorker() {
		return histoWorker;
	}
	
	public QueueForTransactions<Avto> getQueueToRoad() {
		if(queueToRoad==null) {
			queueToRoad = new QueueForTransactions<>("queueToRoad",dispatcher,getHistoForQueueToRozvantag());
		}
		return queueToRoad;
	}
	public Histo getHistoAvto() {
		return histoAvto;
	}
	public Store getAreagruz() {
		if (areagruz == null) {
			areagruz = new Store("Площадка для контейнерів", dispatcher, getHistoarea());
		}
		return areagruz;
	}
	public Histo getHistoarea() {
		if(histoarea==null) {
			histoarea= new Histo();
		}
		return histoarea;
	}
}
