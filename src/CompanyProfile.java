import java.awt.EventQueue;

import javax.swing.JFrame;

import dorkbox.notify.Notify;
import dorkbox.notify.Pos;
import dorkbox.util.ActionHandler;

public class CompanyProfile {

	private JFrame frame;


	public static void main(String[] args) {
		CompanyProfile x = new CompanyProfile("AAPL");
	}

	//https://github.com/dorkbox/Notify
	
	public CompanyProfile(String profileName) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame();
				frame.setBounds(100, 100, 450, 300);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
	            Notify notify = Notify.create()
                        .title("Notify title ")
                        .text("This is a notification " + " popup message This is a notification popup message This is a " +
                              "notification popup message")
                        .hideAfter(10000)
                        .position(Pos.TOP_RIGHT)
//                    .setScreen(0)
                        .darkStyle()
                        // .shake(1300, 4)
                   //.shake(1300, 10)
                   .hideCloseButton()
                        .onAction(new ActionHandler<Notify>() {
                            @Override
                            public
                            void handle(final Notify arg0) {
                                System.err.println("Notification " + "clicked on!");
                            }
                        });
         notify.show();

			      
			}
		});
	}
}
