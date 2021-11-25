package com.hilton.demo.start.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilton.demo.start.core.IPMetaData;
import com.hilton.demo.start.db.IPMetaDataDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.*;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/IP")
public class IPMetaDataResource {

    private final IPMetaDataDAO ipMetaDataDAO;
    private final HttpClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public IPMetaDataResource(final IPMetaDataDAO ipMetaDataDAO, final HttpClient client) {
        this.ipMetaDataDAO = ipMetaDataDAO;
        this.client = client;
    }

    @GET
    @Path("/MetaData")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getIPMetaData(@QueryParam("ip") String ip) {

        List<IPMetaData> ipMetaDataList = ipMetaDataDAO.findByIp(ip);
        if (!ipMetaDataList.isEmpty()) {
            return Response.ok().entity(ipMetaDataDAO.findByIp(ip)).build();
        } else {
            HttpUriRequest httpUriRequest = new HttpGet("http://ip-api.com/json/" + ip);
            HttpEntity httpEntity = null;
            String jsonString = null;
            IPMetaData ipMetaData = null;
            try {
                HttpResponse httpResponse = client.execute(httpUriRequest);
                httpEntity = httpResponse.getEntity();
                jsonString = EntityUtils.toString(httpEntity);
                ipMetaData = objectMapper
                        .readValue(jsonString, IPMetaData.class);
                System.out.println("Saving new object to database...");
                System.out.println(ipMetaData);
                ipMetaData = ipMetaDataDAO.save(ipMetaData);
                System.out.println("Object is - " + ipMetaData.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

            return Response.ok().entity(ipMetaData).build();

            //String jsonString = null;
            //try {
            //jsonString = EntityUtils.toString(httpEntity);
            //} catch (IOException e) {
            //e.printStackTrace();
            //}
            //System.out.println("++++++++++");
            //System.out.println(jsonString);
            //IPMetaData ipMetaData= null;
            //try {

            //} catch (IOException e) {
            //e.printStackTrace();
            //}
            //System.out.println("---------");
            //System.out.println(ipMetaData.toString());


            //return Response.ok().entity(ipMetaDataDAO.findByIp(ip)).build();
        }
    }
    @GET
    @Path("/MetaData/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getIPMetaData () {
        return Response.ok().entity(ipMetaDataDAO.findById()).build();
    }
}
