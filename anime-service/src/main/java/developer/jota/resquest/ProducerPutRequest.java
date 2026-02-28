package developer.jota.resquest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPutRequest {
    private Long id;
    private String name;
}
