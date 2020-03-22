import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register {

	public JFrame frame = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
					
					textField = new JTextField();
					textField.setBounds(200, 39, 124, 19);
					frame.getContentPane().add(textField);
					textField.setColumns(10);
					
					JLabel lblUsername = new JLabel("Username:");
					lblUsername.setBounds(97, 39, 98, 15);
					frame.getContentPane().add(lblUsername);
					
					textField_1 = new JTextField();
					textField_1.setColumns(10);
					textField_1.setBounds(200, 70, 124, 19);
					frame.getContentPane().add(textField_1);
					
					JLabel lblPassword = new JLabel("Password:");
					lblPassword.setBounds(97, 70, 98, 15);
					frame.getContentPane().add(lblPassword);
					
					textField_2 = new JTextField();
					textField_2.setColumns(10);
					textField_2.setBounds(200, 101, 124, 19);
					frame.getContentPane().add(textField_2);
					
					JLabel lblUsername_1_1 = new JLabel("Confirm:");
					lblUsername_1_1.setBounds(97, 101, 98, 15);
					frame.getContentPane().add(lblUsername_1_1);
					
					JButton btnCreate = new JButton("Create");
					btnCreate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("Fuck");
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
