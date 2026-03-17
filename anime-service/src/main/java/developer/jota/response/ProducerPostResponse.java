package developer.jota.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPostResponse {
    private Long id;
    private String name;
}
