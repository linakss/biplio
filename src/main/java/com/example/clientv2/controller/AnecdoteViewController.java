package com.example.clientv2;

import com.example.clientv2.service.AnecdoteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnecdoteViewController {
    private final AnecdoteService service = new AnecdoteService();

    @FXML
    private Label anecdoteText;

    @FXML
    private Label anecdoteText1;

    @FXML
    private Label anecdoteTextNumber;

    @FXML
    private void initialize() {
        service.setArrayAnecdotes();
    }

    @FXML
    void cancelShowAnecdoteAction(ActionEvent event) {

    }

    @FXML
    void showAnotherAnecdoteAction(ActionEvent event) {
        anecdoteText1.setText(service.getRandomAnecdote());
        anecdoteTextNumber.setText(String.valueOf(service.getRandomNumberAnecdote()));
    }

}
