package com.example.Libmansys.Services;

import com.example.Libmansys.Entities.Book;
import com.example.Libmansys.Entities.LibraryCard;
import com.example.Libmansys.Entities.Transaction;
import com.example.Libmansys.Enums.TransactionStatus;
import com.example.Libmansys.Repository.BookRepository;
import com.example.Libmansys.Repository.LibraryCardRepository;
import com.example.Libmansys.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public static final Integer maxNoOfBookIssue = 3;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{
        Optional<Book> getBook = bookRepository.findById(bookId);

        if (getBook.isEmpty()){
            throw new Exception("Invalid book id");
        }

        Book book = getBook.get();

        Optional<LibraryCard> getCard = libraryCardRepository.findById(cardId);

        if (getCard.isEmpty()){
            throw new Exception("Invalid card id");
        }

        LibraryCard card = getCard.get();

        if (card.getNoOfBooksIssued() >= maxNoOfBookIssue){
            throw new Exception("You've reached max number of issue limit");
        }

        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setLibraryCard(card);
        transaction.setTransactionStatus(TransactionStatus.PENDING);

        if (book.getIsIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Book is already issued to card with id: " + card.getCardNo();
        }

        Long validityTime = card.getValidity().getTime();
        Long currentTime = System.currentTimeMillis();

        if (currentTime >= validityTime){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
            transactionRepository.save(transaction);
            return "Your card is now not valid";
        }

        transaction.setTransactionStatus(TransactionStatus.ISSUED);

        book.setIsIssued(true);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);

        bookRepository.save(book);
        libraryCardRepository.save(card);

        transaction = transactionRepository.save(transaction);

        return "Book has been issued successfully with transaction id: " + transaction.getTransactionId();
    }

    public String returnBook(Integer bookId, Integer cardId){
        Optional<LibraryCard> libcard = libraryCardRepository.findById(cardId);

        LibraryCard card = libcard.get();

        Optional<Book> getBook = bookRepository.findById(bookId);

        Book book = getBook.get();

        Transaction transaction = transactionRepository.
                findTransactionByBookAndCardAndTransactionStatus(book, card, TransactionStatus.ISSUED);

        Long timeDiff = System.currentTimeMillis() - transaction.getIssueDate().getTime();

        Long days = TimeUnit.DAYS.convert(timeDiff,TimeUnit.MILLISECONDS);

        Integer fineAmt = 0;

        if (days > 15){
            fineAmt = Math.toIntExact(days - 15 * 5);
        }

        transaction.setFineAmount(fineAmt);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        transaction.setReturnDate(new Date());
        book.setIsIssued(Boolean.FALSE);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);

        transactionRepository.save(transaction);
        bookRepository.save(book);
        libraryCardRepository.save(card);
        return "Book has been returned successfully";
    }
}
