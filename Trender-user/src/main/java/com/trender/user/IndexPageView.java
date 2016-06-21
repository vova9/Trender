/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.trender.user.component.Header;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;

/**
 *
 * @author Vladimir.Avtushko
 */
@SpringView(name = IndexPageView.INDEX_PAGE_VIEW)
public class IndexPageView extends CustomComponent implements View {

    public static final String INDEX_PAGE_VIEW = "index";

    private Label textLabel, studyTestLable;
    private TreeTable studyTreeTable;
    private VerticalLayout verticalLayout;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }

    //TODO 
    private void init() {

        verticalLayout = new VerticalLayout();

        studyTreeTable = new TreeTable();
        textLabel = new Label();
        studyTestLable = new Label();
        verticalLayout.addComponent(new Header().intitIngexPageComponent());

        textLabel.setValue("МЕСТО ДЛЯ ТЕКСТА! И КАРТИНОК!!!");
        textLabel.addStyleName("h1");
        textLabel.addStyleName("v-label");
        textLabel.addStyleName("v-label-align-center");
        textLabel.setWidth(100, Unit.PERCENTAGE);
        verticalLayout.addComponent(textLabel);
        verticalLayout.setComponentAlignment(textLabel, Alignment.MIDDLE_CENTER);
        studyTestLable.setValue("Вы можите использовать готовые семантические ядро или "
                + "составить свое. Для это нужно зарегистрироваться");

        studyTestLable.setWidth(100, Unit.PERCENTAGE);
        studyTestLable.setHeight(21, Unit.PIXELS);
        verticalLayout.addComponent(studyTestLable);
        studyTreeTable.setWidth(636, Unit.PIXELS);
        studyTreeTable.setHeight(273, Unit.PIXELS);
        verticalLayout.addComponent(studyTreeTable);
        verticalLayout.setSizeFull();

        setCompositionRoot(verticalLayout);
        setSizeFull();
    }
}
