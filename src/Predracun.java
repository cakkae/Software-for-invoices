import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;

public class Predracun {

	public JFrame ulaznaFaktura;

	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Predracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Predracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Predracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Predracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Predracun window = new Predracun();
					window.ulaznaFaktura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Predracun() {
		initialize();
	}

	
	private void initialize() {
		ulaznaFaktura = new JFrame();
		ulaznaFaktura.setTitle("Program za evidenciju faktura");
		ulaznaFaktura.setLocationRelativeTo(null); 
		ulaznaFaktura.setBounds(100, 100, 900, 550);
		ulaznaFaktura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ulaznaFaktura.getContentPane().setLayout(null);
		ulaznaFaktura.setLocationRelativeTo(null);
		ulaznaFaktura.setResizable(false);
		
		ulaznaFaktura.getContentPane().setBackground(new Color(0, 35, 101));
		
		JLabel lDodajFakturu = new JLabel("New label");
		lDodajFakturu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				NoviPredracun.main(null);
				ulaznaFaktura.dispose();
			}
		});
		ImageIcon slikaDodajFakturu = new ImageIcon("slike/dodajFakturu.png");
		lDodajFakturu.setIcon(new ImageIcon("slike/napraviPredracun.png"));
		lDodajFakturu.setBounds(176, 130, 200, 200);
		ulaznaFaktura.getContentPane().add(lDodajFakturu);
		
		JLabel lPretrazi = new JLabel("New label");
		lPretrazi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				PretraziPredracun.main(null);
				ulaznaFaktura.dispose();
			}
		});
		ImageIcon slikaPretraziFakturu = new ImageIcon("slike/pretraziPredracun.png");
		lPretrazi.setIcon(slikaPretraziFakturu);
		lPretrazi.setBounds(510, 130, 200, 200);
		ulaznaFaktura.getContentPane().add(lPretrazi);
		
		JLabel lMeni = new JLabel("New label");
		lMeni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainMenu.main(null);
				ulaznaFaktura.dispose();
			}
		});
		ImageIcon slikaMeni = new ImageIcon("slike/meni.jpg");
		lMeni.setIcon(slikaMeni);
		lMeni.setBounds(356, 393, 200, 100);
		ulaznaFaktura.getContentPane().add(lMeni);
		ulaznaFaktura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel Naslov = new JLabel("Naziv firme: TOPLOMONT");
		Naslov.setBounds(10, 11, 268, 31);
		Naslov.setFont(new Font("Calibri", Font.BOLD, 20));
		Naslov.setForeground(Color.white);
		ulaznaFaktura.getContentPane().add(Naslov);
		
		JLabel a = new JLabel("Vrijeme");
		a.setBounds(307, 19, 234, 14);
		ulaznaFaktura.getContentPane().add(a);
		
		JLabel b = new JLabel("New label");
		b.setBounds(569, 19, 181, 14);
		ulaznaFaktura.getContentPane().add(b);
		
		ulaznaFaktura.getContentPane().setBackground(new Color(0, 35, 101));



		
		
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
	}
}
