import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome {

	public JFrame frame;
	public JTextField txtUser;
	private JTextField txtPassword;

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
		
		txtPassword = new JTextField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().toString().equals("Password")) {
					txtPassword.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().toString().trim().equals("")) {
					txtPassword.setText("Password");
				}
			}
		});
		
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.setBounds(164, 121, 124, 19);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("TEST");
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
		
	}
}
