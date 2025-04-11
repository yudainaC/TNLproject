package GameObjects.GameElements.Items;
import GameObjects.Model;

/**
 * Le model plus spécifique aux objets récupérables. Super-Classe.
 */
public class Item extends Model {

    // Constructeur
    public Item(String itName, String itDescription) {
        super(itName, itDescription);
    }
}
