/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.trender.user.component.Header;
import com.trender.user.component.UserForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Vladimir
 */
@SpringView(name = ProfilePageView.PROFILE_PAGE_VIEW_NAME)
public class ProfilePageView extends CustomComponent implements View {

    public static final String PROFILE_PAGE_VIEW_NAME = "profile";
    private VerticalLayout verticalLayout;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        verticalLayout = new VerticalLayout();
        Component header = new Header().intitUserPageComponent("ПОЛЬЗОВАТЕЛЬ 1");
        verticalLayout.addComponent(header);
        verticalLayout.setComponentAlignment(header, Alignment.TOP_LEFT);
        verticalLayout.setExpandRatio(header, 12);
        Component form = new UserForm().ProfilForm();
        verticalLayout.addComponent(form);
        verticalLayout.setComponentAlignment(form, Alignment.TOP_CENTER);
        verticalLayout.setExpandRatio(form, 88);
        verticalLayout.setSizeFull();
        setCompositionRoot(verticalLayout);
        setSizeFull();
    }
}
