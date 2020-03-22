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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class Register {

	public JFrame frame = null;
	private JTextField txtUser;
	private JPasswordField txtPwd;
	private JPasswordField txtPwd1;

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

					JLabel lblPassword = new JLabel("Password:");
					lblPassword.setBounds(97, 70, 98, 15);
					frame.getContentPane().add(lblPassword);

					JLabel lblUsername_1_1 = new JLabel("Confirm:");
					lblUsername_1_1.setBounds(97, 101, 98, 15);
					frame.getContentPane().add(lblUsername_1_1);

					JButton btnCreate = new JButton("Create");
					btnCreate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String usr = txtUser.getText().toString();
							String pwd = String.valueOf(txtPwd.getPassword());
							String pwdTwo = String.valueOf(txtPwd1.getPassword());
							System.out.println("USRIS: " + pwd +"test");
							if(pwd.isEmpty()  || pwdTwo.isEmpty() || usr.isEmpty()) {
								JOptionPane.showMessageDialog(frame, "Please ensure all fields are complete", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
							}
							else if(!pwd.equals(pwdTwo)) {
								JOptionPane.showMessageDialog(frame, "Please ensure your passwords match", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);	
							}
							else if(pwd.length() < 6) {
								JOptionPane.showMessageDialog(frame, "Please use at least 6 characters", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								MessageDigest md5 = null;
								try {
									md5 = MessageDigest.getInstance("MD5");
								} catch (NoSuchAlgorithmException e1) {
									e1.printStackTrace();
								}
								md5.update(StandardCharsets.UTF_8.encode(pwd));
								String pwd2 = String.format("%032x", new BigInteger(1, md5.digest()));
								String toPass = "http://oscarmeanwell.me:3001/create?usr=" + usr + "&pwd=" + pwd2;
								try {
									JSONObject json = new JSONObject(IOUtils.toString(new URL(toPass), Charset.forName("UTF-8")));
									if(Integer.parseInt(json.get("status").toString()) == 1) {
										JOptionPane.showMessageDialog(frame, "Success! Please login", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
										frame.setVisible(false);
										Welcome window = new Welcome();
										window.txtUser.setText(usr);
										window.frame.setVisible(true);
										window.frame.requestFocusInWindow();
										
									}
									else if(Integer.parseInt(json.get("status").toString()) == 100) {
										JOptionPane.showMessageDialog(frame, "Please try a different username, that ones exists", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
									}

								} catch (Exception e) {
									e.printStackTrace();
								} 
							}
						}
					});
					btnCreate.setBounds(172, 159, 114, 25);
					frame.getContentPane().add(btnCreate);

					txtPwd = new JPasswordField();
					txtPwd.setBounds(200, 70, 124, 19);
					frame.getContentPane().add(txtPwd);

					txtPwd1 = new JPasswordField();
					txtPwd1.setBounds(200, 99, 124, 19);
					frame.getContentPane().add(txtPwd1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
