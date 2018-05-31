import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class MainMenu {

	public JFrame frame;
	private final Box horizontalBox = Box.createHorizontalBox();


	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainMenu() {
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
		
		JLabel lUlaznaFaktura = new JLabel("New label");
		lUlaznaFaktura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				UlaznaFaktura.main(null);
				frame.dispose();
			}
		});
	
		ImageIcon ulaznaFakturaSlika = new ImageIcon("slike/ulaznaFaktura.png");
		lUlaznaFaktura.setIcon(ulaznaFakturaSlika);
		lUlaznaFaktura.setBounds(43, 181, 200, 200);
		frame.getContentPane().add(lUlaznaFaktura);
		frame.setResizable(false);
		
		JLabel lIzlaznaFaktura = new JLabel("New label");
		lIzlaznaFaktura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				IzlaznaFaktura.main(null);
				frame.dispose();
			}
		});
		ImageIcon slikaIzlaznaFaktura = new ImageIcon("slike/izlaznaFaktura.png");
		lIzlaznaFaktura.setIcon(slikaIzlaznaFaktura);
		lIzlaznaFaktura.setBounds(242, 181, 200, 200);
		frame.getContentPane().add(lIzlaznaFaktura);
		
		JLabel lNapraviPredracun = new JLabel("New label");
		lNapraviPredracun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Predracun.main(null);
				frame.dispose();
			}
		});
		ImageIcon slikaPredracun = new ImageIcon("slike/napraviPredracun.png");
		lNapraviPredracun.setIcon(slikaPredracun);		
		lNapraviPredracun.setBounds(641, 181, 200, 200);
		frame.getContentPane().add(lNapraviPredracun);
		JLabel lLagerLista = new JLabel("New label");		
		lLagerLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				LagerLista.main(null);
				frame.dispose();
			}
		});
		ImageIcon slikaLager = new ImageIcon("slike/lagerLista.png");
		lLagerLista.setIcon(slikaLager);		
		lLagerLista.setBounds(442, 181, 200, 200);
		frame.getContentPane().add(lLagerLista);		
		
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
		

		frame.setLocationRelativeTo(null);  
		
	}
}
