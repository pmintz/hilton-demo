package com.hilton.demo.start.test;

import com.hilton.demo.start.HiltonDemoApplication;

import com.hilton.demo.start.HiltonDemoConfiguration;
import com.hilton.demo.start.core.IPMetaData;
import com.hilton.demo.start.db.IPMetaDataDAO;
import com.hilton.demo.start.resources.IPMetaDataResource;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;
//import jdk.internal.org.objectweb.asm.TypeReference;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.annotation.Resource;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IPMetaDataIntegrationTest {

    @Rule
    public final DropwizardAppRule<HiltonDemoConfiguration> RULE =
            new DropwizardAppRule<HiltonDemoConfiguration>(HiltonDemoApplication.class,
                    ResourceHelpers.resourceFilePath("config.yml"));
    private List<IPMetaData> ipMetaDataList;

    @Test
    public void runServerTest() {
        Client client = new JerseyClientBuilder().build();
        IPMetaData result = client.target(
                String.format("http://localhost:%d/IP/MetaData", RULE.getLocalPort())
        ).queryParam("ip", "127.0.0.1").request().get(IPMetaData.class) ;
        /*System.out.println("printing response");
        System.out.println(result.getQuery());*/
        assertThat(result.getQuery(), is("127.0.0.1"));
        /*Map<String, String> map =
                result.readEntity(new GenericType<Map<String, String>>() { });
        List<String> values = map.get("");*/

        //assertThat(result.readEntity(new GenericType<Map<String, List<String>>>(){})
                //, is("127.0.0.1"));

    }
}
