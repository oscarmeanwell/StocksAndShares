import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.sun.tools.javac.Main;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen {

	public static JFrame frame = null;
	private JTable table;
	public static String USERNAME = "";
	public static JLabel lblNewLabel = null;

	public MainScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Object[][] data1 = null;
				frame = new JFrame();
				frame.setBounds(100, 100, 492, 330);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				try {
					JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me:3001/getSubs?usr="+MainScreen.USERNAME), Charset.forName("UTF-8")));
					String[] toFind = ((JSONObject)json.get("values")).get("subs").toString().split(",");
					data1 = new Object[toFind.length][6];
					int count = 0;
					for(String tmp : toFind) {
						data1[count] = new Object[]{tmp, "2", "3", "4", "5", "6"};
						count++;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				String[] colNames = new String[]{"Name", "Price", "Trending", "High", "Low", "Currency"};
				table = new JTable(data1, colNames);
				table.setBounds(12, 266, 428, -248);
				table.setVisible(true);
				frame.getContentPane().setLayout(null);
				table.setEnabled(false);
				JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(0, 0, 492, 226);
				frame.getContentPane().add(scrollPane);
				
				lblNewLabel = new JLabel("New label");
				lblNewLabel.setBounds(105, 238, 66, 15);
				frame.getContentPane().add(lblNewLabel);
				
				JMenuBar menuBar = new JMenuBar();
				
				JMenu subBar = new JMenu("Manage");
				JMenuItem i1 = new JMenuItem("Subscriptions");
				i1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Lets manage subscriptions
						Subscriptions subs = new Subscriptions();
						new UpdateWorker().execute();
					}
				});
				subBar.add(i1);
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

			}
		});

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
		
		Thread.sleep(5000);
		return null;
	}
}
