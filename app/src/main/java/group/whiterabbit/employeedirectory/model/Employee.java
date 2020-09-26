package group.whiterabbit.employeedirectory.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Employee {
    private int id;
    private String name;
    private String username;
    private String email;
    private String profileImage;
    private Address address;
    private String phone;
    private String website;
    private Company company;



    public int getID() { return id; }

    public void setID(int value) { this.id = value; }


    public String getName() { return name; }

    public void setName(String value) { this.name = value; }


    public String getUsername() { return username; }

    public void setUsername(String value) { this.username = value; }


    public String getEmail() { return email; }

    public void setEmail(String value) { this.email = value; }


    public String getProfileImage() { return profileImage; }

    public void setProfileImage(String value) { this.profileImage = value; }


    public Address getAddress() { return address; }

    public void setAddress(Address value) { this.address = value; }


    public String getPhone() { return phone; }

    public void setPhone(String value) { this.phone = value; }


    public String getWebsite() { return website; }

    public void setWebsite(String value) { this.website = value; }


    public Company getCompany() {
        if(company!=null)
        return company;

    else{
        return  new Company();
        }
    }

    public void setCompany(Company value) { this.company = value; }

    public Employee setData(JSONObject object){

        try {
            setName(object.getString("name"));
            setEmail(object.getString("email"));
            setPhone(object.getString("phone"));
            setID(object.getInt("id"));
            setWebsite(object.getString("website"));
            setUsername(object.getString("username"));
            setProfileImage(object.getString("profile_image"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            setCompany(new Company().setData(object.getJSONObject("company")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  this;
    }
}