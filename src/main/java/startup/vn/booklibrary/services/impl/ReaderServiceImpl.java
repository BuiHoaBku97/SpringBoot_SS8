package startup.vn.booklibrary.services.impl;

import com.cloudinary.Cloudinary;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import startup.vn.booklibrary.exceptions.DuplicateInstanceException;
import startup.vn.booklibrary.mappers.ReaderMapper;
import startup.vn.booklibrary.models.dtos.requests.ReaderCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.ReaderCreatedDTO;
import startup.vn.booklibrary.repositories.ReaderRepository;
import startup.vn.booklibrary.services.ReaderService;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class ReaderServiceImpl implements ReaderService {
    private static final Set<String> ALLOWED_AVATAR_EXTENSIONS = Set.of("png", "jpg", "jpeg");

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;
    private final Cloudinary cloudinary;

    public ReaderServiceImpl(ReaderRepository readerRepository, ReaderMapper readerMapper, Cloudinary cloudinary) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
        this.cloudinary = cloudinary;
    }

    @Override
    @Transactional
    public ReaderCreatedDTO createReader(ReaderCreateDTO readerCreateDTO) {
        if (readerRepository.existsByEmail(readerCreateDTO.getEmail())) {
            throw new DuplicateInstanceException("Reader with email " + readerCreateDTO.getEmail() + " already exists");
        }

        validateAvatarFile(readerCreateDTO.getAvatarFile());
        String avatarUrl = uploadAvatar(readerCreateDTO.getAvatarFile());
        var reader = readerRepository.save(readerMapper.toEntity(readerCreateDTO, avatarUrl));
        return readerMapper.toResponse(reader);
    }

    private void validateAvatarFile(MultipartFile avatarFile) {
        if (avatarFile == null || avatarFile.isEmpty()) {
            throw new IllegalArgumentException("Avatar file is require");
        }

        String filename = StringUtils.cleanPath(
                Objects.requireNonNullElse(avatarFile.getOriginalFilename(), "")
        );
        if (filename.contains("..")) {
            throw new IllegalArgumentException("Avatar filename is invalid");
        }

        String extension = getFileExtension(filename);
        if (!ALLOWED_AVATAR_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("Avatar file must be png, jpg, or jpeg");
        }
    }

    private String uploadAvatar(MultipartFile avatarFile) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(avatarFile.getBytes(), Map.of("folder", "readers"));
            Object url = result.get("secure_url");
            if (url == null) {
                url = result.get("url");
            }
            if (url == null) {
                throw new IllegalStateException("Cloudinary upload response does not contain image url");
            }

            return url.toString();
        } catch (IOException ex) {
            throw new IllegalStateException("Could not upload avatar to Cloudinary", ex);
        }
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex < 0 || lastDotIndex == filename.length() - 1) {
            return "";
        }

        return filename.substring(lastDotIndex + 1).toLowerCase(Locale.ROOT);
    }
}
