package com.example.gabo.practicapc.Entities;



import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alumnos on 4/28/18.
 */

public class Item {
    private int id;
    private int userId;
    private String userName;
    private int categoryId;
    private String categoryName;
    private String name;
    private String description;
    private double referencialValue;
    private Boolean tradable;
    private String status;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getReferencialValue() {
        return referencialValue;
    }

    public void setReferencialValue(double referencialValue) {
        this.referencialValue = referencialValue;
    }

    public Boolean getTradable() {
        return tradable;
    }

    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public String toJson(){
        String jsonText = null;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", getId());
            jsonObject.put("userId", getUserId());
            jsonObject.put("categoryId", getCategoryId());
            jsonObject.put("name", getName());
            jsonObject.put("description", getDescription());
            jsonObject.put("referencialValue", getReferencialValue());
            jsonObject.put("tradable", getTradable());
            jsonObject.put("status", getStatus());
            jsonText = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
