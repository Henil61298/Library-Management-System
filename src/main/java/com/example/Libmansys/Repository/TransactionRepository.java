package com.example.Libmansys.Repository;

import com.example.Libmansys.Entities.Book;
import com.example.Libmansys.Entities.LibraryCard;
import com.example.Libmansys.Entities.Transaction;
import com.example.Libmansys.Enums.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findTransactionByBookAndCardAndTransactionStatus(Book book, LibraryCard card, TransactionStatus transactionStatus);
}
