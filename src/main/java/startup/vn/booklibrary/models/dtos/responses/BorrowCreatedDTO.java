package startup.vn.booklibrary.models.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import startup.vn.booklibrary.enums.BorrowStatus;

import java.time.LocalDate;

@Getter
@Builder
public class BorrowCreatedDTO {
    private Long id;
    private String username;
    private Long bookId;
    private BorrowStatus status;
    private LocalDate returnDate;
}
