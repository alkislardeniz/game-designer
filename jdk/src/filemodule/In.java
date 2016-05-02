package filemodule;

import filemodule.FileIO;

import java.io.*;

/**
 * In.java
 * @author Mehmet Can Altuntaş
 * @version 28 April 2016
 */
public class In extends FileIO
{

    //properties

    //constructors

    /**
     * Takes a file as a parameter and opens it
     * @param file
     */
    public In(File file) {
        this.file = file;
        path = file.getPath();
    }

    /**
     * Takes a path as a parameter, finds the file in it and opens it
     * @param path
     */
    public In(String path)
    {
        file = new File(path);
        this.path = path;
    }

    //methods

    /**
     * Deserializes the object
     * @return deserialized object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object deserializeFile() throws IOException, ClassNotFoundException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

        return ois.readObject();
    }
}
