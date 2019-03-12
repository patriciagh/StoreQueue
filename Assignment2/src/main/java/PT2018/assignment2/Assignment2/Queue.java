package PT2018.assignment2.Assignment2;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextArea;
public class Queue implements Runnable{
	private int idQueue;
	private ArrayList<Client> clients=new ArrayList<Client>();// tre sa ajunga
	private ArrayList<Client> queue=new ArrayList<Client>();// deja in coada
	private ArrayList<Client> processed=new ArrayList<Client>();
	private int time=0;
	private int nrCozi;
	private int simulationTime;
	private int totalWaitingTime=0;
	private float avgWaitingTime=0;
	private JTextArea logger;
	private JTextArea sim;
	private JLabel timeGui;
	private JLabel peakHourGui;
	private int peakHour;
	private int max;
	private String LD ="";
	public Queue(ArrayList<Client> clienti , int id)
	{
		this.clients=clienti;
		this.idQueue=id;
	}
	public Queue(int i,int nrCozi, int simTime ,JTextArea log , JTextArea sim , JLabel time , JLabel peakHourGui) {
		this.idQueue=i;
		this.nrCozi=nrCozi;
		this.simulationTime=simTime;
		this.logger=log;
		this.sim=sim;
		this.timeGui=time;
		this.peakHourGui=peakHourGui;
	}
	public int getIdQueue()
	{
		return this.idQueue;
	}
	public void setClients(ArrayList<Client> c)	
	{
		this.clients=c;
	}
	public ArrayList<Client> getClients()
	{
		return this.clients;
	}
	public int isFirst(Client c)
	{
		int ok=1;
		if(queue.size()>0)
		{
			Client first=queue.get(0);
			if(first.equals(c))
				ok=0;
		}
		return ok;
	}
	public void setSimulationTime(int sm)
	{
		this.simulationTime=sm;
	}
	public void afisareCoada(ArrayList<Client> coada)
	{
		for(int i=0;i<coada.size();i++)
			System.out.println(coada.get(i).afisareClient());
	}
	// GET LD
	public String getLD()
	{		
		return this.LD;
	}
	public void run() 
	{	//max=0;
		//peakHour=0;
		try {
			
				while(time <= simulationTime)
				{	
									
						System.out.println("Time is : "+time);
						timeGui.setText("Simulation time is : "+time+"");//afisare timp in gui
						time++;		
						// Adaug clientii generati in coada 
						for(Client c : this.clients)
						{
							if(c.getArrivalTime()==time-1)
							{
								queue.add(c);
								System.out.println("Clientul "+ c.getId() +" a fost adaugat in coada "+this.idQueue+" la momentul "+(time-1));
								logger.append("Clientul "+ c.getId() +" a fost adaugat in coada "+this.idQueue+" la momentul "+(time-1)+"\n");
							}
						}									
						Iterator<Client> i=queue.iterator();
						LD = (time-1)+"  Coada  "+this.idQueue+" : ";
						while(i.hasNext())
						{
							if(queue.size() > max)
							{
								max=queue.size();
								peakHour=time-1;
							}
							Client c= i.next();
							if(isFirst(c)==0)
							{
								if(c.getProcessingTime()>0)
								{
									c.setProcessingTime(c.getProcessingTime()-1);
									LD=LD+"    C "+c.getId();
								}
								else 
								{							
									processed.add(c);
									System.out.println("Clientul "+c.getId() +" a plecat din coada "+this.idQueue+" la momentul "+(time-1));
									logger.append("Clientul "+c.getId() +" a plecat din coada "+this.idQueue+" la momentul "+(time-1)+"\n");
									i.remove();
									clients.remove(c);
								}
			
							}
							else
							{					
								int wt=c.getWaitingTime();
								wt++;
								c.setWaitingTime(wt);
								LD=LD+"    C "+c.getId();
							}
						}
						try {
								Thread.sleep(1000);
						} catch (InterruptedException e) {
								e.printStackTrace();
							}
										
					}//while
			//}//lock		
		}catch (Exception e) {
			e.printStackTrace();
	        }// end try		
		logger.append("\nSimularea s-a terminat"+"\n");
		System.out.println("Processed coada : "+this.idQueue);
		afisareCoada(processed);
		System.out.println("Queue coada : "+this.idQueue);
		afisareCoada(queue);	
		totalWaitingTime=this.afisareWaitingTime(processed, processed.size());
		if(processed.size()>0) avgWaitingTime = ( totalWaitingTime / processed.size());
		else avgWaitingTime=0;
		peakHourGui.setText("Peak hour : "+peakHour);
		logger.append("Queue : "+this.idQueue+" Total waiting time : "+totalWaitingTime + " Avg waiting time : "+avgWaitingTime);
	}	
	public void addNewClient(Client c)
	{
		this.clients.add(c);
	}
	public int getQueueWaitingTime()
	{
		int nr=0;
		for(Client c : this.clients)
		{
			nr=nr+c.getProcessingTime();
		}
		nr=nr-simulationTime;
		if(nr<0) nr=0;
		return nr;
	}	
	public int afisareWaitingTime(ArrayList<Client> q , int nr)
	{
		int max=0;
		for(Client c : q)
		{
				if(c.getWaitingTime() > max)
					max=c.getWaitingTime();
		}
		return max;
	}
}