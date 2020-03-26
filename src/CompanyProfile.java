import java.awt.Desktop;
import java.awt.EventQueue;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;

import javax.swing.JFrame;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import dorkbox.notify.Notify;
import dorkbox.notify.Pos;
import dorkbox.util.ActionHandler;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompanyProfile {

	private JFrame frame;
	public String gURL = ""; 
	public static void main(String[] args) {
		CompanyProfile x = new CompanyProfile("AAPL");
	}

	//https://github.com/dorkbox/Notify
	
	public CompanyProfile(String profileName) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 450, 335);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				frame.setDefaultCloseOperation(0);
				//frame.getContentPane().setLayout(null);
				
				JLabel lblCompany = new JLabel("Company:");
				lblCompany.setBounds(12, 12, 426, 15);
				frame.getContentPane().add(lblCompany);
				
				JLabel lblTicker = new JLabel("Ticker: ");
				lblTicker.setBounds(12, 32, 426, 15);
				frame.getContentPane().add(lblTicker);
				
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(12, 54, 426, 15);
				frame.getContentPane().add(lblDescription);
				
				JTextArea textArea = new JTextArea();
				//frame.getContentPane().add(textArea);
				textArea.setBounds(73, 81, 318, 15);
				textArea.setLineWrap(true);
				textArea.setEditable(false);
				
				JScrollPane pane = new JScrollPane(textArea);
				pane.setBounds(12, 72, 423, 68);
				frame.getContentPane().add(pane);
				
				
				JLabel lblEmployee = new JLabel("Total");
				lblEmployee.setBounds(12, 146, 426, 15);
				frame.getContentPane().add(lblEmployee);
				
				JLabel lblExchange = new JLabel("Exchange");
				lblExchange.setBounds(12, 173, 426, 15);
				frame.getContentPane().add(lblExchange);
				
				JLabel lblCity = new JLabel("City");
				lblCity.setBounds(12, 200, 426, 15);
				frame.getContentPane().add(lblCity);
				
				JLabel lblUrl = new JLabel("City");
				lblUrl.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
						    try {
								Desktop.getDesktop().browse(new URI(gURL));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						}
					}
				});
				lblUrl.setBounds(12, 227, 450, 23);
				frame.getContentPane().add(lblUrl);
				
				frame.setVisible(true);
				JButton btnDone = new JButton("Done");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frame.setVisible(false);
					}
				});
				btnDone.setBounds(168, 256, 114, 25);
				frame.getContentPane().add(btnDone);
				
				try {
					JSONObject json = new JSONObject(IOUtils.toString(new URL("http://oscarmeanwell.me/stockProfile.php?sym="+profileName), Charset.forName("UTF-8")));
					//[country, address, city, phone, name, description, currency, employeeTotal, exchange, url]
					System.out.println(json.keySet());
					lblCity.setText("Address: " + json.get("city").toString() + ", " + json.getString("address") + ", " + json.getString("country"));
					lblCompany.setText("Name: " + json.getString("name"));
					lblTicker.setText("Ticker: " + profileName);
					lblEmployee.setText("Employee Total: " + json.getString("employeeTotal"));
					lblExchange.setText("Exchange: " + json.getString("exchange"));
					lblUrl.setText("URL: " + json.getString("url"));
					
					gURL = json.getString("url");
					textArea.setText(json.getString("description"));
					
				
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			      
			}
		});
	}
}
