import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Frame{
        JFrame frame;
        FileChooser fileChooser;
        String[] selectedDirectory = new String[1];


    Frame() throws IOException {
            FileHandler fileHandler=new FileHandler();


            frame = new JFrame("sIDE");
            frame.setLayout(new BorderLayout());

            JPanel menuPanel=new JPanel();
            menuPanel.setLayout(new BorderLayout());
            MainMenu mainMenu=new MainMenu(new JFrame(),new JPanel());
            ToolBar toolBar=new ToolBar(new JMenuBar());



            menuPanel.add(mainMenu,BorderLayout.PAGE_START);

            frame.add(menuPanel,BorderLayout.PAGE_START);


            JTextArea textArea1=new JTextArea(150,50);
            JScrollPane taScrollPane1=new JScrollPane(textArea1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JTextArea textArea2=new JTextArea(150,50);
            JScrollPane taScrollPane2=new JScrollPane(textArea2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JTabbedPane tabbedPane=new JTabbedPane();
            tabbedPane.addTab("pane 1",taScrollPane1);
            tabbedPane.addTab("pane 2",taScrollPane2);
            frame.add(tabbedPane,BorderLayout.EAST);


            SideBar sideBar=new SideBar(new BorderLayout());

            final int[] selectedFile=new int[1];

            sideBar.list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    selectedFile[0]=sideBar.list.getSelectedIndex();
                }
            });

            sideBar.setPreferredSize(new Dimension(400,500));
            JScrollPane scrollPane=new JScrollPane(sideBar,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            frame.add(scrollPane,BorderLayout.WEST);




            ArrayList<Path> filePaths=new ArrayList<>();

        toolBar.btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea1.setText(fileHandler.readFile(filePaths.get(selectedFile[0]).toString()));
            }
        });
            toolBar.btn5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    fileChooser=new FileChooser();
                    selectedDirectory[0]=fileChooser.selectedDirectory[0];
                    try {
                        sideBar.data.clear();
                        filePaths.clear();
                        filePaths.addAll(fileHandler.readDirectory(selectedDirectory[0]));
                        sideBar.data.addAll(fileHandler.getFileNames(filePaths));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Archive archive=new Archive();
            toolBar.btn7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String fList=new String();
                    for(int i=0;i<filePaths.size();i++){
                        fList+=filePaths.get(i)+",";
                    }
                    fList+=new File("").getAbsolutePath()+"/prefs.xml";
                    try {
                        archive.pack("archive",fList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            menuPanel.add(toolBar,BorderLayout.PAGE_END);


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(1080,720));
            frame.setVisible(true);
        }
}
