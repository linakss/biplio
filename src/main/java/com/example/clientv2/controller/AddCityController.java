package com.example.clientv2.controller;

import com.example.clientv2.entity.CityEntity;
import com.example.clientv2.service.CityService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddCityController {
    private final CityService service = new CityService();
    private boolean addFlag = true;
    private CityEntity getSelectionElement() {
        CityEntity temp = dataCityList.getSelectionModel().getSelectedItem();
        return temp;
    }

    @FXML
    private ListView<CityEntity> dataCityList;

    @FXML
    private TextField textTitleCity;

    @FXML
    private void initialize(){
        service.getAll();
        dataCityList.setItems(service.getData());
    }

    @FXML
    void addCityAction(ActionEvent event) {
        CityEntity city = new CityEntity();
        city.setTitle(textTitleCity.getText());
        if (addFlag) {
            service.add(city);
        } else {
            city.setId(getSelectionElement().getId());
            service.update(city, getSelectionElement());
        }
        textTitleCity.clear();
    }

    @FXML
    void cancelCityAction(ActionEvent event) {
        addFlag = true;
    }

    @FXML
    void deleteCityAction(ActionEvent event) {

    }
    @FXML
    void onMouseClickDataList(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if (event.getClickCount() == 2){
                addFlag = false;
                CityEntity temp = getSelectionElement();
                textTitleCity.setText(temp.getTitle());
            }
        }
    }

}
