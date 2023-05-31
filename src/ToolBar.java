import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.prefs.BackingStoreException;

public class ToolBar extends JToolBar{
    JButton btnM0,btnM1,btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    AppSettings appSettings;
    FileChooser fileChooser;
    ToolBar(JMenuBar mb){
        JToolBar tlb=this;

        btnM0=new JButton("\u2630");
        btnM0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tlb.setVisible(!mb.isVisible());
            }
        });

        btnM1=new JButton("\u2756");
        btnM1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tlb.setFloatable(!tlb.isFloatable());
            }
        });

        btn1=new JButton("Otwórz");
        btn2=new JButton("Zapisz");
        btn3=new JButton("Kopiuj");
        btn4=new JButton("Wklej");
        btn5=new JButton("Otwórz katalog");
        btn5.setIcon(new ImageIcon(""));

        btn6=new JButton("Preferencje");

        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(appSettings==null || !appSettings.isVisible()){
                    try {
                        appSettings=new AppSettings(true);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (BackingStoreException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        btn7=new JButton("Archiwizuj");

        this.setFloatable(false);
        this.setRollover(true);
        this.add(btnM0);
        this.add(btnM1);
        this.add(btn1);
        this.add(btn2);
        this.addSeparator();
        this.add(btn3);
        this.add(btn4);
        this.add(btn5);
        this.add(btn6);
        this.add(btn7);
    }

}
