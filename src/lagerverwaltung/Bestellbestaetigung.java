package lagerverwaltung;

/**
 * Die Angabe �ber den Erfolg bei der Ausf�hrung einer Bestellung. 
 */
public class Bestellbestaetigung {

	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	/**
	 * Erstellt eine Best�tigung, ob eine Bestellung erfolgreich ausgef�hrt werden konnte.
	 * 
	 * @param ausgefuehrt - Wurde die Bestellung erfolgreich ausgef�hrt?
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
