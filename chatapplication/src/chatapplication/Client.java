
package chatapplication;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;
import javax.swing.*;
import java.text.*;
import javax.swing.border.*;


public class Client implements ActionListener{
    JTextField text;
     static JPanel a1;
     static DataOutputStream dout;
      static JFrame f=new JFrame();
        static Box vertical=Box.createVerticalBox();
    Client(){
       f.setTitle("Chatting App");
        
        
//        for upper plane 
            JPanel p1=new JPanel();
            p1.setBackground(new Color(18 ,140 ,126));
            p1.setBounds(0,0,450,60);
            p1.setLayout(null);
            f.add(p1);
        
          
                 
          ImageIcon b1=new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
		 Image b2=b1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon b3=new  ImageIcon(b2);
		 JLabel backbtn=new JLabel(b3);
		 backbtn.setBounds(5, 15, 25, 25);
		p1.add(backbtn);
                
          ImageIcon pr1=new ImageIcon(ClassLoader.getSystemResource("icons/yash.png"));
		 Image pr2=pr1.getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
		ImageIcon pr3=new  ImageIcon(pr2);
		 JLabel profile=new JLabel(pr3);
		 profile.setBounds(40, 8, 45, 45);
		p1.add(profile);
          ImageIcon v1=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		 Image v2=v1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon v3=new  ImageIcon(v2);
		 JLabel videobtn=new JLabel(v3);
		 videobtn.setBounds(310, 15, 25, 25);
		p1.add(videobtn);
          ImageIcon ph1=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		 Image ph2=ph1.getImage().getScaledInstance(27, 27, Image.SCALE_DEFAULT);
		ImageIcon ph3=new  ImageIcon(ph2);
		 JLabel phonebtn=new JLabel(ph3);
		 phonebtn.setBounds(355, 15, 27, 27);
		p1.add(phonebtn);
          ImageIcon v31=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		 Image v32=v31.getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT);
		ImageIcon v33=new  ImageIcon(v32);
		 JLabel video3btn=new JLabel(v33);
		 video3btn.setBounds(395, 15, 23, 23);
		p1.add(video3btn);
                
                
                
                
                
                JLabel name=new JLabel("YASH");
                name.setBounds(95,5,80,50);
                name.setForeground(Color.white);
                name.setFont(new Font("SAN_SERIF", Font.BOLD,20));
                p1.add(name);
                
                JLabel ac=new JLabel("Active now");
                ac.setBounds(95,20,100,50);
                ac.setForeground(Color.white);
                ac.setFont(new Font("SAN_SERIF", Font.BOLD,10));
                p1.add(ac);
                
                   ImageIcon s1=new ImageIcon(ClassLoader.getSystemResource("icons/background.jpg"));
		 Image s2=s1.getImage().getScaledInstance(450, 700, Image.SCALE_DEFAULT);
		ImageIcon s3=new  ImageIcon(s2);
		 JLabel image=new JLabel(s3);
		 image.setBounds(0, 0, 450, 700);
		 f.add(image);
                 
                 
                 
                 
                
//                lower panel
             a1=new JPanel();
            a1.setBounds(0,65,430,550);
           a1.setBackground(new Color(0,0,0,65));
           image.add(a1);
           
           
           
           
           
           
           
//          text field      
            text=new JTextField();
           text.setBounds(10, 615, 310, 35);
           text.setBackground(Color.white);
           text.setFont(new Font("Times New Roman", Font.BOLD,17));
           f.add(text);
           
//           send btn
               JButton send=new JButton("Send");
               send.setBounds(329, 615, 100, 35);
               send.setBackground(new Color(18 ,140 ,126));
               send.setForeground(Color.white);
               send.setFont(new Font("SAN_SERIF", Font.BOLD,16));
               send.addActionListener(this);
           image.add(send);
           
                backbtn.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent ae){
                        f.setVisible(false);
                    }
                });
                 
                
                
                
                
                
              
                 
                  f.setLayout(null);
        f.setSize(450,700);
        
        f.setLocation(800,50);
//        this is used to delete the upper titile bar
//         f.setUndecorated(true); 
         f.setVisible(true);
                
    }
    public static void main(String[] args) {
        new Client();
        
        try {
//            Socket s=new Socket("192.168.2.159",6001);
            Socket s=new Socket("127.0.0.1",6001);
             DataInputStream din=new DataInputStream(s.getInputStream());
                 dout=new DataOutputStream(s.getOutputStream());
                  while(true){
                      a1.setLayout(new BorderLayout()); 
                    String msg=din.readUTF();
                    JPanel panel=formatLabel(msg);
                    JPanel left =new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    
                    vertical.add(Box.createVerticalStrut(10));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    
                    
                    f.validate();
                }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
      String out=text.getText();
    
      
      JPanel m2=formatLabel(out);
     
      a1.setLayout(new BorderLayout());
      JPanel right= new JPanel(new  BorderLayout());
       right.add(m2,BorderLayout.LINE_END);
       vertical.add(right);
       vertical.add(Box.createVerticalStrut(10));
       a1.add(vertical,BorderLayout.PAGE_START);
       
       
       dout.writeUTF(out);
       text.setText("");
       
       
       f.repaint();
       f.invalidate();
       f.validate();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
//        panel.setBackground(new Color(0,0,0,65));

        JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p><html>");
        output.setFont(new Font("Tohoma",Font.PLAIN,15));
        output.setBackground(new Color(0,0,0,65));
//         output.setBackground(new Color(25,65,45));
        output.setBackground(Color.green);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf=new  SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
}
