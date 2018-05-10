package rgr;

import java.util.HashMap;
import java.util.Map;

import process.Dispatcher;
import process.MultiActor;
import process.QueueForTransactions;
import process.Store;
import qusystem.GetPutDevice;
import stat.DiscretHisto;
import stat.Histo;
import stat.IHisto;
import widgets.stat.StatisticsManager;
import widgets.trans.ITransMonitoring;
import widgets.trans.ITransProcesable;
import widgets.experiments.IExperimentable;
import widgets.stat.IStatisticsable;

public class Model implements IExperimentable, ITransProcesable,
IStatisticsable{
	private Avto avto;
	private Workers workers;
	private QueueForTransactions<Barge> queueToRozvantag;
	private QueueForTransactions<Avto> queueToZavantagAvto;// ����� �� ������������ ���� 

	private QueueForTransactions<Avto> queueToRoad;// ��������� � �����
	
	private Store areagruz;
	private Histo histoarea;

	private Store areados;
	private Histo histoareados;
	
	private DiscretHisto histoForQueueToRozvantag;
	private DiscretHisto histoForQueueToZavantagAvto;
	public DiscretHisto getHistoForQueueToZavantagAvto() {
		if(histoForQueueToZavantagAvto==null) {
			histoForQueueToZavantagAvto= new DiscretHisto();
		}
		return histoForQueueToZavantagAvto;
	}
	private Histo histowoketime;//��� ������� j�������� ������  
	public Histo gethistowoketime() {
		if(histowoketime==null) {
			histowoketime = new Histo();
		}
		return histowoketime;
	}
	private Histo histoServirsBarg;//��� ������������� 
	public Histo getHistoServirsBarg() {
		if(histoServirsBarg==null) {
			histoServirsBarg = new Histo();
		}
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
	private StatisticsManager statisticsManager;

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
		statisticsManager=g.getStatisticsManager();
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
			workers.setHistoForActorWaitingTime(gethistowoketime());
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
		getAreados().setPainter(interfase.getDiagramTodostavki().getPainter());
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
	
//	public QueueForTransactions<Avto> getQueueToRoad() {
//		if(queueToRoad==null) {
//			queueToRoad = new QueueForTransactions<>("queueToRoad",dispatcher,getHistoForQueueToRozvantag());
//		}
//		return queueToRoad;
//	}
	public Histo getHistoAvto() {
		if(histoAvto==null) {
			histoAvto=new Histo();
		}
		return histoAvto;
	}
	public Store getAreagruz() {
		if (areagruz == null) {
			areagruz = new Store("�������� ��� ����������", dispatcher, getHistoarea());
		}
		return areagruz;
	}
	public Store getAreados() {
		if (areados == null) {
			areados = new Store("�������� ��������", dispatcher, getHistoareados());
		}
		return areados;
	}
	
	public Histo getHistoarea() {
		if(histoarea==null) {
			histoarea= new Histo();
		}
		return histoarea;
	}
	
	public Histo getHistoareados() {
		if(histoareados==null) {
			histoareados= new Histo();
		}
		return histoareados;
	}
	////////////////////////
	public void initForStatistics() {
		
	}
	
	public Map<String, IHisto> getStatistics() {
		Map<String, IHisto> map = new HashMap<>();
		map.put("ó�������� ��� �������", getHistoarea());
		map.put("ó�������� ��� ����������� �����i�", getHistoareados());
		map.put("ó�������� ��� ������� ����� ���� �� �������������", getHistoForQueueToRozvantag());
		map.put("ó�������� ��� ������� ����� ���� �� ������������", getHistoForQueueToZavantagAvto());
		map.put("ó�������� ��� ���� ������� ����", getHistoAvto());
		map.put("ó�������� ��� ���� ������� ������", gethistowoketime());
		//	map.put("2", getHistoForQueueToZavantagAvto());

		return map;
	}
	//////////////
	@Override
	public void initForTrans(double finishTime) {
		getWorkers().setFinishTime(finishTime);
		getAvto().setFinishTime(finishTime);
		getBargegenerator().setFinishTime(finishTime);
		interfase.getChooseChasMod().setDouble(finishTime);
		
		
	}
	
	@Override
	public Map<String, ITransMonitoring> getMonitoringObjects() {
		Map<String, ITransMonitoring> transMap = new HashMap<>();
		transMap.put("����� ���� �� ������������",(ITransMonitoring) getQueueToZavantagAvto());
		transMap.put("����i� ��������",(ITransMonitoring) getAreagruz());
		transMap.put("����� ���� �� �������������",(ITransMonitoring) getQueueToRozvantag());
		return transMap;
	}
	///////////////////////////
	@Override
	public void initForExperiment(double factor) {
		Avtos.setNumberOfClones((int) factor);
		
	}
	
	@Override
	public Map<String, Double> getResultOfExperiment() {
		// TODO Auto-generated method stub
		Map<String, Double> resultMap = new HashMap<>();
		resultMap.put("ó�������� ��� �������", getHistoarea()
				.getAverage());
		resultMap.put("ó�������� ��� ����������� �����i�", getHistoareados()
				.getAverage());
		resultMap.put("ó�������� ��� ������� ����� ���� �� �������������", getHistoForQueueToRozvantag().getAverage());
		resultMap.put("ó�������� ��� ������� ����� ���� �� ������������", getHistoForQueueToZavantagAvto().getAverage());
		resultMap.put("ó�������� ��� ���� ������� ����", getHistoAvto().getAverage());
		resultMap.put("ó�������� ��� ���� ������� ������", gethistowoketime().getAverage());
		return resultMap;

	}
}
