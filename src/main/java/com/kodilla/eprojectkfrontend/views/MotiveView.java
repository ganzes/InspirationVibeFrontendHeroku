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

    public MotiveView(){
        gridMotiveDto.setColumns("motiveText", "motiveAuthor", "motiveRating");
        gridSearchResult.setColumns("motiveText", "motiveAuthor", "motiveRating");

        HorizontalLayout sercondContent = new HorizontalLayout(gridSearchResult, motiveForm);
        sercondContent.setSizeFull();
        HorizontalLayout mainContent = new HorizontalLayout(gridMotiveDtoLabel, gridMotiveDto);
        mainContent.setSizeFull();
        gridMotiveDto.setSizeFull();
        gridSearchResult.setSizeFull();

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        HorizontalLayout goTos = new HorizontalLayout(goToLoveView, goToQuoteView);

        tutorialMotives.setReadOnly(true);
        tutorialMotives.setValue("Add motivational quotes from your favourite authors, and rate them!");
        tutorialMotives.setAutofocus(true);
        tutorialMotives.setWidthFull();

        add(goTos);
        add(labelMotiveView);
        add(tutorialMotives);

        add(mainContent);
        add(gridSearchResultLabel);
        add(sercondContent);
        setSizeFull();
        refresh();

        gridMotiveDto.asSingleSelect().addValueChangeListener(event -> motiveForm.setMotiveDto(gridMotiveDto.asSingleSelect().getValue()));
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> motiveForm.setMotiveDto(gridSearchResult.asSingleSelect().getValue()));

    }

    public void refresh(){
        gridMotiveDto.setItems(motiveService.getAllMotive());
    }

    public void refreshByAllAuthors(String author){
        gridSearchResult.setItems(motiveService.findMotiveByAuthor(author));
    }

    public void refreshByAllRatings(String motiveRating){
        gridSearchResult.setItems(motiveService.findMotiveByRating(motiveRating));
    }
}
