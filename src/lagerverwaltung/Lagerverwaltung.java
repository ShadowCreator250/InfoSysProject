package lagerverwaltung;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Die Verwaltung des Lagers, die alle wichtigen Aufgaben übernimmt. <br>
 * <br>
 * Den {@link Mitarbeiter}n kann die Berechtigung zur Verwaltung erteilt bzw. entzogen werden. <br>
 * Sie können sowohl Wareneingänge, als auch Bestellungen verbuchen.
 */
public class Lagerverwaltung {

	/**
	 * Ein Set der Mitarbeiter, die dazu berechtigt sind, das Lager zu verwalten.
	 */
	private Set<String> berechtigteMitarbeiter;
	/**
	 * der Lagerbestand
	 */
	private List<Lagerposten> lagerposten;
	/**
	 * der {@link PrintWriter} f&uuml;r die .log-Datei
	 */
	private PrintWriter writer = null;

	/**
	 * Erstellt ein neues Lager und seine Verwaltung.
	 */
	public Lagerverwaltung() {
		this.berechtigteMitarbeiter = new HashSet<>();
		this.lagerposten = new ArrayList<>();
	}

	/**
	 * Erteilt einem {@link Mitarbeiter} die Berechtigung, das Lager zu verwalten.
	 * 
	 * @param mitarbeiter - Der {@link Mitarbeiter}, der berechtigt werden soll.
	 */
	public void berechtigungErteilen(Mitarbeiter mitarbeiter) {
		if(!berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			berechtigteMitarbeiter.add(mitarbeiter.getId());
			addToLog(mitarbeiter.toString() + " ist nun berechtigt.");
		} else {
			addToLog(mitarbeiter.toString() + " ist schon berechtigt.");
		}
	}

