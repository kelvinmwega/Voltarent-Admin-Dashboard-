package com.vunyx.clientdashboardui.database;

import com.cloudant.client.api.query.QueryBuilder;
import com.cloudant.client.api.query.QueryResult;
import com.cloudant.client.api.query.Selector;
import com.cloudant.client.api.query.Sort;
import com.vunyx.clientdashboardui.beans.Device;
import com.vunyx.clientdashboardui.beans.DeviceType;
import com.vunyx.clientdashboardui.controllers.RequestsController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cloudant.client.api.query.Expression.*;
import static com.cloudant.client.api.query.Operation.and;

@Service
public class query {

    private RequestsController requestsController = new RequestsController();

    public void testQuery(){

        Selector selector = and(gt("timestamp", "2019-07-27T07:19:23Z"), lt("timestamp", "2019-07-28T07:19:23Z"),
                eq("deviceid","863591029486077"));
        QueryResult<Object> user = requestsController.database("wlm").
                query(new QueryBuilder(selector).sort(Sort.desc("timestamp")).
                build(), Object.class);
        System.out.println(user.getDocs());

    }

}
