package com.example.clientv2.service;

import com.example.clientv2.entity.PublisherEntity;
import com.example.clientv2.entity.BookEntity;
import com.example.clientv2.entity.PublisherEntity;
import com.example.clientv2.response.BaseResponse;
import com.example.clientv2.response.DataResponse;
import com.example.clientv2.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class PublishService {
    @Getter
    private ObservableList<PublisherEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<PublisherEntity>>() {}.getType();
    private Type listType = new TypeToken<ListResponse<PublisherEntity>>() {}.getType();
    private void sort(){
        data.sort(Comparator.comparing(PublisherEntity::getTitle));
    }

    public void getAll() {
        ListResponse<PublisherEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllPublisher()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getDataList());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(PublisherEntity data) {
        String temp = http.post(prop.getSavePublisher(), service.getJson(data));
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(PublisherEntity after, PublisherEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdatePublisher(), service.getJson(after));
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    //у последних двух на сервере нужно дописать TODO
    public void delete(PublisherEntity data) {
        String temp = http.delete(prop.getDeletePublisher(), service.getJson(data));
        DataResponse<PublisherEntity> response = service.getObject(temp, BaseResponse.class);
        if (response.isSuccess()) {
            this.data.remove(data);
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(PublisherEntity data) {
        String temp = http.get(prop.getFindByIdPublisher() + data.getId());
        DataResponse<PublisherEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
