package com.vunyx.clientdashboardui.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestControllers {

    private RequestsController requestsController = new RequestsController();
    private JsonParser parser = new JsonParser();

    @CrossOrigin
    @RequestMapping(value = "/getDevices",  method = RequestMethod.GET)
    public ResponseEntity<String> getCCAssets(){
        return new ResponseEntity<>(requestsController.getDevices().toString(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/newDevice",  method = RequestMethod.POST)
    public ResponseEntity<JsonObject> newDevice(@RequestBody String data) throws Exception{
//        JsonObject req = parser.parse(data.trim()).getAsJsonObject();

       System.out.println(data);

        return new ResponseEntity<JsonObject>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/getWLMData",  method = RequestMethod.POST)
    public ResponseEntity<String> getPowerData(@RequestBody String data) throws Exception{

        JsonObject req = parser.parse(data.trim()).getAsJsonObject();

        return new ResponseEntity<>(requestsController.getDevData(req, "wlm").toString(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/getEMDData",  method = RequestMethod.POST)
    public ResponseEntity<String> getEMDData(@RequestBody String data) throws Exception{

        JsonObject req = parser.parse(data.trim()).getAsJsonObject();

        return new ResponseEntity<>(requestsController.getDevData(req, "emd").toString(), HttpStatus.OK);
    }

}
