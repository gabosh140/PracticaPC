package com.example.gabo.practicapc.Parsers.Parsers.Item;

import android.util.JsonReader;

import com.example.gabo.practicapc.Entities.Item;
import com.example.gabo.practicapc.Entities.ItemCategory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ItemsAPI implements  IItemsAPI{
    public ArrayList<ItemCategory> GetItems() {
        ArrayList<ItemCategory> categories = null;
        URL apiUrl = null;
        try {
            apiUrl = new URL("http://vmdev1.nexolink.com:90/TruequeAppAPI/api/ItemCategoriesApp/");
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here

                //Read Response
                InputStream responseBody = myConnection.getInputStream();
                // Read Json for response
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");

                JsonReader jsonReader = new JsonReader(responseBodyReader);

                //start reading array
                jsonReader.beginArray();

                //read elements
                categories = new ArrayList<>();
                while (jsonReader.hasNext()) {
                    //read every object
                    jsonReader.beginObject();
                    int id = 0;
                    String description = "";
                    while (jsonReader.hasNext()) {

                        String property = jsonReader.nextName();
                        switch (property.toLowerCase()) {
                            case "id":
                                id = jsonReader.nextInt();
                                break;
                            case "description":
                                description = jsonReader.nextString();
                                break;
                            default:
                                jsonReader.skipValue();
                                break;

                        }

                    }
                    ItemCategory objItemCategory = new ItemCategory(id, description);
                    categories.add(objItemCategory);
                    jsonReader.endObject();

                }

                jsonReader.endArray();
                jsonReader.close();
                myConnection.disconnect();

            }else {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Boolean PostItems(Item objItem) {
        Boolean result = false;
        objItem.setStatus("Created");
        try {
            URL apiUrl =
                    new URL("http://vmdev1.nexolink.com:90/TruequeAppAPI/api/Items");
            // Create connection
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiUrl.openConnection();

            //Set method type
            myConnection.setRequestMethod("POST");

            myConnection.setDoOutput(true);

            //Set data
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestProperty("Accept", "application/json");
            String itemJson = objItem.toJson();
            myConnection.getOutputStream().write(itemJson.getBytes());

            //Process response
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                result = true;
            } else {
                // Error handling code goes here
            }
            myConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}