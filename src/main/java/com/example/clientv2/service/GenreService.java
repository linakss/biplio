package com.example.clientv2.service;

import com.example.clientv2.entity.GenreEntity;
import com.example.clientv2.entity.GenreEntity;
import com.example.clientv2.response.BaseResponse;
import com.example.clientv2.response.DataResponse;
import com.example.clientv2.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class GenreService {
    @Getter
    private ObservableList<GenreEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<GenreEntity>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<GenreEntity>>() {}.getType();
    private void sort(){
        data.sort(Comparator.comparing(GenreEntity::getTitle));
    }

    public void getAll() {
        ListResponse<GenreEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllGenre()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getDataList());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(GenreEntity data) {
        String temp = http.post(prop.getSaveGenre(), service.getJson(data));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(GenreEntity after, GenreEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateGenre(), service.getJson(after));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    //у последних двух на сервере нужно дописать TODO
    public void delete(GenreEntity data) {
        String temp = http.delete(prop.getDeleteGenre(), service.getJson(data));
        DataResponse<GenreEntity> response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(GenreEntity data) {
        String temp = http.get(prop.getFindByIdGenre() + data.getId());
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
