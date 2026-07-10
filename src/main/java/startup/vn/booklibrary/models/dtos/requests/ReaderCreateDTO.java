package startup.vn.booklibrary.models.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReaderCreateDTO {
    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is require")
    private String email;

    @NotBlank(message = "Full name is require")
    private String fullName;

    @Pattern(regexp = "^(0|\\+84)(3|5|7|8|9)\\d{8}$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Address is require")
    private String address;

    private MultipartFile avatarFile;
}
