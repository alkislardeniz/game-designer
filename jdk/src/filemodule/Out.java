package filemodule;

import filemodule.FileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Out.java
 * @author Mehmet Can Altuntaş
 * @version 28 April 2016
 */
public class Out extends FileIO
{

    //properties
    private Object object;

    //constructors

    public Out(File file, Object object)
    {
        this.file = file;
        path = file.getAbsolutePath();
        this.object = object;
    }

    /**
     * Takes the save path and the object as parameters
     * @param path save path
     * @param object class to be saved
     */
    public Out(String path, Object object)
    {
        file = new File(path);
        this.path = path;
        this.object = object;
    }


    //methods

    /**
     * Serializes the given object when called.
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void serializeFile() throws ClassNotFoundException, IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(object);
        oos.flush();
    }
}
