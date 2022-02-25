/** This Class opens a user interface that searchs and allows users
 * to select a desired txt file.
 */


import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class txtSelectorinterface{


    String txtFileName;

    
    txtSelectorinterface(){

    File dir = new File(System.getProperty("user.dir"));
    LinkedList<String> textFiles = new LinkedList<String>();
    for(File file: dir.listFiles()){
        if(file.getName().endsWith((".txt"))){
            textFiles.add(file.getName());
        }
    }

    String [] contents = textFiles.toArray(new String[textFiles.size()]);

   
    JFrame txtFrame = new JFrame("Interactive Quiz");
    txtFrame.setSize(250,200);
    txtFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    FlowLayout flowLay = new FlowLayout(FlowLayout.CENTER);
    flowLay.setHgap(100);
    txtFrame.setLayout(flowLay);

    JLabel txtLabel = new JLabel(" Plsease select a txt file and hit confirm ");
    txtFrame.add(txtLabel);

    JComboBox<String> comboBox = new JComboBox<String>(contents);
    txtFrame.add(comboBox);

    JButton txtButton = new JButton("Confirm");
    txtFrame.add(txtButton);
    txtFrame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    txtFrame.setVisible(true);


    txtButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            txtFileName = (String) comboBox.getSelectedItem();
            txtFrame.dispose();
            setTxtName(txtFileName);

        }
     });
    
}

public void setTxtName(String txtss){
    txtFileName = txtss;
}

public boolean getClickedCombo(){
    boolean clickedCombo =false;
    if(txtFileName!=null){
        clickedCombo = true;
    }
    return clickedCombo;
}

public synchronized String getTxtName(){
    return this.txtFileName;
}

}
