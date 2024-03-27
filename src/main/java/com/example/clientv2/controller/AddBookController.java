package com.example.clientv2.controller;

import com.example.clientv2.entity.AuthorEntity;
import com.example.clientv2.entity.BookEntity;
import com.example.clientv2.entity.GenreEntity;
import com.example.clientv2.entity.PublisherEntity;
import com.example.clientv2.service.AuthorService;
import com.example.clientv2.service.GenreService;
import com.example.clientv2.service.PublishService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


import java.util.Optional;

public class AddBookController {

    private final AuthorService authorService = new AuthorService();
    private final GenreService genreService = new GenreService();
    private final PublishService publishService = new PublishService();

    @FXML
    private ComboBox<AuthorEntity> comboBookAuthor;

    @FXML
    private ComboBox<GenreEntity> comboBookGenre;

    @FXML
    private ComboBox<PublisherEntity> comboBookPublisher;

    @FXML
    private TextField textBookTitle;

    @FXML
    private TextField textBookYear;

    @FXML
    private Button btnAddOrChangeBook;

    @FXML
    private Button btnCancelBook;


    @Setter
    @Getter
    private Optional<BookEntity> book;
    @FXML
    private void initialize() {
        authorService.getAll();
        publishService.getAll();
        genreService.getAll();
        comboBookAuthor.setItems(authorService.getData());
        comboBookGenre.setItems(genreService.getData());
        comboBookPublisher.setItems(publishService.getData());

    }

    public void start(){
        if(book.isPresent()){
            textBookTitle.setText(book.get().getTitle());
            textBookYear.setText(book.get().getYear());
            comboBookAuthor.setValue(book.get().getAuthor());
            comboBookGenre.setValue(book.get().getGenre());
            comboBookPublisher.setValue(book.get().getPublisher());
        }
    }


    @FXML
    void addBookAction(ActionEvent event) {
        BookEntity temp = BookEntity.builder().title(textBookTitle.getText())
                .year(textBookYear.getText())
                .genre(comboBookGenre.getSelectionModel().getSelectedItem())
                .publisher(comboBookPublisher.getSelectionModel().getSelectedItem())
                .author(comboBookAuthor.getSelectionModel().getSelectedItem())
                .build();
        book = Optional.of(temp);
        Stage stage = (Stage) btnAddOrChangeBook.getScene().getWindow();
        System.out.println(temp);
    }

    @FXML
    void cancelBookAction(ActionEvent event) {
    }
}
