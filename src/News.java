import java.awt.EventQueue;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.JLabel;


public class News {

	public JFrame frame;
	HashMap<String, JSONObject> main = new HashMap<>();
	public static void main(String[] args) {
		News x = new News();
		
	}

	public News() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 640, 420);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.getContentPane().setLayout(null);
				
				
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
				//frame.getContentPane().setLayout(null);
				//frame.getContentPane().setLayout(null);
				//frame.getContentPane().setLayout(null);
				
				JList lstNews = new JList(main.keySet().toArray());
				//frame.getContentPane().add(lstNews);
				JScrollPane scrollP = new JScrollPane(lstNews);
				scrollP.setBounds(0, 41, 640, 131);
				lstNews.setBounds(0, 45, 640, 111);
				frame.getContentPane().add(scrollP);
				
				JLabel lblClickATitle = new JLabel("NEWS: Click a title to see the details");
				lblClickATitle.setBounds(12, 12, 376, 15);
				frame.getContentPane().add(lblClickATitle);
				
				frame.setVisible(true);
			}
		});
	}


}
