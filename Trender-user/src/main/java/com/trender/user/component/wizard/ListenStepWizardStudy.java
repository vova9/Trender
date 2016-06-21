/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.component.wizard;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.teemu.wizards.WizardStep;

/**
 *
 * @author Vladimir
 */
public class ListenStepWizardStudy implements WizardStep {

    private VerticalLayout content;
    private Label mainText;
    private ProgressBar progressBar;
    private float progress = 0;

    @Override
    public String getCaption() {
        return "Составление семантического ядра";
    }

    @Override
    public Component getContent() {
        content = new VerticalLayout();
        content.setSizeFull();
        content.setMargin(true);

        mainText = new Label("Пожалуйста, подождите, идет составления семантического ядра!");
        mainText.setStyleName("h2");
        content.addComponent(mainText);

        progressBar = new ProgressBar();
        progressBar.setWidth(100, Unit.PERCENTAGE);
        content.addComponent(progressBar);
        return content;
    }

    @Override
    public boolean onAdvance() {
        return false;
    }

    @Override
    public boolean onBack() {
        return false;
    }

    private Thread threadProgressBar = new Thread() {
        @Override
        public void run() {
            while (progress < 1.0) {
                progress += 0.01;
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                }

                UI.getCurrent().access(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setValue(progress);
                    }
                });
            }

            UI.getCurrent().access(new Runnable() {
                @Override
                public void run() {
                    UI.getCurrent().setPollInterval(-1);
                }
            });
        }
    };

    public Thread getThreadProgressBar() {
        return threadProgressBar;
    }
}
