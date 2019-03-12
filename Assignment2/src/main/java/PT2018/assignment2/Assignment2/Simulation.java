package PT2018.assignment2.Assignment2;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextArea;
public class Simulation implements Runnable{
	private Gui gui=new Gui();
	private ArrayList<Client> clients=new ArrayList<Client>();//clientii generati random
	private ArrayList<Queue> queues = new ArrayList<Queue>();
	private int minArrivalTime=1;
	private int maxArrivalTime=4;
	private	int minProcessingTime=3;
	private	int maxProcessingTime=5;
	private	int nrOfClients=10;
	private	int nrOfQueues=3;
	private	int simTime=10;
	private int strategy;
	private JTextArea textSimulare;
	public Simulation()
	{
		gui.startSimulation(e->{		
			 minArrivalTime=Integer.parseInt(gui.getMinArrival());
			maxArrivalTime=Integer.parseInt(gui.getMaxArrival());
			 minProcessingTime=Integer.parseInt(gui.getMinProcessing());
			 maxProcessingTime=Integer.parseInt(gui.getMaxProcessing());
		 nrOfClients=Integer.parseInt(gui.getNrClients());
		 nrOfQueues=Integer.parseInt(gui.getNrQueues());
			 simTime=Integer.parseInt(gui.getSimulationTime());
			Random random=new Random();
			int arrivalRandom=0;
			int processingRandom=0;
			System.out.println(minProcessingTime+" "+maxProcessingTime);
			if(gui.getRadioQueue()==true && gui.getRadioRandom()==false && gui.getRadioTime()==false)
				strategy=1;
			if(gui.getRadioQueue()==false && gui.getRadioRandom()==true && gui.getRadioTime()==false)
				strategy=0;
			if(gui.getRadioQueue()==false && gui.getRadioRandom()==false && gui.getRadioTime()==true)
				strategy=2;					
			for(int i=1;i<=nrOfClients;i++)
			{
				arrivalRandom=random.nextInt(maxArrivalTime-minArrivalTime)+minArrivalTime;
				processingRandom=random.nextInt(maxProcessingTime-minProcessingTime)+minProcessingTime;
				Client nou=new Client(i,arrivalRandom,processingRandom);
				clients.add(nou);
			}
			// creare cozi in functie de numarul dat-3
			System.out.println("Clientii generati inainte sa fie adaugati in cozi : ");
			for(Client c : clients)
				System.out.println(c.afisareClient());
			//creare cozi in funcie de numarul dat	
			
			for(int i=1;i<=nrOfQueues;i++)
			{
				Queue q=new Queue(i,nrOfQueues,simTime,gui.getTextAreaLogger(),gui.getTextAreaSimulation(),gui.getTime(),gui.getPeakHour());
				queues.add(q);				
			}			
			if(strategy==0) //RANDOM
			{
				for(Client c : clients)
				{
					int poz=random.nextInt(nrOfQueues);
					queues.get(poz).addNewClient(c);
				}
			}
			if(strategy==1) //COADA
			{
				for(Client c : clients)
				{
					int minIndex=0;
					int minSize=queues.get(0).getClients().size();
					for(Queue q : queues)
					{
						if(q.getClients().size() < minSize)
						{
							minIndex=queues.indexOf(q);
							minSize=q.getClients().size();
						}
					}
					queues.get(minIndex).addNewClient(c);
				}
			}			
			if(strategy==2)
			{
				//clientul se pune la coada cu cel mai putin waiting time
				for(Client c : clients)
				{
					int minWaitingTime=queues.get(0).getQueueWaitingTime();
					int indexMinWaiting=0;
					for(Queue q : queues)
					{
						if(q.getQueueWaitingTime() < minWaitingTime) 
						{	indexMinWaiting=q.getIdQueue();
							minWaitingTime=q.getQueueWaitingTime();
						}
					}
				queues.get(indexMinWaiting).addNewClient(c);			
				}
			}
			for(Queue q : queues)
			{
				System.out.println("Coada "+q.getIdQueue());
				q.afisareCoada(q.getClients());
			}
			
			for(int i=0;i<nrOfQueues;i++)
			{
				Thread t=new Thread(queues.get(i));
				t.start();
			}		
			System.out.println(queues.get(0).getLD());
			Thread t1=new Thread(this);
			t1.start();
		
		});		
		
	}

	@Override
	public void run() 
	{
		textSimulare=gui.getTextAreaSimulation();
		while(true)
		{
			textSimulare.setText("");
		for(int i=0 ; i< nrOfQueues ; i++)			
		{
			textSimulare.append(queues.get(i).getLD()+"\n");
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		
	}	
	
}
