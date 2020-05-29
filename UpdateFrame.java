import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class UpdateFrame extends JFrame {
Container c;
JLabel lblEid, lblEname, lblEsalary;
JTextField txtEid, txtEname, txtEsalary;
JButton btnSave, btnBack;
int eid;
String ename;
double esalary;

UpdateFrame() {
c = getContentPane();
c.setLayout(new GridBagLayout());

JLabel lbltitle;
 
lbltitle= new JLabel("Employee Management System -> UPDATE details of Employee ");
lbltitle.setFont(new Font("Arial", Font.BOLD, 40));
lbltitle.setOpaque(true);
lbltitle.setBackground(Color.PINK);

JLabel background;
ImageIcon img=new ImageIcon("new4.jpg");
background = new JLabel("",img,JLabel.CENTER);
 background.setPreferredSize(new Dimension(700, 700));


lblEid = new JLabel("Enter Employee ID : ");
txtEid = new JTextField(20);
lblEname = new JLabel("Enter Employee Name :");
txtEname = new JTextField(20);
lblEsalary = new JLabel("Enter Employee Salary : ");
txtEsalary = new JTextField(20);
btnSave = new JButton("Update Details");
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

        m.gridy = 4;//change the y location
        c.add(lblEname,m);
 m.gridy = 5;
c.add(txtEname,m);

 m.gridy = 6;//change the y location
        c.add(lblEsalary,m);
 m.gridy = 7;
c.add(txtEsalary,m);

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

try{
t = session.beginTransaction();

try{
eid = Integer.parseInt(txtEid.getText());
if(eid <=0)
	throw new NumberFormatException();
} 
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Valid Employee ID");
txtEid.setText("");
txtEid.requestFocus();
return;
}

Employee e = (Employee)session.get(Employee.class, eid);
if(e != null) {

try{
ename = txtEname.getText();
if(ename.matches(".*\\d+.*"))
	throw new NullPointerException();
if(!ename.matches("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$"))
	throw new NullPointerException();
if(ename.length() < 2)
	throw new IllegalAccessException();
}
catch(NullPointerException npe){
JOptionPane.showMessageDialog(new JDialog(),"Name Should only include Alphabets");
txtEname.setText("");
txtEname.requestFocus();
return;
}
catch(IllegalAccessException iae) {
JOptionPane.showMessageDialog(new JDialog(),"Name Should contain 2 or more than 2 Alphabets \n Please enter valid name");
txtEname.setText("");
txtEname.requestFocus();
return;
}
e.setEname(ename);

try{
esalary = Double.parseDouble(txtEsalary.getText());
if(esalary < 8000.0)
	throw new NullPointerException();
}
catch(NullPointerException npe) {
JOptionPane.showMessageDialog(new JDialog(),"Minimum Salary Should Be 8000");
txtEsalary.setText("");
txtEsalary.requestFocus();
return;
}
catch(NumberFormatException nfe) {
JOptionPane.showMessageDialog(new JDialog(),"Please Enter Valid Salary");
txtEsalary.setText("");
txtEsalary.requestFocus();
return;
}
e.setEsalary(esalary);
t.commit();
JOptionPane.showMessageDialog(new JDialog(), "Record Updated");
txtEid.setText("");
txtEname.setText("");
txtEsalary.setText("");
txtEid.requestFocus();
}
else{
JOptionPane.showMessageDialog(new JDialog(), "Record Not Found");
txtEid.setText("");
txtEid.requestFocus();
}
}
catch(Exception e) {
JOptionPane.showMessageDialog(new JDialog(), "Error : " + e);
}
finally {
session.close();
} } } );


setTitle("Update ");
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
pack();
setSize(screenSize.width,screenSize.height);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}

