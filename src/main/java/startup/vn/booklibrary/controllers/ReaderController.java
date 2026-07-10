package startup.vn.booklibrary.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import startup.vn.booklibrary.commons.ApiResponse;
import startup.vn.booklibrary.models.dtos.requests.ReaderCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.ReaderCreatedDTO;
import startup.vn.booklibrary.services.ReaderService;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ReaderCreatedDTO>> createReader(@Valid @ModelAttribute ReaderCreateDTO readerCreateDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.build(HttpStatus.CREATED.name(),
                        "Created reader",
                        readerService.createReader(readerCreateDTO)));
    }
}
