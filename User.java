import java.util.ArrayList;
import java.util.List;

public class User
{
  public static List<Contact> contacts = new ArrayList<Contact>();
  public static String userName;
  
  public void setName(String n)
  {
    userName = n;
  }

  public String getName()
  {
    return userName;
  }

  public void editContact()
  {
    
  }

  public void addContact()
  {
    Contact c = new Contact();
    
  }

  public void viewContact()
  {

  }

  public void removeContact()
  {

  }
}