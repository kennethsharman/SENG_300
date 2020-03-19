package iteration_2;

/**
 * The StaffLogin.java class provides a frame for Staff login. Both doctors and nurses are redirected to this frame
 * from the MainFrame class when attempting to login.
 * 
 * @author		SENG 300 Group 12 - Winter 2020
 * Date:		2020-03-16
 */

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class StaffLogin extends JPanel {

	private static final long serialVersionUID = 8L; // serial ID for java object saving
	private JTextField txt_username_doc;
	private JTextField txt_password_doc;
	private JTextField txt_password_nurse;
	private JTextField txt_username_nurse;

	/**
	 * Create the panel.
	 */
	public StaffLogin(final JFrame frame) {

		setLayout(null);

		JLabel lbl_Staff_login_screen = new JLabel("Staff Login Screen");
		lbl_Staff_login_screen.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_Staff_login_screen.setBounds(147, 11, 174, 36);
		add(lbl_Staff_login_screen);

		JLabel lbl_doctors_login_here = new JLabel("Doctors Login Here");
		lbl_doctors_login_here.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_doctors_login_here.setBounds(36, 58, 167, 23);
		add(lbl_doctors_login_here);

		JLabel lbl_username_doc = new JLabel("Username");
		lbl_username_doc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_username_doc.setBounds(60, 92, 70, 23);
		add(lbl_username_doc);

		JLabel lbl_password_doc = new JLabel("Password");
		lbl_password_doc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_password_doc.setBounds(220, 92, 70, 23);
		add(lbl_password_doc);

		txt_username_doc = new JTextField();
		txt_username_doc.setBounds(60, 126, 115, 20);
		add(txt_username_doc);
		txt_username_doc.setColumns(10);

		txt_password_doc = new JPasswordField();
		txt_password_doc.setColumns(10);
		txt_password_doc.setBounds(220, 126, 115, 20);
		add(txt_password_doc);

		JLabel lbl_nurses_login_here = new JLabel("Nurses Login Here");
		lbl_nurses_login_here.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_nurses_login_here.setBounds(36, 157, 167, 23);
		add(lbl_nurses_login_here);

		JLabel lbl_username_nurse = new JLabel("Username");
		lbl_username_nurse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_username_nurse.setBounds(60, 191, 70, 23);
		add(lbl_username_nurse);

		JLabel lbl_password_nurse = new JLabel("Password");
		lbl_password_nurse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_password_nurse.setBounds(220, 191, 70, 23);
		add(lbl_password_nurse);

		txt_password_nurse = new JPasswordField();
		txt_password_nurse.setColumns(10);
		txt_password_nurse.setBounds(220, 225, 115, 20);
		add(txt_password_nurse);

		txt_username_nurse = new JTextField();
		txt_username_nurse.setColumns(10);
		txt_username_nurse.setBounds(60, 225, 115, 20);
		add(txt_username_nurse);

		JButton btn_exit = new JButton("Exit");
		// if the exit button is clicked, then terminate application
		btn_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btn_exit.setBounds(351, 266, 89, 23);
		add(btn_exit);

		final JLabel lbl_invalid_password = new JLabel("Invalid Information");
		lbl_invalid_password.setVisible(false);
		lbl_invalid_password.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_invalid_password.setForeground(Color.RED);
		lbl_invalid_password.setBounds(351, 174, 110, 23);
		add(lbl_invalid_password);

		JButton btn_login_doc = new JButton("Login");
		btn_login_doc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String username = txt_username_doc.getText();
				String password = txt_password_doc.getText();

				try {
					DoctorDB.initDB(); // initialize the doctor database file
					DoctorDB doctorDB = new DoctorDB();
					doctorDB = doctorDB.loadDoctorDB(); // load database from file

					// if username and password don't match in the doctor database, then display error
					// else, create DoctorDashboards frame and pass parameter String array containing username+pass
					if (!doctorDB.checkForMatch(username, password)) {
						lbl_invalid_password.setVisible(true);
					} else {
						String doctorID = doctorDB.getID(username, password);
						DoctorDashboard login = new DoctorDashboard(frame, doctorID);
						frame.setContentPane(login);
						frame.revalidate();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}); // end MouseListener for btn_login_doc "Login"

		btn_login_doc.setBounds(351, 125, 89, 23);
		add(btn_login_doc);

		JButton btn_login_nurse = new JButton("Login");
		btn_login_nurse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String username = txt_username_nurse.getText();
				String password = txt_password_nurse.getText();

				try {
					NurseDB.initDB(); // initialize the nurse database file
					NurseDB nurseDB = new NurseDB();
					nurseDB = nurseDB.loadNurseDB(); // load database from file

					// if username and password don't match in the doctor database, then display error
					// else, create NurseDashboard frame and pass parameter String array containing username+pass
					if (!nurseDB.checkForMatch(username, password)) {
						lbl_invalid_password.setVisible(true);
					} else {
						String nurseID = nurseDB.getID(username, password);
						NurseDashboard login = new NurseDashboard(frame, nurseID);
						frame.setContentPane(login);
						frame.revalidate();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}); // end MouseListener for btn_login_nurse "Login"

		btn_login_nurse.setBounds(351, 224, 89, 23);
		add(btn_login_nurse);

	}

} // end class StaffLogin
