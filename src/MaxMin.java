

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class MaxMin {

	public JFrame frame;
	private JTable table;
	public static Object[][] data1 = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MaxMin x = new MaxMin();
	}

	/**
	 * Create the application.
	 */
	public MaxMin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 531, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				//frame.getContentPane().setLayout(null);
				//frame.getContentPane().setLayout(null);
				
				JLabel lblSetMaximumAnd = new JLabel("Set Maximum and Minimum Stock Prices to receive real time alerts");
				lblSetMaximumAnd.setBounds(12, 12, 507, 15);
				frame.getContentPane().add(lblSetMaximumAnd);
				String[] colNames = new String[]{"Name", "Min", "Max"};
				
			
				
				String jsonSubs = null;
				HashMap<String, HashMap<String, Double>> hashmaxMin = new HashMap<>();
				String jsonLevels;
				try {
					jsonSubs = ((JSONObject)new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8"))).get("values")).get("subs").toString();
					try {
						jsonLevels = ((JSONObject) new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getMaxMin?usr=" +MainScreen.USERNAME), Charset.forName("UTF-8"))).get("values")).get("levels").toString();
						System.out.println(jsonLevels);
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
					
					System.out.println(jsonSubs);
					data1 = new Object[jsonSubs.split(",").length][6];
					int count = 0;
					for(String z : jsonSubs.split(",")) {
						//Only show them levels of subs they are still following
						System.out.println(z);
						try {
							data1[count] = new Object[]{z, hashmaxMin.get(z).get("min"), hashmaxMin.get(z).get("max")};
						}
						catch(NullPointerException n) {
							data1[count] = new Object[]{z, "", ""};
						}
						
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
				table = new JTable(data1, colNames);
				table.setBounds(511, 39, -488, 181);
				JScrollPane pane = new JScrollPane(table);
				pane.setBounds(0, 39, 531, 192);
				frame.getContentPane().add(pane);
				
				JButton btnConfirm = new JButton("Confirm");
				
				btnConfirm.setBounds(208, 235, 114, 25);
				frame.getContentPane().add(btnConfirm);
				frame.setVisible(true);
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						TableModel model = (TableModel) table.getModel();
						String toServer = "";
				        for (int i = 0; model.getRowCount() > i; i++) {
				            final String col1 = (String) model.getValueAt(i, 0);
				            final String colMin = model.getValueAt(i, 1).toString();
				            final String colMax = model.getValueAt(i, 2).toString();
				            if(colMin.length()> 1 && colMax.length()>1) {
				            	//Then send to server
				            	toServer += col1 + ":" + colMin + ":" + colMax + ",";
				            }
				        }
				        
				        toServer = toServer.substring(0, toServer.length()-1);
				        try {
				        	if(toServer.length()>5) {
				        		System.out.println("HERE TO " + toServer);
				        		JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/saveMaxMin?usr=" + MainScreen.USERNAME + "&levels=" + toServer), Charset.forName("UTF-8")));
								frame.setVisible(false);
				        	}
				        }
				        catch(Exception e) {
				        	e.printStackTrace();
				        }
				        //Now send this to server and hide the window
					}
				});
				//String[] toFind = ((JSONObject)jsonSubs.get("values")).get("subs").toString().split(",");
			}
		});
		
	}
}
