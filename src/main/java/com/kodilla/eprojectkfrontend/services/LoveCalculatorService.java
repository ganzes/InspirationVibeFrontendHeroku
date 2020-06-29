package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.LoveCalculatorDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class LoveCalculatorService {

    private RestTemplate restTemplate = new RestTemplate();

    public LoveCalculatorDto getPercentage(final LoveCalculatorDto loveCalculatorDto) throws HttpServerErrorException {
        URI url = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/lc/?fname=" + loveCalculatorDto.getFname() + "&sname=" + loveCalculatorDto.getSname())
                .build().encode().toUri();
        return restTemplate.getForObject(url, LoveCalculatorDto.class);
    }
}