package com.seeth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seeth.models.User;
import com.seeth.services.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringAutoConfigurationApplication.class)
@EnableJpaRepositories(basePackages = {"com"})
public class SpringAutoConfigurationApplicationTests {

	@Autowired
    private UserService userService;

    @Test
    public void whenSaveUser_thenOk() {
        User user = new User();
        user.setName("Seethend");
        user.setEmail("seeth@gmail.com");
        userService.saveUser(user);
    }

}
