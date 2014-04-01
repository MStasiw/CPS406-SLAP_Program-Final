package slap;

import java.io.* ;

/**
 * A generic class to read and write objects to a file defined by a relative path
 */
public class ObjectIO
{
    private static FileInputStream fis ;
    private static FileOutputStream fos ;
    private static ObjectInputStream ois ;
    private static ObjectOutputStream oos ;

    public static Object objectIn(String dir, String file)
    {
        try
        {
        	new File(dir).mkdir() ;
            fis = new FileInputStream(dir + "/" + file) ;
            ois = new ObjectInputStream(fis) ;
            Object obj = ois.readObject() ;
            ois.close() ;
            fis.close() ;
            return obj ;
        }
        catch(Exception e)
        {
        	System.err.println(e) ;
        	System.err.println("Stack Trace:");
        	e.printStackTrace();
            return null ;
        }
    }

    public static boolean objectOut(String dir, String file, Object obj)
    {
        try
        {
        	new File(dir).mkdir() ;
            fos = new FileOutputStream(dir + System.getProperty("file.separator")  + file) ;
            oos = new ObjectOutputStream(fos) ;
            oos.writeObject(obj) ;
            oos.close() ;
            fos.close() ;
            return true ;
        }
        catch(Exception e)
        {
        	System.err.println(e) ;
        	System.err.println("Stack Trace:");
        	e.printStackTrace();
            return false ;
        }
    }
}
