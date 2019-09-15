package com.vunyx.clientdashboardui.controllers;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.cloudant.client.api.query.Sort;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vunyx.clientdashboardui.beans.Device;
import com.vunyx.clientdashboardui.beans.DeviceType;
import com.vunyx.clientdashboardui.beans.User;
import com.vunyx.clientdashboardui.util.Commons;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.cloudant.client.api.query.Expression.*;
import static com.cloudant.client.api.query.Operation.and;
import static com.cloudant.client.api.query.Operation.or;

@Service
public class RequestsController {

    public boolean createUser(User newuser){

        JsonObject newUser = new JsonParser().parse(newuser.toString()).getAsJsonObject();
        newUser.addProperty("_id", newuser.getEmail());
        newUser.addProperty("userType", "client");

        try {
            database("users").save(newUser);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean checkUser(Authentication auth){

        try {
            JsonObject user = database("users").find(JsonObject.class, auth.getName());
            return user.get("password").getAsString().equals(auth.getCredentials());
        } catch (Exception e){
            return false;
        }

    }

    public boolean registerNewDevice(Device device){
        System.out.println(device);

        Commons commons = new Commons();

        JsonObject newDevice = new JsonParser().parse(device.toString()).getAsJsonObject();
        newDevice.addProperty("_id", device.getId());
        newDevice.addProperty("deviceowner", "Voltarent");
        newDevice.addProperty("email", "info@voltarent.co.ke");
        newDevice.addProperty("location", "Voltarent Lab/Storage");
        newDevice.addProperty("timestamp", commons.Timestamp());
        newDevice.addProperty("phone", "0715794913");

        try {
            database("devicesdb").save(newDevice);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public JsonArray getDevices(){

        JsonParser parser = new JsonParser();
        JsonArray devList = new JsonArray();


//        RestTemplate restTemplate = new RestTemplate();
//        final String resourceUrl = "http://localhost:8030/api/getRegisteredDevices";
//            ResponseEntity<String> result = restTemplate.getForEntity(resourceUrl, String.class);
//
//        return parser.parse(result.getBody()).getAsJsonArray();

        try {
            List<JsonObject> devices = database("devicesdb").getAllDocsRequestBuilder()
                    .includeDocs(true).build().getResponse().getDocsAs(JsonObject.class);

            devList = parser.parse(devices.toString()).getAsJsonArray();

        } catch (Exception e){
            e.printStackTrace();
        }

        return devList;

    }

    public List<DeviceType> getDeviceTypes(){

        List<DeviceType> devicetypes = new ArrayList<>();

        try {

            List<JsonObject> devTypes = database("devicetypes").getAllDocsRequestBuilder()
                    .includeDocs(true).build().getResponse().getDocsAs(JsonObject.class);


            for (int i = 0; i<devTypes.size(); i++){
                DeviceType devtype = new DeviceType();
                devtype.setId(devTypes.get(i).getAsJsonObject().get("_id").getAsString());
                devtype.setName(devTypes.get(i).getAsJsonObject().get("name").getAsString());
                devicetypes.add(devtype);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return devicetypes;
    }

    public List<Device> getDeviceList(String type){

        List<Device> devices = new ArrayList<>();
        Selector selector = new Selector() {
        };

        switch (type) {
            case "wlm":
                selector =  or(eq("devicetype","swlm"), eq("devicetype","mwlm"));
                break;

            case "emd":
                selector =  or(eq("devicetype","memd"), eq("devicetype","semd"));
                break;
        }

        try {
            List<JsonObject> devicesList = database("devicesdb").
                    query(new QueryBuilder(selector).
                            build(), JsonObject.class).getDocs();

            for (int i = 0; i < devicesList.size(); i++){
                Device device = new Device();
                device.setId(devicesList.get(i).getAsJsonObject().get("id").getAsString());
                device.setImsi(devicesList.get(i).getAsJsonObject().get("imsi").getAsString());
                devices.add(device);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(devices);

        return devices;
    }

    public JsonArray getDevData(JsonObject dataQuery, String dbToUse){

        Selector selector = and(gt("timestamp", dataQuery.get("stopTime")), lt("timestamp", dataQuery.get("startTime")),
                eq("deviceid",dataQuery.get("deviceId")));
        QueryResult<JsonObject> devData = database(dbToUse).
                query(new QueryBuilder(selector).sort(Sort.desc("timestamp")).
                        build(), JsonObject.class);

        JsonArray dataArray = new JsonParser().parse(devData.getDocs().toString()).getAsJsonArray();

        return dataArray;
    }

    public boolean regDeviceTypes(){
        JsonObject devType = new JsonObject();

        devType.addProperty("_id", "swlm");
        devType.addProperty("name", "Solar Water Monitor");

        try {
            database("devicetypes").save(devType);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Database database(String dbName){

        String user = "15489ce4-1884-46c2-ab6f-47798cad2a5b-bluemix";
        String password = "78f015059b952321ac22eaea9183f1d35f284074036220c07b59c9fe265e186d";

        CloudantClient client = ClientBuilder.account(user)
                .username(user)
                .password(password)
                .build();

        return client.database(dbName, false);
    }

}
