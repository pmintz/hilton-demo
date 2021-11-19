package com.hilton.demo.start.resources;

import com.hilton.demo.start.db.IPMetaDataDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/IP")
public class IPMetaDataResource {

    private final IPMetaDataDAO ipMetaDataDAO;

    public IPMetaDataResource(IPMetaDataDAO ipMetaDataDAO) {
        this.ipMetaDataDAO = ipMetaDataDAO;
    }

    @GET
    @Path("/MetaData")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
   public Response getIPMetaData(@QueryParam("ip") String ip){
        System.out.println(ip);
       return Response.ok().build();
               //.entity(ipMetaDataDAO.findByIp(ip)).build();
   }
}
