package developer.jota.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPostResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
