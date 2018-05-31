import java.sql.*;
import javax.swing.*;


public class DBCaka
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/toplomont?useUnicode=true&characterEncoding=UTF-8";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	
	Connection conn = null;
	Statement stmt = null;
	
	public void otvoriKonekciju() //povezivanje sa bazom, obevezno na kraju zatvorit sa zatvoriKonekciju()
	{	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Povezujem se...");
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Povezao sam se!");
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
	}
	public void zatvoriKonekciju()//obavezno na kraju
	{
		try
		{
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			
			System.out.println("Zatvorena konekcija!");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
	}
	
	/*public String[][] ucitajSiktaricu()
	{
		String[][] rezultati = new String [50][3];
		otvoriKonekciju();
		try
		{
			int brojac;
			stmt = conn.createStatement();
			String query = "SELECT Kljuc, Ime, Prezime FROM siktarica";
			ResultSet rs = stmt.executeQuery(query);
			
			for (brojac = 0; rs.next(); brojac++)
			{
				rezultati[brojac][0] = rs.getString("Kljuc"); 
				rezultati[brojac][1] = rs.getString("Ime"); 
				rezultati[brojac][2] = rs.getString("Prezime"); 
				
			}
			
		
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Došlo je do greške: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultati;
	}
	*/
	
	public void dodajFakturu (String BrojFakture, String DatumIzdavanja, String DatumIsporuke, String DatumValute, String FiskalniRacun, 
			String MjestoIzdavanja, String MjestoIsporuke, String NacinNaplate, String JIB, String PDV, String Adresa,
			int IDKupac, String Naziv) 
	{
		otvoriKonekciju();
		try{
			
			stmt = conn.createStatement();
			String query = "INSERT INTO IzlaznaFaktura (BrojFakture, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun,"
					+ "MjestoIzdavanja, MjestoIsporuke, NacinNaplate, JIB, PDV, Adresa, IDKupac, Naziv) "
					+ "VALUES ('"+BrojFakture+"','"+DatumIzdavanja+"','"+DatumIsporuke+"','"+DatumValute+"','"+FiskalniRacun+"','"
					+MjestoIzdavanja+"','"+MjestoIsporuke+"','"+NacinNaplate+"','"+JIB+"','"+PDV+"','"+Adresa+"','"+IDKupac+"','"+Naziv+"')";
			System.out.println(query); //radi provjere
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Došlo je do greške: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	
	

	
	
	public void dodajArtikal (String RedniBroj, String Sifra, String NazivArtikla, String JM, String Prodano, String BrojFakture, String CijenaSaPDV,
			String VrijednostSaPDV, String PostoPopusta, String PDVOsnovica, String PDVIznos, String IznosZaNaplatu) //meho dodao
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO IzlazniArtikal (RedniBroj, Sifra, NazivArtikla, JM, Prodano, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta"
					+ ", PDVOsnovica,  PDVIznos, IznosZaNaplatu) VALUES ('"+RedniBroj+"','"+Sifra+"','"+NazivArtikla+"','"+JM+"','"+Prodano+"','"
					+BrojFakture+"','"+CijenaSaPDV+"','"+VrijednostSaPDV+"','"+PostoPopusta+"','"+PDVOsnovica+"','"+PDVIznos+"','"+IznosZaNaplatu+"')";
			System.out.println(query); //radi provjere
			stmt.executeUpdate(query);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Došlo je do greške: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	
	
	
	public String[] provjeriIme(int IDKupac)
	{
		String[] a = new String[4];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Naziv, Adresa, JIB, PDV FROM IzlaznaFaktura WHERE IDKupac = '"+IDKupac+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				a[0] = rs.getString("Naziv");
				a[1] = rs.getString("Adresa");
				a[2] = rs.getString("JIB");
				a[3] = rs.getString("PDV");
			}
			
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske: "+e);
		}
		
		finally
		{
			zatvoriKonekciju();
		}
		
		return a;
		
	}
	
	public String provjeriNaziv(String Sifra)
	{
		String a = "";
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT NazivArtikla FROM IzlazniArtikal WHERE Sifra = '"+Sifra+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				a = rs.getString("NazivArtikla");
			}
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske: "+e);
		}
		
		finally
		{
			zatvoriKonekciju();
		}
		
		return a;
		
	}
	
	
	
	}
