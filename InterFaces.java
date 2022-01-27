package com.codewithhowie;
//*****************************************
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InterFaces implements ActionListener, MouseListener {    public static String book_img_str = "images/book.jpg";
    public static String plus_img_str = "images/plus.png";
    public static String musicIcon_str = "images/music.jpg";
    public static String noMusicIcon_str = "images/no_music.jpg";

    public static String musicRoot_str = "music/soviet_anthem.wav";
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
    public static boolean enteredPageAlready = false;
    public static List<String> contactNamesList = new ArrayList<String>();

    public static List<Contact> contacts = new ArrayList<Contact>();


    public static List<String> contactPhoneList = new ArrayList<String>();
    public static List<String> contactCompanyList = new ArrayList<String>();
    public static List<String> contactAddressList = new ArrayList<String>();

    public static Clip clip = null;
    public static AudioInputStream audioinput = null;

    public static boolean music_paused = false;
    public static long music_paused_ms_position = 0;
    public static JButton playPauseButton;

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
    public static JLabel tag(JPanel panel, String text, int x, int y, int width, int height, boolean smartsize) {

        JLabel label = new JLabel(text);
        if(smartsize){

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

        }

        else{
            Font labelFont = label.getFont();
            label.setFont(new Font(labelFont.getName(), Font.PLAIN, height));

            label.setBounds(x, y, width, height);
            panel.add(label);
        }

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
        //music
        File musicRoot = new File(musicRoot_str);
        try {
            audioinput = AudioSystem.getAudioInputStream(musicRoot);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioinput);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        //Title Label
        //JLabel title = tag(startMenu, "Blackjack", windowWidth/2-(int)(titlefontSize/0.38), 0, 200, 100);
        //title.setForeground(Color.WHITE);
        //title.setFont(new Font("Serif", Font.BOLD, titlefontSize));

        //Play Button
        //JButton play = button(startMenu, "Play", windowWidth/4-50, windowLength/2, 100, 50, true);

        //play.setBackground(bookPaperColor);
        //dummy input
        contactNames = new String[]{"Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Sanjeev", "Thomas", "Howie", "Howie",};

        contactPhone = new String[]{"911", "912", "913"};

        contactCompany = new String[]{"Microsoft", "Shopify", "Tesla"};

        contactAddress = new String[]{"One Microsoft Way, Redmond, WA, USA", "151 O\'Connor St Ground floor, Ottawa, ON K2P 2L8", "3500 Deer Creek Road. Palo Alto, California, United States of America"};

        contactNames = new String[]{"Sanjeev", "Thomas", "HowieHowieHowieHowieHowie"};
        Random rand = new Random();
        for(int i = 0; i<37; i++){
            contactNamesList.add(contactNames[rand.nextInt(3)]);
            if (contactNamesList.get(i) == "Sanjeev"){
                contactPhoneList.add("911-111-000000");
                contactCompanyList.add("Microsoft");
                contactAddressList.add("One Microsoft Way, Redmond, WA, USA");
            }
            else if (contactNamesList.get(i) == "Thomas"){
                contactPhoneList.add("912");
                contactCompanyList.add("Shopify");
                contactAddressList.add("151 O\'Connor St Ground floor, Ottawa, ON K2P 2L8");
            }
            else if (contactNamesList.get(i) == "HowieHowieHowieHowieHowie"){
                contactPhoneList.add("913");
                contactCompanyList.add("Tesla");
                contactAddressList.add("3500 Deer Creek Road. Palo Alto, California, United States of America");
            }
        }

        System.out.println(contactNamesList.size());
        //panel setups

        startMenu.addMouseListener(new InterFaces());

        startMenu.setVisible(true);
        //System.out.println("mouse clicked 1: ");
        //System.out.println(window.getComponent(0).getWidth());
    }
    public static void InBook(int pageAt){ //




        System.out.println("inbook");
        //window.getContentPane().remove(bookPages);


        if(!enteredPageAlready){
            //startMenu.removeAll();
            //window.getContentPane().remove(startMenu);
            //startMenu.setVisible(false);

            bookPages = panel(window, bookWindowWidth, windowLength, "False", true, true);
            bookPages.setBackground(bookPaperColor);
            bookPages.setLayout(null);
            enteredPageAlready = true;

        }
        else{
            bookPages.removeAll();

        }


        //Title Label
        //JLabel title = tag(bookPages, "Blackjack", bookWindowWidth/2-(int)(titlefontSize/0.38), 0, 200, 100);

        //add button
        JButton addButton = button(bookPages, "Add contact", bookWindowWidth-150, windowLength-80, 130, 20, true, plus_img_str);
        addButton.setBackground(Color.ORANGE);

        //music button
        String playPauseButtonMes = "play";
        String this_musicIcon_str = noMusicIcon_str;
        if(!music_paused){
             this_musicIcon_str = musicIcon_str;
            playPauseButtonMes = "pause";
        }
        playPauseButton = button(bookPages, "", bookWindowWidth-60, windowLength-150, 35, 40, false, this_musicIcon_str);
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!music_paused){
                    music_paused = true;
                    music_paused_ms_position = clip.getMicrosecondPosition();
                    clip.stop();

                    Main.gameplay();

                }

                else{
                    music_paused = false;
                    clip.setMicrosecondPosition(music_paused_ms_position);
                    clip.start();
                    Main.gameplay();

                }
            }
        });

        if(contactNamesList.size() % 20 == 0){
            JLabel lpage = tag(bookPages, "Page "+(pageAt+1)+" out of "+(User.contacts.size()/20), bookWindowWidth-160, windowLength-110, 200, 20, true);
        }
        else{
            JLabel lpage = tag(bookPages, "Page "+(pageAt+1)+" out of "+(User.contacts.size()/20+1), bookWindowWidth-160, windowLength-110, 200, 20, true);
        }


        //panel setups
        bookPages.addMouseListener(new InterFaces());

        bookPages.setVisible(true);
        System.out.println("mouse clicked 2: ");


        //contact Labels output
        int inPage_number = 0;
        int labelHeight = 20;
        for(int this_contact_i = pageAt*20; this_contact_i<User.contacts.size(); this_contact_i++){
            if(inPage_number == 20){
                break;
            }
            //StringBuilder this_contact_message_builder = new StringBuilder();
            //this_contact_message_builder.append(contactNamesList.get(this_contact_i));
            /*

            */
            //this_contact_message_builder.append("123456789123456789");

            //System.out.println(this_contact_message_builder.toString());
            if(inPage_number < 10){

                //JLabel personNameLabel = tag(bookPages, contactNamesList.get(this_contact_i), 0, inPage_number*labelHeight, 200, labelHeight, false);
                JLabel personNameLabel = tag(bookPages, User.contacts.get(this_contact_i).getName(), 0, inPage_number*labelHeight, 200, labelHeight, false);

                //JLabel personPhoneLabel = tag(bookPages, contactPhoneList.get(this_contact_i), 200, (inPage_number)*labelHeight, 147, labelHeight, false);
                JLabel personPhoneLabel = tag(bookPages, User.contacts.get(this_contact_i).getPhone(), 200, (inPage_number)*labelHeight, 147, labelHeight, false);
            }
            else{
                //JLabel personNameLabel = tag(bookPages, contactNamesList.get(this_contact_i), bookWindowWidth/2, (inPage_number-10)*labelHeight, 200, labelHeight, false);
                //JLabel personPhoneLabel = tag(bookPages, contactPhoneList.get(this_contact_i), bookWindowWidth/2+208, (inPage_number-10)*labelHeight, 145, labelHeight, false);

                JLabel personNameLabel = tag(bookPages, User.contacts.get(this_contact_i).getName(), bookWindowWidth/2, (inPage_number-10)*labelHeight, 200, labelHeight, false);
                JLabel personPhoneLabel = tag(bookPages, User.contacts.get(this_contact_i).getPhone(), bookWindowWidth/2+208, (inPage_number-10)*labelHeight, 145, labelHeight, false);

            }


            inPage_number++;
        }

        bookPages.repaint();

    }


    public static void openWindow(JFrame thisFrame, JPanel thispanel, int id, boolean alter, String this_name, String this_address, String this_company, String this_phone){

        thispanel.removeAll();




        JLabel lname = tag(thispanel, "Name: ", 40, 100, 800, 20, true);
        lname.setForeground(Color.WHITE);

        JTextField tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(200, 20);
        tname.setLocation(100, 100);
        tname.setVisible(true);
        tname.setText(this_name);
        thispanel.add(tname);



        JLabel lphone = tag(thispanel, "Phone number: ", 40, 130, 800, 20, true);
        lphone.setForeground(Color.WHITE);

        JTextField tphone = new JTextField();
        tphone.setFont(new Font("Arial", Font.PLAIN, 15));
        tphone.setSize(200, 20);
        tphone.setLocation(180, 130);
        tphone.setVisible(true);
        tphone.setText(this_phone);
        thispanel.add(tphone);

        JLabel lcompany = tag(thispanel, "Company: ", 40, 160, 800, 20, true);
        lcompany.setForeground(Color.WHITE);

        JTextField tcompany = new JTextField();
        tcompany.setFont(new Font("Arial", Font.PLAIN, 15));
        tcompany.setSize(200, 20);
        tcompany.setLocation(130, 160);
        tcompany.setVisible(true);
        tcompany.setText(this_company);
        thispanel.add(tcompany);


        JLabel laddress = tag(thispanel, "Address: ", 40, 190, 800, 20, true);
        laddress.setForeground(Color.WHITE);

        JTextArea taddress = new JTextArea();
        taddress.setLineWrap(true);
        taddress.setWrapStyleWord(true);
        taddress.setFont(new Font("Arial", Font.PLAIN, 15));
        taddress.setSize(200, 100);
        taddress.setLocation(130, 190);
        taddress.setVisible(true);
        taddress.setText(this_address);
        JScrollPane scroll = new JScrollPane (taddress);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        thispanel.add(scroll, BorderLayout.CENTER);
        thispanel.add(taddress);

        //submit button
        JButton sub;
        if(alter){
            sub = button(thispanel, "Update", 100, 400, 100, 20, false);

        }
        else{
            sub = button(thispanel, "Add", 100, 400, 100, 20, false);
        }
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                System.out.println("change to: "+tname.getText());
                if(tname.toString().length() > 0){



                        if(alter) {

                            User.editContact(tname.getText(), tphone.getText(), tcompany.getText(), taddress.getText(), id);

                            JOptionPane.showMessageDialog(thispanel,
                                    "great you just edited a contact");
                            thispanel.removeAll();

                        }
                        else{
                            User.addContact(tname.getText(), tphone.getText(), tcompany.getText(), taddress.getText());
                            JOptionPane.showMessageDialog(thispanel,
                                    "great you just added one more contact");

                        }

                        pageAtc = contacts.size()/20;
                        Main.gameplay();

                }
                //openWindow(thisFrame, thispanel, id, alter, tname.getText(), taddress.getText(), tcompany.getText(), tphone.getText());





            }
        });
        thispanel.add(sub);
        //cancel button

        JButton cancelB = button(thispanel, "Cancel", 200, 400, 100, 20, false);

        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alter){
                    System.out.println("change to: "+tname.getText());
                    tname.setText(this_name);
                    tphone.setText(this_phone);
                    taddress.setText(this_address);
                    tcompany.setText(this_company);

                }
                else {
                    thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
                }

            }
        });
        JButton deleteB = button(thispanel, "Delete", 300, 400, 100, 20, false);

        deleteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.removeContact(id);
                //thisFrame.dispatchEvent(new WindowEvent(thisFrame, WindowEvent.WINDOW_CLOSING));
                Main.gameplay();

            }
        });


    }
    public static void openNewWindow(JFrame thisFrame, int id){



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
                JFrame thisFrame = new JFrame("Add new contact");

                AddContact(thisFrame);

                System.out.println("pressed Add contact");
                break;
            default:
                break;
        }
    }
    public static void AddContact(JFrame this_frame){

        JPanel thispanel = panel(this_frame, 500, 500,"False", false, false);

        thispanel.setLayout(null);
        openWindow(this_frame, thispanel, 0, false, "", "", "", "");



    }
    @Override
    public void mouseClicked(MouseEvent e) {
        press_x = e.getX();
        press_y = e.getY();
        if (screen == 0){
            window.remove(startMenu);
            screen = 1;
            Main.gameplay();

        }
        if (screen == 1){
            if(e.getY()<=200){
                int clickedContact =-1;
                if(e.getX()<windowWidth/2){
                    clickedContact = e.getY()/20+pageAtc*20;
                    //System.out.print("Contact number: "+clickedContact);


                }
                else{

                    clickedContact = e.getY()/20+pageAtc*20+10;
                    System.out.print("Contact number: "+clickedContact);


                }

                if(clickedContact<User.contacts.size()){
                    String this_name = User.contacts.get(clickedContact).getName();
                    JFrame thisFrame = new JFrame(this_name+"\'s profile");
                    openNewWindow(thisFrame, clickedContact);
                    System.out.print("add");
                    System.out.println(ttname);


                    String this_address = User.contacts.get(clickedContact).getAddress();
                    String this_company = User.contacts.get(clickedContact).getCName();
                    String this_phone = User.contacts.get(clickedContact).getPhone();


                    JPanel thispanel = panel(thisFrame, 500, 500,"False", false, false);
                    thispanel.setLayout(null);


                    openWindow(thisFrame, thispanel, clickedContact, true, this_name, this_address, this_company, this_phone);
                    //thispanel.setLayout(null);
                    //thispanel.setVisible(true);

                    //new frame list new everything
                }
            }

        }


    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println(" "+e.getX()+", "+press_x);
        if(e.getX()-press_x>100){

            //bookPages.setBackground(Color.GREEN);

            //bookPages.repaint();

            if(pageAtc>0){
                pageAtc--;
                Main.gameplay();
            }


        }
        else if (press_x-e.getX()>100){
            //bookPages.setBackground(Color.GREEN);

            //bookPages.repaint();


            if((double)User.contacts.size()/20-pageAtc>1){
                pageAtc++;
                System.out.println("pageAtc"+pageAtc+", "+(double)User.contacts.size()/20+", "+User.contacts.size());
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