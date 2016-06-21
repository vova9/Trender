/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.component.wizard;

import com.trender.entity.Question;
import com.trender.service.QuestionService;
import com.trender.service.exception.ServiceException;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.teemu.wizards.WizardStep;

/**
 *
 * @author Vladimir
 */
public class SetupStepWizardStudy implements WizardStep {

    private QuestionService questionService;

    private VerticalLayout content;
    private Label mainText;
    private TextField questionField[];
    private FormLayout form;

    public SetupStepWizardStudy(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String getCaption() {
        return "Иформация об иследонании";
    }

    @Override
    public Component getContent() {
        try {
            content = new VerticalLayout();
            content.setSizeFull();
            content.setMargin(true);

            form = new FormLayout();
            form.addStyleName("light");

            mainText = new Label("Введите информацию");
            mainText.setStyleName("h2");
            form.addComponent(mainText);

            List<Question> question = questionService.getAll();
            questionField = new TextField[question.size()];

            for (int i = 0; i < question.size(); i++) {
                questionField[i] = new TextField(question.get(i).getValue());
                questionField[i].setWidth(95, Unit.PERCENTAGE);
                form.addComponent(questionField[i]);
            }

            content.addComponent(form);
        } catch (ServiceException ex) {
            Logger.getLogger(SetupStepWizardStudy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return true;
    }

    public TextField[] getQuestionField() {
        return questionField;
    }
}
