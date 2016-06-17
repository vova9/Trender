/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trender.user.config;

import com.vaadin.spring.server.SpringVaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Vladimir
 */
@WebServlet(value = "/*", asyncSupported = true)
public class ServletConfig extends SpringVaadinServlet {

}
