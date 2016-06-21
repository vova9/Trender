
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.component.wizard;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.teemu.wizards.WizardStep;

/**
 *
 * @author Vladimir
 */
public class IntroStepWizardStudy implements WizardStep {

    private VerticalLayout content;
    private Embedded arrow;
    private Label mainText;

    @Override
    public String getCaption() {
        return "Начало";
    }

    @Override
    public Component getContent() {
        content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(true);

        mainText = getText();
        content.addComponent(mainText);

        arrow = new Embedded("", new ThemeResource("img/arrow-down.png"));
        arrow.setStyleName("intro-arrow");
        content.addComponent(arrow);

        return content;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return false;
    }

    private Label getText() {
        return new Label(
                "<h2>БЛОК ДЛЯ НАПИСАНИЯ ЧТО НУЖНО ДЕЛАТЬ И ПИСАТЬ</h2>"
                + "<p>описания полей и того что нужно написать в поля на следующем шаге</p>",
                ContentMode.HTML);
    }
}
