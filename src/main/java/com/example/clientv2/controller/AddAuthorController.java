package com.example.clientv2.controller;

import com.example.clientv2.entity.AuthorEntity;
import com.example.clientv2.service.AuthorService;
import com.example.clientv2.service.ErrorAlertService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddAuthorController {
    private final AuthorService service = new AuthorService();
    private final ErrorAlertService alertService = new ErrorAlertService();
    private boolean addFlag = true;
    private AuthorEntity getSelectionElement() {
        AuthorEntity temp = dataAuthorsList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    private ListView<AuthorEntity> dataAuthorsList;

    @FXML
    private void initialize(){
        service.getAll();
        dataAuthorsList.setItems(service.getData());
    }

    @FXML
    private TextField textLastName;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private Button btnAddOrChangeAuthor;

    @FXML
    private Button btnCancelAuthor;

    @FXML
    void addAuthorAction(ActionEvent event) {
        AuthorEntity author = new AuthorEntity();
        author.setLastname(textLastName.getText());
        author.setName(textName.getText());
        author.setSurname(textSurname.getText());
        if (addFlag) {
            service.add(author);
        } else {
            author.setId(getSelectionElement().getId());
            service.update(author, getSelectionElement());
        }
        textLastName.clear();
        textName.clear();
        textSurname.clear();
    }

    @FXML
    void cancelAuthorAction(ActionEvent event) {
        addFlag = true;
    }
    @FXML
    void onMouseClickDataList(MouseEvent event) throws IOException {
        try{if(event.getButton().equals(MouseButton.PRIMARY)) {
            if (event.getClickCount() == 2) {
                addFlag = false;
                AuthorEntity temp = getSelectionElement();
                textLastName.setText(temp.getLastname());
                textName.setText(temp.getName());
                textSurname.setText(temp.getSurname());
                }
            }
        } catch (Exception e){
            alertService.showError(e);
        }
    }


    @FXML
    void deleteAuthorAction(ActionEvent event) {
        try {alertService.doSomething();
        }catch (Exception e){
            alertService.showError(e);
        }

    }

}
