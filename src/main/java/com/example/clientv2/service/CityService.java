package com.example.clientv2.service;

import com.example.clientv2.entity.BookEntity;
import com.example.clientv2.entity.CityEntity;
import com.example.clientv2.entity.CityEntity;
import com.example.clientv2.response.BaseResponse;
import com.example.clientv2.response.DataResponse;
import com.example.clientv2.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class CityService {
    @Getter
    private ObservableList<CityEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<CityEntity>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<CityEntity>>() {}.getType();
    private void sort(){
        data.sort(Comparator.comparing(CityEntity::getTitle));
    }

    public void getAll() {
        ListResponse<CityEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllCity()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getDataList());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(CityEntity data) {
        String temp = http.post(prop.getSaveCity(), service.getJson(data));
        DataResponse<CityEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(CityEntity after, CityEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateCity(), service.getJson(after));
        DataResponse<CityEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    //у последних двух на сервере нужно дописать TODO
    public void delete(CityEntity data) {
        String temp = http.delete(prop.getDeleteCity(), service.getJson(data));
        DataResponse<CityEntity> response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(CityEntity data) {
        String temp = http.get(prop.getFindByIdCity() + data.getId());
        DataResponse<CityEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
