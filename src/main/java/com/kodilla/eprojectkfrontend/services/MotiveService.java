package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.MotiveDto;
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
public class MotiveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotiveService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public List<MotiveDto> getAllMotive() throws HttpServerErrorException {
        MotiveDto[] allMotiveList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/getMotives", MotiveDto[].class);

        return new ArrayList<>(Arrays.asList(allMotiveList));
    }

    public void createMotive(final MotiveDto motiveDto) throws HttpServerErrorException {
        try {
            URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/createMotive")
                    .build().encode().toUri();
            restTemplate.postForObject(url, motiveDto, MotiveDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteMotive(final Long motiveID) throws HttpClientErrorException {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/deleteMotive?motiveID=" + motiveID);
    }

    public void updateMotive(final MotiveDto motiveDto) throws HttpServerErrorException {
        try {
            restTemplate.put("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/updateMotive", motiveDto, MotiveDto.class);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("User out of bounds! " + e);
        }
    }

    public void deleteAllMotives() {
        restTemplate.delete("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/deleteAllMotives");
    }

    public List<MotiveDto> findMotiveByAuthor(final String author) throws HttpServerErrorException {
        MotiveDto[] allMotiveList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/getMotiveByAuthor?motiveAuthor=" + author, MotiveDto[].class);

        assert allMotiveList != null;
        return new ArrayList<>(Arrays.asList(allMotiveList));
    }

    public List<MotiveDto> findMotiveByRating(final String motiveRating) throws HttpServerErrorException {
        MotiveDto[] allMotiveList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/getMotiveByRating?motiveRating=" + motiveRating, MotiveDto[].class);

        assert allMotiveList != null;
        return new ArrayList<>(Arrays.asList(allMotiveList));
    }

    public List<MotiveDto> getMotivesFacade() throws HttpServerErrorException {
        MotiveDto[] allMotiveList = restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/getMotivesFacade", MotiveDto[].class);

        assert allMotiveList != null;
        return new ArrayList<>(Arrays.asList(allMotiveList));
    }

    public Long countAllMotives() throws NullPointerException {
        return restTemplate.getForObject("https://inspirationvibebackend.herokuapp.com/eprojectk/motive/countAllMotives", Long.class);
    }
}
