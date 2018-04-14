package rgr;

import process.DispatcherFinishException;
import process.QueueForTransactions;
import stat.Histo;

public class Barge extends process.Actor {

	private double createTime;
	@Override
	public String toString() {
		return "Barge [createTime=" + createTime + "]";
	}



	public boolean isServiceDone() {
		return serviceDone;
	}



	public void setServiceDone(boolean serviceDone) {
		this.serviceDone = serviceDone;
	}



	public double getCreateTime() {
		return createTime;
	}


	private boolean serviceDone;
	private QueueForTransactions<Barge> queue;
	private Histo histoQueue;
	private Histo histoService;
	
	public Barge(Model model) {
		this.queue = model.getQueueToRozvantag();
		this.histoQueue = model.gethistoBargeInQue();
		this.histoService = model.getHistoServirsBarg();
	}

	
	
	public static void main(String[] args) {
		

	}
	
	
	@Override
	public void rule() throws DispatcherFinishException {
		createTime =dispatcher.getCurrentTime();
		nameForProtocol = "barge "+createTime;
		queue.add(this);
		waitForCondition(()->!queue.contains(this), "мають забрати на обслуговування");
		histoQueue.add(dispatcher.getCurrentTime() - createTime);
		waitForCondition(()->serviceDone, "мають завершити обслуговування");
		histoService.add(dispatcher.getCurrentTime()-createTime);
		
	}

}
