package com.example.Libmansys.Controllers;

import com.example.Libmansys.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {

    @Autowired
    private CardService cardService;


    @PostMapping("/generateCard")
    public ResponseEntity addCard(){

        String result = cardService.generateCard();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/associateCardAndStudent")
    public ResponseEntity associateCardAndStudent(@RequestParam("cardId")Integer cardId,
                                                  @RequestParam("studentId")Integer studentId){

        String result = cardService.associateStudentWithCard(studentId,cardId);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}