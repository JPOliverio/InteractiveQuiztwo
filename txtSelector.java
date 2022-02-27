/** This Class opens a user interface that searchs and allows users
 * to select a desired txt file.
 */



import java.io.File;
import java.util.LinkedList;

public class txtSelector{

    String [] contents;

    
    txtSelector(){
    File dir = new File(System.getProperty("user.dir"));
    LinkedList<String> textFiles = new LinkedList<String>();
    for(File file: dir.listFiles()){
        if(file.getName().endsWith((".txt"))){
            textFiles.add(file.getName());
        }
    }

    contents = textFiles.toArray(new String[textFiles.size()]);
}

public synchronized String[] getTxt(){
    return contents;
}

public int getNumberOfTxt(){
    return contents.length;
}

public String getTxtName(int j){
    return contents[j];
}

public void printTxtNames(){
    for(int i=0; i<contents.length; i++){
        System.out.println(i+") "+contents[i]);
    }

}

}