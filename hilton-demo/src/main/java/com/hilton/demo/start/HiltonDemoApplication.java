package com.hilton.demo.start;

import com.hilton.demo.start.core.IPMetaData;
import com.hilton.demo.start.db.IPMetaDataDAO;
import com.hilton.demo.start.resources.IPMetaDataResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.migrations.MigrationsBundle;

public class HiltonDemoApplication extends Application<HiltonDemoConfiguration> {

    /**
     * Create Hibernate bundle.
     */
    private final HibernateBundle<HiltonDemoConfiguration> hibernateBundle
            = new HibernateBundle<HiltonDemoConfiguration>(
            IPMetaData.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                HiltonDemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new HiltonDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "HiltonDemo";
    }

    @Override
    public void initialize(final Bootstrap<HiltonDemoConfiguration> bootstrap) {
        /**
         * Adding Hibernate bundle.
         */
        bootstrap.addBundle(hibernateBundle);
        /**
         * Adding migrations bundle.
         */
        bootstrap.addBundle(
                new MigrationsBundle<HiltonDemoConfiguration>() {
                    @Override
                    public DataSourceFactory getDataSourceFactory(
                            HiltonDemoConfiguration configuration) {
                        return configuration.getDataSourceFactory();
                    }
                });
    }

    @Override
    public void run(final HiltonDemoConfiguration configuration,
                    final Environment environment) {
        // Create DAOs.
        final IPMetaDataDAO ipMetaDataDAO
                = new IPMetaDataDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new IPMetaDataResource(ipMetaDataDAO));


    }

}
