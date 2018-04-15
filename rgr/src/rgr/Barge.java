package rgr;

import java.util.function.BooleanSupplier;

import process.DispatcherFinishException;
import process.QueueForTransactions;
import stat.Histo;

public class Barge extends process.Actor {

	private double createTime;
	private int cnt_container;
	private BooleanSupplier isEmpty;
	private Histo histoBarg;
	@Override
	public String toString() {
		return "Barge [createTime=" + createTime + "]";
	}

	public boolean haveContainer(){
		return cnt_container>0;
	}
	
	public void addContainer(){
		cnt_container++;
		return;
	}
	
	public void getContainer(){
		cnt_container--;
		return;
	}
	
	private void initCondition(){
		this.isEmpty = () -> isEmpty_b();
	}

	private boolean isEmpty_b(){
		return cnt_container<=0;
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
