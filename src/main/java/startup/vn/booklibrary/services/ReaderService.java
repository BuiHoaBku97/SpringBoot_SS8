package startup.vn.booklibrary.services;

import startup.vn.booklibrary.models.dtos.requests.ReaderCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.ReaderCreatedDTO;

public interface ReaderService {
    ReaderCreatedDTO createReader(ReaderCreateDTO readerCreateDTO);
}
