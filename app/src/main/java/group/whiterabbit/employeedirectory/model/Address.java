package group.whiterabbit.employeedirectory.model;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    public String getStreet() { return street; }

    public void setStreet(String value) { this.street = value; }


    public String getSuite() { return suite; }

    public void setSuite(String value) { this.suite = value; }


    public String getCity() { return city; }

    public void setCity(String value) { this.city = value; }


    public String getZipcode() { return zipcode; }

    public void setZipcode(String value) { this.zipcode = value; }


    public Geo getGeo() { return geo; }

    public void setGeo(Geo value) { this.geo = value; }
}
