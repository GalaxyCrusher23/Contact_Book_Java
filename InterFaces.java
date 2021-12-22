import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InterFaces implements ActionListener, MouseListener {
    public static String book_img_str = "images/book.jpg";
    public static String plus_img_str = "images/plus.png";
        public static JPanel startMenu = new JPanel();
    public static JPanel bookPages = new JPanel();
    public static JFrame window = new JFrame("Blackjack");
    //public static ArrayList<JFrame> windowsForContacts = new ArrayList<JFrame>();
    public static boolean running = false;
    public static Color bookPaperColor = new Color(255, 229, 153);
    public static ImageIcon book_img = null;
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
    public static int pageAtc = 0;
    public static String[] contactNames;
    public static String[] contactPhone;
    public static String[] contactCompany;
    public static String[] contactAddress;
    public static String ttname;


    //JPanel template
    public static JPanel panel(JFrame frame, int w, int h, String bgi_loc, boolean drawLine, boolean closeOnOp) {

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        if (!bgi_loc.equals("False")){
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
        if (drawLine){
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
        if(closeOnOp){
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().add(panel);
        return panel;
    }

    //JLabel template
    public static JLabel tag(JPanel panel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        Font labelFont = label.getFont();
        String labelText = label.getText();

        int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);

        // Find out how much the font can grow in width.
        double widthRatio = (double)width / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, height);

        // Set the label's font size to the newly determined size.
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
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
        startMenu = panel(window, windowWidth, windowLength, book_img_str, false, true);
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
    public static void InBook(int pageAt){ //
        System.out.println("inbook");
        window.getContentPane().remove(bookPages);
        bookPages = panel(window, bookWindowWidth, windowLength, "False", true, true);
        bookPages.setBackground(bookPaperColor);
        bookPages.setLayout(null);

        //Title Label
        //JLabel title = tag(bookPages, "Blackjack", bookWindowWidth/2-(int)(titlefontSize/0.38), 0, 200, 100);

        //add button
        JButton addButton = button(bookPages, "Add contact", bookWindowWidth-150, windowLength-80, 130, 20, true, plus_img_str);
        addButton.setBackground(Color.ORANGE);
        //next button
        JButton nextButton = button(bookPages, "next page", bookWindowWidth-150, windowLength-105, 130, 20, true);
        addButton.setBackground(Color.ORANGE);


        //panel setups
        bookPages.addMouseListener(new InterFaces());

        bookPages.setVisible(true);
        System.out.println("mouse clicked 2: ");


        contactNames = new String[]{"Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Howie",};
        contactPhone = new String[]{"911", "912", "913", };
        contactCompany = new String[]{"Microsoft", "Shopify", "Tesla"};
        contactAddress = new String[]{"One Microsoft Way, Redmond, WA, USA", "151 O’Connor St Ground floor, Ottawa, ON K2P 2L8", "3500 Deer Creek Road. Palo Alto"};
        int[] contactSocialCreditScore = {1, 1, 1};

        //contact Labels output
        int inPage_number = 0;
        int labelHeight = 20;
        for(int this_contact_i = pageAt*20; this_contact_i<contactNames.length; this_contact_i++){
            if(inPage_number == 20){
                break;
            }
            StringBuilder this_contact_message_builder = new StringBuilder();
            this_contact_message_builder.append(contactNames[this_contact_i]);
            /*

            */
            //System.out.println(this_contact_message_builder.toString());
            if(inPage_number >= 10){
                JLabel personNameLabel = tag(bookPages, this_contact_message_builder.toString(), windowWidth/2+80, (inPage_number-10)*labelHeight, windowWidth/2-100, labelHeight);
            }
            else{
                JLabel personNameLabel = tag(bookPages, this_contact_message_builder.toString(), 0, inPage_number*labelHeight, windowWidth/2-100, labelHeight);
            }


            inPage_number++;
        }

        bookPages.repaint();

    }

    public static Object[] AddContact(){

        //ask for name
        String name = (String)JOptionPane.showInputDialog(
                bookPages,
                "Contact Name",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Stanislas");


        //System.out.println(name);

        //ask for phone number
        String phoneNumber = (String)JOptionPane.showInputDialog(
                bookPages,
                name+"\'s"
                        + "phone number",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "1335");

        //System.out.println(phoneNumber);

        //ask for address
        String address = (String)JOptionPane.showInputDialog(
                bookPages,
                name+"\'s"
                        + "address",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Buckingham Palace");

        //System.out.println(address);

        //ask for company name
        String companyName = (String)JOptionPane.showInputDialog(
                bookPages,
                name+"\'s"
                        + "company name",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "MI6");

        //System.out.println(companyName);

        return new Object[]{(Object) name, (Object) phoneNumber, (Object) address, (Object) companyName};
    }
    public static void openNewWindow(int id){

        System.out.println(ttname);

        String this_name = contactNames[id];
        //String this_address = contactAddress[id];
        //String this_company = contactCompany[id];
        //String this_phone = contactPhone[id];

        JFrame thisFrame = new JFrame(this_name+"\'s detailed profile");

        JPanel thispanel = panel(thisFrame, 500, 500,"False", false, false);

        JButton edit = button(thispanel, "edit", 470, 0, 30, 30, false);

        ActionListener newAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("edit pressed"+this_name);


                thispanel.removeAll();


                JTextField tname = new JTextField();
                tname.setFont(new Font("Arial", Font.PLAIN, 15));
                tname.setSize(200, 20);
                tname.setLocation(100, 100);
                tname.setVisible(true);
                thispanel.add(tname);

                JButton sub = new JButton("Submit");
                sub.setFont(new Font("Arial", Font.PLAIN, 15));
                sub.setSize(100, 20);
                sub.setLocation(150, 400);
                sub.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("change to: "+tname.getText());
                        ttname = tname.getText();

                    }
                });


                thispanel.add(sub);

                thispanel.repaint();
            }
        };

        edit.addActionListener(newAL);

        String displayName = "Name: "+this_name;
        //String displayAddress = "Address: "+this_address;
        //String displayCompany = "Company: "+this_company;

        JLabel nameLabel = tag(thispanel, displayName, 0, 20*0, 100, 80);
        //JLabel addressLabel = tag(thispanel, displayAddress, 100, 200, 400, 80);
        //JLabel companyLabel = tag(thispanel, displayCompany, 0, 100*2, 100, 20);

        thispanel.setVisible(true);

        //windowsForContacts.add(thisFrame);

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
            case "Add contact": //checks if Play or Rules Button is pressed
                AddContact();

                System.out.println("pressed Add contact");
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
        if (screen == 1){
            if(e.getY()>200){

            }
            else{
                if(e.getX()<windowWidth/2){
                    int clickedContact = e.getY()/20+pageAtc*20;
                    //System.out.print("Contact number: "+clickedContact);

                    if(clickedContact<contactNames.length){
                        openNewWindow(clickedContact);
                        //System.out.print("Contact name: "+contactNames[clickedContact]);
                        //new frame list new everything
                    }
                }
                else{

                    int clickedContact = e.getY()/20+pageAtc*20+10;
                    System.out.print("Contact number: "+clickedContact);

                    if(clickedContact<contactNames.length){
                        openNewWindow(clickedContact);
                        //System.out.print("Contact name: "+contactNames[clickedContact]);
                        //new frame list new everything
                    }

                }
            }

        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
        press_x = e.getX();
        press_y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println(" "+e.getX()+", "+press_x);
        if(e.getX()-press_x>100){

            bookPages.setBackground(Color.GREEN);

            bookPages.repaint();

            if(pageAtc>0){
                pageAtc--;
                Main.gameplay();
            }


        }
        else if (press_x-e.getX()>100){
            bookPages.setBackground(Color.GREEN);

            bookPages.repaint();


            if((double)contactNames.length/20-pageAtc>1){
                pageAtc++;
                System.out.println("pageAtc"+pageAtc+", "+(double)contactNames.length/20+", "+contactNames.length);
                Main.gameplay();
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}