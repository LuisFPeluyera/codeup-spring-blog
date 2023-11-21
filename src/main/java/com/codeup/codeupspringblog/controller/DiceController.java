package com.codeup.codeupspringblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String showRollDice(){
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String getUserInput(@RequestParam(name = "userGuess") String userGuess, Model model){
        model.addAttribute("userGuess", userGuess);
        ;
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{number}")
    public String getResult(@PathVariable int number, Model model) {
        int randomNumber = (int) (Math.floor(Math.random() * 6) + 1);
        String message = "";
        if (randomNumber == number) {
             message = "you guess " + number + " correctly!";
        } else {
             message = "You guess incorrectly. The correct guess is " + randomNumber;

        }

        model.addAttribute("randomNumber", randomNumber);
        model.addAttribute("userGuess", number);
        model.addAttribute("message", message);
        return "result";
    }
}
