package com.kodilla.eprojectkfrontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

public class MainView extends VerticalLayout {

    private Button goToMotiveView = new Button("Go to Motives!");
    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to 150000+ Quotes!");
    private Button goToBookView = new Button("Go to Books!");
    private Button goToMovieView = new Button("Go to Movies!");
    private Button goToGameView = new Button("Go to Games!");
    private Button goToTvShowView = new Button("Go to TV Shows!");

    private Label mainViewLabel = new Label("Welcome to InspirationVibe");
    private Icon vaadinIcon = new Icon(VaadinIcon.VAADIN_V);

    public MainView() {
        goToMotiveView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToMotiveView.setAutofocus(true);
        goToMotiveView.addClickListener(event -> goToMotiveView.getUI().ifPresent(ui -> ui.navigate("motiveView")));

        goToLoveView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToLoveView.setAutofocus(true);
        goToLoveView.addClickListener(event -> goToLoveView.getUI().ifPresent(ui -> ui.navigate("loveCalculatorView")));

        goToQuoteView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToQuoteView.setAutofocus(true);
        goToQuoteView.addClickListener(event -> goToQuoteView.getUI().ifPresent(ui -> ui.navigate("quotesView")));

        goToBookView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToBookView.setAutofocus(true);
        goToBookView.addClickListener(event -> goToBookView.getUI().ifPresent(ui -> ui.navigate("bookView")));

        goToMovieView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToMovieView.setAutofocus(true);
        goToMovieView.addClickListener(event -> goToMovieView.getUI().ifPresent(ui -> ui.navigate("movieView")));

        goToGameView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToGameView.setAutofocus(true);
        goToGameView.addClickListener(event -> goToGameView.getUI().ifPresent(ui -> ui.navigate("gameView")));

        goToTvShowView.addThemeVariants(ButtonVariant.LUMO_LARGE);
        goToTvShowView.setAutofocus(true);
        goToTvShowView.addClickListener(event -> goToTvShowView.getUI().ifPresent(ui -> ui.navigate("tvShowView")));

        mainViewLabel.setWidthFull();

        vaadinIcon.setColor("blue");
        vaadinIcon.setSize("66px");

        HorizontalLayout goToApiButtons = new HorizontalLayout();
        goToApiButtons.add(goToLoveView);
        goToApiButtons.add(goToQuoteView);

        HorizontalLayout secondRowView = new HorizontalLayout();
        secondRowView.add(goToGameView);
        secondRowView.add(goToTvShowView);

        add(mainViewLabel);
        add(goToMotiveView, goToBookView, goToMovieView);
        add(secondRowView);
        add(vaadinIcon);
        add(goToApiButtons);

        setAlignItems(Alignment.CENTER);

        setSizeFull();
    }
}