package com.example.clientv2.service;

import com.example.clientv2.entity.BookEntity;
import com.example.clientv2.response.DataResponse;
import com.example.clientv2.response.BaseResponse;
import com.example.clientv2.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;

public class BookService {
    @Getter
    private ObservableList<BookEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<BookEntity>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<BookEntity>>() {}.getType();

    public void getAll() {
        ListResponse<BookEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllBook()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getDataList());
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(BookEntity data) {
        String temp = http.post(prop.getSaveBook(), service.getJson(data));
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(BookEntity data) {
        String temp = http.put(prop.getUpdateBook(), service.getJson(data));
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(data);
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    //у последних двух на сервере нужно дописать TODO
    public void delete(BookEntity data) {
        String temp = http.delete(prop.getDeleteBook(), service.getJson(data));
        DataResponse<BookEntity> response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(BookEntity data) {
        String temp = http.get(prop.getFindByIdBook() + data.getId());
        DataResponse<BookEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

}
