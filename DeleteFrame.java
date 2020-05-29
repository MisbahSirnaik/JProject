import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class DeleteFrame extends JFrame {
Container c;
JLabel lblEid;
JTextField txtEid;
JButton btnSave, btnBack;
int eid;

DeleteFrame() {
c = getContentPane();
c.setLayout(new GridBagLayout());

JLabel lbltitle;
 
lbltitle= new JLabel("Employee Management System -> DELETE details of Employee ");
lbltitle.setFont(new Font("Arial", Font.BOLD, 40));
lbltitle.setOpaque(true);
lbltitle.setBackground(Color.CYAN);

JLabel background;
ImageIcon img=new ImageIcon("new5.jpg");
background = new JLabel("",img,JLabel.CENTER);
 background.setPreferredSize(new Dimension(700, 700));


lblEid = new JLabel("Enter Employee ID : ");
txtEid = new JTextField(20);

btnSave = new JButton("Delete Details");
btnBack = new JButton("Back");

GridBagConstraints m = new GridBagConstraints();
m.fill=GridBagConstraints.HORIZONTAL;
 m.gridx = 0;//set the x location of the grid for the next component
        m.gridy = 0;//set the y location of the grid for the next component
        
c.add(lbltitle,m);
m.gridy = 1;
c.add(background,m);
m.gridx = 0;
        m.gridy = 2;//change the y location
 m.insets = new Insets(5, 0, 5, 0);
        m.anchor=GridBagConstraints.CENTER;//left align components after this point
        c.add(lblEid,m);
 m.gridy = 3;
c.add(txtEid,m);

       
        m.gridy = 8;//change the y location
        c.add(btnSave,m);

        m.gridy = 9;//change the y location
        c.add(btnBack,m);


btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
MainFrame a = new MainFrame();
dispose();
} } );

btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

Transaction t = null;

try {
t = session.beginTransaction();

try {
eid = Integer.parseInt(txtEid.getText());
}
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Valid Employee Id");
txtEid.setText("");
txtEid.requestFocus();
return;
}

Employee e = (Employee)session.get(Employee.class, eid);
if(e != null) {
session.delete(e);
t.commit();
JOptionPane.showMessageDialog(new JDialog(), "Record Deleted");
txtEid.setText("");
txtEid.requestFocus();
}
else {
JOptionPane.showMessageDialog(new JDialog(), "Record Not Found");
txtEid.setText("");
txtEid.requestFocus();
}
}
catch(Exception e) {
t.rollback();
JOptionPane.showMessageDialog(new JDialog(), "Error : " + e);
}
finally {
session.close();
}
}
});

setTitle("Delete ");
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
pack();
setSize(screenSize.width,screenSize.height);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}