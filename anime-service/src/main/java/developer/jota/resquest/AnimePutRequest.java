package developer.jota.resquest;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimePutRequest {
    private Long id;
    private String name;
}
