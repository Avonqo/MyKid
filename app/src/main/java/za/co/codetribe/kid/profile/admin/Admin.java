package za.co.codetribe.kid.profile.admin;

/**
 * Created by Codetribe on 2017/04/25.
 */
public class Admin
{
    String name,surname,gender,dateofbith,address,url ,contact;
    int id;
    public Admin() {

    }


    public Admin(String name, String surname, String gender, String parentName, String parentContants, String address, String dateofbith, String url,String contact) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateofbith = dateofbith;
        this.address = address;
        this.url=url;
        this.contact=contact;


    }

    public Admin(String name, String surname, String gender, String parentName, String parentContants, String address, String dateofbith) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateofbith = dateofbith;
        this.address = address;



    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbith() {
        return dateofbith;
    }

    public void setDateofbith(String dateofbith) {
        this.dateofbith = dateofbith;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
