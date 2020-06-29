package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<BookDto> getAllBook() throws HttpServerErrorException {
        BookDto[] allBookList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/book/getBooks", BookDto[].class);

        assert allBookList != null;

        return new ArrayList<>(Arrays.asList(allBookList));
    }

    public void createBook(final BookDto bookDto) throws HttpServerErrorException {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/book/createBook")
                    .build().encode().toUri();
            restTemplate.postForObject(url, bookDto, BookDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteBook(final Long bookID) throws HttpServerErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/book/deleteBook?bookID=" + bookID);
    }

    public void updateBook(final BookDto bookDto) throws HttpServerErrorException {
        try {
            restTemplate.put("https://inspirationvibebackend.herokuapp.com/eprojectk/book/updateBook", bookDto, BookDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteAllBooks() throws HttpClientErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/book/deleteAllBooks");
    }

    public List<BookDto> findBookByAuthor(final String author) throws HttpServerErrorException {
        BookDto[] allBookList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/book/getBookByAuthor?bookAuthor=" + author, BookDto[].class);

        assert allBookList != null;

        return new ArrayList<>(Arrays.asList(allBookList));
    }

    public List<BookDto> findBookByRating(final String bookRating) throws HttpServerErrorException {
        BookDto[] allBookList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/book/getBookByRating?bookRating=" + bookRating, BookDto[].class);

        assert allBookList != null;
        return new ArrayList<>(Arrays.asList(allBookList));
    }

    public Long countAllBooks() throws NullPointerException {
        return restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/book/countAllBooks", Long.class);
    }
}
