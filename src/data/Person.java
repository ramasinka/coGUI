package data;

import java.io.Serializable;

/**
 * Created by CodeAcademy on 2017.07.11.
 */
public class Person implements Serializable{

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String ipAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return firstName;
    }

    public void setFirst_name(String firstName) {
        this.firstName = firstName;
    }

    public String getLast_name() {
        return lastName;
    }

    public void setLast_name(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIp_address() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", ip_address='" + ipAddress + '\'' +
                '}';
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}