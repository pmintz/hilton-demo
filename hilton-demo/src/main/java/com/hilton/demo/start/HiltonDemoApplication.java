package com.hilton.demo.start;

import com.hilton.demo.start.core.IPMetaData;
import com.hilton.demo.start.db.IPMetaDataDAO;
import com.hilton.demo.start.resources.IPMetaDataResource;
import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.migrations.MigrationsBundle;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HiltonDemoApplication extends Application<HiltonDemoConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HiltonDemoApplication.class);

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

        LOGGER.info("Initializing application");
        /**
         * Adding Hibernate bundle.
         */
        bootstrap.addBundle(hibernateBundle);
        /**
         * Adding migrations bundle.
         */
        LOGGER.info("Bootstrapping hibernate bundle completed");
        bootstrap.addBundle(
                new MigrationsBundle<HiltonDemoConfiguration>() {
                    @Override
                    public DataSourceFactory getDataSourceFactory(
                            HiltonDemoConfiguration configuration) {
                        return configuration.getDataSourceFactory();
                    }
                });
        LOGGER.info("Bootstrapping DataSourceFactory bundle");
    }

    @Override
    public void run(final HiltonDemoConfiguration configuration,
                    final Environment environment) {
        // Create DAOs.
        final IPMetaDataDAO ipMetaDataDAO
                = new IPMetaDataDAO(hibernateBundle.getSessionFactory());
        //create http client
        final HttpClient httpClient = new HttpClientBuilder(environment).using(configuration
                        .getHttpClientConfiguration())
                .build("http-client");



        // Register Resource
        environment.jersey().register(new IPMetaDataResource(ipMetaDataDAO, httpClient));
       // environment.jersey().register(new IPMetaDataResource(ipMetaDataDAO, httpClient));


    }

}
