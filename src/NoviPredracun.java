import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NoviPredracun {

	public JFrame ulaznaFaktura;
	private JTextField tfBrojFakture;
	private JTextField tfNazivFirme;
	private JTextField tfDatum;
	private JTextField tfDatumIsporuke;
	private JTextField tfDatumValute;
	private JTextField tfFiskalniRacun;
	private JTextField tfMjestoIzdavanja;
	private JTextField tfMjestoIsporuke;
	private JTextField tfNacinNaplate;
	private JTextField tfJIB;
	private JTextField tfPDV;
	private JTextField tfNazivArtikla;
	private JTextField tfJM;
	private JTextField tfCijenaSaPDV;
	private JTextField tfKolicina;
	private JTextField tfBrojFaktureArtikal;
	private JTextField tfRedniBroj;
	private JTextField tfSifra;
	private JTextField tfVrijednostSaPDV;
	private JTextField tfPopust;
	private JTextField tfPDVOsnovica;
	private JTextField tfPostoPDV;
	private JTextField tfPDVIznos;
	private JTextField tfIznosZaNaplatu;
	private JTextField tfAdresa;
	boolean rezultatPostojiBrojFakture;
	boolean rezultatBrojArtikla;
	
	public static void main(String[] args) {
		try {
            //here you can put the selected theme class name in JTattoo
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NoviPredracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NoviPredracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NoviPredracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NoviPredracun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoviPredracun window = new NoviPredracun();
					window.ulaznaFaktura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public NoviPredracun() throws ParseException {
		initialize();
	}

	
	private void initialize() throws ParseException {
		ulaznaFaktura = new JFrame();
		ulaznaFaktura.setTitle("Program za evidenciju faktura");
		ulaznaFaktura.setLocationRelativeTo(null); 
		ulaznaFaktura.setBounds(100, 100, 1084, 693);
		ulaznaFaktura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ulaznaFaktura.getContentPane().setLayout(null);
		ulaznaFaktura.setLocationRelativeTo(null);
		ulaznaFaktura.setResizable(false);
		
		
		ulaznaFaktura.getContentPane().setBackground(new Color(0, 35, 101));
		
		JLabel lMeni = new JLabel("New label");
		lMeni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Predracun.main(null);
				ulaznaFaktura.dispose();
			}
		});
		ImageIcon slikaMeni = new ImageIcon("slike/meni.jpg");
		lMeni.setIcon(slikaMeni);
		lMeni.setBounds(805, 90, 200, 100);
		ulaznaFaktura.getContentPane().add(lMeni);
		
		Border borderCrveni = BorderFactory.createLineBorder(Color.RED, 1);
		
		JLabel lPostojiRedniBroj = new JLabel("Redni broj une\u0161enog artikla ve\u0107 postoji u bazi. Ukucajte novi!");
		lPostojiRedniBroj.setFont(new Font("Calibri", Font.PLAIN, 16));
		lPostojiRedniBroj.setForeground(Color.WHITE);
		lPostojiRedniBroj.setHorizontalAlignment(SwingConstants.CENTER);
		lPostojiRedniBroj.setBounds(641, 201, 427, 88);
		lPostojiRedniBroj.setBorder(borderCrveni);
		lPostojiRedniBroj.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Broj fakture:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(24, 58, 75, 14);
		ulaznaFaktura.getContentPane().add(lblNewLabel);
		
		tfBrojFakture = new JTextField();
		
		tfBrojFakture.setBounds(131, 55, 164, 20);
		ulaznaFaktura.getContentPane().add(tfBrojFakture);
		tfBrojFakture.setColumns(10);
		
		JLabel lblNazivFirme = new JLabel("Naziv firme:");
		lblNazivFirme.setForeground(Color.WHITE);
		lblNazivFirme.setBounds(24, 83, 75, 14);
		ulaznaFaktura.getContentPane().add(lblNazivFirme);
		
		tfNazivFirme = new JTextField();
		tfNazivFirme.setColumns(10);
		tfNazivFirme.setBounds(131, 80, 164, 20);
		ulaznaFaktura.getContentPane().add(tfNazivFirme);
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setForeground(Color.WHITE);
		lblDatum.setBounds(24, 108, 75, 14);
		ulaznaFaktura.getContentPane().add(lblDatum);
		
		tfDatum = new JTextField();
		tfDatum.setColumns(10);
		tfDatum.setBounds(131, 105, 164, 20);
		ulaznaFaktura.getContentPane().add(tfDatum);
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-20");
		String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		tfDatum.setText(formattedDate);
		
		JLabel label = new JLabel("Datum isporuke:");
		label.setForeground(Color.WHITE);
		label.setBounds(24, 133, 105, 14);
		ulaznaFaktura.getContentPane().add(label);
		
		tfDatumIsporuke = new JTextField();
		tfDatumIsporuke.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) 
			{
				if (!tfDatumIsporuke.getText().isEmpty() && tfDatumIsporuke.getText().length() == 10) //da bezveze ne baca errore ima i provjera
				{  //za duzinu, ako nema 10 karaktera sibat ce errore
					// a ne da mi se pravit majmunije sa indexOf i brojanja /// jer pitaj boga sta ce unosit moze unjet i 1.1.2017
					String datum = tfDatumIsporuke.getText();
					String dan = datum.substring(0, 2);
					String mjesec = datum.substring(3, 5);
					String godina = datum.substring(6);
					
					int provjera = Integer.valueOf(mjesec) + 1; //za provjeru jer bi mjesec mogo bit veci od 12
					mjesec = String.valueOf(provjera);
					
					if (provjera > 12)
					{
						mjesec = "01";
						godina = String.valueOf(Integer.valueOf(godina) + 1);
					}
					else if (mjesec.length() < 2)
					{
						mjesec = String.valueOf("0"+Integer.valueOf(mjesec));
					}
					else mjesec = String.valueOf(Integer.valueOf(mjesec));
					
					tfDatumValute.setText(dan+"/"+mjesec+"/"+godina);
				}
			}
		});
		tfDatumIsporuke.setColumns(10);
		tfDatumIsporuke.setBounds(131, 130, 164, 20);
		ulaznaFaktura.getContentPane().add(tfDatumIsporuke);
		tfDatumIsporuke.setText(formattedDate);
		
		JLabel label_1 = new JLabel("Datum valute:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(24, 158, 85, 14);
		ulaznaFaktura.getContentPane().add(label_1);
		
		tfDatumValute = new JTextField();
		tfDatumValute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if (!tfDatumIsporuke.getText().isEmpty() && tfDatumIsporuke.getText().length() == 10) //da bezveze ne baca errore ima i provjera
				{ //za duzinu, ako nema 10 karaktera sibat ce errore to jest ako bude npr 1/1/2015 jer program uzima substring
					// a ne da mi se pravit majmunije sa indexOf i brojanja /// jer pitaj boga sta ce unosit moze unjet i 1.1.2017
					String datum = tfDatumIsporuke.getText();
					String dan = datum.substring(0, 2);
					String mjesec = datum.substring(3, 5);
					String godina = datum.substring(6);
					
					int provjera = Integer.valueOf(mjesec) + 1; //za provjeru jer bi mjesec mogo bit veci od 12
					mjesec = String.valueOf(provjera);
					
					if (provjera >= 12)
					{
						mjesec = "01";
						godina = String.valueOf(Integer.valueOf(godina) + 1);
					}
					else if (mjesec.length() < 2)
					{
						mjesec = String.valueOf("0"+mjesec);
					}
					
					tfDatumValute.setText(dan+"/"+mjesec+"/"+godina);
				}
			}
		});
		tfDatumValute.setColumns(10);
		tfDatumValute.setBounds(131, 155, 164, 20);
		ulaznaFaktura.getContentPane().add(tfDatumValute);
		tfDatumValute.setText(formattedDate);
		
		JLabel label_2 = new JLabel("Fiskalni ra\u010Dun:");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(24, 183, 85, 14);
		ulaznaFaktura.getContentPane().add(label_2);
		
		tfFiskalniRacun = new JTextField();
		tfFiskalniRacun.setColumns(10);
		tfFiskalniRacun.setBounds(131, 180, 164, 20);
		ulaznaFaktura.getContentPane().add(tfFiskalniRacun);
		
		JLabel label_3 = new JLabel("Mjesto izdavanja:");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(24, 208, 105, 14);
		ulaznaFaktura.getContentPane().add(label_3);
		
		tfMjestoIzdavanja = new JTextField();
		tfMjestoIzdavanja.setColumns(10);
		tfMjestoIzdavanja.setBounds(131, 205, 164, 20);
		ulaznaFaktura.getContentPane().add(tfMjestoIzdavanja);
		
		JLabel label_4 = new JLabel("Mjesto isporuke:");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(24, 233, 105, 14);
		ulaznaFaktura.getContentPane().add(label_4);
		
		tfMjestoIsporuke = new JTextField();
		tfMjestoIsporuke.setColumns(10);
		tfMjestoIsporuke.setBounds(131, 230, 164, 20);
		ulaznaFaktura.getContentPane().add(tfMjestoIsporuke);
		
		JLabel label_5 = new JLabel("Na\u010Din naplate:");
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(24, 258, 85, 14);
		ulaznaFaktura.getContentPane().add(label_5);
		
		tfNacinNaplate = new JTextField();
		tfNacinNaplate.setColumns(10);
		tfNacinNaplate.setBounds(131, 255, 164, 20);
		ulaznaFaktura.getContentPane().add(tfNacinNaplate);
		
		JLabel label_6 = new JLabel("JIB:");
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(24, 283, 69, 14);
		ulaznaFaktura.getContentPane().add(label_6);
		
		tfJIB = new JTextField();
		tfJIB.setColumns(10);
		tfJIB.setBounds(131, 280, 164, 20);
		ulaznaFaktura.getContentPane().add(tfJIB);
		
		JLabel label_7 = new JLabel("PDV:");
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(24, 308, 46, 14);
		ulaznaFaktura.getContentPane().add(label_7);
		
		tfPDV = new JTextField();
		tfPDV.setColumns(10);
		tfPDV.setBounds(131, 305, 164, 20);
		ulaznaFaktura.getContentPane().add(tfPDV);
		
		JLabel lblDodavanjeFakture = new JLabel("Dodavanje predra\u010Duna");
		lblDodavanjeFakture.setForeground(Color.WHITE);
		lblDodavanjeFakture.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDodavanjeFakture.setBounds(24, 11, 259, 36);
		ulaznaFaktura.getContentPane().add(lblDodavanjeFakture);
		
		JLabel lSpremiFakturu = new JLabel("Spremi fakturu");
		lSpremiFakturu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (tfBrojFakture.getText().isEmpty() || tfNazivFirme.getText().isEmpty() || tfDatum.getText().isEmpty() || tfDatumIsporuke.getText().isEmpty()
						|| tfDatumValute.getText().isEmpty() || tfFiskalniRacun.getText().isEmpty() || tfMjestoIzdavanja.getText().isEmpty()
						|| tfMjestoIsporuke.getText().isEmpty() || tfNacinNaplate.getText().isEmpty() || tfJIB.getText().isEmpty() ||
						tfPDV.getText().isEmpty() || tfAdresa.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Morate popuniti sva polja prije nego što spremite fakturu!");
				}
				else
				{
					int odabrano = JOptionPane.showConfirmDialog(null, "Jeste li sigurni da želite spremiti fakturu?");
					if (odabrano == JOptionPane.YES_OPTION && !rezultatPostojiBrojFakture)
					{
						DBHome db = new DBHome();
						db.dodajPredracun(tfBrojFakture.getText(), tfNazivFirme.getText(), tfDatum.getText(), tfDatumIsporuke.getText(), tfDatumValute.getText(),
								tfFiskalniRacun.getText(), tfMjestoIzdavanja.getText(), tfMjestoIsporuke.getText(), tfNacinNaplate.getText(),
								tfJIB.getText(), tfPDV.getText(), tfAdresa.getText());
						
						String unos = tfBrojFakture.getText();
						tfBrojFaktureArtikal.setText(unos);
						
						tfNazivFirme.setText(""); tfMjestoIzdavanja.setText(""); tfFiskalniRacun.setText(""); 
						tfMjestoIzdavanja.setText(""); tfMjestoIsporuke.setText(""); tfNacinNaplate.setText(""); tfJIB.setText(""); tfPDV.setText("");
						tfAdresa.setText("");
						
						tfBrojFaktureArtikal.setText(tfBrojFakture.getText());
						
					}
					else JOptionPane.showMessageDialog(null, "Broj fakture postoji u bazi podataka!");
				}
			}
		});
		lSpremiFakturu.setBackground(new Color(0, 0, 255));
		lSpremiFakturu.setForeground(new Color(255, 255, 255));
		lSpremiFakturu.setHorizontalAlignment(SwingConstants.CENTER);
		lSpremiFakturu.setFont(new Font("Calibri", Font.BOLD, 25));
		lSpremiFakturu.setBounds(24, 385, 271, 36);
		ulaznaFaktura.getContentPane().add(lSpremiFakturu);
		Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
		lSpremiFakturu.setBorder(border);
		
		JLabel lblDodavanjeArtikla = new JLabel("Dodavanje artikla");
		lblDodavanjeArtikla.setForeground(Color.WHITE);
		lblDodavanjeArtikla.setFont(new Font("Calibri", Font.BOLD, 25));
		lblDodavanjeArtikla.setBounds(356, 11, 259, 36);
		ulaznaFaktura.getContentPane().add(lblDodavanjeArtikla);
		
		JLabel lblNazivArtikla = new JLabel("Naziv artikla:");
		lblNazivArtikla.setForeground(Color.WHITE);
		lblNazivArtikla.setBounds(356, 108, 75, 14);
		ulaznaFaktura.getContentPane().add(lblNazivArtikla);
		
		JLabel lblJM = new JLabel("JM:");
		lblJM.setHorizontalAlignment(SwingConstants.LEFT);
		lblJM.setForeground(Color.WHITE);
		lblJM.setBounds(356, 133, 67, 14);
		ulaznaFaktura.getContentPane().add(lblJM);
		
		JLabel label_11 = new JLabel("Cijena sa PDV-om:");
		label_11.setForeground(Color.WHITE);
		label_11.setBounds(356, 208, 107, 14);
		ulaznaFaktura.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("Koli\u010Dina:");
		label_12.setForeground(Color.WHITE);
		label_12.setBounds(356, 158, 67, 14);
		ulaznaFaktura.getContentPane().add(label_12);
		
		tfNazivArtikla = new JTextField();
		tfNazivArtikla.setColumns(10);
		tfNazivArtikla.setBounds(467, 105, 164, 20);
		ulaznaFaktura.getContentPane().add(tfNazivArtikla);
		
		tfJM = new JTextField();
		tfJM.setColumns(10);
		tfJM.setBounds(467, 130, 164, 20);
		ulaznaFaktura.getContentPane().add(tfJM);
		
		tfCijenaSaPDV = new JTextField();
		/*tfCijenaSaPDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (!tfCijenaBezPDV.getText().isEmpty() && !tfPDVArtikal.getText().isEmpty())
				{
					double ukupnaCijena = Double.valueOf(tfCijenaBezPDV.getText()) + Double.valueOf(tfPDVArtikal.getText());
					tfCijenaSaPDV.setText(String.valueOf(ukupnaCijena));
				}
			}
		});
		tfCijenaSaPDV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (!tfCijenaBezPDV.getText().isEmpty() && !tfPDVArtikal.getText().isEmpty())
				{
					double ukupnaCijena = Double.valueOf(tfCijenaBezPDV.getText()) + Double.valueOf(tfPDVArtikal.getText());
					tfCijenaSaPDV.setText(String.valueOf(ukupnaCijena));
				}
			}
		});*/
		tfCijenaSaPDV.setColumns(10);
		tfCijenaSaPDV.setBounds(467, 205, 164, 20);
		ulaznaFaktura.getContentPane().add(tfCijenaSaPDV);
		
		tfKolicina = new JTextField();
		tfKolicina.setColumns(10);
		tfKolicina.setBounds(467, 155, 164, 20);
		ulaznaFaktura.getContentPane().add(tfKolicina);
		
		JLabel lSpremiArtikal = new JLabel("Spremi artikal");
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");     
        Object[] test = {"R.br", "Šifra", "Naziv", "JM", "Kolicina", "Cijena sa PDV", "Vrijednost sa PDV", "% popusta", "PDV osnovica", "% PDV", "PDV iznos", "Iznos za uplatiti"};
        model.addRow(test);
		JTable tabela = new JTable(model);
		tabela.setBounds(24, 447, 1034, 197);
		ulaznaFaktura.getContentPane().add(tabela);
		tabela.getTableHeader().getColumnModel().getColumn(0).setResizable(false);
		UIManager.getDefaults().put("TableHeader.cellBoder", BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tabela.setEnabled(false);
		JScrollPane jp = new JScrollPane(tabela);
		jp.setEnabled(false);
		jp.setBounds(24, 447, 1034, 197);
		ulaznaFaktura.getContentPane().add(jp);
		
		
		lSpremiArtikal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{	
				if (tfRedniBroj.getText().isEmpty() || tfSifra.getText().isEmpty() || tfNazivArtikla.getText().isEmpty() || tfJM.getText().isEmpty()
						|| tfKolicina.getText().isEmpty() || tfBrojFaktureArtikal.getText().isEmpty() || tfCijenaSaPDV.getText().isEmpty()
						|| tfVrijednostSaPDV.getText().isEmpty() || tfPopust.getText().isEmpty() || tfPDVOsnovica.getText().isEmpty()
						|| tfPostoPDV.getText().isEmpty() || tfPDVIznos.getText().isEmpty() || tfIznosZaNaplatu.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Morate popuniti sva polja prije nego što spremite fakturu!");
				}
				else
				{
					
					boolean postoji = provjeriRedniBroj(tfRedniBroj.getText(), tfBrojFakture.getText());
					
					if (postoji)
					{
						int odabir = JOptionPane.showConfirmDialog(null, "Redni broj artikla veæ postoji za unijeti broj fakture. Želite nastaviti?");
						if (odabir == JOptionPane.YES_OPTION)
						{
						
							DBHome db = new DBHome();
							db.dodajPredracunArtikal(tfRedniBroj.getText(), tfSifra.getText(), tfNazivArtikla.getText(), tfJM.getText(), tfKolicina.getText(),
									tfBrojFaktureArtikal.getText(), tfCijenaSaPDV.getText(), tfVrijednostSaPDV.getText(), tfPopust.getText(),
									tfPDVOsnovica.getText(), tfPostoPDV.getText(), tfPDVIznos.getText(), tfIznosZaNaplatu.getText());
	
							int redniBroj = Integer.valueOf(tfRedniBroj.getText());
							
							Object[] zaUnos = {tfRedniBroj.getText(), tfSifra.getText(), tfNazivArtikla.getText(), tfJM.getText(), tfKolicina.getText(),
									tfCijenaSaPDV.getText(), tfVrijednostSaPDV.getText(), tfPopust.getText(), tfPDVOsnovica.getText(), tfPostoPDV.getText(),
									tfPDVIznos.getText(), tfIznosZaNaplatu.getText()};
							model.addRow(zaUnos);
							tfRedniBroj.setText(""); tfSifra.setText(""); tfNazivArtikla.setText(""); tfJM.setText(""); tfKolicina.setText("");
							tfCijenaSaPDV.setText(""); tfVrijednostSaPDV.setText(""); tfPopust.setText(""); tfPDVOsnovica.setText("");
							tfPDVIznos.setText(""); tfIznosZaNaplatu.setText("");
							
							redniBroj = redniBroj + 1;
							tfRedniBroj.setText(String.valueOf(redniBroj));
						}
					}
					else if (!postoji)
					{
						int odabir = JOptionPane.showConfirmDialog(null, "Jeste li sigurni da želite dodati artikal?");
						if(odabir == JOptionPane.YES_OPTION)
						{
							DBHome db = new DBHome();
							db.dodajPredracunArtikal(tfRedniBroj.getText(), tfSifra.getText(), tfNazivArtikla.getText(), tfJM.getText(), tfKolicina.getText(),
									tfBrojFaktureArtikal.getText(), tfCijenaSaPDV.getText(), tfVrijednostSaPDV.getText(), tfPopust.getText(),
									tfPDVOsnovica.getText(), tfPostoPDV.getText(), tfPDVIznos.getText(), tfIznosZaNaplatu.getText());
	
							int redniBroj = Integer.valueOf(tfRedniBroj.getText());
							
							Object[] zaUnos = {tfRedniBroj.getText(), tfSifra.getText(), tfNazivArtikla.getText(), tfJM.getText(), tfKolicina.getText(),
									tfCijenaSaPDV.getText(), tfVrijednostSaPDV.getText(), tfPopust.getText(), tfPDVOsnovica.getText(), tfPostoPDV.getText(),
									tfPDVIznos.getText(), tfIznosZaNaplatu.getText()};
							model.addRow(zaUnos);
							tfRedniBroj.setText(""); tfSifra.setText(""); tfNazivArtikla.setText(""); tfJM.setText(""); tfKolicina.setText("");
							tfCijenaSaPDV.setText(""); tfVrijednostSaPDV.setText(""); tfPopust.setText(""); tfPDVOsnovica.setText("");
							tfPDVIznos.setText(""); tfIznosZaNaplatu.setText("");
							
							redniBroj = redniBroj + 1;
							tfRedniBroj.setText(String.valueOf(redniBroj));
						}
					}
				}
			}
		});
		lSpremiArtikal.setHorizontalAlignment(SwingConstants.CENTER);
		lSpremiArtikal.setForeground(Color.WHITE);
		lSpremiArtikal.setFont(new Font("Calibri", Font.BOLD, 25));
		lSpremiArtikal.setBackground(Color.BLUE);
		lSpremiArtikal.setBounds(356, 385, 275, 36);
		ulaznaFaktura.getContentPane().add(lSpremiArtikal);
		lSpremiArtikal.setBorder(border);
		
		JLabel lblBrojFakture = new JLabel("Broj fakture:");
		lblBrojFakture.setForeground(Color.WHITE);
		lblBrojFakture.setBounds(356, 183, 92, 14);
		ulaznaFaktura.getContentPane().add(lblBrojFakture);
		
		
		tfBrojFaktureArtikal = new JTextField();
		tfBrojFaktureArtikal.setColumns(10);
		tfBrojFaktureArtikal.setBounds(467, 180, 164, 20);
		ulaznaFaktura.getContentPane().add(tfBrojFaktureArtikal);
		
		tfRedniBroj = new JTextField();
		tfRedniBroj.setColumns(10);
		tfRedniBroj.setBounds(467, 55, 164, 20);
		ulaznaFaktura.getContentPane().add(tfRedniBroj);
		tfRedniBroj.setText("1");
		
		JLabel lblRedniBroj = new JLabel("Redni broj:");
		lblRedniBroj.setHorizontalAlignment(SwingConstants.LEFT);
		lblRedniBroj.setForeground(Color.WHITE);
		lblRedniBroj.setBounds(356, 58, 75, 14);
		ulaznaFaktura.getContentPane().add(lblRedniBroj);
		
		JLabel lblSifra = new JLabel("\u0160ifra:");
		lblSifra.setHorizontalAlignment(SwingConstants.LEFT);
		lblSifra.setForeground(Color.WHITE);
		lblSifra.setBounds(356, 83, 75, 14);
		ulaznaFaktura.getContentPane().add(lblSifra);
		
		tfSifra = new JTextField();
		tfSifra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) 
			{
				if (!tfSifra.getText().isEmpty())
				{
					Timer timer = new Timer();
					timer.schedule(new TimerTask()
					{
						@Override
						public void run()
						{
							DBHome db = new DBHome(); 
							String rezultat = db.pronadiUlazniArtikalSaSifrom(tfSifra.getText());
							if (!rezultat.isEmpty())
							{
								tfNazivArtikla.setText(rezultat);
							}
						}
					}, 2000);
				}
			}
		});
		tfSifra.setColumns(10);
		tfSifra.setBounds(467, 80, 164, 20);
		ulaznaFaktura.getContentPane().add(tfSifra);
		
		JLabel lblVrijednostSaPDV = new JLabel("Vrijednost sa PDV:");
		lblVrijednostSaPDV.setForeground(Color.WHITE);
		lblVrijednostSaPDV.setBounds(356, 233, 107, 14);
		ulaznaFaktura.getContentPane().add(lblVrijednostSaPDV);
		
		tfVrijednostSaPDV = new JTextField();
		tfVrijednostSaPDV.setEnabled(false);
		tfVrijednostSaPDV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (!tfCijenaSaPDV.getText().isEmpty() && !tfKolicina.getText().isEmpty())
				{
					double vrijednost = Double.valueOf(tfCijenaSaPDV.getText()) * Double.valueOf(tfKolicina.getText());
					tfVrijednostSaPDV.setText(String.valueOf(vrijednost));
				}
			}
		});
		tfVrijednostSaPDV.setColumns(10);
		tfVrijednostSaPDV.setBounds(467, 230, 164, 20);
		ulaznaFaktura.getContentPane().add(tfVrijednostSaPDV);
		/*int vrijednostSaPDV, CijenaSaPDV, Kolicina;
		CijenaSaPDV = Integer.parseInt(tfCijenaSaPDV.getText());
		Kolicina = Integer.parseInt(tfKolicina.getText());
		vrijednostSaPDV = CijenaSaPDV * Kolicina;
		tfVrijednostSaPDV.setText(Integer.toString(vrijednostSaPDV));*/
		
		
		JLabel lblPopust = new JLabel("% popusta:");
		lblPopust.setHorizontalAlignment(SwingConstants.LEFT);
		lblPopust.setForeground(Color.WHITE);
		lblPopust.setBounds(356, 258, 107, 14);
		ulaznaFaktura.getContentPane().add(lblPopust);
		
		tfPopust = new JTextField();
		tfPopust.setColumns(10);
		tfPopust.setBounds(467, 255, 164, 20);
		ulaznaFaktura.getContentPane().add(tfPopust);
		
		JLabel lblPDVOsnovica = new JLabel("PDV osnovica:");
		lblPDVOsnovica.setHorizontalAlignment(SwingConstants.LEFT);
		lblPDVOsnovica.setForeground(Color.WHITE);
		lblPDVOsnovica.setBounds(356, 283, 107, 14);
		ulaznaFaktura.getContentPane().add(lblPDVOsnovica);
		
		tfPDVOsnovica = new JTextField();
		tfPDVOsnovica.setEnabled(false);
		tfPDVOsnovica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (!tfVrijednostSaPDV.getText().isEmpty() && !tfPopust.getText().isEmpty())
				{
					double pdvOsnovica = (Double.valueOf(tfVrijednostSaPDV.getText()) - (Double.valueOf(tfPopust.getText()) / 100)*Double.valueOf(tfVrijednostSaPDV.getText())) / 1.17;
					double zaokruzi = Math.round(pdvOsnovica * 100.0) / 100.0;
					tfPDVOsnovica.setText(String.valueOf(zaokruzi));
				}
			}
		});
		tfPDVOsnovica.setColumns(10);
		tfPDVOsnovica.setBounds(467, 280, 164, 20);
		ulaznaFaktura.getContentPane().add(tfPDVOsnovica);
		
		JLabel lblPostoPDV = new JLabel("% PDV:");
		lblPostoPDV.setHorizontalAlignment(SwingConstants.LEFT);
		lblPostoPDV.setForeground(Color.WHITE);
		lblPostoPDV.setBounds(356, 308, 107, 14);
		ulaznaFaktura.getContentPane().add(lblPostoPDV);
		
		tfPostoPDV = new JTextField();
		tfPostoPDV.setColumns(10);
		tfPostoPDV.setBounds(467, 305, 164, 20);
		ulaznaFaktura.getContentPane().add(tfPostoPDV);
		tfPostoPDV.setText("17");
		
		JLabel lblPDVIznos = new JLabel("PDV iznos:");
		lblPDVIznos.setHorizontalAlignment(SwingConstants.LEFT);
		lblPDVIznos.setForeground(Color.WHITE);
		lblPDVIznos.setBounds(356, 334, 107, 14);
		ulaznaFaktura.getContentPane().add(lblPDVIznos);
		
		tfPDVIznos = new JTextField();
		tfPDVIznos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (!tfPDVOsnovica.getText().isEmpty() && !tfPostoPDV.getText().isEmpty())
				{
					double rezultat = (Double.valueOf(tfPDVOsnovica.getText()) * Double.valueOf(tfPostoPDV.getText())) / 100;
					rezultat = Math.round(rezultat * 100.0) / 100.0;
					tfPDVIznos.setText(String.valueOf(rezultat));
				}
			}
		});
		tfPDVIznos.setColumns(10);
		tfPDVIznos.setBounds(467, 331, 164, 20);
		ulaznaFaktura.getContentPane().add(tfPDVIznos);
		
		JLabel lblIznosZaNaplatu = new JLabel("Iznos za naplatu:");
		lblIznosZaNaplatu.setHorizontalAlignment(SwingConstants.LEFT);
		lblIznosZaNaplatu.setForeground(Color.WHITE);
		lblIznosZaNaplatu.setBounds(356, 360, 107, 14);
		ulaznaFaktura.getContentPane().add(lblIznosZaNaplatu);
		
		tfIznosZaNaplatu = new JTextField();
		tfIznosZaNaplatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (!tfPDVIznos.getText().isEmpty() && !tfPDVOsnovica.getText().isEmpty())
				{
					double iznosZaNaplatu = Double.valueOf(tfPDVIznos.getText()) + Double.valueOf(tfPDVOsnovica.getText());
					iznosZaNaplatu = Math.round(iznosZaNaplatu * 100.0) / 100.0;
					
					tfIznosZaNaplatu.setText(String.valueOf(iznosZaNaplatu));
				}
			}
		});
		tfIznosZaNaplatu.setColumns(10);
		tfIznosZaNaplatu.setBounds(467, 357, 164, 20);
		ulaznaFaktura.getContentPane().add(tfIznosZaNaplatu);
		
		// poravnanje
		DefaultTableCellRenderer centar = new DefaultTableCellRenderer();
		centar.setHorizontalAlignment(JLabel.CENTER);
		tabela.getColumnModel().getColumn(0).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(1).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(2).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(3).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(4).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(5).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(6).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(7).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(8).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(9).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(10).setCellRenderer(centar);
		tabela.getColumnModel().getColumn(11).setCellRenderer(centar);
		
		// sirina kolona
		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(10).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(11).setPreferredWidth(60);
		
		//stil tabele
		tabela.setForeground(Color.WHITE);
		tabela.setBackground(new Color(0, 31, 105));
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setForeground(Color.WHITE);
		lblAdresa.setBounds(24, 334, 46, 14);
		ulaznaFaktura.getContentPane().add(lblAdresa);
		
		tfAdresa = new JTextField();
		tfAdresa.setColumns(10);
		tfAdresa.setBounds(131, 331, 164, 20);
		ulaznaFaktura.getContentPane().add(tfAdresa);
			
		ulaznaFaktura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public boolean provjeriRedniBroj(String redniBroj, String brojFakture)
	{
		boolean postoji = false;
		
		DBHome db = new DBHome();
		postoji = db.provjeriBrojIzlaznogArtikla(redniBroj, brojFakture);
		
		return postoji;
	}
}
