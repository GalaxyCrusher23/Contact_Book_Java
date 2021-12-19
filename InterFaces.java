//*****************************************
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InterFaces implements ActionListener, MouseListener {
    public static JPanel startMenu = new JPanel();
    public static JPanel BookPages = new JPanel();
    public static JFrame window = new JFrame("Blackjack");
    public static boolean running = false;
    public static Color bookPaperColor = new Color(255, 229, 153);
    public static ImageIcon book_img = null;
    public static String book_img_str = "images/book.jpg";
    public static JLabel book_img_l = null;
    public static String userName = null;
    public static Dimension size = null;
    public static JLabel contactLabel = null;
    public static int windowWidth = 600;
    public static int bookWindowWidth = 700;
    public static int windowLength = 400;
    private static int titlefontSize = 32;
    public static int press_x = 0;
    public static int press_y = 0;
    public static int screen = 0;


    //JPanel template
    public static JPanel panel(JFrame frame, int w, int h, String bgi_loc) {

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        if (!bgi_loc.equals("bookOpen")){
            //paint background image
            ImageIcon input_img = new ImageIcon(bgi_loc);
            Image temp_img = input_img.getImage().getScaledInstance(windowLength, windowLength, Image.SCALE_DEFAULT);
            panel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    g.drawImage(temp_img,(windowWidth-windowLength)/2,0,this);
                }
            };

        }
        else{
            //no paint background
            panel = new JPanel(){
                @Override
                protected void paintComponent(Graphics g){
                    super.paintComponent(g);
                    g.setColor(Color.black);
                    g.drawLine(bookWindowWidth/2, 0, bookWindowWidth/2, windowLength);//drawImage(temp_img,(windowWidth-windowLength)/2,0,this);
                }
            };

        }


        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(panel);
        return panel;
    }

    //JLabel template
    public static JLabel tag(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        panel.add(label);
        return label;
    }
    //JButton template
    public static JButton button(JPanel panel, String text, int x, int y, int width, int height, boolean action){
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        panel.add(button);
        if(action){
            button.addActionListener(new InterFaces());
        }
        return button;
    }
    //JButton template
    public static JButton button(JPanel panel, String text, int x, int y, int width, int height, boolean action, String img_loc){
        ImageIcon imageIcon = new ImageIcon(img_loc); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(height, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        JButton button = new JButton(imageIcon);
        button.setText(text);
        button.setBounds(x, y, width, height);
        panel.add(button);
        if(action){
            button.addActionListener(new InterFaces());
        }
        return button;
    }
    public static void start(){
        //
        startMenu = panel(window, windowWidth, windowLength, book_img_str);
        startMenu.setBackground(Color.black);
        startMenu.setLayout(null);

        //Title Label
        //JLabel title = tag(startMenu, "Blackjack", windowWidth/2-(int)(titlefontSize/0.38), 0, 200, 100);
        //title.setForeground(Color.WHITE);
        //title.setFont(new Font("Serif", Font.BOLD, titlefontSize));

        //Play Button
        //JButton play = button(startMenu, "Play", windowWidth/4-50, windowLength/2, 100, 50, true);

        //play.setBackground(bookPaperColor);

        //panel setups

        startMenu.addMouseListener(new InterFaces());

        startMenu.setVisible(true);
        //System.out.println("mouse clicked 1: ");
        //System.out.println(window.getComponent(0).getWidth());
    }
    public static void InBook(){
        System.out.println("inbook");
        BookPages = panel(window, bookWindowWidth, windowLength, "bookOpen");
        BookPages.setBackground(bookPaperColor);
        BookPages.setLayout(null);

        //Title Label
        //JLabel title = tag(BookPages, "Blackjack", bookWindowWidth/2-(int)(titlefontSize/0.38), 0, 200, 100);

        //add button
        JButton addButton = button(BookPages, "Add contact", bookWindowWidth-150, windowLength-80, 130, 20, true, "images/plus.png");
        addButton.setBackground(Color.ORANGE);
        //next button
        JButton nextButton = button(BookPages, "next page", bookWindowWidth-150, windowLength-105, 130, 20, true);
        addButton.setBackground(Color.ORANGE);


        //panel setups
        BookPages.addMouseListener(new InterFaces());

        BookPages.setVisible(true);
        //System.out.println("mouse clicked 2: ");
        //System.out.println(window.getComponent(0).getWidth());
        //System.out.println(window.getComponent(0).getName());
        //System.out.println(window.getComponent(1).getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Play":
                System.out.println("pressed");
                startMenu.setBackground(Color.ORANGE);
                window.remove(startMenu);
                screen = 1;
                Main.gameplay();
                break;
            case "Rules": //checks if Play or Rules Button is pressed


                System.out.println("pressed");
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (screen == 0){
            window.remove(startMenu);
            screen = 1;
            Main.gameplay();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        press_x = e.getX();
        press_y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println(" "+e.getX()+", "+press_x);
        if(e.getX()-press_x>100 || press_x-e.getX()>100){
            press_x = e.getX();
            press_y = e.getY();


            startMenu.setBackground(Color.GREEN);
            startMenu.repaint();

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}