package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.MovieDto;
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
public class MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<MovieDto> getAllMovie() throws HttpServerErrorException {
        MovieDto[] allMovieList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/getMovies", MovieDto[].class);

        assert allMovieList != null;

        return new ArrayList<>(Arrays.asList(allMovieList));
    }

    public void createMovie(final MovieDto movieDto) throws HttpServerErrorException {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/createMovie")
                    .build().encode().toUri();
            restTemplate.postForObject(url, movieDto, MovieDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteMovie(final Long movieID) throws HttpClientErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/deleteMovie?movieID=" + movieID);
    }

    public void updateMovie(final MovieDto movieDto) throws HttpServerErrorException {
        try {
            restTemplate.put("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/updateMovie", movieDto, MovieDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteAllMovies() throws HttpServerErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/deleteAllMovies");
    }

    public List<MovieDto> findMovieByDirector(final String author) throws HttpServerErrorException {
        MovieDto[] allMovieList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/getMovieByDirector?movieDirector=" + author, MovieDto[].class);

        assert allMovieList != null;
        return new ArrayList<>(Arrays.asList(allMovieList));
    }

    public List<MovieDto> findMovieByRating(final String movieRating) throws HttpServerErrorException {
        MovieDto[] allMovieList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/getMovieByRating?movieRating=" + movieRating, MovieDto[].class);

        assert allMovieList != null;
        return new ArrayList<>(Arrays.asList(allMovieList));
    }

    public Long countAllMovies() throws NullPointerException {
        return restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/movie/countAllMovies", Long.class);
    }
}