import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;

class ViewFrame extends JFrame {
Container c;
TextArea ta;
JButton btnBack;

ViewFrame() {
c = getContentPane();
c.setLayout(new FlowLayout());
ta = new TextArea(58, 80);
btnBack = new JButton("Back");

JLabel lbltitle;
 
lbltitle= new JLabel("Employee Management System -> VIEW details of Employee ");
lbltitle.setFont(new Font("Arial", Font.BOLD, 40));
lbltitle.setOpaque(true);
lbltitle.setBackground(Color.ORANGE);

JLabel background;
ImageIcon img=new ImageIcon("new6.jpg");
background = new JLabel("",img,JLabel.CENTER);
 background.setPreferredSize(new Dimension(700, 700));


c.add(lbltitle);
c.add(background);
c.add(ta);
c.add(btnBack);

Configuration cfg = new Configuration();
cfg.configure("hibernate.cfg.xml");

SessionFactory sfact = cfg.buildSessionFactory();
Session session = sfact.openSession();

try{
java.util.List<Employee> emp = new ArrayList<>();
emp = session.createQuery("from Employee").list();
for (Employee e: emp)
	ta.append("eid: " + e.getEid() + "  ename: " + e.getEname() + "  esalary: " + e.getEsalary() + "\n");
}
catch(Exception e) {
JOptionPane.showMessageDialog(new JDialog(),"Error : " + e);
}
finally {
session.close();
}

btnBack.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae) {
MainFrame a = new MainFrame();
dispose();
} } );
ta.setEditable(false);
setTitle("View ");
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
pack();
setSize(screenSize.width,screenSize.height);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}


