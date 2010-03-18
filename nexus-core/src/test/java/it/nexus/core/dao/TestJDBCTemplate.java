package it.nexus.core.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-17
 * Time: 13:03:57
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
public class TestJDBCTemplate extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    JdbcTemplate jdbcTemplate;
    @Test
    public void testCRUD(){
        int count = jdbcTemplate.queryForInt("select count(*) from sys_user");
        System.out.println("Count is:" + count);
//        jdbcTemplate.query("select id,username,password from sys_user",new RowCallbackHandler(){
//            @Override
//            public void processRow(ResultSet resultSet) throws SQLException {
//                System.out.println(resultSet.getString("id"));
//                System.out.println(resultSet.getString("username"));
//                System.out.println(resultSet.getString("password"));
//            }
//        });
    }

    @Test
    public void testQueryForList(){
        List rows=jdbcTemplate.queryForList("select * from sys_user");
        Iterator it=rows.iterator();
        while(it.hasNext()){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Map result=(Map)it.next();
            System.out.println(result.get("id"));
            System.out.println(result.get("username"));
            System.out.println(result.get("password"));
         }
    }
}
