package com.kodilla.eprojectkfrontend.services;

import com.kodilla.eprojectkfrontend.domains.QuotesDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class QuotesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuotesService.class);

    private static final QuotesDto errorMessageGetQuoteByAuthorClient =
            new QuotesDto("Please try again\n", "Example: John, Adam", "", "", "", "", "");

    private static final QuotesDto errorMessageGetQuoteByKeywordClient =
            new QuotesDto("Please try again\n", "Check you spelling", "Example: Wisdom, Life", "", "", "", "");

    private RestTemplate restTemplate = new RestTemplate();

    public QuotesDto getQuoteByKeyword(final QuotesDto quotesDto) throws HttpServerErrorException {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/quotes/?keyword=" + quotesDto.getKeywords())
                    .build().encode().toUri();

            return restTemplate.getForObject(uri, QuotesDto.class);
        } catch (HttpServerErrorException e) {
            LOGGER.warn("User did not follow tutorial " + e);
        }
        return errorMessageGetQuoteByKeywordClient;
    }

    public QuotesDto getQuoteByAuthor(final QuotesDto quotesDto) throws HttpServerErrorException {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/quotes/byAuthor?author=" + quotesDto.getAuthor())
                    .build().encode().toUri();

            return restTemplate.getForObject(uri, QuotesDto.class);
        } catch (HttpServerErrorException e) {
            LOGGER.warn("User did not follow tutorial " + e);
        }
        return errorMessageGetQuoteByAuthorClient;
    }

    public QuotesDto getRandomQuote() throws HttpServerErrorException {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://inspirationvibebackend.herokuapp.com/eprojectk/quotes/getRandom")
                .build().encode().toUri();
        return restTemplate.getForObject(uri, QuotesDto.class);
    }
}
