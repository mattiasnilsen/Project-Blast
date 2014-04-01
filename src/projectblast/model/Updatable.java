package projectblast.model;
/**
 * Common interface for all updatable classes in project blast.
 * @author Mattias Nilsen
 */
public interface Updatable {
    /**
     * Called on an object every tick to update it.
     */
	public void update();
}
