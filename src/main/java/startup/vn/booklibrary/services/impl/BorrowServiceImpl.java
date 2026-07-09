package startup.vn.booklibrary.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import startup.vn.booklibrary.mappers.BorrowMapper;
import startup.vn.booklibrary.models.dtos.requests.BorrowCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BorrowCreatedDTO;
import startup.vn.booklibrary.repositories.BorrowRepository;
import startup.vn.booklibrary.services.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;

    public BorrowServiceImpl(BorrowRepository borrowRepository, BorrowMapper borrowMapper) {
        this.borrowRepository = borrowRepository;
        this.borrowMapper = borrowMapper;
    }

    @Override
    @Transactional
    public BorrowCreatedDTO createBorrow(BorrowCreateDTO borrowCreateDTO) {
        var borrow = borrowRepository.save(borrowMapper.toEntity(borrowCreateDTO));
        return borrowMapper.toResponse(borrow);
    }
}
