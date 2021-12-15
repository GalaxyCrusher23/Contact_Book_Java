
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.*;

public class GUI implements ActionListener, MouseListener {
  public static JPanel bookPanel = new JPanel();
  public static JFrame bookFrame = new JFrame();
  public static boolean running = false;
  public static Color bookPaperColor = new Color(255, 229, 153);
  public static ImageIcon book_img = null;
  public static String book_img_str = "images/book.jpg";
  public static JLabel book_img_l = null;
  public static String userName = null;
  public static Dimension size = null;
  int pageNum = 1;

  GUI() {

    book_img = new ImageIcon(book_img_str);
    book_img_l = new JLabel(book_img);

    bookPanel.setPreferredSize(new Dimension(500, 500));
    // temporary
    Scanner myObj = new Scanner(System.in);
    userName = myObj.nextLine();
    // user name
    JLabel userName_label = new JLabel(userName);
	

    bookPanel.setFocusable(true);
	//bookPanel.setLayout(null);
    bookPanel.setBackground(Color.WHITE);
    bookPanel.addMouseListener(this);
    bookPanel.add(userName_label);
    size = userName_label.getPreferredSize();
    userName_label.setBounds(250, 250, size.width, size.height);

    bookPanel.add(book_img_l);
    userName_label.setVisible(true);
    book_img_l.setVisible(true);


    bookFrame.setTitle("Phone Book");
    bookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    bookFrame.setResizable(false);
    bookFrame.add(bookPanel);
    bookFrame.pack();
    bookFrame.setVisible(true);
    bookFrame.setLocationRelativeTo(null);
  }

  public static void startBook() {
    running = true;
    bookPanel.setBackground(bookPaperColor);
    book_img_l.setVisible(false);
    ImageIcon addContact_img = new ImageIcon("images/plus.png");
    JLabel addContact_label = new JLabel(addContact_img);
    bookPanel.add(addContact_label);
    addContact_label.setBounds(450, 450, 50, 50);

    bookPanel.repaint();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    startBook();
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
  /*
   * @Override public void actionPerfomed(ActionEvent e){
   * 
   * }
   */

}
