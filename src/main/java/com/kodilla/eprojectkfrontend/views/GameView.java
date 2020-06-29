package com.kodilla.eprojectkfrontend.views;

import com.kodilla.eprojectkfrontend.domains.GameDto;
import com.kodilla.eprojectkfrontend.forms.GameForm;
import com.kodilla.eprojectkfrontend.services.GameService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("gameView")
public class GameView extends VerticalLayout {

    private GameService gameService = new GameService();

    private Grid<GameDto> gridGameDto = new Grid<>(GameDto.class);
    private Grid<GameDto> gridSearchResult = new Grid<>(GameDto.class);

    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to Quotes!");
    private Button goToMotiveView = new Button("Go to Motives!");
    private Button goToMovieView = new Button("Go to Movies!");
    private Button goToBookView = new Button("Go to Books!");
    private Button goToTvShowView = new Button("Go to TV Shows!");

    private GameForm gameForm = new GameForm(this);

    private Label gameViewLabel = new Label("Games");
    private Label gridGameDtoLabel = new Label("Main view from Games");
    private Label gridSearchResultLabel = new Label("Search results from Games");

    private TextArea tutorialGames = new TextArea();

    public Grid<GameDto> getGridGameDto() {
        return gridGameDto;
    }

    public Grid<GameDto> gridSearchResult() {
        return gridSearchResult;
    }

    public GameView() {
        HorizontalLayout mainContent = new HorizontalLayout(gridGameDtoLabel, gridGameDto);
        mainContent.setSizeFull();

        HorizontalLayout secondContent = new HorizontalLayout(gridSearchResult, gameForm);
        secondContent.setSizeFull();

        HorizontalLayout goTos = new HorizontalLayout(goToMotiveView, goToBookView, goToMovieView,
                goToTvShowView, goToLoveView, goToQuoteView);

        gridGameDto.setColumns("gameTitle", "gameDeveloper", "gameRating");
        gridGameDto.setSizeFull();
        gridGameDto.setHeightByRows(true);
        gridGameDto.setMaxHeight("200px");
        gridGameDto.asSingleSelect().addValueChangeListener(event -> check());

        gridSearchResult.setColumns("gameTitle", "gameDeveloper", "gameRating");
        gridSearchResult.setSizeFull();
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> gameForm.setGameDto(gridSearchResult.asSingleSelect().getValue()));

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        goToMotiveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMotiveView.addClickListener(event -> goToMotiveView.getUI().ifPresent(ui -> ui.navigate("motiveView")));

        goToMovieView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMovieView.addClickListener(event -> goToMovieView.getUI().ifPresent(ui -> ui.navigate("movieView")));

        goToBookView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToBookView.addClickListener(event -> goToBookView.getUI().ifPresent(ui -> ui.navigate("bookView")));

        goToTvShowView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToTvShowView.addClickListener(event -> goToTvShowView.getUI().ifPresent(ui -> ui.navigate("tvShowView")));

        tutorialGames.setReadOnly(true);
        tutorialGames.setValue("Add your favourite games, and rate them! Or add empty row. When in doubt, refresh page!");
        tutorialGames.setAutofocus(true);
        tutorialGames.setWidthFull();

        add(goTos);
        add(gameViewLabel);
        add(tutorialGames);
        add(mainContent);
        add(gridSearchResultLabel);
        add(secondContent);

        setSizeFull();
        refresh();
    }

    public void refresh() {
        gridGameDto.setItems(gameService.getAllGame());
    }

    public void refreshByAllDevelopers(String author) {
        gridSearchResult.setItems(gameService.findGameByDeveloper(author));
    }

    public void refreshByAllRatings(String gameRating) {
        gridSearchResult.setItems(gameService.findGameByRating(gameRating));
    }

    public void check(){
        gameForm.setGameDto(gridGameDto.asSingleSelect().getValue());
        gameForm.getUpdateGame().setEnabled(true);
        gameForm.getDeleteGame().setEnabled(true);
    }
}