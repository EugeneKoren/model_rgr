package rgr;

import java.util.function.BooleanSupplier;

import process.DispatcherFinishException;
import process.QueueForTransactions;
import rnd.Randomable;

public class Workers extends process.Actor {
	


	private String name;
	private QueueForTransactions<Barge> queue;
	private Randomable rnd;
	private double finishTime;
	private UserInterface gui;
	
	public Workers(String name,UserInterface gui,Model model) {
		this.gui=gui;
		this.name = name;
		finishTime = gui.getChooseChasMod().getDouble();
		rnd = gui.getChooseRandomChasZavAvto();
		queue = model.getQueueToRozvantag();

	}
	
	
	public void rule() throws DispatcherFinishException {
		// ��������� �����, ��������� ��� ���� ������ �����
				BooleanSupplier queueSize = () -> queue.size() > 0;
				// ���� ��������� ������ 䳿
				while (getDispatcher().getCurrentTime() <= finishTime) {
					// �������� �������� ���������� �� ������� �� �� �����
					waitForCondition(queueSize, "� ���� �� �'������� ����������");
					Barge transaction = queue.removeFirst();
					// ������� ������� ����������
					holdForTime(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					System.out.println(rnd.next()*gui.getChooseKilkistKonteiner().getInt());
					transaction.setServiceDone(true);
				}
			}

		
	}

