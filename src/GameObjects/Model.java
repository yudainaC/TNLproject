package GameObjects;

/**
 * Model pour toutes les Objets du jeu, entités et actions compris.
 */
public class Model {

	protected String name;
	protected String description;

	// Constructeur
	public Model(String itName, String itDescription) {
		this.name = itName;
		this.description = itDescription;
	}

	// Getters
	public String getName() { return this.name; }
	public String getDescription() { return this.description; }
	
}
