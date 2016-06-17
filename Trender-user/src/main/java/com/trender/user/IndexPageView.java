/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user;

import com.trender.entity.Role;
import com.trender.entity.User;
import com.trender.service.exception.ServiceException;
import com.trender.service.impl.RoleServiceImpl;
import com.trender.service.impl.UserServiceImpl;
import com.trender.user.hash.Hash;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vladimir.Avtushko
 */
@SpringView(name = IndexPageView.INDEX_PAGE_VIEW)
public class IndexPageView extends CustomComponent implements View {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    public static final String INDEX_PAGE_VIEW = "index";
    private final String regexpValue = "^[A-Za-zА-Яа-яЁё]+$";

    private AbsoluteLayout absoluteLayout;
    private CssLayout header;
    private Button loginButton, regButton, performButton, clearButton;
    private Link link;
    private Label nameCompamy, label2, label3;
    private TreeTable studyTreeTable;
    private TextField emailField, surnameField, nameField;
    private PasswordField passwordField;
    private FormLayout formLayout;
    private Window windowModal;
    private HorizontalLayout horizontalLayout;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        init();
    }

    // ТРЕБУЕТ ДОРАБОТКИ
    private void init() {
        absoluteLayout = new AbsoluteLayout();
        loginButton = new Button();
        regButton = new Button();
        nameCompamy = new Label();
        studyTreeTable = new TreeTable();
        label2 = new Label();
        label3 = new Label();
        link = new Link();
        header = new CssLayout();

        nameCompamy.setValue("Trender");
        nameCompamy.setWidth(79, Unit.PIXELS);
        nameCompamy.setHeight(39, Unit.PIXELS);

        loginButton.setIcon(FontAwesome.USER);
        loginButton.setCaption("Войти");
        regButton.setCaption("Зарегистрироваться");

        header.addComponentAsFirst(nameCompamy);
        header.addComponent(loginButton);
        header.addComponent(regButton);

       
//        label2.setValue("Лучший иструмент для составления семантического ядра для сайта");
//        label2.addStyleName("h1");
//        label2.addStyleName("FontAwesome");
//        label3.setValue("Вы можите использовать готовые семантические ядро или "
//                + "составить свое. Для это нужно");
//        label3.addStyleName("dark");
//        link.setCaption("зарегистрироватся на сайте");
//
//        absoluteLayout.addComponent(nameCompamy, "left:10px; top:10px");
//        
//        label2.setWidth(100, Unit.PERCENTAGE);
//        label2.setHeight(29, Unit.PIXELS);
//        absoluteLayout.addComponent(label2, "left:10%; top:15%;right:3%;");
//        label3.setWidth(100, Unit.PERCENTAGE);
//        label3.setHeight(21, Unit.PIXELS);
//        absoluteLayout.addComponent(label3, "left:150px; top:30%; right:10%;");
//        absoluteLayout.addComponent(link, "top:30%; right:8%;");
//        studyTreeTable.setWidth(636, Unit.PIXELS);
//        studyTreeTable.setHeight(273, Unit.PIXELS);
//        absoluteLayout.addComponent(studyTreeTable, "left:9%; top:41%");
        absoluteLayout.addComponent(header);
        absoluteLayout.setSizeFull();
        setCompositionRoot(absoluteLayout);
        setSizeFull();

        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.loginForm();
            }
        });

        regButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.regForm();
            }
        });
    }

    private void loginForm() {
        windowModal = new Window("Войти");
        windowModal.setWidth(400, Unit.PIXELS);
        windowModal.setResizable(false);

        formLayout = new FormLayout();
        formLayout.setStyleName("v-formlayout-contentcell");

        emailField = new TextField("Введите Email адрес");
        emailField.addStyleName("v-textfield-borderless");
        emailField.addValidator(new EmailValidator("Введите корректный адрес электронной почты"));
        emailField.setRequired(true);
        emailField.setInputPrompt("user@domen.com");

        passwordField = new PasswordField("Введите пароль");
        passwordField.setInputPrompt("12345678");
        passwordField.setStyleName("v-textfield-borderless");
        passwordField.setRequired(true);

        performButton = new Button("Войти");
        performButton.setStyleName("v-button-friendly");

        clearButton = new Button("Очистить");
        clearButton.setStyleName("v-button-quiet");

        horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(performButton);
        horizontalLayout.addComponent(clearButton);
        horizontalLayout.setSpacing(true);

        formLayout.addComponent(emailField);
        formLayout.addComponent(passwordField);
        formLayout.addComponent(horizontalLayout);
        formLayout.setMargin(new MarginInfo(false, false, true, true));

        windowModal.setContent(formLayout);
        windowModal.setModal(true);
        windowModal.center();

        clearButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.clearForm(false);
            }
        });

        performButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.singIn();
            }
        });

        UI.getCurrent().addWindow(windowModal);
    }

    private void regForm() {
        windowModal = new Window("Регистрация");
        windowModal.setWidth(500, Unit.PIXELS);
        windowModal.setResizable(false);

        formLayout = new FormLayout();
        formLayout.setStyleName("v-formlayout-contentcell");

        emailField = new TextField("Введите логин");
        emailField.addStyleName("v-textfield-borderless");
        emailField.addValidator(new EmailValidator("Введите корректный адрес электронной почты"));
        emailField.setRequired(true);
        emailField.setInputPrompt("user@domen.com");

        passwordField = new PasswordField("Введите пароль");
        passwordField.setInputPrompt("12345678");
        passwordField.setStyleName("v-textfield-borderless");
        passwordField.setRequired(true);

        surnameField = new TextField("Фамилия");
        surnameField.addStyleName("v-textfield-borderless");
        surnameField.addValidator(new StringLengthValidator("Введите Вашу фамилию, "
                + "минимальная длина 2 символа, максимальная 45", 2, 45, false));
        surnameField.addValidator(new NullValidator("Обязательное поле", false));
        surnameField.addValidator(new RegexpValidator(regexpValue, "Разрешено вводить только "
                + "буквы русского или английского языка"));
        surnameField.setInputPrompt("Фамилия");
        surnameField.setRequired(true);

        nameField = new TextField("Имя");
        nameField.addStyleName("v-textfield-borderless");
        nameField.addValidator(new StringLengthValidator("Введите Ваше имя, минимальная "
                + "длина 2 символа, максимальная 45", 2, 45, false));
        nameField.addValidator(new RegexpValidator(regexpValue, "Разрешено вводить только "
                + "буквы русского или английского языка"));
        nameField.addValidator(new NullValidator("Обязательное поле", false));
        nameField.setInputPrompt("Имя");
        nameField.setRequired(true);

        horizontalLayout = new HorizontalLayout();
        performButton = new Button("Зарегистрироваться");
        performButton.setStyleName("v-button-friendly");

        clearButton = new Button("Очистить");
        clearButton.setStyleName("v-button-quiet");

        horizontalLayout.addComponent(performButton);
        horizontalLayout.addComponent(clearButton);
        horizontalLayout.setSpacing(true);

        formLayout.addComponent(emailField);
        formLayout.addComponent(passwordField);
        formLayout.addComponent(surnameField);
        formLayout.addComponent(nameField);
        formLayout.addComponent(horizontalLayout);
        formLayout.setMargin(new MarginInfo(false, false, true, true));

        windowModal.setContent(formLayout);
        windowModal.setModal(true);
        windowModal.center();

        performButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.singUp();
            }
        });

        clearButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                IndexPageView.this.clearForm(true);
            }
        });

        UI.getCurrent().addWindow(windowModal);
    }

    private void clearForm(boolean flag) {
        emailField.setValue("");
        passwordField.setValue("");
        if (flag) {
            surnameField.setValue("");
            nameField.setValue("");
        }
    }

    private void singIn() {
        try {
            emailField.validate();
            passwordField.validate();
            if (passwordField.isValid() && emailField.isValid()) {
                // ВЫПОНЛИТЬ ДЕЙСТВИЯ ДЛЯ ВХОДА ЗДЕСЬ
                Notification.show("ok", Notification.Type.ERROR_MESSAGE);
            }
        } catch (InvalidValueException e) {
            Notification.show("Заполните все поля правильно!", Notification.Type.ERROR_MESSAGE);
        }
    }

    private void singUp() {
        try {
            emailField.validate();
            passwordField.validate();
            surnameField.validate();
            nameField.validate();
            if (emailField.isValid() && passwordField.isValid() && surnameField.isValid()
                    && nameField.isValid()) {

                User user = new User();
                user.setEmail(emailField.getValue());
                user.setFirstName(surnameField.getValue());
                user.setPassword(Hash.getPassword(passwordField.getValue()));
                user.setSecondName(nameField.getValue());

                Set<Role> role = new HashSet<>();
                role.add(roleService.getById(2L));
                user.setRoles(role);

                userService.create(user);
                Notification.show("Вы успешно зарегистрировались! Войдите под своим логинам и паролем!",
                        Notification.Type.TRAY_NOTIFICATION);
                loginForm();
            }
        } catch (InvalidValueException e) {
            Notification.show("Заполните все поля правильно!", Notification.Type.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            Notification.show("В процессе работы возникла ошибка!", Notification.Type.TRAY_NOTIFICATION);
            Logger.getLogger(IndexPageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
