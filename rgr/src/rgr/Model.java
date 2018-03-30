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
	private Histo histoBarge;
	private Histo histoWorker;
	private Histo histoAvto;
	private Histo histoArea;
	protected MultiActor brigadOfWorkers;
	private Dispatcher dispatcher;
	private UserInterface interfase;
	private BargeGenerator bargegenerator;
	
	public Model(Dispatcher d, UserInterface g) {
		if (d == null || g == null) {
			System.out.println("�� ��������� ���������� ��� GUI ��� Model");
			System.out.println("�������� ������ ���������");
			System.exit(0);
		}
		dispatcher = d;
		interfase = g;
		//�������� ������ �� ���������� ������ ����������
		componentsToStartList();
	}
	public void componentsToStartList() {
		// �������� ������ ����������
		dispatcher.addStartingActor(getBargegenerator());
		//dispatcher.addStartingActor(getMultiDevice());
	}
	public BargeGenerator getBargegenerator() {
		if (bargegenerator == null) {
			//bargegenerator = new BargeGenerator("�����", interfase , this);
		}
		return bargegenerator;
	}
	public void initForTest() {
		// TODO Auto-generated method stub
		
	}
	
	

}
