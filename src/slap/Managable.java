package slap;

/**
 *  Interface to define a manageable item which can be used by a Manager of the same type
 */
public interface Managable
{
	/**
	 * Get the id of the Managable item
	 * @return the id of the item
	 */
    @SuppressWarnings("rawtypes")
	public Comparable getID() ;
}
