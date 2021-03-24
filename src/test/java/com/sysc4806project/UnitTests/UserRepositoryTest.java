package com.sysc4806project.UnitTests;

import com.sysc4806project.models.User;
import com.sysc4806project.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp(){
        userRepository.save(new User("Broog", "pass","Omar", "B"));


    }

    @Test
    public void testCreateUser() {

        User savedUser = userRepository.save(new User("Borg", "pass","Omar", "B"));

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testFindUserByUsername() {


        User user = userRepository.findByUsername("Broog");


        assertThat(user.getUsername()).isEqualTo("Brg");
    }

    @Test
    public void testShowUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        assertThat(users).size().isGreaterThan(0);
    }

    @Test
    public void testUpdateUser() {

        User user = userRepository.findByUsername("Broog");
        user.setFirstName("The Reaper");

        userRepository.save(user);

        User updatedUser = userRepository.findByUsername("Broog");

        assertThat(updatedUser.getFirstName()).isEqualTo("The Reaper");
    }

    @Test
    public void testDeleteUser() {

        User user = userRepository.findByUsername("Broog");

        userRepository.deleteById(user.getId());

        User deletedUser = userRepository.findByUsername("Broog");

        assertThat(deletedUser).isNull();

    }

}
