package com.example.clientv2.controller;

import com.example.clientv2.entity.CityEntity;
import com.example.clientv2.entity.PublisherEntity;
import com.example.clientv2.service.CityService;
import com.example.clientv2.service.PublishService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class AddPublisherController {
    private final CityService cityService = new CityService();
    private final PublishService publishService = new PublishService();
    private boolean addFlag = true;
    private PublisherEntity getSelectionElement(){
        PublisherEntity temp = dataPublisherList.getSelectionModel().getSelectedItem();
        return temp;
    }


    @FXML
    private ComboBox<CityEntity> comboBoxPublisher;

    @FXML
    private ListView<PublisherEntity> dataPublisherList;

    @FXML
    private TextField textPublisherTitle;
    @FXML
    private void initialize(){
        cityService.getAll();
        publishService.getAll();
        dataPublisherList.setItems(publishService.getData());
        comboBoxPublisher.setItems(cityService.getData());
    }

    @FXML
    void addPublisherAction(ActionEvent event) {
        PublisherEntity publisher = new PublisherEntity();
        publisher.setTitle(textPublisherTitle.getText());
        publisher.setCity(comboBoxPublisher.getSelectionModel().getSelectedItem());
        if(addFlag){
            publishService.add(publisher);
        } else {
            publisher.setId(getSelectionElement().getId());
            publishService.update(publisher,getSelectionElement());
        }
        textPublisherTitle.clear();
    }

    @FXML
    void cancelPublisherAction(ActionEvent event) {
        addFlag = true;
    }

    @FXML
    void deletePublisherAction(ActionEvent event) {
        publishService.delete(getSelectionElement());
    }

    @FXML
    void clickedToList(MouseEvent event){
        if (event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                PublisherEntity temp = getSelectionElement();
                textPublisherTitle.setText(temp.getTitle());
                comboBoxPublisher.getSelectionModel().select(temp.getCity());
            }
        }
    }

}
