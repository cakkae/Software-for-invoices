import java.sql.*;
import javax.swing.*;


public class DB 
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
	public String[][] ucitajSiktaricu()
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
	
	public void dodajFakturu (String brojFakture, String ime, String mjestoIzdavanja, String datumFakture, String datumIsporuke, String datumValute,
			String fiskalniRacun, String mjestoIsporuke, String nacinUplate, String JIB, String PDV, String adresa) //meho dodao
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO dobavljac VALUES ('"+brojFakture+"','"+ime+"','"+mjestoIzdavanja+"','"+datumFakture+"','"+datumIsporuke+"','"
					+datumValute+"','"+fiskalniRacun+"','"+mjestoIsporuke+"','"+nacinUplate+"','"+JIB+"','"+PDV+"','"+adresa+"')";
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
	public void dodajArtikal (String sifra, String JM, String naziv, String brojFakture, String cijenaSaPDV, String naStanju, String prodano,
			String postoPopusta, String PDVOsnovica, String postoPDV, String iznosZaNaplatu, String redniBroj, String vrijednostSaPDV, String PDVIznos) //meho dodao
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO artikal (Sifra, JM, Naziv, BrojFakture, CijenaSaPDV, NaStanju, Prodano, PostoPopusta, PDVOsnovica, PostoPDV"
					+ ", IznosZaNaplatu, RedniBroj, VrijednostSaPDV, PDVIznos) VALUES ('"+sifra+"','"+JM+"','"+naziv+"','"+brojFakture+"','"+cijenaSaPDV+"','"
					+naStanju+"','"+prodano+"','"+postoPopusta+"','"+PDVOsnovica+"','"+postoPDV+"','"+iznosZaNaplatu+"','"+redniBroj+"','"+vrijednostSaPDV+"','"+PDVIznos+"')";
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
	public String provjeriIme (String BrojFakture)
	{
		String a = "";
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Ime FROM dobavljac WHERE BrojFakture = '"+BrojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				a = rs.getString("ime");
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
