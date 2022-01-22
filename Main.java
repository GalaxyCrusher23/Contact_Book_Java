import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //new InterFaces();

        gameplay();


    }


    //allows us to dictate which screen should be uploaded
    public static void gameplay(){
        switch(InterFaces.screen){
            case 0:
                InterFaces.start();
                break;
            case 1:
                InterFaces.InBook(InterFaces.pageAtc);
                break;
            default:
                break;
        }
    }
}