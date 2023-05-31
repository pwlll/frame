import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainMenu extends JMenuBar implements ActionListener {
    JFrame box;
    JPanel pnl;
    JMenu menu,submenu;
    JMenuItem mi1,mi2,mi3,mi4,mi5,mi6;
    MainMenu(JFrame box,JPanel pnl){
        this.box=box;
        this.pnl=pnl;
        menu=new JMenu("Plik");
        menu.setMnemonic(KeyEvent.VK_P);
        submenu=new JMenu("TestMenu1");
        mi1=new JMenuItem("Opcja 1");
        mi2=new JMenuItem("Opcja 2");
        mi3=new JMenuItem("Opcja 3");
        mi4=new JMenuItem("Opcja 4");
        mi5=new JMenuItem("Opcja 5");
        mi6=new JMenuItem("Opcja 6");

        mi1.addActionListener(this);
        mi3.addActionListener(this);
        mi5.addActionListener(this);

        menu.add(mi1);
        menu.add(mi2);
        menu.add(mi3);
        menu.add(mi4);
        menu.add(mi5);
        menu.add(mi6);
        menu.add(submenu);

        this.add(menu);
    }
    public void actionPerformed(ActionEvent e){
        Object src=e.getSource();

        if(src==mi1){
            pnl.setBackground(Color.RED);
        }
        if(src==mi2){
            pnl.setBackground(Color.GREEN);
        }
        if(src==mi3){
            pnl.setBackground(Color.BLUE);
        }
        if(src==mi4){
            pnl.setBackground(Color.WHITE);
        }
        if(src==mi5){
            box.dispose();
        }
    }


}
