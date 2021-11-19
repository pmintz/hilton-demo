package com.hilton.demo.start.db;

import com.hilton.demo.start.core.IPMetaData;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

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

        public Optional<IPMetaData> findByIp(String ip) {
           return Optional.ofNullable(
                   uniqueResult(
                           namedQuery("IPMetaData.findByIp")
                                   .setParameter("ip",ip)));
        }

}
