package za.co.codetribe.kid.profile.learners;

/**
 * Created by Codetribe on 2017/04/25.
 */
public class Learners
{
    String name,surname,gender,dateofbith,parentName,parentContants,address,url;
    int id;
    public Learners() {

    }


    public Learners(String name, String surname, String gender, String parentName, String parentContants, String address, String dateofbith,String url) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateofbith = dateofbith;
        this.parentName = parentName;
        this.parentContants = parentContants;
        this.address = address;
        this.url=url;


    }

    public Learners(String name, String surname, String gender, String parentName, String parentContants, String address, String dateofbith) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateofbith = dateofbith;
        this.parentName = parentName;
        this.parentContants = parentContants;
        this.address = address;



    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentContants() {
        return parentContants;
    }

    public void setParentContants(String parentContants) {
        this.parentContants = parentContants;
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



    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
