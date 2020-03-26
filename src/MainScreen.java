import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import dorkbox.notify.Notify;
import dorkbox.notify.Pos;
import dorkbox.util.ActionHandler;
import java.awt.Panel;

public class MainScreen {

	public static JFrame frame = null;
	public static JTable table;
	public static String USERNAME = "Simon";
	public static JLabel lblNewLabel = null;
	public static Object[][] data1 = null;
	public static DefaultTableModel model = null;
	public static HashMap<String, HashMap<String, Double>> hashMaxLevels = null;
	public static void main(String[] args) {
		MainScreen x = new MainScreen();
	}

	public MainScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				hashMaxLevels = buildHash();
				System.out.println(hashMaxLevels.get("ABIO").get("min"));
				frame = new JFrame();
				frame.setBounds(100, 100, 492, 454);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				//On load we need to build the hashMaxLevels hash to monitor share prices.
				try {
					JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8")));
					String[] toFind = ((JSONObject)json.get("values")).get("subs").toString().split(",");

					data1 = new Object[toFind.length][6];
					int count = 0;
					String toPass = ((JSONObject)json.get("values")).get("subs").toString();
					JSONObject jsonStocks = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me/stocks.php?sym=" +toPass), Charset.forName("UTF-8")));
					for(String tmp : toFind) {
						JSONObject tmp1 = ((JSONObject)jsonStocks.get(tmp));
						//System.out.println(((JSONObject)jsonStocks.get(tmp)).get("c"));
						if(tmp1.get("diff").toString().length() > 5) {
							tmp1.put("diff", tmp1.get("diff").toString().substring(0, 5));
						}
						data1[count] = new Object[]{tmp, tmp1.get("c"), tmp1.get("pc"), tmp1.get("diff").toString(), tmp1.get("h"), tmp1.get("l"), "$us"};
						count++;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}

				String[] colNames = new String[]{"Name", "Price", "Close", "Trending", "High", "Low", "Currency"};
				table = new JTable();
				table.setBounds(12, 266, 428, -248);
				table.setVisible(true);
				//TableModel tableModel = new DefaultTableModel(data1,colNames);
				model = new DefaultTableModel(data1, colNames);
				table.setModel(model);

				frame.getContentPane().setLayout(null);
				table.setEnabled(false);
				JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(0, 0, 492, 226);
				frame.getContentPane().add(scrollPane);

				JMenuBar menuBar = new JMenuBar();
				Digital_Clock zz = new Digital_Clock();
				zz.start();
				zz.setVisible(true);
				JPanel x = zz.jPanel1;
				frame.getContentPane().add(x);
				
				//JPanel panel = zz.jPanel1;
				//panel.setBounds(273, 221, 162, 50);
				//frame.getContentPane().add(panel);
				
				JPanel panel_1 = zz.jPanel1;
			
				panel_1.setBounds(10, 238, 190, 86);
				frame.getContentPane().add(panel_1);

				//frame.getContentPane().add(zz);
				JMenu subBar = new JMenu("Manage");
				JMenuItem i1 = new JMenuItem("Subscriptions");
				JMenuItem i2 = new JMenuItem("Alerts");
				table.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent mouseEvent) {
						JTable table =(JTable) mouseEvent.getSource();
						Point point = mouseEvent.getPoint();
						int row = table.rowAtPoint(point);


						//System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
						if (mouseEvent.getClickCount() == 2) {
							// your valueChanged overridden method 
							System.out.println(data1[row][0]);
							CompanyProfile cp = new CompanyProfile(data1[row][0].toString());
							//Now get data on this company in another window

						}
					}
				});
				i2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MaxMin x = new MaxMin();
					}
				});
				i1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Lets manage subscriptions
						Subscriptions subs = new Subscriptions();
						//new UpdateWorker().execute();
					}
				});
				subBar.add(i1);
				subBar.add(i2);
				menuBar.add(subBar);
				JMenuItem menuItem = new JMenuItem("News");
				menuItem.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						System.out.println("OK");
						News news = new News();
					}
				});
				menuBar.add(menuItem);
				frame.setJMenuBar(menuBar);
				frame.setVisible(true);
				//frame.pack();
				new UpdateWorker().execute();

			}
		});

	}
	public HashMap buildHash() {
		String jsonSubs = null;
		HashMap<String, HashMap<String, Double>> hashmaxMin = new HashMap<>();
		String jsonLevels;
		try {
			jsonSubs = ((JSONObject)new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8"))).get("values")).get("subs").toString();
			try {
				jsonLevels = ((JSONObject) new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getMaxMin?usr=" +MainScreen.USERNAME), Charset.forName("UTF-8"))).get("values")).get("levels").toString();
				String[] levelsFormat = jsonLevels.split(",");
				for(String tf : levelsFormat) {
					String [] y = tf.split(":");
					HashMap<String, Double> toAdd = new HashMap<>();
					toAdd.put("min", Double.parseDouble(y[1]));
					toAdd.put("max", Double.parseDouble(y[2]));
					hashmaxMin.put(y[0], toAdd);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return hashmaxMin;
	}
}

class UpdateWorker extends SwingWorker<Void, Void> {

	protected void done() {
		System.out.println("DONE");
		MainScreen.lblNewLabel.setText("TEST");
		//MainScreen.frame.pack();
	}

	@Override
	protected Void doInBackground() throws InterruptedException{

		String belowMin = "";
		String aboveMax = "";
		while(true) {
			Thread.sleep(10000);
			String[] colNames = new String[]{"Name", "Price", "Close", "Trending", "High", "Low", "Currency"};
			System.out.println("Starting");
			try {
				JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8")));
				String[] toFind = ((JSONObject)json.get("values")).get("subs").toString().split(",");
				MainScreen.data1 = new Object[toFind.length][6];
				int count = 0;
				String toPass = ((JSONObject)json.get("values")).get("subs").toString();
				JSONObject jsonStocks = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me/stocks.php?sym=" +toPass), Charset.forName("UTF-8")));
				for(String tmp : toFind) {
					JSONObject tmp1 = ((JSONObject)jsonStocks.get(tmp));
					if(tmp1.get("diff").toString().length() > 5) {
						tmp1.put("diff", tmp1.get("diff").toString().substring(0, 5));
					}
					try {
						if(Double.parseDouble(tmp1.get("c").toString()) > MainScreen.hashMaxLevels.get(tmp).get("max")) {
							aboveMax += tmp + ",";
						}
						if(Double.parseDouble(tmp1.get("c").toString()) < MainScreen.hashMaxLevels.get(tmp).get("min")) {
							belowMin += tmp + ",";
						}
					}
					catch(Exception e) {
						//
					}
					MainScreen.data1[count] = new Object[]{tmp, tmp1.get("c"), tmp1.get("pc"), tmp1.get("diff").toString(), tmp1.get("h"), tmp1.get("l"), "$us"};
					count++;
				}			
				MainScreen.model.setDataVector(MainScreen.data1, colNames);
				if(belowMin.length() > 1 || aboveMax.length() > 1) {
					String txt = "";
					if(belowMin.length() > 1) {
						belowMin = belowMin.substring(0, belowMin.length()-1);
						txt += belowMin + " dropped below your minimum price" + System.getProperty("line.separator");
					}
					if(aboveMax.length() > 1) {
						aboveMax = aboveMax.substring(0, aboveMax.length()-1);
						txt += aboveMax + " rose above your maximum price" + System.getProperty("line.separator");
					}
					Notify notify = Notify.create()
	                        .title("Stock Warning")
	                        .text(txt)
	                        .hideAfter(20000)
	                        .position(Pos.TOP_RIGHT)
//	                    .setScreen(0)
	                        .darkStyle()
	                        // .shake(1300, 4)
	                   //.shake(1300, 10)
	                   .hideCloseButton()
	                        .onAction(new ActionHandler<Notify>() {
	                            @Override
	                            public
	                            void handle(final Notify arg0) {
	                                System.err.println("Notification " + "clicked on!");
	                            }
	                        });
	         notify.show();
	         belowMin = "";
	         aboveMax = "";
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//return null;
	}
}
