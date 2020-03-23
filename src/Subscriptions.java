import java.awt.EventQueue;
import java.io.IOException;
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
import javax.swing.JButton;

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
				int [] selected = {1,2,3};
				list_1.setSelectedIndices(selected);

				
				list_1.setBounds(12, 253, 426, -189);
				JScrollPane pane = new JScrollPane(list_1);
				pane.setBounds(0, 39, 450, 186);
				frame.getContentPane().add(pane);
				
				JButton btnSubmit = new JButton("Confirm");
				btnSubmit.setBounds(168, 235, 114, 25);
				frame.getContentPane().add(btnSubmit);
				frame.setVisible(true);


			}
		});
	}
}
