package startup.vn.booklibrary.mappers;

import org.springframework.stereotype.Component;
import startup.vn.booklibrary.models.dtos.requests.ReaderCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.ReaderCreatedDTO;
import startup.vn.booklibrary.models.entities.Reader;

@Component
public class ReaderMapper {
    public Reader toEntity(ReaderCreateDTO readerCreateDTO, String avatarUrl) {
        return Reader.builder()
                .email(readerCreateDTO.getEmail())
                .fullName(readerCreateDTO.getFullName())
                .phoneNumber(readerCreateDTO.getPhoneNumber())
                .address(readerCreateDTO.getAddress())
                .avatar(avatarUrl)
                .build();
    }

    public ReaderCreatedDTO toResponse(Reader reader) {
        return ReaderCreatedDTO.builder()
                .id(reader.getId())
                .email(reader.getEmail())
                .fullName(reader.getFullName())
                .phoneNumber(reader.getPhoneNumber())
                .address(reader.getAddress())
                .avatar(reader.getAvatar())
                .build();
    }
}
