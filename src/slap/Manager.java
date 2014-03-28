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

    public Manager()
    {
        items = new TreeMap<C, M>() ;
    }

    public Managable get(C id)
    {
        return items.get(id) ;
    }

    public boolean add(C c, M m)
    {   
        if(items.containsKey(c))
        {
            return false ;
        }
        items.put(c, m) ;        
        return true ;
    } 

    public boolean remove(C id)
    {
        if(!items.containsKey(id))
        {
            return false ;
        }
        items.remove(id) ;
        return true ;
    }

    public boolean contains(C id)
    {
        return items.containsKey(id) ;
    }

    public int size()
    {
        return items.size() ;
    }

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
