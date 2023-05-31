import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.HashMap;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class AppSettings extends JDialog {
    JPanel panel1,panel2,panel3,panel4;
    JLabel label1Panel1,label1Panel3;
    JCheckBox[] oknoCheckboxes,IdeCheckboxes,GuiCheckboxes;
    JComboBox languageSelect;
    JButton odrzucButton,zachowajButton;

    Preferences prefs;
    HashMap pf;
    AppSettings() throws FileNotFoundException, BackingStoreException {
        this.prefs=Preferences.userRoot().node("prefs");
        this.pf=new HashMap<String,String>();
        CreateAndShowGUI();

        this.setVisible(true);
    }
    AppSettings(boolean showGUI) throws FileNotFoundException, BackingStoreException {
        this.prefs=Preferences.userRoot().node("prefs");
        this.pf=new HashMap<String,String>();
        CreateAndShowGUI();

        this.setVisible(showGUI);
    }

    private void CreateAndShowGUI() throws FileNotFoundException, BackingStoreException {
        panel1=new JPanel(new GridLayout(1,3));
        label1Panel1=new JLabel("Dostępne ustawienia");
        odrzucButton=new JButton("Odrzuć");
        zachowajButton=new JButton("Zachowaj");
        panel1.add(label1Panel1);
        panel1.add(odrzucButton);
        panel1.add(zachowajButton);
        this.add(panel1);


        panel2=new JPanel(new GridLayout(4,1));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Okno"));
        oknoCheckboxes=new JCheckBox[]{new JCheckBox("uruchom wyśrodkowanie"),new JCheckBox("uruchom zmaksymalizowanie"),new JCheckBox("zapamiętaj pozycje"),new JCheckBox("zapamiętaj rozmiar")};
        for(JCheckBox checkBox:oknoCheckboxes){
            panel2.add(checkBox);
        }

        this.add(panel2);

        panel3=new JPanel(new GridLayout(4,1));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"IDE"));
        label1Panel3=new JLabel("język intefejsu");
        panel3.add(label1Panel3);

        languageSelect=new JComboBox();
        languageSelect.addItem("Polski");
        languageSelect.addItem("English");

        panel3.add(languageSelect);

        IdeCheckboxes=new JCheckBox[]{new JCheckBox("zapamiętaj katalog przeglądania"),new JCheckBox("zapamiętaj otwarte pliki")};
        for(JCheckBox checkBox:IdeCheckboxes){
            panel3.add(checkBox);
        }
        this.add(panel3);


        panel4=new JPanel(new GridLayout(4,1));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"GUI"));
        GuiCheckboxes=new JCheckBox[]{new JCheckBox("ukryj menu główne"),new JCheckBox("odpięty pasek narzędziowy"),new JCheckBox("zablokowany pasek narzędziowy"),new JCheckBox("tryb ciemny")};
        for(JCheckBox checkBox:GuiCheckboxes){
            panel4.add(checkBox);
        }

        this.add(panel4);
        readPrefs();
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                closeWindow();
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

        this.setLayout(new GridLayout(4,1));
        this.setPreferredSize(new Dimension(400,600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }
    private void closeWindow(){
        try {
            storePrefs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }

    private void storePrefs() throws IOException, BackingStoreException {
        try{
            FileOutputStream fos=new FileOutputStream("prefs.xml");
            for(JCheckBox checkBox:this.oknoCheckboxes){
                this.prefs.put(checkBox.getText(),String.valueOf(checkBox.isSelected()));
            }
            for(JCheckBox checkBox:this.IdeCheckboxes){
                this.prefs.put(checkBox.getText(),String.valueOf(checkBox.isSelected()));
            }
            this.prefs.put(label1Panel3.getText(),String.valueOf(languageSelect.getSelectedItem()));
            for(JCheckBox checkBox:this.GuiCheckboxes){
                this.prefs.put(checkBox.getText(),String.valueOf(checkBox.isSelected()));
            }
            this.prefs.exportNode(fos);
            fos.close();
        }
        catch(IOException ioException){
            System.out.println(ioException);
        }
        catch (BackingStoreException backingStoreException){
            System.out.println(backingStoreException);
        }
        catch (NullPointerException nullPointerException){
            System.out.println(nullPointerException);
        }
    }

    private void readPrefs() throws FileNotFoundException, BackingStoreException {
        FileInputStream inputStream = new FileInputStream("prefs.xml");
        String[] oknoCheckboxKeys={
                "uruchom wyśrodkowanie",
                "uruchom zmaksymalizowanie",
                "zapamiętaj pozycje",
                "zapamiętaj rozmiar"
        };
        String[] ideCheckboxKeys={
                "zapamiętaj katalog przeglądania",
                "zapamiętaj otwarte pliki"
        };
        String[] guiCheckboxKeys={
                "ukryj menu główne",
                "odpięty pasek narzędziowy",
                "zablokowany pasek narzędziowy",
                "tryb ciemny"
        };

        for (int i = 0;i<oknoCheckboxes.length;i++){oknoCheckboxes[i].setSelected(this.prefs.getBoolean(oknoCheckboxKeys[i],false));}

        for (int i = 0;i<IdeCheckboxes.length;i++) {IdeCheckboxes[i].setSelected(this.prefs.getBoolean(ideCheckboxKeys[i],false));}

        for (int i = 0;i<GuiCheckboxes.length;i++) {GuiCheckboxes[i].setSelected(this.prefs.getBoolean(guiCheckboxKeys[i],false));}
    }
}
