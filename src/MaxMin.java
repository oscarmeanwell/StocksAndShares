

import java.awt.EventQueue;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import javax.swing.JTable;

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
				frame.setVisible(true);
				//String[] toFind = ((JSONObject)jsonSubs.get("values")).get("subs").toString().split(",");
			}
		});
		
	}
}
