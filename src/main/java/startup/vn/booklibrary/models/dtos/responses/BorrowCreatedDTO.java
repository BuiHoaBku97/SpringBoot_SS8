package startup.vn.booklibrary.models.dtos.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BorrowCreatedDTO {
    private Long id;
    private String username;
    private Long bookId;
}
