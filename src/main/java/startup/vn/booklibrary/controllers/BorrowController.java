package startup.vn.booklibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import startup.vn.booklibrary.commons.ApiResponse;
import startup.vn.booklibrary.models.dtos.requests.BorrowCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BorrowCreatedDTO;
import startup.vn.booklibrary.services.BorrowService;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BorrowCreatedDTO>> createBorrow(@Valid @RequestBody BorrowCreateDTO borrowCreateDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.build(HttpStatus.CREATED.name(),
                        "Created borrow",
                        borrowService.createBorrow(borrowCreateDTO)));
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<ApiResponse<BorrowCreatedDTO>> returnBook(@PathVariable Long id) {
        return ResponseEntity
                .ok(ApiResponse.build(HttpStatus.OK.name(),
                        "Returned book",
                        borrowService.returnBook(id)));
    }
}
