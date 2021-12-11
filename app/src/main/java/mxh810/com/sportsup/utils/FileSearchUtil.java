package mxh810.com.sportsup.utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class FileSearchUtil {
    /**
     * Search a directory and return a list of all **directories** contained inside
     * @param directory
     * @return
     */
    public static ArrayList<String> getDirectoryPaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        Log.d("dir", directory);
        File file = new File(directory);
        File[] listFiles = file.listFiles();
        if (null != listFiles){
            for(int i = 0; i < listFiles.length; i++){
                if(listFiles[i].isDirectory()){
                    pathArray.add(listFiles[i].getAbsolutePath());
                }
            }
        }

        return pathArray;
    }

    /**
     * Search a directory and return a list of all **files** contained inside
     * @param directory
     * @return
     */
    public static ArrayList<String> getFilePaths(String directory){
        ArrayList<String> pathArray = new ArrayList<>();
        File file = new File(directory);
        File[] listFiles = file.listFiles();

        if (null != listFiles){
            for(int i = 0; i < listFiles.length; i++){
                if(listFiles[i].isFile()){
                    pathArray.add(listFiles[i].getAbsolutePath());
                }
            }
        }

        return pathArray;
    }
}
