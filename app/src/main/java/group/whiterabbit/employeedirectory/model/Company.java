package group.whiterabbit.employeedirectory.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    Company(){
        setName("");
    }
    public String getName() { return name; }

    public void setName(String value) { this.name = value; }


    public String getCatchPhrase() { return catchPhrase; }

    public void setCatchPhrase(String value) { this.catchPhrase = value; }


    public String getBs() { return bs; }

    public void setBs(String value) { this.bs = value; }

    public Company setData(JSONObject company) {

        try {
            setBs(company.getString("bs"));
            setName(company.getString("name"));
            setCatchPhrase(company.getString("catchPhrase"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;
    }
}
