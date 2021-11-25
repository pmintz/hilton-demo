package com.hilton.demo.start.db;

import com.hilton.demo.start.core.IPMetaData;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SessionStatistics;
import javax.persistence.NamedNativeQuery;


import java.util.List;
import java.util.Optional;

public class IPMetaDataDAO extends AbstractDAO<IPMetaData> {

    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public IPMetaDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<IPMetaData> findByIp(String ip) {

        /*List<IPMetaData> ll = currentSession().getNamedQuery("findByIp")
                .setParameter("query", ip).list();
        System.out.println("TRyiNg");
        for(IPMetaData i : ll){
            System.out.println(i.toString());
        }*/


        /*SessionStatistics ss = currentSession().getStatistics();

        System.out.println(ss.toString());*/
       /* StringBuilder builder = new StringBuilder("%");
        builder.append(ip).append("%");*/
        /*System.out.println("ip: " + ip);
        Query q =  namedQuery("findByIp");
        System.out.println("Printing query");
        System.out.println(q.toString());*/

        /*List l = q.setParameter("query", ip).list();
        System.out.println("Attempting to print list");
        l.stream().forEach(x -> System.out.println(x.toString()));*/
         List<IPMetaData> list =  list(
                namedQuery("findByIp")
                        .setParameter("query", ip)
        );
        System.out.println("printing list");

         System.out.println(list.toString());

         for(IPMetaData i : list) {
             System.out.println(i.toString());
         }

       /*Query query =
               currentSession().getNamedQuery("findByIp").setParameter("query", ip) ;

        List<IPMetaData> lastList = query.list();
        System.out.println(lastList.toString());*/

        return list;
    }

    public Optional<IPMetaData> findById() {
         Optional<IPMetaData> i = Optional.ofNullable(get((long)2));
         System.out.println("Is Present: " + i.isPresent());
        return null;
    }

    public IPMetaData save(IPMetaData ipMetaData) {
        return persist(ipMetaData);
    }

}
