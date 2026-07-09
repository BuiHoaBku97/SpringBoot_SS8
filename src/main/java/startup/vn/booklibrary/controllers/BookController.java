package startup.vn.booklibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import startup.vn.booklibrary.commons.ApiResponse;
import startup.vn.booklibrary.models.dtos.requests.BookCreateDTO;
import startup.vn.booklibrary.models.dtos.requests.BookUpdateStockDTO;
import startup.vn.booklibrary.models.dtos.responses.BookCreatedDTO;
import startup.vn.booklibrary.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<BookCreatedDTO>> createBook(@Valid @ModelAttribute BookCreateDTO bookCreateDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.build(HttpStatus.CREATED.name(),
                        "Created book",
                        bookService.createBook(bookCreateDTO)));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<BookCreatedDTO>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateStockDTO bookUpdateStockDTO
    ) {
        return ResponseEntity
                .ok(ApiResponse.build(HttpStatus.OK.name(),
                        "Updated book stock",
                        bookService.updateBook(id, bookUpdateStockDTO)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookCreatedDTO>> getBookById(@PathVariable Long id) {
        return ResponseEntity
                .ok(ApiResponse.build(HttpStatus.OK.name(),
                        "Success",
                        bookService.getBookById(id)));
    }
}
