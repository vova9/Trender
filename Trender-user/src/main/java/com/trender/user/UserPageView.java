/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.trender.user.component.Header;
import com.vaadin.ui.CustomComponent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Vladimir.Avtushko
 */
@SpringView(name = UserPageView.USER_PAGE_VIEW_NAME)
public class UserPageView extends CustomComponent implements View {

    public static final String USER_PAGE_VIEW_NAME = "user";

    private VerticalLayout verticalLayout;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(new Header().intitUserPageComponent("ПОЛЬЗОВАТЕЛЬ 1"));
        verticalLayout.setSizeFull();
        setCompositionRoot(verticalLayout);
        setSizeFull();
    }

}
