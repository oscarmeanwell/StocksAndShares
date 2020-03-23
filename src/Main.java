import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;

public class Main extends JFrame {
	private JTable tblMain;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		JMenuItem i1 = new JMenuItem("Test");
		menuBar.add(i1);
		setJMenuBar(menuBar);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(450, 0, -448, 251);
		
		
		
		String[] colNames = {"Name", "Price", "Trending", "High", "Low", "Currency"};
		String[][] data = {
				{"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}
		};
		tblMain = new JTable(data,colNames);
		tblMain.setBounds(0, 173, 438, -161);
		JScrollPane sp = new JScrollPane(tblMain);
		//getContentPane().add(tblMain);
		panel.add(sp);
		getContentPane().add(panel);
	}
}
