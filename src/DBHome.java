import java.sql.*;
import javax.swing.*;

public class DBHome 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://server1.iso.ba/tomplomont-baza?useUnicode=true&characterEncoding=UTF-8";
	static final String USERNAME = "kale";
	static final String PASSWORD = "glupSam123!";
	
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
	public void dodajFakturu(String brojFakture, String nazivFirme, String datumIzdavanja, String datumIsporuke, String datumValute, String fiskalniRacun,
			String mjestoIzdavanja, String mjestoIsporuke, String nacinNaplate, String JIB, String PDV, String adresa)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO UlaznaFaktura (BrojFakture, NazivFirme, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja,"
					+ "MjestoIsporuke, NacinNaplate, JIB, PDV, Adresa) ";
			query = query + "VALUES ('"+brojFakture+"','"+nazivFirme+"','"+datumIzdavanja+"','"+datumIsporuke+"','"+datumValute+"','"+fiskalniRacun+
					"','"+mjestoIzdavanja+"','"+mjestoIsporuke+"','"+nacinNaplate+"','"+JIB+"','"+PDV+"','"+adresa+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public boolean provjeriBrojFakture(String brojFakture)
	{
		boolean postoji = true;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture FROM UlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) postoji = true;
			else postoji = false;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return postoji;
	}
	public void dodajArtikal(String redniBroj, String sifra, String nazivArtikla, String JM, String kupljeno, String brojFakture, String cijenaSaPDV,
			String vrijednostSaPDV, String postoPopusta, String PDVOsnovica, String postoPDV, String PDVIznos, String iznosZaNaplatu)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO UlazniArtikal ";
			query = query + "(RedniBroj, Sifra, NazivArtikla, JM, Kupljeno, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica, ";
			query = query + "PostoPDV, PDVIznos, IznosZaNaplatu) VALUES ('"+redniBroj+"','"+sifra+"','"+nazivArtikla+"','"+JM+"','"+kupljeno+"','";
			query = query +brojFakture+"','"+cijenaSaPDV+"','"+vrijednostSaPDV+"','"+postoPopusta+"','"+PDVOsnovica+"','"+postoPDV+"','"+PDVIznos+"','";
			query = query +iznosZaNaplatu+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public boolean provjeriBrojArtikla(String redniBrojArtikla, String brojFakture)
	{
		boolean postoji = false;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, BrojFakture FROM UlazniArtikal ";
			query = query + "WHERE RedniBroj ='"+redniBrojArtikla+"' AND BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) postoji = true;
			else postoji = false;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return postoji;
	}
	public String[][] ucitajUlazneFakture()
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM UlaznaFaktura";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("BrojFakture");
				rezultat[brojac][1] = rs.getString("NazivFirme");
				rezultat[brojac][2] = rs.getString("DatumIzdavanja");
				rezultat[brojac][3] = rs.getString("DatumIsporuke");
				rezultat[brojac][4] = rs.getString("DatumValute");
				rezultat[brojac][5] = rs.getString("FiskalniRacun");
				rezultat[brojac][6] = rs.getString("MjestoIzdavanja");
				rezultat[brojac][7] = rs.getString("MjestoIsporuke");
				rezultat[brojac][8] = rs.getString("NacinNaplate");
				rezultat[brojac][9] = rs.getString("JIB");
				rezultat[brojac][10] = rs.getString("PDV");
				rezultat[brojac][11] = rs.getString("Adresa");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public int nadiBrojArtikalaZaUlaznuFakturu(String brojFakture)
	{
		int rezultat = 0;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture FROM UlazniArtikal WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) rezultat = rezultat + 1;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public String[][] nadiArtikle(String brojFakture)
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, Sifra, NazivArtikla, JM, Kupljeno, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica,"
					+ " PostoPDV, PDVIznos, IznosZaNaplatu FROM UlazniArtikal WHERE BrojFakture ='"+brojFakture+"' ORDER BY RedniBroj ASC";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("RedniBroj");
				rezultat[brojac][1] = rs.getString("Sifra");
				rezultat[brojac][2] = rs.getString("NazivArtikla");
				rezultat[brojac][3] = rs.getString("JM");
				rezultat[brojac][4] = rs.getString("Kupljeno");
				rezultat[brojac][5] = rs.getString("CijenaSaPDV");
				rezultat[brojac][6] = rs.getString("VrijednostSaPDV");
				rezultat[brojac][7] = rs.getString("PostoPopusta");
				rezultat[brojac][8] = rs.getString("PDVOsnovica");
				rezultat[brojac][9] = rs.getString("PostoPDV");
				rezultat[brojac][10] = rs.getString("PDVIznos");
				rezultat[brojac][11] = rs.getString("IznosZaNaplatu");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	
	public boolean provjeriBrojIzlazneFakture(String brojFakture)
	{
		boolean postoji = true;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture FROM IzlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) postoji = true;
			else postoji = false;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return postoji;
	}
	public void dodajIzlaznuFakturu(String brojFakture, String nazivFirme, String datumIzdavanja, String datumIsporuke, String datumValute, String fiskalniRacun,
			String mjestoIzdavanja, String mjestoIsporuke, String nacinNaplate, String JIB, String PDV, String adresa)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO IzlaznaFaktura (BrojFakture, NazivFirme, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja,"
					+ "MjestoIsporuke, NacinNaplate, JIB, PDV, Adresa) ";
			query = query + "VALUES ('"+brojFakture+"','"+nazivFirme+"','"+datumIzdavanja+"','"+datumIsporuke+"','"+datumValute+"','"+fiskalniRacun+
					"','"+mjestoIzdavanja+"','"+mjestoIsporuke+"','"+nacinNaplate+"','"+JIB+"','"+PDV+"','"+adresa+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public void dodajIzlazniArtikal(String redniBroj, String sifra, String nazivArtikla, String JM, String kupljeno, String brojFakture, String cijenaSaPDV,
			String vrijednostSaPDV, String postoPopusta, String PDVOsnovica, String postoPDV, String PDVIznos, String iznosZaNaplatu)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO IzlazniArtikal ";
			query = query + "(RedniBroj, Sifra, NazivArtikla, JM, Prodano, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica, ";
			query = query + "PostoPDV, PDVIznos, IznosZaNaplatu) VALUES ('"+redniBroj+"','"+sifra+"','"+nazivArtikla+"','"+JM+"','"+kupljeno+"','";
			query = query +brojFakture+"','"+cijenaSaPDV+"','"+vrijednostSaPDV+"','"+postoPopusta+"','"+PDVOsnovica+"','"+postoPDV+"','"+PDVIznos+"','";
			query = query +iznosZaNaplatu+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public boolean provjeriBrojIzlaznogArtikla(String redniBrojArtikla, String brojFakture)
	{
		boolean postoji = false;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, BrojFakture FROM IzlazniArtikal ";
			query = query + "WHERE RedniBroj ='"+redniBrojArtikla+"' AND BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) postoji = true;
			else postoji = false;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return postoji;
	}
	public String[][] ucitajIzlazneFakture()
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM IzlaznaFaktura";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("BrojFakture");
				rezultat[brojac][1] = rs.getString("NazivFirme");
				rezultat[brojac][2] = rs.getString("DatumIzdavanja");
				rezultat[brojac][3] = rs.getString("DatumIsporuke");
				rezultat[brojac][4] = rs.getString("DatumValute");
				rezultat[brojac][5] = rs.getString("FiskalniRacun");
				rezultat[brojac][6] = rs.getString("MjestoIzdavanja");
				rezultat[brojac][7] = rs.getString("MjestoIsporuke");
				rezultat[brojac][8] = rs.getString("NacinNaplate");
				rezultat[brojac][9] = rs.getString("JIB");
				rezultat[brojac][10] = rs.getString("PDV");
				rezultat[brojac][11] = rs.getString("Adresa");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public int nadiBrojArtikalaZaIzlaznuFakturu(String brojFakture)
	{
		int rezultat = 0;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture FROM IzlazniArtikal WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) rezultat = rezultat + 1;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public String[][] nadiIzlazneArtikle(String brojFakture)
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, Sifra, NazivArtikla, JM, Prodano, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica,"
					+ " PostoPDV, PDVIznos, IznosZaNaplatu FROM IzlazniArtikal WHERE BrojFakture ='"+brojFakture+"' ORDER BY RedniBroj ASC";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("RedniBroj");
				rezultat[brojac][1] = rs.getString("Sifra");
				rezultat[brojac][2] = rs.getString("NazivArtikla");
				rezultat[brojac][3] = rs.getString("JM");
				rezultat[brojac][4] = rs.getString("Prodano");
				rezultat[brojac][5] = rs.getString("CijenaSaPDV");
				rezultat[brojac][6] = rs.getString("VrijednostSaPDV");
				rezultat[brojac][7] = rs.getString("PostoPopusta");
				rezultat[brojac][8] = rs.getString("PDVOsnovica");
				rezultat[brojac][9] = rs.getString("PostoPDV");
				rezultat[brojac][10] = rs.getString("PDVIznos");
				rezultat[brojac][11] = rs.getString("IznosZaNaplatu");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public boolean provjeriStanjeArtikla(String nazivArtikla, String unesenaKolicina)
	{
		int kupljeno = 0;
		int prodano = 0;
		int stanje = 0;
		boolean imaDovoljno = false;
		
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			//prvo uzimanje ulaznog stanja
			String query = "SELECT NazivArtikla, Kupljeno FROM UlazniArtikal WHERE NazivArtikla = '"+nazivArtikla+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next())
			{
				kupljeno = kupljeno + Integer.valueOf(rs.getString("Kupljeno"));
			}
			
			//zatvaranje konekciji i malo spring clean up za slijedeci query
			zatvoriKonekciju(); rs.close();
			
			//kad ocisitimo opet prljamo :D
			otvoriKonekciju();
			stmt = conn.createStatement();
			//sad provjera prodanog
			query = "SELECT NazivArtikla, Prodano FROM IzlazniArtikal WHERE NazivArtikla = '"+nazivArtikla+"'";
			rs = stmt.executeQuery(query);
			
			while (rs.next())
			{
				prodano = prodano + Integer.valueOf(rs.getString("Prodano"));
			}
			
			stanje = kupljeno - prodano;
			
			if (Integer.valueOf(unesenaKolicina) <= stanje) imaDovoljno = true;
			else imaDovoljno = false;
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return imaDovoljno;
	}
	public String[] ucitajDetaljeIzlazneFakture(String brojFakture)
	{
		otvoriKonekciju();
		String rezultat[] = new String[8];
		
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja, MjestoIsporuke, NacinNaplate "
					+ "FROM IzlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("BrojFakture");
				rezultat[1] = rs.getString("DatumIzdavanja");
				rezultat[2] = rs.getString("DatumIsporuke");
				rezultat[3] = rs.getString("DatumValute");
				rezultat[4] = rs.getString("FiskalniRacun");
				rezultat[5] = rs.getString("MjestoIzdavanja");
				rezultat[6] = rs.getString("MjestoIsporuke");
				rezultat[7] = rs.getString("NacinNaplate");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[] nadiDetaljeKupca(String brojFakture)
	{
		String rezultat[] = new String[4];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture, NazivFirme, Adresa, JIB, PDV FROM IzlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("NazivFirme");
				rezultat[1] = rs.getString("Adresa");
				rezultat[2] = rs.getString("JIB");
				rezultat[3] = rs.getString("PDV");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String pronadiUlazniArtikalSaSifrom(String sifra)
	{
		String rezultat = "";
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Sifra, NazivArtikla FROM UlazniArtikal WHERE Sifra = '"+sifra+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) rezultat = rs.getString("NazivArtikla");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String pronadiIzlazniArtikalSaSifrom(String sifra)
	{
		String rezultat = "";
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Sifra, NazivArtikla FROM IzlazniArtikal WHERE Sifra = '"+sifra+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) rezultat = rs.getString("NazivArtikla");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[] pronadiDetaljeProdavaca(String brojFakture)
	{
		String rezultat[] = new String[4];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT NazivFirme, Adresa, JIB, PDV, BrojFakture FROM UlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("NazivFirme");
				rezultat[1] = rs.getString("Adresa");
				rezultat[2] = rs.getString("JIB");
				rezultat[3] = rs.getString("PDV");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[] ucitajDetaljeUlazneFakture(String brojFakture)
	{
		otvoriKonekciju();
		String rezultat[] = new String[8];
		
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja, MjestoIsporuke, NacinNaplate "
					+ "FROM UlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("BrojFakture");
				rezultat[1] = rs.getString("DatumIzdavanja");
				rezultat[2] = rs.getString("DatumIsporuke");
				rezultat[3] = rs.getString("DatumValute");
				rezultat[4] = rs.getString("FiskalniRacun");
				rezultat[5] = rs.getString("MjestoIzdavanja");
				rezultat[6] = rs.getString("MjestoIsporuke");
				rezultat[7] = rs.getString("NacinNaplate");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[][] nadiUlazneArtikle(String brojFakture)
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, Sifra, NazivArtikla, JM, Kupljeno, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica,"
					+ " PostoPDV, PDVIznos, IznosZaNaplatu FROM UlazniArtikal WHERE BrojFakture ='"+brojFakture+"' ORDER BY RedniBroj ASC";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("RedniBroj");
				rezultat[brojac][1] = rs.getString("Sifra");
				rezultat[brojac][2] = rs.getString("NazivArtikla");
				rezultat[brojac][3] = rs.getString("JM");
				rezultat[brojac][4] = rs.getString("Kupljeno");
				rezultat[brojac][5] = rs.getString("CijenaSaPDV");
				rezultat[brojac][6] = rs.getString("VrijednostSaPDV");
				rezultat[brojac][7] = rs.getString("PostoPopusta");
				rezultat[brojac][8] = rs.getString("PDVOsnovica");
				rezultat[brojac][9] = rs.getString("PostoPDV");
				rezultat[brojac][10] = rs.getString("PDVIznos");
				rezultat[brojac][11] = rs.getString("IznosZaNaplatu");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public String[][] ucitajSveUlazneArtikle()
	{
		String rezultat[][] = new String[9999][3];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Sifra, NazivArtikla, Kupljeno FROM UlazniArtikal";
			ResultSet rs = stmt.executeQuery(query);
			
			for(int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("Sifra");
				rezultat[brojac][1] = rs.getString("NazivArtikla");
				rezultat[brojac][2] = rs.getString("Kupljeno");
			}
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[][] ucitajSveIzlazneArtikle()
	{
		String rezultat[][] = new String[9999][3];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT Sifra, NazivArtikla, Prodano FROM IzlazniArtikal";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("Sifra");
				rezultat[brojac][1] = rs.getString("NazivArtikla");
				rezultat[brojac][2] = rs.getString("Prodano");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public void dodajPredracun(String brojFakture, String nazivFirme, String datumIzdavanja, String datumIsporuke, String datumValute, String fiskalniRacun,
			String mjestoIzdavanja, String mjestoIsporuke, String nacinNaplate, String JIB, String PDV, String adresa)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO Predracun (BrojFakture, NazivFirme, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja,"
					+ "MjestoIsporuke, NacinNaplate, JIB, PDV, Adresa) ";
			query = query + "VALUES ('"+brojFakture+"','"+nazivFirme+"','"+datumIzdavanja+"','"+datumIsporuke+"','"+datumValute+"','"+fiskalniRacun+
					"','"+mjestoIzdavanja+"','"+mjestoIsporuke+"','"+nacinNaplate+"','"+JIB+"','"+PDV+"','"+adresa+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public void dodajPredracunArtikal(String redniBroj, String sifra, String nazivArtikla, String JM, String kupljeno, String brojFakture, String cijenaSaPDV,
			String vrijednostSaPDV, String postoPopusta, String PDVOsnovica, String postoPDV, String PDVIznos, String iznosZaNaplatu)
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "INSERT INTO PredracunArtikal ";
			query = query + "(RedniBroj, Sifra, NazivArtikla, JM, Prodano, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica, ";
			query = query + "PostoPDV, PDVIznos, IznosZaNaplatu) VALUES ('"+redniBroj+"','"+sifra+"','"+nazivArtikla+"','"+JM+"','"+kupljeno+"','";
			query = query +brojFakture+"','"+cijenaSaPDV+"','"+vrijednostSaPDV+"','"+postoPopusta+"','"+PDVOsnovica+"','"+postoPDV+"','"+PDVIznos+"','";
			query = query +iznosZaNaplatu+"')";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public String[][] ucitajPredracune()
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT * FROM Predracun";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("BrojFakture");
				rezultat[brojac][1] = rs.getString("NazivFirme");
				rezultat[brojac][2] = rs.getString("DatumIzdavanja");
				rezultat[brojac][3] = rs.getString("DatumIsporuke");
				rezultat[brojac][4] = rs.getString("DatumValute");
				rezultat[brojac][5] = rs.getString("FiskalniRacun");
				rezultat[brojac][6] = rs.getString("MjestoIzdavanja");
				rezultat[brojac][7] = rs.getString("MjestoIsporuke");
				rezultat[brojac][8] = rs.getString("NacinNaplate");
				rezultat[brojac][9] = rs.getString("JIB");
				rezultat[brojac][10] = rs.getString("PDV");
				rezultat[brojac][11] = rs.getString("Adresa");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public int nadiBrojArtikalaZaPredracun(String brojFakture)
	{
		int rezultat = 0;
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture FROM PredracunArtikal WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) rezultat = rezultat + 1;
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public String[] ucitajDetaljePredracuna(String brojFakture)
	{
		otvoriKonekciju();
		String rezultat[] = new String[8];
		
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture, DatumIzdavanja, DatumIsporuke, DatumValute, FiskalniRacun, MjestoIzdavanja, MjestoIsporuke, NacinNaplate "
					+ "FROM Predracun WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("BrojFakture");
				rezultat[1] = rs.getString("DatumIzdavanja");
				rezultat[2] = rs.getString("DatumIsporuke");
				rezultat[3] = rs.getString("DatumValute");
				rezultat[4] = rs.getString("FiskalniRacun");
				rezultat[5] = rs.getString("MjestoIzdavanja");
				rezultat[6] = rs.getString("MjestoIsporuke");
				rezultat[7] = rs.getString("NacinNaplate");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[] nadiDetaljeKupcaPredracun(String brojFakture)
	{
		String rezultat[] = new String[4];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT BrojFakture, NazivFirme, Adresa, JIB, PDV FROM Predracun WHERE BrojFakture = '"+brojFakture+"'";
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
			{
				rezultat[0] = rs.getString("NazivFirme");
				rezultat[1] = rs.getString("Adresa");
				rezultat[2] = rs.getString("JIB");
				rezultat[3] = rs.getString("PDV");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		return rezultat;
	}
	public String[][] nadiArtiklePredracun(String brojFakture)
	{
		String rezultat[][] = new String[999][12];
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "SELECT RedniBroj, Sifra, NazivArtikla, JM, Prodano, BrojFakture, CijenaSaPDV, VrijednostSaPDV, PostoPopusta, PDVOsnovica,"
					+ " PostoPDV, PDVIznos, IznosZaNaplatu FROM PredracunArtikal WHERE BrojFakture ='"+brojFakture+"' ORDER BY RedniBroj ASC";
			ResultSet rs = stmt.executeQuery(query);
			
			for (int brojac = 0; rs.next(); brojac++)
			{
				rezultat[brojac][0] = rs.getString("RedniBroj");
				rezultat[brojac][1] = rs.getString("Sifra");
				rezultat[brojac][2] = rs.getString("NazivArtikla");
				rezultat[brojac][3] = rs.getString("JM");
				rezultat[brojac][4] = rs.getString("Prodano");
				rezultat[brojac][5] = rs.getString("CijenaSaPDV");
				rezultat[brojac][6] = rs.getString("VrijednostSaPDV");
				rezultat[brojac][7] = rs.getString("PostoPopusta");
				rezultat[brojac][8] = rs.getString("PDVOsnovica");
				rezultat[brojac][9] = rs.getString("PostoPDV");
				rezultat[brojac][10] = rs.getString("PDVIznos");
				rezultat[brojac][11] = rs.getString("IznosZaNaplatu");
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
		
		return rezultat;
	}
	public void izbrisiUlaznuFakturu(String brojFakture)  //1.
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "DELETE FROM UlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public void izbrisiIzlaznuFakturu(String brojFakture)  //1.
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "DELETE FROM IzlaznaFaktura WHERE BrojFakture = '"+brojFakture+"'";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public void izbrisiPredracun(String brojFakture)  //1.
	{
		otvoriKonekciju();
		try
		{
			stmt = conn.createStatement();
			String query = "DELETE FROM Predracun WHERE BrojFakture = '"+brojFakture+"'";
			stmt.executeUpdate(query);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
		finally
		{
			zatvoriKonekciju();
		}
	}
	public String[][] ipisiArtikleUlazneFakture(String brojFakture)
	{
		String rezultat[][] = new String[999][]; //nasti ovdje metod
		return rezultat;
	}
}