package startup.vn.booklibrary.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import startup.vn.booklibrary.exceptions.DuplicateInstanceException;
import startup.vn.booklibrary.mappers.BookMapper;
import startup.vn.booklibrary.models.dtos.requests.BookCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BookCreatedDTO;
import startup.vn.booklibrary.repositories.BookRepository;
import startup.vn.booklibrary.services.BookService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final String uploadDir;

    public BookServiceImpl(
            BookRepository bookRepository,
            BookMapper bookMapper,
            @Value("${app.upload-dir:uploads}") String uploadDir
    ) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.uploadDir = uploadDir;
    }

    @Override
    @Transactional
    public BookCreatedDTO createBook(BookCreateDTO bookCreateDTO) {
        if (bookRepository.existsByTitleAndAuthor(bookCreateDTO.getTitle(), bookCreateDTO.getAuthor())) {
            throw new DuplicateInstanceException(bookCreateDTO.getTitle() + " of " + bookCreateDTO.getAuthor() + " was created!");
        }

        String coverUrl = storeCoverImage(bookCreateDTO.getCoverImage());
        var book = bookRepository.save(bookMapper.toEntity(bookCreateDTO, coverUrl));
        return bookMapper.toResponse(book);
    }

    private String storeCoverImage(MultipartFile coverImage) {
        if (coverImage == null || coverImage.isEmpty()) {
            throw new IllegalArgumentException("Cover image is require");
        }

        String originalFilename = StringUtils.cleanPath(
                Objects.requireNonNullElse(coverImage.getOriginalFilename(), "cover-image")
        );
        if (originalFilename.contains("..")) {
            throw new IllegalArgumentException("Cover image filename is invalid");
        }

        String storedFilename = UUID.randomUUID() + "-" + originalFilename;
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Path destination = uploadPath.resolve(storedFilename).normalize();

        if (!destination.startsWith(uploadPath)) {
            throw new IllegalArgumentException("Cover image filename is invalid");
        }

        try {
            Files.createDirectories(uploadPath);
            coverImage.transferTo(destination);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not store cover image", ex);
        }

        return uploadDir.replace("\\", "/") + "/" + storedFilename;
    }
}
