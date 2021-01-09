package lagerverwaltung;

/**
 * Die Angabe &uuml;ber den Erfolg bei der Ausf&uuml;hrung einer Bestellung.
 */
public class Bestellbestaetigung {

	private final boolean ausgefuehrt;
	private final double gesamtpreis;

	/**
	 * Erstellt eine Best&auml;tigung, ob eine Bestellung erfolgreich ausgef&uuml;hrt werden konnte.
	 * 
	 * @param ausgefuehrt - Wurde die Bestellung erfolgreich ausgef&uuml;hrt?
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
