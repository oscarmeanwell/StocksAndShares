import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class News {

	public JFrame frame;
	JLabel lblLINK = new JLabel("");
	JTextArea txtArea = new JTextArea();
	JLabel lblLink = new JLabel("Link:");
	JLabel lblPublished = new JLabel("Published");
	JLabel lblTitle = new JLabel("Title");
	JList lstNews = null;
	JLabel lblClickATitle = new JLabel("NEWS: Click a title to see the details");
	HashMap<String, JSONObject> main = new HashMap<>();
	private final JLabel lblPoweredByNewsapi = new JLabel("Powered by newsapi.org");
	public static void main(String[] args) {
		News x = new News();
	}

	public News() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 640, 459);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ArrayList<String> newsTitles = new ArrayList<>();
				

				try {
					JSONArray json = new JSONArray(IOUtils.toString(new URL("http://oscarmeanwell.me/stockNews.php"), Charset.forName("UTF-8")));
					
					for(int i = 0; i < json.length(); i++) {
						JSONObject tmp = json.getJSONObject(i);
						main.put(tmp.get("title").toString(), tmp);
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.getContentPane().setLayout(null);
				
				lstNews = new JList(main.keySet().toArray());
				
				//frame.getContentPane().add(lstNews);
				JScrollPane scrollP = new JScrollPane(lstNews);
				scrollP.setBounds(0, 41, 640, 131);
				lstNews.setBounds(0, 45, 640, 111);
				frame.getContentPane().add(scrollP);
				
				
				lblClickATitle.setBounds(12, 12, 376, 15);
				frame.getContentPane().add(lblClickATitle);
				
				
				lblTitle.setBounds(12, 184, 598, 15);
				frame.getContentPane().add(lblTitle);
				
				
				lblPublished.setBounds(12, 211, 574, 15);
				frame.getContentPane().add(lblPublished);
				
				
				lblLink.setBounds(12, 238, 66, 15);
				frame.getContentPane().add(lblLink);
				
				
				txtArea.setBounds(12, 265, 616, 115);
				frame.getContentPane().add(txtArea);
				lstNews.setSelectedIndex(0);
				updateLBLS();
			
				
				lblLINK.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String toOpen = lblLINK.getText().toString();
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    try {
								Desktop.getDesktop().browse(new URI(toOpen));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						}
					}
				});
				lblLINK.setBounds(62, 238, 566, 15);
				frame.getContentPane().add(lblLINK);
				
				JButton btnDone = new JButton("Done");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
					}
				});
				btnDone.setBounds(263, 392, 114, 25);
				frame.getContentPane().add(btnDone);
				lblPoweredByNewsapi.setForeground(Color.GREEN);
				lblPoweredByNewsapi.setBounds(22, 397, 182, 15);
				
				frame.getContentPane().add(lblPoweredByNewsapi);
				frame.setDefaultCloseOperation(0);
				frame.setVisible(true);
				
				lstNews.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent arg0) {
						if (!arg0.getValueIsAdjusting()) {//This line prevents double events
							updateLBLS();
					    }
						
					}
				});
			}
		});
	}
	
	public void updateLBLS() {
		String article = lstNews.getSelectedValue().toString();
		lblTitle.setText("Title: " + article);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String p1 = main.get(article).getString("publishedAt").substring(0, main.get(article).getString("publishedAt").length()-1);
		p1 = p1.replace('T', ' ');
		Date formattedD = null;
		try {
			formattedD = format1.parse(p1);
			System.out.println(formattedD);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		lblPublished.setText("Published: " + formattedD);
		txtArea.setText(main.get(article).getString("description"));
		txtArea.setLineWrap(true);
		lblLINK.setText(main.get(article).getString("url"));
		lblLINK.setForeground(Color.BLUE);
	}


	
}
