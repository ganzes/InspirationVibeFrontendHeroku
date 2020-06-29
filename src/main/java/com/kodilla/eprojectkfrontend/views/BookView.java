package com.kodilla.eprojectkfrontend.views;

import com.kodilla.eprojectkfrontend.domains.BookDto;
import com.kodilla.eprojectkfrontend.forms.BookForm;
import com.kodilla.eprojectkfrontend.services.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

@Route("bookView")
public class BookView extends VerticalLayout {

    private BookService bookService = new BookService();

    private Grid<BookDto> gridBookDto = new Grid<>(BookDto.class);
    private Grid<BookDto> gridSearchResult = new Grid<>(BookDto.class);

    private Button goToLoveView = new Button("Go to Love Calculator!");
    private Button goToQuoteView = new Button("Go to Quotes!");
    private Button goToMotiveView = new Button("Go to Motives!");
    private Button goToMovieView = new Button("Go to Movies!");
    private Button goToGameView = new Button("Go to Games!");
    private Button goToTvShowView = new Button("Go to TV Shows!");

    private BookForm bookForm = new BookForm(this);

    private Label bookViewLabel = new Label("Books");
    private Label gridBookDtoLabel = new Label("Main view from Books");
    private Label gridSearchResultLabel = new Label("Search results from Books");

    private TextArea tutorialBooks = new TextArea();

    public Grid<BookDto> getGridBookDto() {
        return gridBookDto;
    }

    public Grid<BookDto> gridSearchResult() {
        return gridSearchResult;
    }

    public BookView() {
        HorizontalLayout goTos = new HorizontalLayout(goToMotiveView, goToMovieView, goToGameView,
                goToTvShowView, goToLoveView, goToQuoteView);

        HorizontalLayout mainContent = new HorizontalLayout(gridBookDtoLabel, gridBookDto);
        mainContent.setSizeFull();

        HorizontalLayout secondContent = new HorizontalLayout(gridSearchResult, bookForm);
        secondContent.setSizeFull();

        gridBookDto.setColumns("bookTitle", "bookAuthor", "bookRating");
        gridBookDto.setSizeFull();
        gridBookDto.asSingleSelect().addValueChangeListener(event -> check());

        gridSearchResult.setColumns("bookTitle", "bookAuthor", "bookRating");
        gridSearchResult.setSizeFull();
        gridSearchResult.asSingleSelect().addValueChangeListener(event -> bookForm.setBookDto(gridSearchResult.asSingleSelect().getValue()));

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

        goToTvShowView.addThemeVariants(ButtonVariant.LUMO_SMALL);
        goToTvShowView.addClickListener(event -> goToTvShowView.getUI().ifPresent(ui -> ui.navigate("tvShowView")));

        tutorialBooks.setReadOnly(true);
        tutorialBooks.setValue("Add your favourite books, and rate them! Or add empty row. When in doubt, refresh page!");
        tutorialBooks.setAutofocus(true);
        tutorialBooks.setWidthFull();

        add(goTos);
        add(bookViewLabel);
        add(tutorialBooks);
        add(mainContent);
        add(gridSearchResultLabel);
        add(secondContent);

        setSizeFull();
        refresh();
    }

    public void refresh() {
        gridBookDto.setItems(bookService.getAllBook());
    }

    public void refreshByAllAuthors(String author) {
        gridSearchResult.setItems(bookService.findBookByAuthor(author));
    }

    public void refreshByAllRatings(String bookRating) {
        gridSearchResult.setItems(bookService.findBookByRating(bookRating));
    }

    public void check(){
        bookForm.setBookDto(gridBookDto.asSingleSelect().getValue());
        bookForm.getUpdateBook().setEnabled(true);
        bookForm.getDeleteBook().setEnabled(true);
    }
}