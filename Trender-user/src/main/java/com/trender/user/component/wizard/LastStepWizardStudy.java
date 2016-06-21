/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.component.wizard;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;

/**
 *
 * @author Vladimir
 */
public class LastStepWizardStudy implements WizardStep {

    private CheckBox allowBack;
    private VerticalLayout layout;
    private Wizard owner;

    public LastStepWizardStudy(Wizard owner) {
        this.owner = owner;
    }

    @Override
    public String getCaption() {
        return "Итог";
    }

    @Override
    public Component getContent() {
        if (layout == null) {
            allowBack = new CheckBox("типо вопрос открыть или нет?", false);

            layout = new VerticalLayout();
            layout.setMargin(true);
            layout.addComponent(new Label(
                    "<h2>место для текста</h2>",
                    ContentMode.HTML));
//            layout.addComponent(new Button("Add new steps",
//                    new Button.ClickListener() {
//
//                private static final long serialVersionUID = 1L;
//
//                @Override
//                public void buttonClick(Button.ClickEvent event) {
//                    owner.addStep(new WizardStep() {
//
//                        private final Date createdAt = new Date();
//
//                        public boolean onBack() {
//                            return true;
//                        }
//
//                        public boolean onAdvance() {
//                            return true;
//                        }
//
//                        public Component getContent() {
//                            VerticalLayout layout = new VerticalLayout();
//                            layout.setMargin(true);
//                            layout.addComponent(new Label(
//                                    "This step was created on "
//                                    + createdAt));
//                            return layout;
//                        }
//
//                        public String getCaption() {
//                            return "Generated step";
//                        }
//                    });
//                }
//            }));
//
//            layout.addComponent(new Label(
//                    "<h2>Want to go back?</h2><p>This step is also an example of conditionally allowing you to go back.<br />"
//                    + "Try to click the back button and then again after checking the checkbox below.</p>",
//                    ContentMode.HTML));
            layout.addComponent(allowBack);
        }
        return layout;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
//        boolean allowed = allowBack.getValue();
//        if (!allowed) {
//            Notification.show("Not allowed, sorry");
//        }
        return false;
    }
}
