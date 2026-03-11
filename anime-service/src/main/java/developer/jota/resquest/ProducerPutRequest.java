package developer.jota.resquest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPutRequest {
    private Long id;
    private String name;
}
