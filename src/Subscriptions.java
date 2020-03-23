import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Subscriptions {

	public JFrame frame;

	public static void main(String[] args) {
		Subscriptions x = new Subscriptions();

	}

	public Subscriptions() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Subscriptions window = new Subscriptions();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame = new JFrame();
				frame.setBounds(100, 100, 450, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				//frame.getContentPane().setLayout(null);
				
				JLabel lblFollowing = new JLabel("Ctrl Click to subscribe/unsubscribe from a company");
				lblFollowing.setBounds(12, 12, 401, 15);
				frame.getContentPane().add(lblFollowing);
				
				
				List<String> list = null;
				try {
					list = Files.readAllLines(Paths.get("src/lib/names.txt"), StandardCharsets.UTF_8);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String[] a = list.toArray(new String[list.size()]);
				
				ArrayList<String> newA = new ArrayList<>();
				JList list_1 = new JList(newA.toArray(a));
				list_1.setSelectionMode(
	                    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				int [] selected = new int[1000];
				

				try {
					MainScreen.USERNAME = "Simon"; //debug
					JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8")));
					String[] toFind = ((JSONObject)json.get("values")).get("subs").toString().split(",");
					int count = 0;
					for(String tmp : toFind) {
						selected[count] = list.indexOf(tmp);
						count++;
					}
				} catch (JSONException | IOException e) {
					e.printStackTrace();
				}
				list_1.setSelectedIndices(selected);
				list_1.setBounds(12, 253, 426, -189);
				JScrollPane pane = new JScrollPane(list_1);
				pane.setBounds(0, 39, 450, 186);
				frame.getContentPane().add(pane);
				
				JButton btnSubmit = new JButton("Confirm");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						List<String> selectedItems = list_1.getSelectedValuesList();
						String buildString = "";
						for(String tmp2 : selectedItems) {
							if(tmp2 != null) {
								buildString += tmp2 + ",";
							}
						}
						buildString = buildString.substring(0, buildString.length()-1);
						try {
							JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/saveSubs?usr=" + MainScreen.USERNAME + "&subs=" + buildString), Charset.forName("UTF-8")));
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
				});
				btnSubmit.setBounds(168, 235, 114, 25);
				frame.getContentPane().add(btnSubmit);
				frame.setVisible(true);


			}
		});
	}
}
