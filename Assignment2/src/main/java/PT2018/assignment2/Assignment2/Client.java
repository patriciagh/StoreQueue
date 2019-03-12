package PT2018.assignment2.Assignment2;


public class Client {
	private int id;
	private int arrivalTime;
	private int processingTime;
	private int waitingTime;
	
	public Client(int id , int arrival , int processing)
	{
		this.id=id;
		this.arrivalTime=arrival;
		this.processingTime=processing;
	}
	public int getId()
	{
		return this.id;
	}
	public void setArrivalTime(int a)
	{
		this.arrivalTime=a;
	}
	public  int getArrivalTime()
	{
		return this.arrivalTime;
	}
	public void setProcessingTime(int p)
	{
		this.processingTime=p;
	}
	public  int getProcessingTime()
	{
		return this.processingTime;
	}
	public  void setWaitingTime(int w)
	{
		this.waitingTime=w;
	}
	public  int getWaitingTime()
	{
		return this.waitingTime;
	}
	public String afisareClient()
	{
		return ("Clientul "+this.id+" arrival time : "+this.arrivalTime+" processing time : "+this.processingTime+ " waiting time : "+this.waitingTime+"\n");
	}
}
