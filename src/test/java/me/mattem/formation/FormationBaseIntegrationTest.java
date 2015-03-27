package me.mattem.formation;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@IntegrationTest(value={"server.port=5000"})
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes=FormationApplication.class)
public abstract class FormationBaseIntegrationTest {

}
