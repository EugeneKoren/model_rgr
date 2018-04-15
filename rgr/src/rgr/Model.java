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
	private QueueForTransactions<Avto> queueToZavantagAvto;// ����� �� ������������ ���� 

	private QueueForTransactions<Avto> queueToRoad;// ��������� � �����
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
	private Histo histoBargeInQue;//���������� � ����� 
	public Histo gethistoBargeInQue() {
		return histoBargeInQue;
	}
	private Histo histoServirsBarg;//��� ������������� 
	public Histo getHistoServirsBarg() {
		return histoServirsBarg;
	}
	private Histo histoWorker;//��� ���� �������  
	
	private Histo histoAvto;//��� ���� ����

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
			System.out.println("�� ��������� ���������� ��� GUI ��� Model");
			System.out.println("�������� ������ ���������");
			System.exit(0);
		}
		dispatcher = d;
		interfase = g;
		//�������� ������ �� ���������� ������ �����������
		componentsToStartList();
	}
	public void componentsToStartList() {
		// �������� ������ ����������
		dispatcher.addStartingActor(getBargegenerator());
		dispatcher.addStartingActor(getMultiBrigadOfWorkers());
		dispatcher.addStartingActor(getAvtos());
	}
	public BargeGenerator getBargegenerator() {
		if (bargegenerator == null) {
		bargegenerator = new BargeGenerator("�����", this , interfase);
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
			areagruz = new Store("�������� ��� ����������", dispatcher, getHistoarea());
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
