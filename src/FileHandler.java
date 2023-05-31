import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler {
    public Set<Path> readDirectory(String dir) throws IOException {
        Set<Path> files=new HashSet<>();
        try{
            DirectoryStream<Path> stream= Files.newDirectoryStream(Paths.get(dir));
            for(Path path : stream){
                if(!Files.isDirectory(path)){
                    files.add(path);
                }
            }
        }
        catch (IOException ioException){
            System.out.println(ioException);
        }
        return files;
    }
    public ArrayList<String> getFileNames(ArrayList<Path> files){
        ArrayList<String> fileNames=new ArrayList<>();
        for(Path path:files){
            fileNames.add(path.getFileName().toString());
        }
        return fileNames;
    }
    public String readFile(String input){
        String out="";
        File file=new File(input);
        try{
            Scanner reader=new Scanner(file);

            while(reader.hasNextLine()){
                out+=reader.nextLine().toString()+"\n";
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException);
        }

        return out;
    }
    FileHandler(){

    }

}
