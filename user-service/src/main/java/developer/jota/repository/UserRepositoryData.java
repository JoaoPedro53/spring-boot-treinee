package developer.jota.repository;

import developer.jota.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryData {
    private final List<User> USERS = new ArrayList<>();

    {
        var user1 = User.builder().id(1L).firstName("João").lastName("Pedro").email("pedro1@gmail.com").build();
        var user2 = User.builder().id(2L).firstName("Junior").lastName("Santos").email("Santos@gmail.com").build();
        var user3 = User.builder().id(3L).firstName("Pedro").lastName("Vieira").email("Vieira1@gmail.com").build();
        USERS.addAll(List.of(user1, user2, user3));
    }

    public List<User> getUSERS() {
        return this.USERS;
    }
}
