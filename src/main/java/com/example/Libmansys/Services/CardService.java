package com.example.Libmansys.Services;

import com.example.Libmansys.Entities.LibraryCard;
import com.example.Libmansys.Entities.Student;
import com.example.Libmansys.Enums.CardStatus;
import com.example.Libmansys.Repository.LibraryCardRepository;
import com.example.Libmansys.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {
    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String generateCard(){
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setNoOfBooksIssued(0);
        libraryCard.setCardStatus(CardStatus.NEW);
        libraryCard.setValidity(new Date(128, 6, 1));
        libraryCard = libraryCardRepository.save(libraryCard);
        return "Library card has been generated with Id: " + libraryCard.getCardNo();
    }

    public String associateStudentWithCard(Integer studentId, Integer cardId){
        Student student = studentRepository.findById(studentId).get();
        LibraryCard libraryCard = libraryCardRepository.findById(cardId).get();

        libraryCard.setStudent(student);
        libraryCardRepository.save(libraryCard);
        return "Card and student has been associated";
    }
}
