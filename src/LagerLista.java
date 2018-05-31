import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.PatternSyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.JComponent;

public class LagerLista {

	public JFrame ulaznaFaktura;
	
	private JTable tabelaSiktarica;
	private JTextField tfSifra;


	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LagerLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LagerLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LagerLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LagerLista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LagerLista window = new LagerLista();
					window.ulaznaFaktura.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public LagerLista() {
		initialize();
	}

	static class ColorArrowUI extends BasicComboBoxUI {

	    public static ComboBoxUI createUI(JComponent c) {
	        return new ColorArrowUI();
	    }

	    @Override protected JButton createArrowButton() {
	        return new BasicArrowButton(
	            BasicArrowButton.SOUTH,
	            Color.blue, Color.blue,
	            Color.WHITE, Color.blue);
	    }
	}
	
	
	
	private void initialize() {
		
		ulaznaFaktura = new JFrame();
		ulaznaFaktura.setLocationRelativeTo(null); 
		ulaznaFaktura.setBounds(100, 100, 900, 550);
		ulaznaFaktura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ulaznaFaktura.getContentPane().setLayout(null);
		ulaznaFaktura.setLocationRelativeTo(null);
		ulaznaFaktura.setResizable(false);
		ulaznaFaktura.setTitle("Program za evidenciju faktura");
		ulaznaFaktura.getContentPane().setBackground(new Color(0, 35, 101));
		
		

        
		
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
		lMeni.setBounds(348, 434, 200, 100);
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
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		
		b.setText("Datum: "+dateFormat.format(date));
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Calibri", Font.BOLD, 18));
		
		
		
		
		
		JLabel lblUnesiteImeRadnika = new JLabel("Unesite naziv artikla:");
		lblUnesiteImeRadnika.setForeground(Color.WHITE);
		lblUnesiteImeRadnika.setBounds(27, 100, 140, 14);
		ulaznaFaktura.getContentPane().add(lblUnesiteImeRadnika);
		JTextField tfNazivArtikla = new JTextField();
		tfNazivArtikla.setBackground(Color.WHITE);
		tfNazivArtikla.setBounds(177, 97, 140, 20);
		ulaznaFaktura.getContentPane().add(tfNazivArtikla);
		JTextField tfSifra = new JTextField();
		tfSifra.setBackground(Color.WHITE);
		tfSifra.setBounds(177, 128, 140, 20);
		ulaznaFaktura.getContentPane().add(tfSifra);
		
		
		
		
		final String [] koloneImena = { "Sifra", "Naziv artikla", "Kupljeno", "Prodano", "Na stanju" };
		DBHome db = new DBHome();
		String[][] ulazniArtikli = new String [999][3];
		ulazniArtikli = db.ucitajSveUlazneArtikle();
		String[][] izlazniArtikli = new String[999][3];
		izlazniArtikli = db.ucitajSveIzlazneArtikle();
		String podatci[][] = new String[999][5];
		
		for (int brojac = 0; ulazniArtikli[brojac][0] != null; brojac++)
		{
			int brojacPamtim = 0;
			boolean postoji = false;
			for (int brojac2 = 0; podatci[brojac2][0] != null; brojac2++)
			{
				if(ulazniArtikli[brojac][0].equals(podatci[brojac2][0]))
				{
					podatci[brojac2][2] = String.valueOf(Integer.valueOf(podatci[brojac2][2]) + Integer.valueOf(ulazniArtikli[brojac][2]));
					postoji = true;
				}
				else
				{
					for (brojacPamtim = 0; podatci[brojacPamtim][0] != null; brojacPamtim++)
					{
						//broji gdje je vrijednost null da znam gdje je kraj da dodam
					}
				}
			}
			if(!postoji)
			{
				podatci[brojacPamtim][0] = ulazniArtikli[brojac][0];
				podatci[brojacPamtim][1] = ulazniArtikli[brojac][1];
				podatci[brojacPamtim][2] = ulazniArtikli[brojac][2];
				podatci[brojacPamtim][3] = "0";
				podatci[brojacPamtim][4] = "0";
			}
		}
		
		for (int brojac = 0; izlazniArtikli[brojac][0] != null; brojac++)
		{
			int brojacPamtim = 0;
			boolean postoji = false;
			for (int brojac2 = 0; podatci[brojac2][0] != null; brojac2++)
			{
				if(izlazniArtikli[brojac][0].equals(podatci[brojac2][0]))
				{
					podatci[brojac2][3] = String.valueOf(Integer.valueOf(podatci[brojac2][3]) + Integer.valueOf(izlazniArtikli[brojac][2]));
					postoji = true;
				}
				else
				{
					for (brojacPamtim = 0; podatci[brojacPamtim][0] != null; brojacPamtim++)
					{
						//broji gdje je vrijednost null da znam gdje je kraj da dodam
					}
				}
			}
			if(!postoji)
			{
				podatci[brojacPamtim][0] = izlazniArtikli[brojac][0];
				podatci[brojacPamtim][1] = izlazniArtikli[brojac][1];
				podatci[brojacPamtim][2] = "0";
				podatci[brojacPamtim][3] = izlazniArtikli[brojac][2];
				podatci[brojacPamtim][4] = "0";
			}
		}
		
		for (int brojac = 0; podatci[brojac][0] != null; brojac++)
		{
			String naStanju;
			naStanju = String.valueOf(Integer.valueOf(podatci[brojac][2]) - Integer.valueOf(podatci[brojac][3]));
			podatci[brojac][4] = naStanju;
		}
		
		
		
		tabelaSiktarica = new JTable(podatci, koloneImena);
		tabelaSiktarica.setBounds(2, 27, 853, 15984);
		ulaznaFaktura.getContentPane().add(tabelaSiktarica);
		JScrollPane js = new JScrollPane(tabelaSiktarica);
		js.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		ulaznaFaktura.getContentPane().add(js);
		js.setBounds(10, 155, 874, 246);
		
		// postavljanje sirine tabele
		tabelaSiktarica.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabelaSiktarica.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabelaSiktarica.getColumnModel().getColumn(1).setPreferredWidth(110);
		/*tabelaSiktarica.getColumnModel().getColumn(2).setPreferredWidth(110);
		tabelaSiktarica.getColumnModel().getColumn(4).setPreferredWidth(40);
		tabelaSiktarica.getColumnModel().getColumn(6).setPreferredWidth(100);
		tabelaSiktarica.getColumnModel().getColumn(7).setPreferredWidth(105);
		tabelaSiktarica.getColumnModel().getColumn(8).setPreferredWidth(125);*/
		
	/*	// nemoguce povecavanje sirine
		tabelaSiktarica.getColumnModel().getColumn(0).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(1).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(2).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(3).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(4).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(5).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(6).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(7).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(8).setResizable(false);
		tabelaSiktarica.getColumnModel().getColumn(9).setResizable(false);*/
		
		// nemoguce pomjeranje
		tabelaSiktarica.getTableHeader().setReorderingAllowed(false);
		tabelaSiktarica.getTableHeader().setForeground(Color.WHITE);

		
		
		UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
		
		TableModel myTableModel = tabelaSiktarica.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(myTableModel);
        tabelaSiktarica.setRowSorter(sorter);
        
        
        
        JLabel lblUnesiteifruArtikla = new JLabel("Unesite \u0161ifru artikla:");
        lblUnesiteifruArtikla.setForeground(Color.WHITE);
        lblUnesiteifruArtikla.setBounds(27, 130, 140, 14);
        ulaznaFaktura.getContentPane().add(lblUnesiteifruArtikla);
        tfNazivArtikla.getDocument().addDocumentListener(new DocumentListener() {

            private void searchFieldChangedUpdate(DocumentEvent evt) {
                String text = tfNazivArtikla.getText();
                
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                    tabelaSiktarica.clearSelection();
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                        tabelaSiktarica.clearSelection();
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern",
                                "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
            }
            
            
            

            @Override
            public void insertUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }
        });
        
