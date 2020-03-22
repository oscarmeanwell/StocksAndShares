import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Register {

	public JFrame frame = null;
	private JTextField txtUser;
	private JTextField txtPwd;
	private JTextField txtPwd1;

	public Register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
					
					JLabel lblWelcomePleaseCreate = new JLabel("Welcome, please create an account");
					lblWelcomePleaseCreate.setBounds(76, 12, 297, 15);
					frame.getContentPane().add(lblWelcomePleaseCreate);
					
					txtUser = new JTextField();
					txtUser.setBounds(200, 39, 124, 19);
					frame.getContentPane().add(txtUser);
					txtUser.setColumns(10);
					
					JLabel lblUsername = new JLabel("Username:");
					lblUsername.setBounds(97, 39, 98, 15);
					frame.getContentPane().add(lblUsername);
					
					txtPwd = new JTextField();
					txtPwd.setColumns(10);
					txtPwd.setBounds(200, 70, 124, 19);
					frame.getContentPane().add(txtPwd);
					
					JLabel lblPassword = new JLabel("Password:");
					lblPassword.setBounds(97, 70, 98, 15);
					frame.getContentPane().add(lblPassword);
					
					txtPwd1 = new JTextField();
					txtPwd1.setColumns(10);
					txtPwd1.setBounds(200, 101, 124, 19);
					frame.getContentPane().add(txtPwd1);
					
					JLabel lblUsername_1_1 = new JLabel("Confirm:");
					lblUsername_1_1.setBounds(97, 101, 98, 15);
					frame.getContentPane().add(lblUsername_1_1);
					
					JButton btnCreate = new JButton("Create");
					btnCreate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String usr = txtUser.getText().toString();
							String pwd = txtPwd.getText().toString();
							MessageDigest md5 = null;
							try {
								md5 = MessageDigest.getInstance("MD5");
							} catch (NoSuchAlgorithmException e1) {
								e1.printStackTrace();
							}
							md5.update(StandardCharsets.UTF_8.encode(pwd));
							String pwd2 = String.format("%032x", new BigInteger(1, md5.digest()));
							String toPass = "http://oscarmeanwell.me:3001/create?usr=" + usr + "&pwd=" + pwd2;
							System.out.println(toPass);
							try {
								JSONObject json = new JSONObject(IOUtils.toString(new URL(toPass), Charset.forName("UTF-8")));
								if(Integer.parseInt(json.get("status").toString()) == 1) {
									System.out.println("Success");
								}
								else if(Integer.parseInt(json.get("status").toString()) == 100) {
									System.out.println("Username Exists");
								}
								
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
					});
					btnCreate.setBounds(172, 159, 114, 25);
					frame.getContentPane().add(btnCreate);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
