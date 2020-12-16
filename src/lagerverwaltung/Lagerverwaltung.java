package lagerverwaltung;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {

	private Set<String> berechtigteMitarbeiter;
	private List<Lagerposten> lagerposten;
	private PrintWriter writer;

	public Lagerverwaltung() {
		// TODO Auto-generated constructor stub

	}

	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		// TODO Auto-generated method stub

	}

	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		// TODO Auto-generated method stub

	}

	public void lagerbestandAusgeben() {
		// TODO Auto-generated method stub

	}

	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int anzahl, double preis) {
		// TODO Auto-generated method stub

	}

	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bestellung) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addToLagerposten(Lagerposten lagerposten) {
		// TODO Auto-generated method stub

	}

	public Set<String> getBerechtigteMitarbeiter() {
		return berechtigteMitarbeiter;
	}

	public List<Lagerposten> getLagerposten() {
		return lagerposten;
	}
}
