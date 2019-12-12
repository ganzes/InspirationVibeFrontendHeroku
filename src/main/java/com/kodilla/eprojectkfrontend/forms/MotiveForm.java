package com.kodilla.eprojectkfrontend.forms;

import com.kodilla.eprojectkfrontend.views.MotiveView;
import com.kodilla.eprojectkfrontend.domains.MotiveDto;
import com.kodilla.eprojectkfrontend.services.MotiveService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class MotiveForm extends FormLayout {

    private MotiveView motiveView;

    private TextField motiveText = new TextField("Text");
    private TextField motiveAuthor = new TextField("Author");
    private TextField motiveRating = new TextField("Rating");

    private Button saveMotive = new Button("Add");
    private Button deleteMotive = new Button("Delete");
    private Button updateMotive = new Button("Update");
    private Button deleteAllMotives = new Button("Delete All");
    private Button findMotiveByAuthor = new Button("Find by Author");

    private Binder<MotiveDto> binder = new Binder<>(MotiveDto.class);

    private MotiveService motiveService = new MotiveService();

    private MotiveDto motiveDto = new MotiveDto();

    public MotiveForm(MotiveView motiveView) {
        this.motiveView = motiveView;

        HorizontalLayout buttons = new HorizontalLayout(saveMotive, deleteMotive, updateMotive, deleteAllMotives);
        HorizontalLayout buttonsSecondRow = new HorizontalLayout(findMotiveByAuthor);

        saveMotive.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteMotive.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateMotive.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteAllMotives.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findMotiveByAuthor.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(motiveText, motiveAuthor, motiveRating, buttons, buttonsSecondRow);

        binder.bindInstanceFields(this);
        binder.setBean(motiveDto);

        saveMotive.addClickListener(event -> saveMotive());
        deleteMotive.addClickListener(event -> deleteMotive());
        updateMotive.addClickListener(event -> updateMotive());
        deleteAllMotives.addClickListener(event -> deleteAllMotives());

        findMotiveByAuthor.addClickListener(event -> findMotiveByAuthor());
    }

    private void saveMotive() {
        MotiveDto motiveDto = binder.getBean();
        motiveService.createMotive(motiveDto);
        motiveView.refresh();
    }

    private void deleteMotive() {
        MotiveDto motiveDto = binder.getBean();
        motiveService.deleteMotive(motiveDto.getMotiveID());
        motiveView.refresh();
    }

    private void deleteAllMotives() {
        motiveService.deleteAllMotives();
        motiveView.refresh();
    }

    public void updateMotive() {
        MotiveDto motiveDto = binder.getBean();
        motiveService.updateMotive(motiveDto);
        motiveView.refresh();
    }

    public void findMotiveByAuthor(){
        String motiveDto = binder.getBean().getMotiveAuthor();
        motiveService.findMotiveByAuthor(motiveDto);
        motiveView.refreshByAllAuthors(motiveDto);
    }

    public void setMotiveDto(MotiveDto motiveDto) {
        binder.setBean(motiveDto);
    }
}