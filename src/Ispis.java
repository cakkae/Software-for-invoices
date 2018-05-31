import java.io.*;
import javax.swing.*;

public class Ispis 
{	
	public void ispisiUlazneArtikla(String brojFakture)
	{
		String zamjena;
		double zbirIznosSaPDV = 0.0;
		double zbirPDVOsnovica = 0.0;
		double zbirPDVIznos = 0.0;
		double zbirIznosUkupno = 0.0;
		DBHome db = new DBHome();
		int brojRovova = db.nadiBrojArtikalaZaUlaznuFakturu(brojFakture);
		try
		{	
			if(brojFakture.contains("/"))
			{
				zamjena = brojFakture.replace("/", "-");
			}
			else zamjena = brojFakture;
			
			
			File putanja = new File("E:\\Documents and Settings\\Adnan\\Desktop\\fakture\\Ulazne fakture-"+zamjena+".html");
			putanja.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(putanja), "UTF-8"));
			
			String detaljiProdavaca[] = new String[4];
			detaljiProdavaca = db.pronadiDetaljeProdavaca(brojFakture);
			//ovdje sam stao
			bw.write("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>"
					+ "<style>@page {size:auto; margin: 0;} .zbir {font-family:'Calibri'; font-size:20px; font-style:italic;}</style></head>"
					+ "<body><h1>"+detaljiProdavaca[0]+"</h1>"
					+ "<b><p style='margin-left:500px'>Adresa:</b> "+detaljiProdavaca[1]+"</p>"
					+ "<b><p style='margin-left:500px'>JIB:</b> "+detaljiProdavaca[2]+"</p>"
					+ "<b><p style='margin-left:500px'>PDV:</b> "+detaljiProdavaca[3]+"</p>"
					+ "</br></br>");
			bw.flush();
			
			
			String detaljiFakture[] = new String [8];
			detaljiFakture = db.ucitajDetaljeUlazneFakture(brojFakture);
			
			bw.write("<table><td><table style='margin-left:15px;'><tr><td style='min-width:150px;'><h2>Broj fakture:</h2></td><td><h2>"+detaljiFakture[0]+"</h2></td></tr>"
					+ "<tr><td><b>Mjesto izdavanja:</b></td><td>"+detaljiFakture[5]+"</td></tr>"
					+ "<tr><td><b>Datum fakture:</b></td><td>"+detaljiFakture[1]+"</td></tr>"
					+ "<tr><td><b>Datum isporuke:</b></td><td>"+detaljiFakture[2]+"</td></tr>"
					+ "<tr><td><b>Datum valute:</b></td><td>"+detaljiFakture[3]+"</td></tr>"
					+ "<tr><td><b>Fiskalni raèun:</b></td><td>"+detaljiFakture[4]+"</td></tr>"
					+ "<tr><td><b>Mjesto isporuke:</b></td><td>"+detaljiFakture[6]+"</td></tr>"
					+ "<tr><td><b>Naèin naplate:</b></td><td>"+detaljiFakture[7]+"</td></tr></table></td></br>"); 
			
			bw.flush();
			
			bw.write("<td width='60%'><table style='border-collapse:collapse; border: 1px solid black; margin-left:50px; max-width:400px;' width='100%'>"
					+ "<tr bgcolor='silver'><td><b><p align='right' style='font-size:20px;'>TOPLOMONT O.D. BL. ÆURIÆ HASAN</p></b></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>Adresa: VAROŠ BB, 75320 GRAÈANICA</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>JIB: 4310436030006</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>PDV: 310436030006</b></p></td></tr></table></td></table></br>");
	
			bw.write("<table rules='rows cols'><th bgcolor='silver'>RB</th><th bgcolor='silver'>Šifra</th><th bgcolor='silver'>Naziv artikla</th><th bgcolor='silver'>JM</th><th bgcolor='silver'>Kolièina</th><th bgcolor='silver'>Cijena sa PDV</th>"
					+ "<th bgcolor='silver'>Vrijednost sa PDV</th><th bgcolor='silver'>% popusta</th><th bgcolor='silver'>PDV osnovica</th><th bgcolor='silver'>% PDV</th><th bgcolor='silver'>PDV Iznos</th>"
					+ "<th bgcolor='silver'>Iznos za naplatu</th>");
			
			String podatci[][] = new String [999][12];
			podatci = db.nadiUlazneArtikle(brojFakture);
			
