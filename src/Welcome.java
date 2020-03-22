import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Welcome {

	public JFrame frame;
	public JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
					window.frame.setVisible(true);
					window.frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Welcome() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, please Login!");
		lblWelcome.setBounds(137, 12, 175, 50);
		frame.getContentPane().add(lblWelcome);
		lblWelcome.requestFocusInWindow();
		frame.getContentPane().requestFocusInWindow();

		txtUser = new JTextField();
		
		txtUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUser.getText().toString().equals("Username")) {
					txtUser.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUser.getText().toString().trim().equals("")) {
					txtUser.setText("Username");
				}
			}
		});
		txtUser.setText("Username");
		txtUser.setBounds(164, 90, 124, 19);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usr = txtUser.getText().toString();
				String pwd = String.valueOf(txtPassword.getPassword());
				MessageDigest md5 = null;
				try {
					md5 = MessageDigest.getInstance("MD5");
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				md5.update(StandardCharsets.UTF_8.encode(pwd));
				String pwdHashed = String.format("%032x", new BigInteger(1, md5.digest()));
				String toPass = "http://oscarmeanwell.me:3001/retrieve?usr=" + usr + "&pwd=" + pwdHashed;
				try {
					JSONObject json = new JSONObject(IOUtils.toString(new URL(toPass), Charset.forName("UTF-8")));
					if(Integer.parseInt(json.get("status").toString()) == 1) {
						System.out.println("LOGIN");
					}
					else {
						JOptionPane.showMessageDialog(frame, "Authentification error", "InfoBox: Registration", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(164, 152, 124, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Here they want to register
				frame.setVisible(false);
				Register window = new Register();
				
			}
		});
		btnRegister.setBounds(12, 235, 114, 25);
		frame.getContentPane().add(btnRegister);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(txtPassword.getPassword()).equals("Password")) {
					txtPassword.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(txtPassword.getPassword()).equals("")) {
					txtPassword.setText("Password");
				}
			}
		});
		txtPassword.setBounds(164, 121, 124, 19);
		txtPassword.setText("Password");
		frame.getContentPane().add(txtPassword);
		
	}
}
