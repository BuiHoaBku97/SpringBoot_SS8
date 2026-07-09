package startup.vn.booklibrary.models.dtos.responses;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private String status;
    private String message;
    private LocalDateTime timestamp;
}
