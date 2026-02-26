package developer.jota.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPostResponse {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
