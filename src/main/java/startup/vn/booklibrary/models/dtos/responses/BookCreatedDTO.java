package startup.vn.booklibrary.models.dtos.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookCreatedDTO {
    private Long id;
    private String title;
    private String author;
    private Integer stock;
    private String coverUrl;
}
