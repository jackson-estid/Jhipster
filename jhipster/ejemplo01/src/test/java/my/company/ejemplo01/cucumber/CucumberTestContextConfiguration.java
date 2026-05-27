package my.company.ejemplo01.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import my.company.ejemplo01.IntegrationTest;
import my.company.ejemplo01.security.AuthoritiesConstants;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;

@CucumberContextConfiguration
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
public class CucumberTestContextConfiguration {}
