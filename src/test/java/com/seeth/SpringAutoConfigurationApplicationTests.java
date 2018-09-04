package com.seeth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seeth.models.MyUser;
import com.seeth.repositories.MyUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=SpringAutoConfigurationApplication.class)
@EnableJpaRepositories(basePackages = {"com"})
public class SpringAutoConfigurationApplicationTests {

	@Autowired
    private MyUserRepository userRepository;

    @Test
    public void whenSaveUser_thenOk() {
        MyUser user = new MyUser("user@email.com");
        userRepository.save(user);
    }

}
