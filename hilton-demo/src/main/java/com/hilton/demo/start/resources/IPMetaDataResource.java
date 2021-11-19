package com.hilton.demo.start.resources;

import com.hilton.demo.start.db.IPMetaDataDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/IP")
public class IPMetaDataResource {

    private final IPMetaDataDAO ipMetaDataDAO;

    public IPMetaDataResource(final IPMetaDataDAO ipMetaDataDAO) {
        this.ipMetaDataDAO = ipMetaDataDAO;
    }

    @GET
    @Path("/MetaData")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
   public Response getIPMetaData(@QueryParam("ip") String ip){
       return Response.ok().entity(ipMetaDataDAO.findByIp(ip)).build();
   }

    @GET
    @Path("/MetaData/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response getIPMetaData(){
        return Response.ok().entity(ipMetaDataDAO.findById()).build();
    }
}
