package com.example.clientv2.service;

import com.example.clientv2.entity.AuthorEntity;
import com.example.clientv2.entity.AuthorEntity;
import com.example.clientv2.response.BaseResponse;
import com.example.clientv2.response.DataResponse;
import com.example.clientv2.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class AuthorService {
        @Getter
        private ObservableList<AuthorEntity> data = FXCollections.observableArrayList();
        private final HttpService http = new HttpService();
        JsonService service = new JsonService();
        ClientProperties prop = new ClientProperties();
        private Type dataType = new TypeToken<DataResponse<AuthorEntity>>() {}.getType();
        private Type listType = new TypeToken<ListResponse<AuthorEntity>>() {}.getType();
        private void sort(){
            data.sort(Comparator.comparing(AuthorEntity::getSurname));
        }

        public void getAll() {
            ListResponse<AuthorEntity> data = new ListResponse<>();
            data = service.getObject(http.get(prop.getAllAuthor()), listType);
            if (data.isSuccess()) {
                this.data.addAll(data.getDataList());
                sort();
                this.data.forEach(System.out::println);
            } else {
                throw new RuntimeException(data.getMessage());
            }
        }

        public void add(AuthorEntity data) {
            String temp = http.post(prop.getSaveAuthor(), service.getJson(data));
            DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
            if (response.isSuccess()) {
                this.data.add(response.getData());
                sort();
            } else {
                throw new RuntimeException(response.getMessage());
            }
        }

        public void update(AuthorEntity after, AuthorEntity before) {
            System.out.println(before);
            System.out.println(after);
            String temp = http.put(prop.getUpdateAuthor(), service.getJson(after));
            DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
            if (response.isSuccess()) {
                this.data.remove(before);
                this.data.add(after);
                sort();
            } else {
                throw new RuntimeException(response.getMessage());
            }
        }

        //у последних двух на сервере нужно дописать TODO
        public void delete(AuthorEntity data) {
            String temp = http.delete(prop.getDeleteAuthor(), service.getJson(data));
            DataResponse<AuthorEntity> response = service.getObject(temp, BaseResponse.class);
            if (response.isSuccess()) {
                this.data.remove(data);
            } else {
                throw new RuntimeException(response.getMessage());
            }
        }

        public void findById(AuthorEntity data) {
            String temp = http.get(prop.getFindByIdAuthor() + data.getId());
            DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
            if (response.isSuccess()) {
                this.data.add(response.getData());
            } else {
                throw new RuntimeException(response.getMessage());
            }
        }

    }
