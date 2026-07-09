package startup.vn.booklibrary.services.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import startup.vn.booklibrary.enums.BorrowStatus;
import startup.vn.booklibrary.exceptions.BookAlreadyReturnedException;
import startup.vn.booklibrary.exceptions.ResourceNotFoundException;
import startup.vn.booklibrary.mappers.BorrowMapper;
import startup.vn.booklibrary.models.dtos.requests.BorrowCreateDTO;
import startup.vn.booklibrary.models.dtos.responses.BorrowCreatedDTO;
import startup.vn.booklibrary.repositories.BookRepository;
import startup.vn.booklibrary.repositories.BorrowRepository;
import startup.vn.booklibrary.services.BorrowService;

import java.time.LocalDate;

@Service
public class BorrowServiceImpl implements BorrowService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final BorrowMapper borrowMapper;

    public BorrowServiceImpl(BorrowRepository borrowRepository, BookRepository bookRepository, BorrowMapper borrowMapper) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.borrowMapper = borrowMapper;
    }

    @Override
    @Transactional
    public BorrowCreatedDTO createBorrow(BorrowCreateDTO borrowCreateDTO) {
        var borrow = borrowRepository.save(borrowMapper.toEntity(borrowCreateDTO));
        return borrowMapper.toResponse(borrow);
    }

    @Override
    @Transactional
    public BorrowCreatedDTO returnBook(Long ticketId) {
        var borrow = borrowRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow ticket with id " + ticketId + " not found"));

        if (BorrowStatus.RETURNED.equals(borrow.getStatus())) {
            throw new BookAlreadyReturnedException("Sách này đã được trả rồi");
        }

        var book = bookRepository.findById(borrow.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + borrow.getBookId() + " not found"));

        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus(BorrowStatus.RETURNED);
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        return borrowMapper.toResponse(borrowRepository.save(borrow));
    }
}
