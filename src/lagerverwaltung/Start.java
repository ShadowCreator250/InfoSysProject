package lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung verwaltung = new Lagerverwaltung();
		Mitarbeiter bernd = new Mitarbeiter("BeM202", "Bernd M�ller");
		Mitarbeiter hans = new Mitarbeiter("HaB084", "Hans Bieder");
		Mitarbeiter anna = new Mitarbeiter("AnR958", "Anne Richter");
		Mitarbeiter duke = new Mitarbeiter("DuM284", "Duke McCry");
		Mitarbeiter marie = new Mitarbeiter("MaF439", "Marie Fischer");
		Artikel schrauben = new Artikel("schrauben-13", "Schrauben", "Schrauben mit Gewindegr��e 13");
		Lagerposten schraubenPosten = new Lagerposten(schrauben, 75, 0.25);
		
		Bestellposten schraubenBestellung1 = new Bestellposten("schrauben-13", 50);
		Bestellposten schraubenBestellung2 = new Bestellposten("schrauben-13", 100);
		List<Bestellposten> bestellung1 = new ArrayList<>();
		bestellung1.add(schraubenBestellung1);
		List<Bestellposten> bestellung2 = new ArrayList<>();
		bestellung2.add(schraubenBestellung2);

		verwaltung.berechtigungErteilen(bernd);
		//verwaltung.berechtigungErteilen(hans);
		//verwaltung.berechtigungErteilen(anna);
		verwaltung.berechtigungErteilen(duke);
		//verwaltung.berechtigungErteilen(marie);
		verwaltung.berechtigungZurueckziehen(duke);
		
		verwaltung.addToLagerposten(schraubenPosten);
		verwaltung.lagerbestandAusgeben();
		//verwaltung.wareneingangBuchen(bernd, schrauben, 50, 0.25);
		//verwaltung.wareneingangBuchen(duke, schrauben, 50, 0.25);
		//verwaltung.bestellungAusfuehren(bernd, bestellung1);
		//verwaltung.bestellungAusfuehren(bernd, bestellung2);
		verwaltung.bestellungAusfuehren(duke, bestellung1);
		
		verwaltung.lagerbestandAusgeben();

	}

}
