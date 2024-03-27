package com.example.clientv2.controller;

import com.example.clientv2.MainApplication;
import com.example.clientv2.entity.AuthorEntity;
import com.example.clientv2.entity.BookEntity;
import com.example.clientv2.service.AuthorService;
import com.example.clientv2.service.BookService;
import com.example.clientv2.service.HttpService;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.print.Book;
import java.io.IOException;
import java.util.Optional;

public class MainController {
    private Optional<BookEntity> book = Optional.empty();
    private final BookService service = new BookService();
    @FXML
    private TableView<BookEntity> bookTable;

    @FXML
    private MenuItem closeAction;

    @FXML
    private TableColumn<BookEntity, String> columnAuthor;

    @FXML
    private TableColumn<BookEntity, String> columnGenre;

    @FXML
    private TableColumn<BookEntity, String> columnNumber;

    @FXML
    private TableColumn<BookEntity, String> columnPublisher;

    @FXML
    private TableColumn<BookEntity, String> columnTitle;


    @FXML
    private void initialize(){
        //получаем все книги с сервера
        service.getAll();
        //связываем поля таблицы со столбцами
        columnAuthor.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("author"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("genre"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("year"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
        columnPublisher.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("publisher"));
        bookTable.setItems(service.getData());
    }

    @FXML
    void addBookAction(ActionEvent event) {
        Optional<BookEntity> book = Optional.empty();
        MainApplication.showDialog("add-book-view.fxml","Работа с книгой");
    }
    /*@FXML
    void addBookAction(java.awt.event.ActionEvent event) {
        BookEntity temp = BookEntity.builder()
                .title(textBookTitle.getText())
                .year(textBookYear.getText())
                .genre(comboBookGenre.getSelectionModel().getSelectedItem())
                .publisher(comboBookPublisher.getSelectionModel().getSelectedItem())
                .author(comboBookAuthor.getSelectionModel().getSelectedItem())
                .build();
        book = Optional.of(temp);
        System.out.println(temp);
    }*/

    @FXML
    void addOrChangeAuthorAction(ActionEvent event) {
        MainApplication.showDialog("add-author-view.fxml","Работа с авторами");
    }

    @FXML
    void addOrChangeCityAction(ActionEvent event) {
        MainApplication.showDialog("add-city-view.fxml","Работа с городами");
    }

    @FXML
    void addOrChangeGenreAction(ActionEvent event) {
        MainApplication.showDialog("add-genre-view.fxml","Работа с жанрами");
    }

    @FXML
    void addOrChangePublisherAction(ActionEvent event) {
        MainApplication.showDialog("add-publisher-view.fxml","Работа с издательствами");
    }

    @FXML
    void showAnecdoteAction(ActionEvent event) {
        MainApplication.showDialog("anecdote-view.fxml","Анекдотики");
    }
    @FXML
    void openAnecdotesAction (ActionEvent event) {
        MainApplication.showDialog("anecdote-view.fxml","Анекдотики");
    }
    @FXML
    void deleteCityAction(ActionEvent event) {

    }

    @FXML
    void deleteGenreAction(ActionEvent event) {

    }

    @FXML
    void deletePublisherAction(ActionEvent event) {

    }

    @FXML
    void changeBookAction(ActionEvent event) {

    }

    @FXML
    void deleteAuthorAction(ActionEvent event) {

    }

    @FXML
    void deleteBookAction(ActionEvent event) {
        HttpService service = new HttpService();
        System.out.println(service.get("http://localhost:28245/api/v1/book/all"));
    }

    public void setBook(Optional<BookEntity> book){
        this.book = book;
        if(book.isPresent()){
            if(book.get().getId()!=null)
                service.update(book.get());
            else service.add(book.get());
        }
    }


    public void closeAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}