	/**
	 * Entzieht einem {@link Mitarbeiter} die Berechtigung, das Lager zu verwalten.
	 * 
	 * @param mitarbeiter - Der {@link Mitarbeiter}, der nicht mehr berechtigt sein soll.
	 */
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter) {
		if(berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			berechtigteMitarbeiter.remove(mitarbeiter.getId());
			addToLog(mitarbeiter.toString() + " ist nun nicht mehr berechtigt.");
		} else {
			addToLog(mitarbeiter.toString() + " war nie berechtigt.");
		}
	}

	/**
	 * Gibt eine &Uuml;bersicht in der Konsole aus, welcher Artikel wie viel Mal und zu welchem Preis im
	 * Lager vorhanden ist.
	 */
	public void lagerbestandAusgeben() {
		System.out.println("Der Lagerbestand:");
		for (Lagerposten posten : lagerposten) {
			System.out.println("> " + posten.toString());
		}
	}

	/**
	 * Nimmt einen {@link Artikel} mit St&uuml;ckzahl und St&uuml;ckpreis in den Lagerbestand auf.<br>
	 * <br>
	 * Es wird auch gepr&uuml;ft, ob der ausf&uuml;hrende {@link Mitarbeiter} &uuml;berhaupt dazu
	 * berechtigt ist.<br>
	 * Sollte dies nicht der Fall sein, passiert gar nichts.
	 * 
	 * @param mitarbeiter - der ausf&uuml;hrende {@link Mitarbeiter}
	 * @param artikel     - der {@link Artikel} der ins Lager hinzugef&uuml;gt werden soll
	 * @param anzahl      - wie viele St&uuml;ck des {@link Artikel}s ins Lager hinzugef&uuml;gt werden
	 *                    sollen
	 * @param preis       - der Preis pro St&uuml;ck
	 */
	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int anzahl, double preis) {
		Lagerposten lagerposten = new Lagerposten(artikel, anzahl, preis);
		if(berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			addToLog(mitarbeiter.toString() + " hat einen Lagerposten zum Lager hinzugefügt.");
			addToLagerposten(lagerposten);
		} else {
			addToLog(mitarbeiter.toString() + " hat unberechtigt versucht, " + lagerposten.toString() + " zum Lager hinzuzufügen.");
		}
	}

	/**
	 * F&uuml;hrt eine Bestellung aus und entnimmt die darin enthaltenen Artikel in der korrekten Menge
	 * dem Lager.<br>
	 * <br>
	 * Ist zu wenig eines Artikels im Lager vorhanden, kann die gesamte Bestellung nicht ausgef&uuml;hrt
	 * werden.<br>
	 * Das Selbe ist der Fall, wenn der Mitarbeiter nicht berechtigt ist.
	 * 
	 * @param mitarbeiter - der ausf&uuml;hrende {@link Mitarbeiter}
	 * @param bestellung  - Eine Liste der bestellten {@link Artikel} mit ihrer Menge.
	 * @return eine {@link Bestellbestaetigung} die den Gesamtpreis enth&auml;lt und aussagt, ob die
	 *         Bestellung ausgef&uuml;hrt wurde.
	 */
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bestellung) {
		int gesamtpreis = 0;
		Bestellbestaetigung bb = null;
		boolean ausfuehrbar = true;
		List<Lagerposten> zugehoerigeLagerposten = new ArrayList<>();

		String bestellungBeschreibung = "Bestellung[";
		if(bestellung == null || bestellung.size() == 0) {
			bestellungBeschreibung += "]";
			addToLog(mitarbeiter.toString() + " konnte " + bestellungBeschreibung + " nicht ausführen, da sie leer ist.");
			return new Bestellbestaetigung(false, 0);
		} else { // bestellung.size() > 0
			bestellungBeschreibung += bestellung.get(0).toString();
			if(bestellung.size() > 1) {
				for (Bestellposten bp : bestellung) {
					bestellungBeschreibung += ", " + bp.toString();
				}
			}
			bestellungBeschreibung += "]";
		}

		for (Bestellposten bp : bestellung) { // prüft, ob Bestellung ausführbar
			boolean zugehoerigerLagerpostenGefunden = false;
			for (Lagerposten lp : lagerposten) {
				if(bp.getArtikelId().equals(lp.getArtikel().getId())) {
					zugehoerigerLagerpostenGefunden = true; // jeder Bestellposten muss zugehörigen Lagerposten haben
					if(bp.getAnzahl() > lp.getLagerbestand()) { // und es müssen genügend Stück jedes Artikels vorhanden sein
						ausfuehrbar &= false;
					} else {
						zugehoerigeLagerposten.add(lp);
					}
				}
			}
			ausfuehrbar &= zugehoerigerLagerpostenGefunden;
		}

		if(ausfuehrbar) {
			if(berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
				for (Bestellposten bp : bestellung) {
					for (Lagerposten lp : zugehoerigeLagerposten) {
						if(bp.getArtikelId().equals(lp.getArtikel().getId())) {
							gesamtpreis += bp.getAnzahl() * lp.getPreis();
							lp.setLagerbestand(lp.getLagerbestand() - bp.getAnzahl());
							if(lp.getLagerbestand() == 0) {
								this.lagerposten.remove(lp);
							}
							bb = new Bestellbestaetigung(true, gesamtpreis);
							break;
						}
					}
				}
				addToLog(mitarbeiter.toString() + " hat " + bestellungBeschreibung + " ausgeführt.");
			} else {
				bb = new Bestellbestaetigung(false, 0);
				addToLog(mitarbeiter.toString() + " hat unberechtigt versucht, " + bestellungBeschreibung + " auszuführen.");
			}
		} else {
			bb = new Bestellbestaetigung(false, 0);
			addToLog(mitarbeiter.toString() + " konnte " + bestellungBeschreibung
					+ " nicht ausführen, da Artikel fehlen oder nicht in ausreichender Menge vorhanden sind.");
		}
		return bb;
	}

	/**
	 * F&uuml;gt einen {@link Lagerposten} zum Lagerbestand hinzu.<br>
	 * <br>
	 * Wenn der {@link Artikel} des {@link Lagerposten}s schon im Lager vorhanden ist, wird dieser
	 * {@link Lagerposten} zum Lagerbestand hinzugef&uuml;gt und die St&uuml;ckzahl des
	 * {@link Lagerposten}s um die Anzahl der schon im Lager existierenden {@link Artikel}
	 * erh&ouml;ht.<br>
	 * Alle anderen Lagerposten werden aus dem Lagerbestand gel&ouml;scht.<br>
	 * Das hei&szlig;t, dass es für jeden Artikel nur einen Lagerposten gibt.
	 * 
	 * @param neuerLagerposten - Der hinzuzuf&uuml;gende {@link Lagerposten}.
	 */
	public void addToLagerposten(Lagerposten neuerLagerposten) {
		List<Lagerposten> existierendePosten = new ArrayList<>();
		Lagerposten editierterLagerposten = new Lagerposten(neuerLagerposten.getArtikel(), neuerLagerposten.getLagerbestand(),
				neuerLagerposten.getPreis());
		int gesamtLagerbestand = neuerLagerposten.getLagerbestand();
		for (Lagerposten posten : this.lagerposten) { // speichert alle Lagerposten mit dem selben Artikel ab.
			if(posten.getArtikel().equals(neuerLagerposten.getArtikel())) {
				existierendePosten.add(posten);
				gesamtLagerbestand += posten.getLagerbestand();
			}
		}
		editierterLagerposten.setLagerbestand(gesamtLagerbestand); // Lagerposten werden zusammengefügt

		if(existierendePosten.size() >= 1) { // existierende Lagerposten werden aus this.lagerposten gelöscht
			for (Lagerposten posten : existierendePosten) {
				this.lagerposten.remove(posten);
			}
		} // andernfalls gibt es keine anderen Lagerposten mit dem selben Artikel

		this.lagerposten.add(editierterLagerposten);
		addToLog(neuerLagerposten.toString() + " wurde zum Lager hinzugefügt und Lagerposten mit gleichem Artikel gelöscht. ("
				+ (gesamtLagerbestand - neuerLagerposten.getLagerbestand()) + " waren schon im Lager)");
	}

	/**
	 * F&uuml;gt eine Nachricht zum Log hinzu.
	 * 
	 * @param message - die Nachricht
	 */
	private void addToLog(String message) {
		// sources:
		// https://stackoverflow.com/questions/26717733/print-current-date-in-java
		// https://javabeginners.de/Dateien_und_Verzeichnisse/In_Textdatei_schreiben.php
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			writer = new PrintWriter(new FileWriter("lagerverwaltung.log", true), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.println(dateFormat.format(date) + "> " + message);
		writer.close();
	}

	public Set<String> getBerechtigteMitarbeiter() {
		return berechtigteMitarbeiter;
	}

	public List<Lagerposten> getLagerposten() {
		return lagerposten;
	}
}