        JLabel lblUnesiteSifru = new JLabel("Unesite \u0161ifru artikla:");
        lblUnesiteifruArtikla.setForeground(Color.WHITE);
        lblUnesiteifruArtikla.setBounds(27, 130, 140, 14);
        ulaznaFaktura.getContentPane().add(lblUnesiteifruArtikla);
        tfSifra.getDocument().addDocumentListener(new DocumentListener() {

            private void searchFieldChangedUpdate(DocumentEvent evt) {
                String text = tfSifra.getText();
                
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                    tabelaSiktarica.clearSelection();
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                        tabelaSiktarica.clearSelection();
                    } catch (PatternSyntaxException pse) {
                        JOptionPane.showMessageDialog(null, "Bad regex pattern",
                                "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                
            }
            
            
            

            @Override
            public void insertUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                searchFieldChangedUpdate(evt);
            }
        });
        
     /*   tabelaSiktarica.addMouseListener(new MouseAdapter()
        {
        	@Override
        	public void mouseReleased(MouseEvent e)
        	{
        		//uradi nesto sa klikom
        		int row = tabelaSiktarica.rowAtPoint(e.getPoint());
        		if (row >= 0 && row < tabelaSiktarica.getRowCount())
        		{
        			tabelaSiktarica.setRowSelectionInterval(row, row);
        		}
        		else
        		{
        			tabelaSiktarica.clearSelection();
        		}
        		
        		int rowIndex = tabelaSiktarica.getSelectedRow();
        		if (rowIndex < 0) return;
        		if (e.isPopupTrigger() && e.getComponent() instanceof JTable)
        		{
        			JPopupMenu popup = new JPopupMenu();
        			JButton item = new JButton("Pogledaj artikle");
        			popup.add(item);
        			popup.show(e.getComponent(), e.getX(), e.getY());
        			
        			JTable izvor = (JTable) e.getSource();
        			int rov = izvor.rowAtPoint(e.getPoint());
        			String selektovanRov = (String) izvor.getModel().getValueAt(rov, 0);
        			
        			item.addActionListener(new ActionListener()
        			{
        				public void actionPerformed(ActionEvent e)
        				{
        					popup.setVisible(false);
        					
        					Ispis ispis = new Ispis();
        					ispis.ispisiUlazneArtikla(selektovanRov);
        				}
        			}); 
        		}
        	}
        });  */
	}
}