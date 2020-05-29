import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class MainFrame extends JFrame {
Container c;
JButton btnAdd, btnView, btnUpdate, btnDelete;

MainFrame() {
c = getContentPane();
c.setLayout(new GridBagLayout());
JLabel lbltitle;
 
lbltitle= new JLabel("Employee Management System");
lbltitle.setFont(new Font("Arial", Font.BOLD, 40));
lbltitle.setOpaque(true);
lbltitle.setBackground(Color.ORANGE);
JLabel background;
ImageIcon img=new ImageIcon("new2.jpg");
background = new JLabel("",img,JLabel.CENTER);
 background.setPreferredSize(new Dimension(600, 450));


btnAdd = new JButton("Add");
btnView = new JButton("View");
btnUpdate = new JButton("Update");
btnDelete = new JButton("Delete");
btnAdd.setPreferredSize(new Dimension(120, 45));
btnAdd.setFont(new Font("Arial", Font.BOLD, 40));
  btnAdd.setBackground(Color.ORANGE);


btnView.setPreferredSize(new Dimension(150, 45));
btnView.setFont(new Font("Arial", Font.BOLD, 40));
 btnView.setBackground(Color.ORANGE);

btnUpdate.setPreferredSize(new Dimension(200, 45));
btnUpdate.setFont(new Font("Arial", Font.BOLD, 40));

 btnUpdate.setBackground(Color.ORANGE);
btnDelete.setPreferredSize(new Dimension(170, 45));
btnDelete.setFont(new Font("Arial", Font.BOLD, 40));
 btnDelete.setBackground(Color.ORANGE);
  GridBagConstraints m = new GridBagConstraints();

        m.gridx = 0;//set the x location of the grid for the next component
        m.gridy = 0;//set the y location of the grid for the next component
        
c.add(lbltitle,m);
m.gridy = 1;
c.add(background,m);
m.gridx = 0;
        m.gridy = 2;//change the y location
 m.insets = new Insets(5, 0, 5, 0);
        m.anchor=GridBagConstraints.CENTER;//left align components after this point
        c.add(btnAdd,m);

        m.gridy = 3;//change the y location
        c.add(btnView,m);

        m.gridy = 4;//change the y location
        c.add(btnUpdate,m);

        m.gridy = 5;//change the y location
        c.add(btnDelete,m);



      

        
//c.add(btnAdd);
//c.add(btnView);
//c.add(btnUpdate);
//c.add(btnDelete);
//c.add(background);
btnAdd.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
AddFrame a = new AddFrame();
dispose();
} } );


btnView.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
ViewFrame a = new ViewFrame();
dispose();
} } );

btnUpdate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
UpdateFrame a = new UpdateFrame();
dispose();
} } );

btnDelete.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
DeleteFrame a = new DeleteFrame();
dispose();
} } );



Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
pack();
setSize(screenSize.width,screenSize.height);
setTitle("E. M. S");

setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}


public static void main(String args[]) {
MainFrame m = new MainFrame();
} 
}  //end of MainFrame


