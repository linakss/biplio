package com.example.clientv2.controller;

import com.example.clientv2.entity.GenreEntity;
import com.example.clientv2.entity.GenreEntity;
import com.example.clientv2.service.GenreService;
import com.example.clientv2.service.GenreService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class AddGenreController {
    private final GenreService service = new GenreService();
    private boolean addFlag = true;
    private GenreEntity getSelectionElement() {
        GenreEntity temp = dataGenreList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    private ListView<GenreEntity> dataGenreList;

    @FXML
    private TextField textGenreTitle;
    
    @FXML
    private void initialize(){
        service.getAll();
        dataGenreList.setItems(service.getData());
    }

    @FXML
    void addGenreAction(ActionEvent event) {
        GenreEntity city = new GenreEntity();
        city.setTitle(textGenreTitle.getText());
        if (addFlag) {
            service.add(city);
        } else {
            city.setId(getSelectionElement().getId());
            service.update(city, getSelectionElement());
        }
        textGenreTitle.clear();
    }

    @FXML
    void cancelGenreAction(ActionEvent event) {
        addFlag = true;
    }

    @FXML
    void deleteGenreAction(ActionEvent event) {

    }

    @FXML
    void onMouseClickDataList(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                GenreEntity temp = getSelectionElement();
                textGenreTitle.setText(temp.getTitle());
            }
        }
    }

}
