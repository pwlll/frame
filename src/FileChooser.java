import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileChooser extends JFileChooser {
    JFileChooser fileChooser;
    String[] selectedDirectory=new String[1];
    public FileChooser(){
        fileChooser=new JFileChooser(new File("."));

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Wybierz katalog");


        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            selectedDirectory[0]=fileChooser.getSelectedFile().getAbsolutePath();
        }

        this.setPreferredSize(new Dimension(400,600));
        this.setMinimumSize(new Dimension(300,450));
    }
}
