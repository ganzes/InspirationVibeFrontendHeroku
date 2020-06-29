package com.kodilla.eprojectkfrontend.views;

import com.kodilla.eprojectkfrontend.domains.TvShowDto;
import com.kodilla.eprojectkfrontend.forms.TvShowForm;
import com.kodilla.eprojectkfrontend.services.TvShowService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("tvShowView")
public class TvShowView extends VerticalLayout {

    private TvShowService tvShowService = new TvShowService();
    private Grid<TvShowDto> gridTvShowDto = new Grid<>(TvShowDto.class);
    private Grid<TvShowDto> gridSearchResult = new Grid<>(TvShowDto.class);

    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to Quotes!");
    private Button goToMotiveView = new Button("Go to Motives!");
    private Button goToMovieView = new Button("Go to Movies!");
    private Button goToGameView = new Button("Go to Games!");
    private Button goToBookView = new Button("Go to Books!");

    private TvShowForm tvShowForm = new TvShowForm(this);

    private Label labelTvShowView = new Label("TvShows");
    private Label gridTvShowDtoLabel = new Label("Main view from TvShows");
    private Label gridSearchResultLabel = new Label("Search results from TvShows");

    private TextArea tutorialTvShows = new TextArea();

    public Grid<TvShowDto> getGridTvShowDto() {
        return gridTvShowDto;
    }

    public Grid<TvShowDto> gridSearchResult() {
        return gridSearchResult;
    }

    public TvShowView() {
        HorizontalLayout mainContent = new HorizontalLayout(gridTvShowDtoLabel, gridTvShowDto);
        mainContent.setSizeFull();

        HorizontalLayout secondContent = new HorizontalLayout(gridSearchResult, tvShowForm);
        secondContent.setSizeFull();

        HorizontalLayout goTos = new HorizontalLayout(goToMotiveView, goToMovieView, goToGameView,
                goToBookView, goToLoveView, goToQuoteView);

        gridTvShowDto.setColumns("tvShowTitle", "tvShowCategory", "tvShowRating");
        gridTvShowDto.setSizeFull();
        gridTvShowDto.setHeightByRows(true);
        gridTvShowDto.setMaxHeight("200px");
        gridTvShowDto.asSingleSelect().addValueChangeListener(event -> check());

        gridSearchResult.setColumns("tvShowTitle", "tvShowCategory", "tvShowRating");
        gridSearchResult.setSizeFull();
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> tvShowForm.setTvShowDto(gridSearchResult.asSingleSelect().getValue()));

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        goToMotiveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMotiveView.addClickListener(event -> goToMotiveView.getUI().ifPresent(ui -> ui.navigate("motiveView")));

        goToMovieView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMovieView.addClickListener(event -> goToMovieView.getUI().ifPresent(ui -> ui.navigate("movieView")));

        goToGameView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToGameView.addClickListener(event -> goToGameView.getUI().ifPresent(ui -> ui.navigate("gameView")));

        goToBookView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToBookView.addClickListener(event -> goToBookView.getUI().ifPresent(ui -> ui.navigate("bookView")));

        tutorialTvShows.setReadOnly(true);
        tutorialTvShows.setValue("Add your favourite tvShows, and rate them! Or add empty row. When in doubt, refresh page!");
        tutorialTvShows.setAutofocus(true);
        tutorialTvShows.setWidthFull();

        add(goTos);
        add(labelTvShowView);
        add(tutorialTvShows);

        add(mainContent);
        add(gridSearchResultLabel);
        add(secondContent);

        setSizeFull();

        refresh();
    }

    public void refresh() {
        gridTvShowDto.setItems(tvShowService.getAllTvShow());
    }

    public void refreshByAllCategorys(String category) {
        gridSearchResult.setItems(tvShowService.findTvShowByCategory(category));
    }

    public void refreshByAllRatings(String tvShowRating) {
        gridSearchResult.setItems(tvShowService.findTvShowByRating(tvShowRating));
    }

    public void check(){
        tvShowForm.setTvShowDto(gridTvShowDto.asSingleSelect().getValue());
        tvShowForm.getUpdateTvShow().setEnabled(true);
        tvShowForm.getDeleteTvShow().setEnabled(true);
    }
}