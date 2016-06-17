/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.vaadin.ui.CustomComponent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Vladimir.Avtushko
 */
@SpringView(name = testPage.Test_NAME)
public class testPage extends CustomComponent implements View {

    public static final String Test_NAME = "test";
    private Navigator navigator;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        navigator = getUI().getNavigator();
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo("login");
            }
        });
        layout.addComponent(button);
        setCompositionRoot(layout);
    }
}
