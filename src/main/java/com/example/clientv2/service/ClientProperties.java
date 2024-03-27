package com.example.clientv2.service;

import com.example.clientv2.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter

public class ClientProperties {
    private final Properties properties = new Properties();

    private String allBook;
    private String findByIdBook;
    private String saveBook;
    private String updateBook;
    private String findByIdAuthorInBook;
    private String findByIdTitleInBook;
    private String deleteBook;

    private String allCity;
    private String findByIdCity;
    private String saveCity;
    private String updateCity;
    private String deleteCity;

    private String allGenre;
    private String findByIdGenre;
    private String saveGenre;
    private String updateGenre;
    private String deleteGenre;

    private String allPublisher;
    private String findByIdPublisher;
    private String savePublisher;
    private String updatePublisher;
    private String deletePublisher;

    private String allAuthor;
    private String findByIdAuthor;
    private String saveAuthor;
    private String updateAuthor;
    private String deleteAuthor;

    public ClientProperties(){
        try (InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(input);
            properties.load(input);

            allBook = properties.getProperty("book.getAll");
            findByIdBook = properties.getProperty("book.findById");
            saveBook = properties.getProperty("book.save");
            updateBook = properties.getProperty("book.update");
            deleteBook = properties.getProperty("book.del");
            findByIdAuthorInBook = properties.getProperty("book.findByAuthor");
            findByIdTitleInBook = properties.getProperty("book.findByTitle");

            allCity = properties.getProperty("city.getAll");
            findByIdCity = properties.getProperty("city.findById");
            saveCity = properties.getProperty("city.save");
            updateCity = properties.getProperty("city.update");
            deleteCity = properties.getProperty("city.del");

            allGenre = properties.getProperty("genre.getAll");
            findByIdGenre = properties.getProperty("genre.findById");
            saveGenre = properties.getProperty("genre.save");
            updateGenre = properties.getProperty("genre.update");
            deleteGenre = properties.getProperty("genre.del");

            allPublisher = properties.getProperty("publisher.getAll");
            findByIdPublisher = properties.getProperty("publisher.findById");
            savePublisher = properties.getProperty("publisher.save");
            updatePublisher = properties.getProperty("publisher.update");
            deletePublisher = properties.getProperty("publisher.del");

            allAuthor = properties.getProperty("author.getAll");
            findByIdAuthor = properties.getProperty("author.findById");
            saveAuthor = properties.getProperty("author.save");
            updateAuthor = properties.getProperty("author.update");
            deleteAuthor = properties.getProperty("author.del");



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
