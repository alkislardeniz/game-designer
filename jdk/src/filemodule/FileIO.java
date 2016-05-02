package filemodule;

import java.io.File;

/**
 * FileIO.java
 * @author Mehmet Can Altuntaş
 * @version 28 April 2016
 */

public abstract class FileIO {

    File file;
    String path;

    public String getPath()
    {
        if (path != null)
        {
            return path;
        }

        return file.getPath();
    }

    public String getFileExtension()
    {
        int lastIndex;
        if (path != null)
        {
            lastIndex = path.lastIndexOf('.');
            return path.substring(lastIndex + 1);
        }

        String tmp = file.getPath();
        lastIndex = tmp.lastIndexOf('.');
        return tmp.substring(lastIndex + 1);
    }
}
