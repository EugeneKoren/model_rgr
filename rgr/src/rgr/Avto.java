package rgr;

import process.Actor;
import process.DispatcherFinishException;
import process.QueueForTransactions;
import stat.Histo;

public class Avto extends Actor {

	private double createTime;
	private QueueForTransactions<Avto> queue;
	private Histo histoQueue;
	private Histo histoService;
	private boolean serviceDone;
	
	public Avto(Model model ) {
		super();
	}

	
	public double getCreateTime() {
		return createTime;
	}

	public void setServiceDone(boolean b) {
		this.serviceDone = b;
	}

	
	public String toString() {
		return "Avto " + createTime;
	}

	protected void rule() throws DispatcherFinishException {
		createTime = dispatcher.getCurrentTime();
		nameForProtocol = "���������� " + createTime;
		queue.add(this);
		waitForCondition(() -> !queue.contains(this), "����� ������� �� ��������������");
		histoQueue.add(dispatcher.getCurrentTime() - createTime);
		waitForCondition(() -> serviceDone, "����� ��������� ��������������");
		histoService.add(dispatcher.getCurrentTime() - createTime);

	}

}
