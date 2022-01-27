public class Contact{

  public String name;
  public String phone;
  public String companyName;
  public String address;
  public int socialCreditScore;

  /*public Contact(String n, int p, String cN, String a){
    name = n;
    phone = p;
    companyName = cN;
    address = a;
  }*/

  public Contact()
  {

  }

  public void setName(String n)
  {
    name = n;
  }

  public void setPhone(String p)
  {
    phone = p;
  }
  
  public void setCName(String cN)
  {
    companyName = cN;
  }

  public void setAddress(String a)
  {
    address = a;
  }

  public String getName()
  {
    return name;
  }

  public String getPhone()
  {
    return phone;
  }

  public String getCName()
  {
    return companyName;
  }

  public String getAddress()
  {
    return address;
  }     

}