package filemodule.tests;

import filemodule.In;
import gamemodel.Game;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Mehmet on 29/04/16.
 */
public class RegameTest
{
    public static void main(String[] args)
    {
        In in = new In("save.dadamsave");

        Game obj = null;

        try
        {
            obj = (Game) in.deserializeFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        System.out.println(obj);
    }
}
