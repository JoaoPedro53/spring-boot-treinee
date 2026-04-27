package developer.jota.repository;

import developer.jota.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final UserRepositoryData repositoryData;

    public List<User> listAll() {
        return repositoryData.getUSERS();
    }

}
