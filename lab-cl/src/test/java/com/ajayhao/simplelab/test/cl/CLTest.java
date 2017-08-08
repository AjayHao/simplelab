package com.ajayhao.simplelab.test.cl;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;

/**
 * Created by AjayHao on 2017/7/11.
 *
 * DAL层测试父类
 */
@ContextConfiguration({
        "classpath*:spring/spring-lab-cl.xml",
        "classpath*:test-spring-cl-properties.xml"
})
//@Transactional(transactionManager = "transactionManager")
//spring 4.2以下版本有效,4.2及以上版本不用加
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//事务选择
//非事务
// public class CLTest extends AbstractTestNGSpringContextTests {
//事务
public abstract class CLTest extends AbstractTransactionalTestNGSpringContextTests {
    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        MockitoAnnotations.initMocks(this); //基于spring自动装配注解
    }

}
