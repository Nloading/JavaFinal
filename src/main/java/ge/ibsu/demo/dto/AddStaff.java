package ge.ibsu.demo.dto;

import ge.ibsu.demo.entities.Address;

/**
 * AddStaff
 */
public class AddStaff {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Boolean active;

    /*
     * AddStaff(String firstName, String lastName, String email, Boolean active,
     * String address) {
     * 
     * }
     */

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
