package GameObjects;


public class Model {
	protected String name;
	protected String description;
	
	public Model(String itName, String itDescription) {
		this.name = itName;
		this.description = itDescription;
	}
	
	public String getName() { return this.name; }
	public String getDescription() { return this.description; }
	
}
