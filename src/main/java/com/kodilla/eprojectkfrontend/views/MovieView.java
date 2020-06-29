package com.kodilla.eprojectkfrontend.views;

import com.kodilla.eprojectkfrontend.domains.MovieDto;
import com.kodilla.eprojectkfrontend.forms.MovieForm;
import com.kodilla.eprojectkfrontend.services.MovieService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("movieView")
public class MovieView extends VerticalLayout {

    private MovieService movieService = new MovieService();
    private Grid<MovieDto> gridMovieDto = new Grid<>(MovieDto.class);
    private Grid<MovieDto> gridSearchResult = new Grid<>(MovieDto.class);

    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to Quotes!");
    private Button goToMotiveView = new Button("Go to Motives!");
    private Button goToBookView = new Button("Go to Books!");
    private Button goToGameView = new Button("Go to Games!");
    private Button goToTvShowView = new Button("Go to TV Shows!");

    private MovieForm movieForm = new MovieForm(this);

    private Label labelMovieView = new Label("Movies");
    private Label gridMovieDtoLabel = new Label("Main view from Movies");
    private Label gridSearchResultLabel = new Label("Search results from Movies");

    private TextArea tutorialMovies = new TextArea();

    public Grid<MovieDto> getGridMovieDto() {
        return gridMovieDto;
    }

    public Grid<MovieDto> gridSearchResult() {
        return gridSearchResult;
    }

    public MovieView() {
        HorizontalLayout mainContent = new HorizontalLayout(gridMovieDtoLabel, gridMovieDto);
        mainContent.setSizeFull();

        HorizontalLayout secondContent = new HorizontalLayout(gridSearchResult, movieForm);
        secondContent.setSizeFull();

        HorizontalLayout goTos = new HorizontalLayout(goToMotiveView, goToBookView, goToGameView,
                goToTvShowView, goToLoveView, goToQuoteView);

        gridMovieDto.setColumns("movieTitle", "movieDirector", "movieRating");
        gridMovieDto.setSizeFull();
        gridMovieDto.setHeightByRows(true);
        gridMovieDto.setMaxHeight("200px");
        gridMovieDto.asSingleSelect().addValueChangeListener(event -> check());

        gridSearchResult.setColumns("movieTitle", "movieDirector", "movieRating");
        gridSearchResult.setSizeFull();
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> movieForm.setMovieDto(gridSearchResult.asSingleSelect().getValue()));

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        goToMotiveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMotiveView.addClickListener(event -> goToMotiveView.getUI().ifPresent(ui -> ui.navigate("motiveView")));

        goToBookView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToBookView.addClickListener(event -> goToBookView.getUI().ifPresent(ui -> ui.navigate("bookView")));

        goToGameView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToGameView.addClickListener(event -> goToGameView.getUI().ifPresent(ui -> ui.navigate("gameView")));

        goToTvShowView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToTvShowView.addClickListener(event -> goToTvShowView.getUI().ifPresent(ui -> ui.navigate("tvShowView")));

        tutorialMovies.setReadOnly(true);
        tutorialMovies.setValue("Add your favourite movies, and rate them! Or add empty row. When in doubt, refresh page!");
        tutorialMovies.setAutofocus(true);
        tutorialMovies.setWidthFull();

        add(goTos);
        add(labelMovieView);
        add(tutorialMovies);
        add(mainContent);
        add(gridSearchResultLabel);
        add(secondContent);

        setSizeFull();

        refresh();
    }

    public void refresh() {
        gridMovieDto.setItems(movieService.getAllMovie());
    }

    public void refreshByAllDirectors(String author) {
        gridSearchResult.setItems(movieService.findMovieByDirector(author));
    }

    public void refreshByAllRatings(String movieRating) {
        gridSearchResult.setItems(movieService.findMovieByRating(movieRating));
    }

    public void check() {
        movieForm.setMovieDto(gridMovieDto.asSingleSelect().getValue());
        movieForm.getUpdateMovie().setEnabled(true);
        movieForm.getDeleteMovie().setEnabled(true);
    }
}