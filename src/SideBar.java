import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class SideBar extends JPanel implements ListSelectionListener {
    DefaultListModel data;
    JList list;

    SideBar(BorderLayout layout){
        this.setLayout(layout);
        data= new DefaultListModel();
        list=new JList(data);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);

        list.setSelectedIndex(2);
        list.setVisibleRowCount(4);
        list.addListSelectionListener(this);

        JScrollPane scrpList=new JScrollPane(list);

        this.add(scrpList);

    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
            Object src=event.getSource();

            if(src==list){
                if(!event.getValueIsAdjusting()){
                    if(list.getSelectedIndex()!=-1){
                        System.out.println(data.getElementAt(list.getSelectedIndex()).toString());
                    }
                }
            }
    }
}
