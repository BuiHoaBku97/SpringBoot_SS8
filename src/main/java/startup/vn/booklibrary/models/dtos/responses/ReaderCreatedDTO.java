package startup.vn.booklibrary.models.dtos.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReaderCreatedDTO {
    private Long id;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String avatar;
}
