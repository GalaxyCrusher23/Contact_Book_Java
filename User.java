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

  public void editContact(String cname, String cphone, String ccompany, String caddress, int contactindex)
  {
    Contact c = contacts.get(contactindex);
    c.setName(cname);
    c.setPhone(cphone);
    c.setCName(ccompany);
    c.setAddress(caddress);
    contacts.set(contactindex,c);    
  }

  public void addContact(String cname, String cphone, String ccompany, String caddress)
  {
    Contact c = new Contact();
    c.setName(cname);
    c.setPhone(cphone);
    c.setCName(ccompany);
    c.setAddress(caddress);
    contacts.add(c);
  }

  public void removeContact(int contactindex)
  {
    contacts.remove(contactindex);
  }
}