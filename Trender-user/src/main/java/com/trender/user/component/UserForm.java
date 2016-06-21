/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.component;

import com.trender.entity.Role;
import com.trender.entity.User;
import com.trender.service.RoleService;
import com.trender.service.UserService;
import com.trender.service.exception.ServiceException;
import com.trender.user.IndexPageView;
import com.trender.user.hash.Hash;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vladimir
 */
public class UserForm extends FormLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private final String regexpValue = "^[A-Za-zА-Яа-яЁё]+$";
    private Button performButton, clearButton;
    private TextField emailField, surnameField, nameField;
    private PasswordField passwordField;
    private HorizontalLayout horizontalLayout;

    public UserForm() {
        super();
    }

    public Component loginForm() {
        init("Войти");

        addComponent(horizontalLayout);
        setMargin(new MarginInfo(false, false, true, true));

        performButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UserForm.this.singIn();
            }
        });

        clearButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UserForm.this.clearForm(false);
            }
        });

        return this;
    }

    public Component regForm() {
        init("Зарегистрироваться");

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

        addComponent(surnameField);
        addComponent(nameField);
        addComponent(horizontalLayout);
        setMargin(new MarginInfo(false, false, true, true));

        performButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UserForm.this.singUp();
            }
        });

        clearButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UserForm.this.clearForm(true);
            }
        });

        return this;
    }

    public Component ProfilForm() {
        addStyleName("light");
        setWidth(90, Unit.PERCENTAGE);

        Label title = new Label();
        title.setStyleName("h2");
        title.setValue("Профиль");

        passwordField = new PasswordField("Введите пароль");
        passwordField.setInputPrompt("12345678");
        passwordField.setWidth(80, Unit.PERCENTAGE);

        surnameField = new TextField("Фамилия");
        surnameField.addValidator(new StringLengthValidator("Введите Вашу фамилию, "
                + "минимальная длина 2 символа, максимальная 45", 2, 45, false));
        surnameField.addValidator(new RegexpValidator(regexpValue, "Разрешено вводить только "
                + "буквы русского или английского языка"));
        surnameField.setInputPrompt("Фамилия");
        surnameField.setWidth(80, Unit.PERCENTAGE);

        nameField = new TextField("Имя");
        nameField.addValidator(new StringLengthValidator("Введите Ваше имя, минимальная "
                + "длина 2 символа, максимальная 45", 2, 45, false));
        nameField.addValidator(new RegexpValidator(regexpValue, "Разрешено вводить только "
                + "буквы русского или английского языка"));
        nameField.setInputPrompt("Имя");
        nameField.setWidth(80, Unit.PERCENTAGE);

        performButton = new Button("Сохранить");
        performButton.setStyleName("v-button-friendly");

        clearButton = new Button("Очистить");
        clearButton.setStyleName("v-button-quiet");

        horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(performButton);
        horizontalLayout.addComponent(clearButton);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        horizontalLayout.setMargin(new MarginInfo(true, false, false, true));

        addComponent(title);
        addComponent(passwordField);
        addComponent(surnameField);
        addComponent(nameField);
        addComponent(horizontalLayout);

        return this;
    }

    private void init(String valueButtonPerform) {
        setStyleName("v-formlayout-contentcell");

        emailField = new TextField("Введите Email адрес");
        emailField.addStyleName("v-textfield-borderless");
        emailField.addValidator(new EmailValidator("Введите корректный адрес электронной почты"));
        emailField.setRequired(true);
        emailField.setInputPrompt("user@domen.com");

        passwordField = new PasswordField("Введите пароль");
        passwordField.setInputPrompt("12345678");
        passwordField.setStyleName("v-textfield-borderless");
        passwordField.setRequired(true);

        performButton = new Button(valueButtonPerform);
        performButton.setStyleName("v-button-friendly");

        clearButton = new Button("Очистить");
        clearButton.setStyleName("v-button-quiet");

        horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(performButton);
        horizontalLayout.addComponent(clearButton);
        horizontalLayout.setSpacing(true);

        addComponent(emailField);
        addComponent(passwordField);

////        clearButton.addClickListener(new Button.ClickListener() {
////            @Override
////            public void buttonClick(Button.ClickEvent event) {
////                UserForm.this.clearForm(false);
////            }
////        });
////
////        performButton.addClickListener(new Button.ClickListener() {
////            @Override
////            public void buttonClick(Button.ClickEvent event) {
////                UserForm.this.chengProfil();
////            }
////        });
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
        } catch (Validator.InvalidValueException e) {
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
        } catch (Validator.InvalidValueException e) {
            Notification.show("Заполните все поля правильно!", Notification.Type.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            Notification.show("В процессе работы возникла ошибка!", Notification.Type.TRAY_NOTIFICATION);
            Logger.getLogger(IndexPageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO
    private void chengProfil() {
        try {
            passwordField.validate();
            surnameField.validate();
            nameField.validate();
            if (passwordField.isValid() && surnameField.isValid()
                    && nameField.isValid()) {

                User user = new User();
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
        } catch (Validator.InvalidValueException e) {
            Notification.show("Заполните все поля правильно!", Notification.Type.ERROR_MESSAGE);
        } catch (ServiceException ex) {
            Notification.show("В процессе работы возникла ошибка!", Notification.Type.TRAY_NOTIFICATION);
            Logger.getLogger(IndexPageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
