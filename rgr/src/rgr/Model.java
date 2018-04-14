package rgr;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import stat.DiscretHisto;
import stat.Histo;

public class Model {
	private Avto avto;
	private Workers workers;
	private QueueForTransactions<Barge> queueToRozvantag;
	private QueueForTransactions<Avto> queueToZavantagAvto;
	private QueueForTransactions<Avto> queueToRoad;
	private DiscretHisto histoForQueueToRozvantag;
	private DiscretHisto histoForQueueToZavantagAvto;
	private Histo histoBargeInQue;//���������� � ����� 
	public Histo gethistoBargeInQue() {
		return histoBargeInQue;
	}
	private Histo histoServirsBarg;//��� ������������� 
	public Histo getHistoServirsBarg() {
		return histoServirsBarg;
	}
	private Histo histoWorker;//��� ���� �������  
	private Histo histoAvto;
	private Histo histoArea;
	protected MultiActor brigadOfWorkers;
	private Dispatcher dispatcher;
	private UserInterface interfase;
	private BargeGenerator bargegenerator;
	
	public static void main(String[] args) {
		System.out.println("sad");
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
	public MultiActor getMultiBrigadOfWorkers() {
		if(brigadOfWorkers==null) {
			brigadOfWorkers= new MultiActor();
			brigadOfWorkers.setNameForProtocol("brigadOfWorkers");
			brigadOfWorkers.setOriginal(getWorkers());
			brigadOfWorkers.setNumberOfClones(interfase.getChooseKilkistBrig().getInt());
		}
		return brigadOfWorkers;
	}
	public void initForTest() {
		getQueueToRozvantag().setPainter(interfase.getDiagramBarg().getPainter());
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
	

}
