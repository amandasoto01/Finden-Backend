package com.Finden.findenBackEnd;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.Finden.findenBackEnd.models.entity.GetInfo;
import com.google.gson.Gson;
 
public class MyClient {
    public static void main(String[] args) throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getCredentialsProvider().setCredentials(
            new AuthScope("10.26.1.103", 8080, "iMC RESTful Web Services"),
            new UsernamePasswordCredentials("puj_finden", "javeriana2019*"));
        int wcid=2;
        int port=1;
        HttpGet get = new HttpGet("http://10.26.1.103:8080/imcrs/plat/res/device/"+wcid+"/interface/"+port);
        get.addHeader("accept", "application/json");
        HttpResponse response = client.execute(get);
        Gson gson= new Gson();
        GetInfo info=gson.fromJson(EntityUtils.toString(response.getEntity()), GetInfo.class);
    }
}