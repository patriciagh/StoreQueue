package PT2018.assignment2.Assignment2;

import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Gui {
		protected static final JLabel NULL = null;
		private JFrame frame= new JFrame("Supermarket");
		private JLabel arrival ;
		private JLabel minA;
		private JLabel maxA=new JLabel("Max");
		private JLabel processing = new JLabel("Processing time");
		private JLabel minP=new JLabel("Min");
		private JLabel maxP=new JLabel("Max");
		private JLabel nrQueues = new JLabel("Queues");
		private JLabel nrClients = new JLabel("Clients");
		private JLabel simulationTime = new JLabel("Simulation time");
		private JLabel strategy = new JLabel ("Strategy");
		private JLabel time=new JLabel();
		private JLabel peakHour=new JLabel();
		private JRadioButton radioQueue = new JRadioButton("Queue");
		private JRadioButton radioTime = new JRadioButton("Time");
		private JRadioButton radioRandom = new JRadioButton("Random");		
		
		private JTextField minArrival = new JTextField();
		private JTextField maxArrival = new JTextField();
		private JTextField minProcessing = new JTextField();
		private JTextField maxProcessing = new JTextField();
		private JTextField queues = new JTextField();
		private JTextField clients = new JTextField();
		private JTextField simulation = new JTextField();		
		private JButton start=new JButton("Start");
		private JTextArea textArea = new JTextArea();
		private JTextArea logger = new JTextArea();
		private ButtonGroup group = new ButtonGroup();
		private JScrollPane scroll;
		private JScrollPane scrollLogger;
		
		public Gui()
		{
			arrival = new JLabel("Arrival time");
			minA=new JLabel("Min");		
			
			frame.setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			frame.setSize(800,1000);
			
			// Arrival time
			arrival.setBounds(30,20,100,30);
			minA.setBounds(30, 60, 90, 30);
			minArrival.setBounds(70,60,100,30);
			maxA.setBounds(30, 100, 70, 30);
			maxArrival.setBounds(70,100,100,30);
			// Processing time
			processing.setBounds(220,20,100,30);
			minP.setBounds(200, 60, 90, 30);
			minProcessing.setBounds(240,60,100,30);
			maxP.setBounds(200, 100, 90, 30);
			maxProcessing.setBounds(240,100,100,30);
			// Number of queues :
			nrQueues.setBounds(390,20,100,30);
			queues.setBounds(370,60,100,30);
			// Number of clients : 
			nrClients.setBounds(525,20,100,30);
			clients.setBounds(500,60,100,30);
			// simulation stuff
			simulationTime.setBounds(640, 20, 100,30);
			simulation.setBounds(640,60,100,30);
			//buton de start
			start.setBounds(500,100, 100,30);		
			textArea.setBounds(10, 180, 760, 390);	
			textArea.setVisible(true);
			logger.setBounds(10, 590, 760, 350);
			logger.setVisible(true);
			// alegere strategie
			strategy.setBounds(10,140,100,30);
			radioQueue.setBounds(70, 140, 100,30);
			radioQueue.setVisible(true);
			radioTime.setBounds(180, 140, 100,30);
			radioTime.setVisible(true);
			radioRandom.setBounds(290, 140, 100,30);
			radioRandom.setVisible(true);
			time.setBounds(400,560 ,140,40);
			peakHour.setBounds(500,140,150,30);
			logger.setEditable(false);
			group.add(radioQueue);
			group.add(radioTime);
			group.add(radioRandom);
			
			frame.add(radioQueue);
			frame.add(radioTime);
			frame.add(radioRandom);
			frame.add(peakHour);
			frame.add(strategy);
			frame.add(logger);
			scroll = new JScrollPane(textArea);
			scroll.setBounds(10, 180, 760, 390);
			scrollLogger = new JScrollPane(logger);
			scrollLogger.setBounds(10, 590, 760, 350);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollLogger.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			
			frame.add(scrollLogger);
			frame.add(scroll);
			frame.add(start);
			frame.add(simulationTime);
			frame.add(simulation);
			frame.add(clients);
			frame.add(nrClients);
			frame.add(nrQueues);
			frame.add(queues);
			frame.add(minProcessing);
			frame.add(maxProcessing);
			frame.add(processing);
			frame.add(minP);
			frame.add(maxP);
			frame.add(minArrival);
			frame.add(maxArrival);
			frame.add(maxA);
			frame.add(minA);
			frame.add(arrival);
			frame.add(time);
			
			frame.setVisible(true);
			}
		
		public String getMinArrival()
		{			return minArrival.getText();		}
		public String getMaxArrival()
		{			return maxArrival.getText();		}
		public String getMinProcessing()
		{			return minProcessing.getText();		}
		public String getMaxProcessing()
		{			return maxProcessing.getText();		}
		public String getNrQueues()
		{			return queues.getText();		}
		public String getNrClients()
		{			return clients.getText();		}
		public String getSimulationTime()
		{			return simulation.getText();		}		
		public void startSimulation(ActionListener e)
		{			start.addActionListener(e);		}
		public void addText(String s)
		{			logger.append(s);		}		
		public JLabel getTime()
		{			return time;		}
		public JTextArea getTextAreaLogger()
		{			return logger;		}
		public JTextArea getTextAreaSimulation()
		{			return textArea;		}
		public boolean getRadioQueue()
		{			return radioQueue.isSelected();		}
		public boolean getRadioTime()
		{			return radioTime.isSelected();		}
		public boolean getRadioRandom()
		{			return radioRandom.isSelected();		}
		public JLabel getPeakHour()
		{			return this.peakHour;		}
		
		public void setTextArea(JTextArea text)
		{
			this.textArea=text;
		}
}