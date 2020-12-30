package lagerverwaltung;

public class Artikel {

	private final String id;
	private String name;
	private String beschreibung;

	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Artikel#" + id + "[" + name + " ~ " + beschreibung + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Artikel)) {
			return false;
		}
		Artikel artikel = (Artikel) obj;
		if(!(id.equals(artikel.id))) {
			return false;
		}
//		if(!(name.equals(artikel.name))) {
//			return false;
//		}
//		if(!(name.equals(artikel.name))) {
//			return false;
//		}
		return true;
	}
}