			for (int brojac = 0; brojac < brojRovova; brojac++)
			{
				bw.write("<tr>");
				for (int brojac2 = 0; brojac2 < 12; brojac2++)
				{
					if (brojac2 == 5)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						System.out.println("for");
					}
					else if(brojac2 == 6)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosSaPDV = zbirIznosSaPDV + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 8)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVOsnovica = zbirPDVOsnovica + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 10)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVIznos = zbirPDVIznos + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 11)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosUkupno = zbirIznosUkupno + Double.valueOf(podatci[brojac][brojac2]);
					}
					else
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
					}
				}
				bw.write("</tr>");
				
				bw.flush();
			}
			bw.write("</table></br>"
					+ "<table style='margin-left:275px;'><tr><td width='200' align='right'><b>Vrijednost sa PDV:</b></td>"
					+ "<td width='255' align='right'><p class='zbir'>"+(zbirIznosSaPDV)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Osnovica za obraèun PDV-a:</b></td><td width='255' align='right'><p class='zbir'>"+(zbirPDVOsnovica)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Obraèunati PDV:</b></td><td align='right'><p class='zbir'>"+(zbirPDVIznos)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Iznos sa PDV-om za naplatu:</b></td><td align='right'><p class='zbir'>"+(zbirIznosUkupno)+"</p></tr>"
					+ "</table>");
			bw.write("<p style='display:inline-block; margin-left:40px;'>Robu izdao</p>"
					+ "<p style='display:inline-block;margin-left:230px;'>Robu primio</p>"
					+ "<p style='display:inline-block;margin-left:210px;'>Fakturisao</p><br>"
					+ "<hr width='150px' style='display:inline-block; float:left;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:155px;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:135px;'>");  
			
			bw.flush();
			
			bw.write("</body></html>");
			
			bw.flush();
			bw.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
	}
	public void ispisiIzlazneArtikla(String brojFakture)
	{
		String zamjena;
		double zbirIznosSaPDV = 0.0;
		double zbirPDVOsnovica = 0.0;
		double zbirPDVIznos = 0.0;
		double zbirIznosUkupno = 0.0;
		DBHome db = new DBHome();
		int brojRovova = db.nadiBrojArtikalaZaIzlaznuFakturu(brojFakture);
		try
		{	
			if(brojFakture.contains("/"))
			{
				zamjena = brojFakture.replace("/", "-");
			}
			else zamjena = brojFakture;
			
			
			File putanja = new File("E:\\Documents and Settings\\Adnan\\Desktop\\fakture\\Izlazne fakture-"+zamjena+".html");
			putanja.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(putanja), "UTF-8"));
			
			
			bw.write("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>"
					+ "<style>@page {size:auto; margin: 0;} .zbir {font-family:'Calibri'; font-size:20px; font-style:italic;}</style></head>"
					+ "<body><table><tr><td width='63%'><img src='slike/toplomont.jpg' width='400' style='margin-left:20px'/></td><td width='35%'>"
					+ "<p style='margin-left:20px'><b>TOPLOMONT</br>75320 Graèanica</br>Varoš b.b</br>Telefon: +387 62 284 - 171 </br>"
					+ "PDV: 4310436030006 </br>Raèun 132-180200-9722113 </br>NLB Tuzlanska Banka Graèanica</b></p></td></tr></table>"
					+ "</br></br><table><td><table style='margin-left:15px;'>");
			bw.flush();
			
			
			String detaljiFakture[] = new String [8];
			detaljiFakture = db.ucitajDetaljeIzlazneFakture(brojFakture);
			
			bw.write("<tr><td style='min-width:150px;'><h2>Broj fakture:</h2></td><td><h2>"+detaljiFakture[0]+"</h2></td></tr>"
					+ "<tr><td><b>Mjesto izdavanja:</b></td><td>"+detaljiFakture[5]+"</td></tr>"
					+ "<tr><td><b>Datum fakture:</b></td><td>"+detaljiFakture[1]+"</td></tr>"
					+ "<tr><td><b>Datum isporuke:</b></td><td>"+detaljiFakture[2]+"</td></tr>"
					+ "<tr><td><b>Datum valute:</b></td><td>"+detaljiFakture[3]+"</td></tr>"
					+ "<tr><td><b>Fiskalni raèun:</b></td><td>"+detaljiFakture[4]+"</td></tr>"
					+ "<tr><td><b>Mjesto isporuke:</b></td><td>"+detaljiFakture[6]+"</td></tr>"
					+ "<tr><td><b>Naèin naplate:</b></td><td>"+detaljiFakture[7]+"</td></tr></table></td></br>");
				
			String detaljiKupca[] = new String[4];
			detaljiKupca = db.nadiDetaljeKupca(brojFakture);
			
			bw.write("<td width='60%'><table style='border-collapse:collapse; border: 1px solid black; margin-left:75px; max-width:300px;' width='100%'>"
					+ "<tr bgcolor='silver'><td><b><p align='right' style='font-size:20px;'>Ime firme: "+detaljiKupca[0]+"</p></b></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>Adresa: "+detaljiKupca[1]+"</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>JIB: "+detaljiKupca[2]+"</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>PDV: "+detaljiKupca[3]+"</b></p></td></tr></table></td></table></br>");
	
			bw.write("<table rules='rows cols'><th bgcolor='silver'>RB</th><th bgcolor='silver'>Šifra</th><th bgcolor='silver'>Naziv artikla</th><th bgcolor='silver'>JM</th><th bgcolor='silver'>Kolièina</th><th bgcolor='silver'>Cijena sa PDV</th>"
					+ "<th bgcolor='silver'>Vrijednost sa PDV</th><th bgcolor='silver'>% popusta</th><th bgcolor='silver'>PDV osnovica</th><th bgcolor='silver'>% PDV</th><th bgcolor='silver'>PDV Iznos</th>"
					+ "<th bgcolor='silver'>Iznos za naplatu</th>");
			
			String podatci[][] = new String [999][12];
			podatci = db.nadiIzlazneArtikle(brojFakture);
			
			for (int brojac = 0; brojac < brojRovova; brojac++)
			{
				bw.write("<tr>");
				for (int brojac2 = 0; brojac2 < 12; brojac2++)
				{
					if (brojac2 == 5)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
					}
					else if(brojac2 == 6)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosSaPDV = zbirIznosSaPDV + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 8)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVOsnovica = zbirPDVOsnovica + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 10)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVIznos = zbirPDVIznos + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 11)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosUkupno = zbirIznosUkupno + Double.valueOf(podatci[brojac][brojac2]);
					}
					else
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
					}
				}
				bw.write("</tr>");
			}
			bw.write("</table></br>"
					+ "<table style='margin-left:275px;'><tr><td width='200' align='right'><b>Vrijednost sa PDV:</b></td>"
					+ "<td width='255' align='right'><p class='zbir'>"+(zbirIznosSaPDV)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Osnovica za obraèun PDV-a:</b></td><td width='255' align='right'><p class='zbir'>"+(zbirPDVOsnovica)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Obraèunati PDV:</b></td><td align='right'><p class='zbir'>"+(zbirPDVIznos)+"</p></td></tr>"
					+ "<tr><td align='right'><b>Iznos sa PDV-om za naplatu:</b></td><td align='right'><p class='zbir'>"+(zbirIznosUkupno)+"</p></tr>"
					+ "</table>");
			bw.write("<p style='display:inline-block; margin-left:40px;'>Robu izdao</p>"
					+ "<p style='display:inline-block;margin-left:230px;'>Robu primio</p>"
					+ "<p style='display:inline-block;margin-left:210px;'>Fakturisao</p><br>"
					+ "<hr width='150px' style='display:inline-block; float:left;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:155px;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:135px;'>");
			
			
			
			bw.write("</body></html>");
			
			bw.flush();
			bw.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
	}
	public void ispisiPredracun(String brojFakture)
	{
		String zamjena;
		double zbirIznosSaPDV = 0.0;
		double zbirPDVOsnovica = 0.0;
		double zbirPDVIznos = 0.0;
		double zbirIznosUkupno = 0.0;
		DBHome db = new DBHome();
		int brojRovova = db.nadiBrojArtikalaZaPredracun(brojFakture);
		try
		{	
			if(brojFakture.contains("/"))
			{
				zamjena = brojFakture.replace("/", "-");
			}
			else zamjena = brojFakture;
			
			
			File putanja = new File("E:\\Documents and Settings\\Adnan\\Desktop\\fakture\\Ulazne fakture-"+zamjena+".html");
			putanja.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(putanja), "UTF-8"));
			
			
			bw.write("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>"
					+ "<style>@page {size:auto; margin: 0;} .zbir {font-family:'Calibri'; font-size:20px; font-style:italic;}</style></head>"
					+ "<body><table><tr><td width='63%'><img src='slike/toplomont.jpg' width='400' style='margin-left:20px'/></td><td width='35%'>"
					+ "<p style='margin-left:20px'><b>TOPLOMONT</br>75320 Graèanica</br>Varoš b.b</br>Telefon: +387 62 284 - 171 </br>"
					+ "PDV: 4310436030006 </br>Raèun 132-180200-9722113 </br>NLB Tuzlanska Banka Graèanica</b></p></td></tr></table>"
					+ "</br></br><table><td><table style='margin-left:15px;'>");
			bw.flush();
			
			
			String detaljiFakture[] = new String [8];
			detaljiFakture = db.ucitajDetaljePredracuna(brojFakture);
			
			bw.write("<tr><td style='min-width:150px;'><h2>Broj fakture:</h2></td><td><h2>"+detaljiFakture[0]+"</h2></td></tr>"
					+ "<tr><td><b>Mjesto izdavanja:</b></td><td>"+detaljiFakture[5]+"</td></tr>"
					+ "<tr><td><b>Datum fakture:</b></td><td>"+detaljiFakture[1]+"</td></tr>"
					+ "<tr><td><b>Datum isporuke:</b></td><td>"+detaljiFakture[2]+"</td></tr>"
					+ "<tr><td><b>Datum valute:</b></td><td>"+detaljiFakture[3]+"</td></tr>"
					+ "<tr><td><b>Fiskalni raèun:</b></td><td>"+detaljiFakture[4]+"</td></tr>"
					+ "<tr><td><b>Mjesto isporuke:</b></td><td>"+detaljiFakture[6]+"</td></tr>"
					+ "<tr><td><b>Naèin naplate:</b></td><td>"+detaljiFakture[7]+"</td></tr></table></td></br>");
				
			String detaljiKupca[] = new String[4];
			detaljiKupca = db.nadiDetaljeKupcaPredracun(brojFakture);
			
			bw.write("<td width='60%'><table style='border-collapse:collapse; border: 1px solid black; margin-left:75px; max-width:300px;' width='100%'>"
					+ "<tr bgcolor='silver'><td><b><p align='right' style='font-size:20px;'>Ime firme: "+detaljiKupca[0]+"</p></b></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>Adresa: "+detaljiKupca[1]+"</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>JIB: "+detaljiKupca[2]+"</b></p></td></tr>"
					+ "<tr><td><b><p align='right' style='font-size:16px; margin-right:5px;'>PDV: "+detaljiKupca[3]+"</b></p></td></tr></table></td></table></br>");
	
			bw.write("<table rules='rows cols'><th bgcolor='silver'>RB</th><th bgcolor='silver'>Šifra</th><th bgcolor='silver'>Naziv artikla</th><th bgcolor='silver'>JM</th><th bgcolor='silver'>Kolièina</th><th bgcolor='silver'>Cijena sa PDV</th>"
					+ "<th bgcolor='silver'>Vrijednost sa PDV</th><th bgcolor='silver'>% popusta</th><th bgcolor='silver'>PDV osnovica</th><th bgcolor='silver'>% PDV</th><th bgcolor='silver'>PDV Iznos</th>"
					+ "<th bgcolor='silver'>Iznos za naplatu</th>");
			
			String podatci[][] = new String [999][12];
			podatci = db.nadiArtiklePredracun(brojFakture);
			
			for (int brojac = 0; brojac < brojRovova; brojac++)
			{
				bw.write("<tr>");
				for (int brojac2 = 0; brojac2 < 12; brojac2++)
				{
					if (brojac2 == 5)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
					}
					else if(brojac2 == 6)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosSaPDV = zbirIznosSaPDV + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 8)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVOsnovica = zbirPDVOsnovica + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 10)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirPDVIznos = zbirPDVIznos + Double.valueOf(podatci[brojac][brojac2]);
					}
					else if (brojac2 == 11)
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
						zbirIznosUkupno = zbirIznosUkupno + Double.valueOf(podatci[brojac][brojac2]);
					}
					else
					{
						bw.write("<td align='center'>"+podatci[brojac][brojac2]+"</td>");
					}
				}
				bw.write("</tr>");
			}
			bw.write("</table></br>"
					+ "<table style='margin-left:275px;'><tr><td width='200' align='right'><b>Vrijednost sa PDV:</b></td>"
					+ "<td width='255' align='right'><p class='zbir'>"+(zbirIznosSaPDV)+" KM</p></td></tr>"
					+ "<tr><td align='right'><b>Osnovica za obraèun PDV-a:</b></td><td width='255' align='right'><p class='zbir'>"+(zbirPDVOsnovica)+" KM</p></td></tr>"
					+ "<tr><td align='right'><b>Obraèunati PDV:</b></td><td align='right'><p class='zbir'>"+(zbirPDVIznos)+" KM</p></td></tr>"
					+ "<tr><td align='right'><b>Iznos sa PDV-om za naplatu:</b></td><td align='right'><p class='zbir'>"+(zbirIznosUkupno)+" KM</p></tr>"
					+ "</table>");
			bw.write("<p style='display:inline-block; margin-left:40px;'>Robu izdao</p>"
					+ "<p style='display:inline-block;margin-left:230px;'>Robu primio</p>"
					+ "<p style='display:inline-block;margin-left:210px;'>Fakturisao</p><br>"
					+ "<hr width='150px' style='display:inline-block; float:left;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:155px;'>"
					+ "<hr width='150px' style='display:inline-block; margin-left:135px;'>");
			
			
			
			bw.write("</body></html>");
			
			bw.flush();
			bw.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Desila se greška: "+e);
		}
	}
}
