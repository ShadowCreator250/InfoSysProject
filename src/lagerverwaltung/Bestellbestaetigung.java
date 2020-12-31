package lagerverwaltung;

/**
 * Die Angabe über den Erfolg bei der Ausführung einer Bestellung. 
 */
public class Bestellbestaetigung {

	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	/**
	 * Erstellt eine Bestätigung, ob eine Bestellung erfolgreich ausgeführt werden konnte.
	 * 
	 * @param ausgefuehrt - Wurde die Bestellung erfolgreich ausgeführt?
	 * @param gesamtpreis - Der Gesamtpreis der Bestellung.
	 */
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis) {
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
	}

	public boolean isAusgefuehrt() {
		return ausgefuehrt;
	}

	public double getGesamtpreis() {
		return gesamtpreis;
	}

}
