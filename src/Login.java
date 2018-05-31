import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private final Box horizontalBox = Box.createHorizontalBox();
	private JTextField tfKorIme;
	private JPasswordField tfSifra;


	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0, 35, 101));
		frame.setTitle("Program za evidenciju faktura");
	
		ImageIcon ulaznaFakturaSlika = new ImageIcon("slike/ulaznaFaktura.png");
		frame.setResizable(false);
		ImageIcon slikaIzlaznaFaktura = new ImageIcon("slike/izlaznaFaktura.png");
		ImageIcon slikaPredracun = new ImageIcon("slike/napraviPredracun.png");
		ImageIcon slikaLager = new ImageIcon("slike/lagerLista.png");
		
		JLabel Naslov = new JLabel("Naziv firme: TOPLOMONT");
		Naslov.setBounds(10, 11, 268, 31);
		Naslov.setFont(new Font("Calibri", Font.BOLD, 20));
		Naslov.setForeground(Color.white);
		frame.getContentPane().add(Naslov);
		
		JLabel a = new JLabel("Vrijeme");
		a.setBounds(307, 19, 234, 14);
		frame.getContentPane().add(a);
		
		JLabel b = new JLabel("New label");
		b.setBounds(569, 19, 181, 14);
		frame.getContentPane().add(b);
		
		



		
		
		a.setForeground(Color.WHITE);
		a.setFont(new Font("Calibri", Font.BOLD, 18));
		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		       String date = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date() );

		       a.setText("Vrijeme: "+date);
		    }
		}, 1000, 1000);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
		Date date = new Date();
		
		b.setText("Datum: "+dateFormat.format(date));
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Calibri", Font.BOLD, 18));
		horizontalBox.setBackground(new Color(255, 0, 0));
		horizontalBox.setBounds(0, 0, 900, 46);
		frame.getContentPane().add(horizontalBox);
		
		tfKorIme = new JTextField();
		tfKorIme.setBounds(307, 167, 300, 31);
		frame.getContentPane().add(tfKorIme);
		tfKorIme.setColumns(10);
		
		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		lblKorisnikoIme.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblKorisnikoIme.setBackground(Color.WHITE);
		lblKorisnikoIme.setForeground(Color.WHITE);
		lblKorisnikoIme.setBounds(122, 172, 156, 14);
		frame.getContentPane().add(lblKorisnikoIme);
		
		JLabel lblifra = new JLabel("\u0160ifra:");
		lblifra.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblifra.setBackground(Color.WHITE);
		lblifra.setForeground(Color.WHITE);
		lblifra.setBounds(122, 240, 116, 14);
		frame.getContentPane().add(lblifra);
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfKorIme.getText().equals("") || tfSifra.getText().equals(""))
					JOptionPane.showMessageDialog(btnPrijava, "Molimo popunite polja!", "Greška!", 1);
				else
				if(tfKorIme.getText().equals("toplomont") && tfSifra.getText().equals("toplomontharis"))
					{
						MainMenu.main(null);
						frame.dispose();
					}
				else
				{
					JOptionPane.showMessageDialog(btnPrijava, "Korisnièko ime ili šifra nije taèno!", "Greška!", 0);
				}
				
			}
		});
		btnPrijava.setBounds(367, 323, 121, 31);
		frame.getContentPane().add(btnPrijava);
		
		tfSifra = new JPasswordField();
		tfSifra.setBounds(307, 235, 300, 31);
		frame.getContentPane().add(tfSifra);
		

		frame.setLocationRelativeTo(null);  
		
	}
}
