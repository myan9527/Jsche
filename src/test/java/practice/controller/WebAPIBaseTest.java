/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice.controller;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

/**
 *
 * @author myan
 * @param <T>
 */
@WebAppConfiguration
@SpringApplicationConfiguration(classes = SpringConfiguration.class)
public abstract class WebAPIBaseTest<T> {
    private MockMvc mvc;

    protected ResultActions perform(RequestBuilder requestBuilder) throws Exception{
        return mvc.perform(requestBuilder);
    }
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(getController());
        this.mvc = builder.build();
    }
    
    protected abstract T getController();
}

@Configuration
class SpringConfiguration{
}
