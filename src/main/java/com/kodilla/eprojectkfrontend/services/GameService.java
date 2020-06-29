package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.GameDto;
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
public class GameService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<GameDto> getAllGame() throws HttpServerErrorException {
        GameDto[] allGameList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/game/getGames", GameDto[].class);

        assert allGameList != null;

        return new ArrayList<>(Arrays.asList(allGameList));
    }

    public void createGame(final GameDto gameDto) throws HttpServerErrorException {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/game/createGame")
                    .build().encode().toUri();
            restTemplate.postForObject(url, gameDto, GameDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteGame(final Long gameID) throws HttpClientErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/game/deleteGame?gameID=" + gameID);
    }

    public void updateGame(final GameDto gameDto) throws HttpServerErrorException {
        try {
            restTemplate.put("https://inspirationvibebackend.herokuapp.com/eprojectk/game/updateGame", gameDto, GameDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteAllGames() throws HttpServerErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/game/deleteAllGames");
    }

    public List<GameDto> findGameByDeveloper(final String author) throws HttpServerErrorException {
        GameDto[] allGameList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/game/getGameByDeveloper?gameDeveloper=" + author, GameDto[].class);

        assert allGameList != null;

        return new ArrayList<>(Arrays.asList(allGameList));
    }

    public List<GameDto> findGameByRating(final String gameRating) throws HttpServerErrorException {
        GameDto[] allGameList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/game/getGameByRating?gameRating=" + gameRating, GameDto[].class);

        assert allGameList != null;
        return new ArrayList<>(Arrays.asList(allGameList));
    }

    public Long countAllGames() throws NullPointerException {
        return restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/game/countAllGames", Long.class);
    }
}