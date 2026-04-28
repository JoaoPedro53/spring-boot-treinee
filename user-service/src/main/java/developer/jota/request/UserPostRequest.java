package developer.jota.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPostRequest {
    private String firstName;
    private String lastName;
    private String email;
}
