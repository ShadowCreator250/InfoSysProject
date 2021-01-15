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
	 * Wenn der {@link Artikel} schon vorhanden ist, wird der Preis dessen aktualisiert, aber der
	 * {@link Artikel} als separater {@link Lagerposten} aufgenommen.<br>
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
			addToLog(mitarbeiter.toString() + " hat unberechtigt versucht " + lagerposten.toString() + " zum Lager hinzuzufügen.");
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
		if(berechtigteMitarbeiter.contains(mitarbeiter.getId())) {
			for(Bestellposten bp: bestellung) {
				for(Lagerposten lp: lagerposten) {
					if(bp.getArtikelId().equals(lp.getArtikel().getId()) && bp.getAnzahl() <= lp.getLagerbestand()) {
						gesamtpreis += bp.getAnzahl() * lp.getPreis();
						lp.setLagerbestand(lp.getLagerbestand() - bp.getAnzahl());
						bb = new Bestellbestaetigung(true, gesamtpreis);
					}
					else if(bp.getArtikelId().equals(lp.getArtikel().getId()) && bp.getAnzahl() > lp.getLagerbestand()) {
						gesamtpreis = 0;
						bb = new Bestellbestaetigung(false, gesamtpreis);
						break;
					}
				}
			}	
		}
		else {
			bb = new Bestellbestaetigung(false, 0);
			addToLog(mitarbeiter.toString() + " hat unberechtigt versucht, eine Bestellung auszuführen.");
		}
		if(bb.isAusgefuehrt()) {
			addToLog(mitarbeiter.toString() + " hat eine Bestellung ausgeführt.");
		}
		else {
			addToLog(mitarbeiter.toString() + " konnte die Bestellung nicht ausführen.");
		}
		return bb;
	}

	/**
	 * F&uuml;gt einen {@link Lagerposten} zum Lagerbestand hinzu. Wird benutzt um das Lager zu
	 * bef&uuml;llen, ohne das ein {@link Mitarbeiter} ben&ouml;tigt wird.
	 * 
	 * @param lagerposten - Der hinzuzuf&uuml;gende {@link Lagerposten}.
	 */
	public void addToLagerposten(Lagerposten lagerposten) {
		for (Lagerposten posten : this.lagerposten) {
			if(posten.getArtikel().getId().equals(lagerposten.getArtikel().getId())) {
				posten.setPreis(lagerposten.getPreis());
			}
		}
		this.lagerposten.add(lagerposten);
		addToLog(lagerposten.toString() + " wurde zum Lager hinzugefügt und alle Preise aktualisiert.");
	}

	/**
	 * F&uuml;gt eine Nachricht zum Log hinzu.
	 * 
	 * @param message - die Nachricht
	 */
	private void addToLog(String message) {
		// source:
		// https://stackoverflow.com/questions/26717733/print-current-date-in-java
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		try {
			writer = new PrintWriter(new FileWriter("lagerverwaltung.log", true), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		writer.println(dateFormat.format(date) + "> " + message);
		writer.close();
		//source: https://javabeginners.de/Dateien_und_Verzeichnisse/In_Textdatei_schreiben.php
	}

	public Set<String> getBerechtigteMitarbeiter() {
		return berechtigteMitarbeiter;
	}

	public List<Lagerposten> getLagerposten() {
		return lagerposten;
	}
}
