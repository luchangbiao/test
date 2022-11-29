package com.xuexiangban.shardingjdbc.shardingjdbc;

import com.xuexiangban.shardingjdbc.shardingjdbc.entity.User;
import com.xuexiangban.shardingjdbc.shardingjdbc.entity.UserOrder;
import com.xuexiangban.shardingjdbc.shardingjdbc.mapper.UserMapper;
import com.xuexiangban.shardingjdbc.shardingjdbc.mapper.UserOrderMapper;
import com.xuexiangban.shardingjdbc.shardingjdbc.service.UserOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

@SpringBootTest
class ShardingJdbcApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private UserOrderService userOrderService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setNickname("xxxzhangsan" + new Random().nextInt());
        user.setPassword("12345678");
        user.setSex(1);
        user.setAge(3);
        user.setBirthday(new Date());
        userMapper.addUser(user);
        System.out.println(user.getId());
    }


    @Test
    public void orderyearMaster() {
        for (int i = 0; i < 10; i++) {
            UserOrder userOrder = new UserOrder();
            userOrder.setCreateTime(new Date());
            userOrder.setOrdernumber("133455678");
            userOrder.setYearmonth("202101");
            userOrder.setUserid(1L);
            userOrderMapper.addUserOrder(userOrder);
        }
    }


    @Test
    public void countOrderyearMaster() {
        System.out.println(userOrderMapper.countuserOrders());
    }

    @Test
    public void selectOrderyearMaster() {
        // 性能 -- es
        System.out.println(userOrderMapper.finduserOrders(0));
        System.out.println("==================1======================");
        System.out.println(userOrderMapper.finduserOrders(10));
        System.out.println("==================2======================");
        System.out.println(userOrderMapper.finduserOrders(20));
        System.out.println("==================3======================");
        System.out.println(userOrderMapper.finduserOrders(30));
        System.out.println("==================4======================");
        System.out.println(userOrderMapper.finduserOrders(40));
        System.out.println("==================5======================");
        System.out.println(userOrderMapper.finduserOrders(50));
    }

    @Test
    public void testTranscation() {

        User user = new User();
        user.setNickname("xxxzhangsan" + new Random().nextInt());
        user.setPassword("12345678");
        user.setSex(1);
        user.setAge(3);
        user.setBirthday(new Date());


        UserOrder userOrder = new UserOrder();
        userOrder.setCreateTime(new Date());
        userOrder.setOrdernumber("133455678");
        userOrder.setYearmonth("202102");
        userOrder.setUserid(1L);

        userOrderService.saveUserOrder(user, userOrder);
    }

}
