package developer.jota.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPostResponse {
    private Long id;
    private String name;
}
