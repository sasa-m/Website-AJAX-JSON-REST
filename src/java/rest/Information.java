package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.HibernateUtil;
import entity.blog;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.hibernate.Session;


@Path("information")
public class Information {

    @Context
    private UriInfo context;


    public Information() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() throws JsonProcessingException {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        s.beginTransaction();
        List<blog> topics = s.createCriteria(blog.class).list();
        ObjectMapper om = new ObjectMapper();
        s.getTransaction().commit();
        
        return om.writeValueAsString(topics);
    }
}
