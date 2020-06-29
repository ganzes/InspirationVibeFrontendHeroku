package com.kodilla.eprojectkfrontend.views;

import com.kodilla.eprojectkfrontend.domains.MotiveDto;
import com.kodilla.eprojectkfrontend.forms.MotiveForm;
import com.kodilla.eprojectkfrontend.services.MotiveService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("motiveView")
public class MotiveView extends VerticalLayout {

    private MotiveService motiveService = new MotiveService();
    private Grid<MotiveDto> gridMotiveDto = new Grid<>(MotiveDto.class);
    private Grid<MotiveDto> gridSearchResult = new Grid<>(MotiveDto.class);

    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to Quotes!");
    private Button goToBookView = new Button("Go to Books!");
    private Button goToMovieView = new Button("Go to Movies!");
    private Button goToGameView = new Button("Go to Games!");
    private Button goToTvShowView = new Button("Go to TV Shows!");

    private MotiveForm motiveForm = new MotiveForm(this);

    private Label labelMotiveView = new Label("Motives");
    private Label gridMotiveDtoLabel = new Label("Main view from Motives");
    private Label gridSearchResultLabel = new Label("Search results from Motives");

    private TextArea tutorialMotives = new TextArea();

    public Grid<MotiveDto> getGridMotiveDto() {
        return gridMotiveDto;
    }

    public Grid<MotiveDto> gridSearchResult() {
        return gridSearchResult;
    }

    public MotiveView() {
        HorizontalLayout goTos = new HorizontalLayout(goToBookView, goToMovieView, goToGameView,
                goToTvShowView, goToLoveView, goToQuoteView);

        HorizontalLayout mainContent = new HorizontalLayout(gridMotiveDtoLabel, gridMotiveDto);
        mainContent.setSizeFull();

        HorizontalLayout secondContent = new HorizontalLayout(gridSearchResult, motiveForm);
        secondContent.setSizeFull();

        gridMotiveDto.setColumns("motiveText", "motiveAuthor", "motiveRating");
        gridMotiveDto.setSizeFull();
        gridMotiveDto.setHeightByRows(true);
        gridMotiveDto.setMaxHeight("200px");
        gridMotiveDto.asSingleSelect().addValueChangeListener(event -> check());

        gridSearchResult.setColumns("motiveText", "motiveAuthor", "motiveRating");
        gridSearchResult.setSizeFull();
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> motiveForm.setMotiveDto(gridSearchResult.asSingleSelect().getValue()));

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        goToBookView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToBookView.addClickListener(event -> goToBookView.getUI().ifPresent(ui -> ui.navigate("bookView")));

        goToMovieView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToMovieView.addClickListener(event -> goToMovieView.getUI().ifPresent(ui -> ui.navigate("movieView")));

        goToGameView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToGameView.addClickListener(event -> goToGameView.getUI().ifPresent(ui -> ui.navigate("gameView")));

        goToTvShowView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToTvShowView.addClickListener(event -> goToTvShowView.getUI().ifPresent(ui -> ui.navigate("tvShowView")));

        tutorialMotives.setReadOnly(true);
        tutorialMotives.setValue("Add motivational quotes from your favourite authors, and rate them! Or add empty row. When in doubt, refresh page!");
        tutorialMotives.setAutofocus(true);
        tutorialMotives.setWidthFull();

        add(goTos);
        add(labelMotiveView);
        add(tutorialMotives);
        add(mainContent);
        add(gridSearchResultLabel);
        add(secondContent);

        setSizeFull();
        refresh();
    }

    public void refresh() {
        gridMotiveDto.setItems(motiveService.getAllMotive());
    }

    public void refreshByAllAuthors(String author) {
        gridSearchResult.setItems(motiveService.findMotiveByAuthor(author));
    }

    public void refreshByAllRatings(String motiveRating) {
        gridSearchResult.setItems(motiveService.findMotiveByRating(motiveRating));
    }

    public void refreshFacade() {
        gridMotiveDto.setItems(motiveService.getMotivesFacade());
    }

    public void check(){
        motiveForm.setMotiveDto(gridMotiveDto.asSingleSelect().getValue());
        motiveForm.getUpdateMotive().setEnabled(true);
        motiveForm.getDeleteMotive().setEnabled(true);
    }
}