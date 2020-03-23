import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	public JFrame frame = null;
	private JTable table;


	public MainScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 492, 330);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				

				String[] colNames = new String[]{"Name", "Price", "Trending", "High", "Low", "Currency"};
				Object[][] data = new Object[][] {
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"},
					{"1", "2", "3", "4", "5", "6"}
				};
				table = new JTable(data, colNames);
				table.setBounds(12, 266, 428, -248);
				table.setVisible(true);
				frame.getContentPane().add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
				
				JMenuBar menuBar = new JMenuBar();
				JMenu subBar = new JMenu("Manage");
				JMenuItem i1 = new JMenuItem("Subscriptions");
				i1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Lets manage subscriptions
						
					}
				});
				subBar.add(i1);
				menuBar.add(subBar);
				frame.setJMenuBar(menuBar);
				frame.setVisible(true);
				//frame.pack();

			}
		});

	}
}
