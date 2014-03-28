package slap;
import java.util.* ;
import java.io.* ;

/**
 * A manager for any class that implements Manageable
 */
@SuppressWarnings("rawtypes")
public class Manager<C extends Comparable, M extends Managable> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private TreeMap<C, M> items ;

	/**
	 * Make a new manager with a treemap
	 */
    public Manager()
    {
        items = new TreeMap<C, M>() ;
    }

    /**
     * Get the Managable item corresponding to the Comparable id
     * @param id the id of the Managable that you are searching for
     * @return the Managable to which the id corresponds
     */
    public Managable get(C id)
    {
        return items.get(id) ;
    }

    /**
     * Add a new Managable with a Comparable id
     * @param c the id of the Managable to add
     * @param m the Managable to add
     * @return whether or not the item was added (not added if id already exists)
     */
    public boolean add(C c, M m)
    {   
        if(items.containsKey(c))
        {
            return false ;
        }
        items.put(c, m) ;        
        return true ;
    } 

    /**
     * Remove a Managable by Comparable id
     * @param id the id of the Managable to remove
     * @return whether or not the Managable was removed (if it exists)
     */
    public boolean remove(C id)
    {
        if(!items.containsKey(id))
        {
            return false ;
        }
        items.remove(id) ;
        return true ;
    }

    /**
     * Check if a Managable with Comparable id exists in the map
     * @param id the id of the Managable for which to check
     * @return whether or not the Managable with Comparabel id exists
     */
    public boolean contains(C id)
    {
        return items.containsKey(id) ;
    }

    /**
     * Get the size of the map
     * @return the size of the map
     */
    public int size()
    {
        return items.size() ;
    }

    /**
     * Get the 
     * @return
     */
    public Comparable[] getIDArray()
    {
		/*ArrayList<Comparable> idArray = new ArrayList<Comparable>() ;
        idArray.addAll(items.keySet()) ;
        return idArray ;*/
    	Comparable[] idArray = new Comparable[size()] ;
        int count = 0 ;
        for(Map.Entry<C, M> entry : items.entrySet()) 
        {
            idArray[count++] = (Comparable) entry.getKey() ;
        }
        return idArray ;
    }

    public Managable[] getItemArray()
    {
        Managable[] itemArray = new Managable[size()] ;
        int count = 0 ;
        for(Map.Entry<C, M> entry : items.entrySet()) 
        {
            itemArray[count++] = (Managable) entry.getValue() ;
        }
        return itemArray ;
    }
}
