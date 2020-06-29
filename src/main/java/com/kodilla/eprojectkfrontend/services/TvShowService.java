package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.TvShowDto;
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
public class TvShowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvShowService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<TvShowDto> getAllTvShow() throws HttpServerErrorException {
        TvShowDto[] allTvShowList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/getTvShows", TvShowDto[].class);

        return new ArrayList<>(Arrays.asList(allTvShowList));
    }

    public void createTvShow(final TvShowDto tvShowDto) throws HttpServerErrorException {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/createTvShow")
                    .build().encode().toUri();
            restTemplate.postForObject(url, tvShowDto, TvShowDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteTvShow(final Long tvShowID) throws HttpClientErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/deleteTvShow?tvShowID=" + tvShowID);
    }

    public void updateTvShow(final TvShowDto tvShowDto) throws HttpServerErrorException {
        try {
            restTemplate.put("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/updateTvShow", tvShowDto, TvShowDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteAllTvShows() throws HttpServerErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/deleteAllTvShows");
    }

    public List<TvShowDto> findTvShowByCategory(final String author) throws HttpServerErrorException {
        TvShowDto[] allTvShowList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/getTvShowByCategory?tvShowCategory=" + author, TvShowDto[].class);

        assert allTvShowList != null;

        return new ArrayList<>(Arrays.asList(allTvShowList));
    }

    public List<TvShowDto> findTvShowByRating(final String tvShowRating) throws HttpServerErrorException {
        TvShowDto[] allTvShowList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/getTvShowByRating?tvShowRating=" + tvShowRating, TvShowDto[].class);

        assert allTvShowList != null;

        return new ArrayList<>(Arrays.asList(allTvShowList));
    }

    public Long countAllTvShows() throws NullPointerException {
        return restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/tvShow/countAllTvShows", Long.class);
    }
}