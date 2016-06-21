/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.trender.service.QuestionService;
import com.trender.user.component.Header;
import com.trender.user.component.wizard.LastStepWizardStudy;
import com.trender.user.component.wizard.ListenStepWizardStudy;
import com.trender.user.component.wizard.SetupStepWizardStudy;
import com.trender.user.component.wizard.IntroStepWizardStudy;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

/**
 *
 * @author Vladimir
 */
@SpringView(name = AddEditStudyView.ADD_EDIT_STUDY_VIEW_NAME)
public class AddEditStudyView extends CustomComponent implements View, WizardProgressListener {

    @Autowired
    private QuestionService questionService;

    public static final String ADD_EDIT_STUDY_VIEW_NAME = "addEditStudy";
    private Wizard wizard;
    private VerticalLayout mainLayout;
    private Thread update;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        wizard = new Wizard();
        //       wizard.setUriFragmentEnabled(true);
        wizard.addStep(new IntroStepWizardStudy(), "intro");
        wizard.addStep(new SetupStepWizardStudy(questionService), "setup");
        wizard.addStep(new ListenStepWizardStudy(), "listen");
        wizard.addStep(new LastStepWizardStudy(wizard), "last");
        wizard.setWidth(90, Unit.PERCENTAGE);
        wizard.setHeight(95, Unit.PERCENTAGE);
        wizard.getBackButton().setCaption("Назад");
        wizard.getCancelButton().setCaption("Отменить");
        wizard.getNextButton().setCaption("Далее");
        wizard.getFinishButton().setCaption("Закончить");
        wizard.addListener(this);

        Component head = new Header().intitUserPageComponent("ПОЛЬЗОВАТЕЛЬ 1");
        mainLayout.addComponent(head);
        mainLayout.setExpandRatio(head, 15);
        mainLayout.addComponent(wizard);
        mainLayout.setComponentAlignment(wizard, Alignment.TOP_CENTER);
        mainLayout.setExpandRatio(wizard, 85);
        setCompositionRoot(mainLayout);
        setSizeFull();
    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        //       endWizard("Wizard Completed!");
    }

    @Override
    public void activeStepChanged(WizardStepActivationEvent event) {
        Page.getCurrent().setTitle(event.getActivatedStep().getCaption());

        if (event.getWizard().isCompleted(event.getWizard().getSteps().get(1))) {
            SetupStepWizardStudy obj = (SetupStepWizardStudy) event.getWizard().getSteps().get(1);
            TextField[] questionField = obj.getQuestionField();
            for (int j = 0; j < questionField.length; j++) {
                System.out.println(questionField[j].getValue());
            }
        }

        if (event.getWizard().isActive(event.getWizard().getSteps().get(
                event.getWizard().getSteps().size() - 2))) {

            ListenStepWizardStudy obj = (ListenStepWizardStudy) event.getWizard().getSteps().get(
                    event.getWizard().getSteps().size() - 2);

            obj.getThreadProgressBar().start();
            getUI().setPollInterval(500);
            event.getWizard().next();
        }
    }

    @Override
    public void stepSetChanged(WizardStepSetChangedEvent event) {
    }

    @Override
    public void wizardCancelled(WizardCancelledEvent event) {
//        endWizard("Wizard Cancelled!");
    }

//    private void endWizard(String message) {
//        wizard.setVisible(false);
//        Notification.show(message);
//        Page.getCurrent().setTitle(message);
//        Button startOverButton = new Button("Run the demo again",
//                new Button.ClickListener() {
//            public void buttonClick(Button.ClickEvent event) {
//                // Close the session and reload the page.
//                VaadinSession.getCurrent().close();
//                Page.getCurrent().setLocation("");
//            }
//        });
//        mainLayout.addComponent(startOverButton);
//        mainLayout.setComponentAlignment(startOverButton,
//                Alignment.MIDDLE_CENTER);
//    }
}
