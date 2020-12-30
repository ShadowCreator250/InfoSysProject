package lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		Lagerverwaltung verwaltung = new Lagerverwaltung();
		Mitarbeiter bernd = new Mitarbeiter("BeM202", "Bernd Müller");
		Mitarbeiter hans = new Mitarbeiter("HaB084", "Hans Bieder");
		Mitarbeiter anna = new Mitarbeiter("AnR958", "Anne Richter");
		Mitarbeiter duke = new Mitarbeiter("DuM284", "Duke McCry");
		Mitarbeiter marie = new Mitarbeiter("MaF439", "Marie Fischer");

		Artikel schraubenArtikel13 = new Artikel("schraube-13", "13er Sechskantkopfschraube", "Schrauben mit DIN-Schlüsselweite 13");
		Artikel schraubenArtikel17 = new Artikel("schraube-17", "17er Sechskantkopfschraube", "Schrauben mit DIN-Schlüsselweite 17");
		Lagerposten schraubenPosten13 = new Lagerposten(schraubenArtikel13, 37, 0.25);
		Lagerposten schraubenPosten17 = new Lagerposten(schraubenArtikel17, 25, 0.32);

		Bestellposten schraubenBestellPosten1 = new Bestellposten(schraubenArtikel13.getId(), 50);
		Bestellposten schraubenBestellPosten2 = new Bestellposten(schraubenArtikel13.getId(), 100);
		Bestellposten schraubenBestellPosten3 = new Bestellposten(schraubenArtikel17.getId(), 50);
		List<Bestellposten> bestellung1 = new ArrayList<>();
		bestellung1.add(schraubenBestellPosten1);
		List<Bestellposten> bestellung2 = new ArrayList<>();
		bestellung2.add(schraubenBestellPosten2);
		bestellung2.add(schraubenBestellPosten3);

		// -----

//		verwaltung.berechtigungErteilen(hans);
//		verwaltung.berechtigungErteilen(anna);
//		verwaltung.berechtigungErteilen(marie);
		verwaltung.berechtigungErteilen(bernd);
		verwaltung.berechtigungErteilen(duke);
		verwaltung.berechtigungZurueckziehen(duke);

		verwaltung.addToLagerposten(schraubenPosten13);
		verwaltung.addToLagerposten(schraubenPosten17);
		verwaltung.addToLagerposten(schraubenPosten17);
		verwaltung.lagerbestandAusgeben();

		verwaltung.wareneingangBuchen(bernd, schraubenArtikel13, 50, 0.27);
		verwaltung.wareneingangBuchen(duke, schraubenArtikel17, 10, 0.30);
		verwaltung.lagerbestandAusgeben();

		verwaltung.bestellungAusfuehren(duke, bestellung1);
		verwaltung.bestellungAusfuehren(bernd, bestellung1);
		verwaltung.bestellungAusfuehren(bernd, bestellung2);
		verwaltung.lagerbestandAusgeben();

	}

}
