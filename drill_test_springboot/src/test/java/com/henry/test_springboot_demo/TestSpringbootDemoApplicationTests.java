package com.henry.test_springboot_demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.test_springboot_demo.dao.PersonRepository;
import com.henry.test_springboot_demo.domain.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestSpringbootDemoApplication.class) //1 配置Spring Boot的应用上下文 - 手段：@SpringApplicationConfiguration注解
@WebAppConfiguration
@Transactional //2 确保每次测试后的数据都会回滚   手段：@Transactional注解
public class TestSpringbootDemoApplicationTests {

    @Autowired
    PersonRepository personRepository;

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    String expectedJson;

    @Before // 在测试开始前，进行一些初始化工作 - 手段：@Before注解
    public void setUp() throws JsonProcessingException {
        Person p1 = new Person("wyf");
        Person p2 = new Person("wisely");
        personRepository.save(p1);
        personRepository.save(p2);

        // 获取到方法的返回值，并转化成为Json字符串   手段：自定义的Obj2Json()方法
        expectedJson =Obj2Json(personRepository.findAll());
        // 实例化MockMVC - 参考：https://cloud.tencent.com/developer/article/1435504
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


    }

    // 把对象转化成为JSON字符串   - 手段：ObjectMapper对象(com.fasterxml.jackson包中的)
    protected String Obj2Json(Object obj) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    public void testPersonController() throws Exception {
        String uri="/person";
        //6 模拟一个请求，并获取到请求的执行结果
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();


        //7 获取到执行结果中的status
        int status = result.getResponse().getStatus();
        //8 获取到执行结果中的content
        String content = result.getResponse().getContentAsString();

        // 进行断言
        Assert.assertEquals("错误，正确的返回值为200",200, status); //9
        Assert.assertEquals("错误，返回值和预期返回值不一致", expectedJson,content); //10
    }

}
