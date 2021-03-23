package com.sysc4806project.UnitTests;

import com.sysc4806project.models.User;
import com.sysc4806project.repositories.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Rollback(false)
    @Order(1)
    public void testCreateUser() {

        User savedUser = userRepository.save(new User("Broog", "pass","Omar", "B"));

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void testFindUserByUsername() {

        User user = userRepository.findByUsername("Broog");

        assertThat(user.getUsername()).isEqualTo("Broog");
    }

    @Test
    @Order(3)
    public void testShowUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        assertThat(users).size().isGreaterThan(0);
    }

    @Test
    @Rollback(false)
    @Order(4)
    public void testUpdateUser() {
        User user = userRepository.findByUsername("Broog");
        user.setFirstName("The Reaper");

        userRepository.save(user);

        User updatedUser = userRepository.findByUsername("Broog");

        assertThat(updatedUser.getFirstName()).isEqualTo("The Reaper");
    }

    @Test
    @Rollback(false)
    @Order(5)
    public void testDeleteUser() {
        User user = userRepository.findByUsername("Broog");

        userRepository.deleteById(user.getId());

        User deletedProduct = userRepository.findByUsername("Broog");

        assertThat(deletedProduct).isNull();

    }

}
