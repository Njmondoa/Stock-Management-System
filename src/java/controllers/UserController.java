/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Logfile;
import entities.Service;
import entities.Users;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.LogfileFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class UserController {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private UsersFacadeLocal UsersFacade;
    private List<Users> listUsers = new ArrayList<>();
    private Users users = new Users();
    private String operation;
    private String msg;

    @EJB
    private ServiceFacadeLocal serviceFacade;
    private List<Service> listService = new ArrayList<>();
    private Service service = new Service();
    private Integer idService;

    
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }

    @PostConstruct
    public void init() {
        listUsers.clear();
        listUsers.addAll(UsersFacade.findAll());
        listService.clear();
        listService.addAll(serviceFacade.findAll());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void selectService() {
        service = serviceFacade.find(idService);
    }

    public void prepareCreate(ActionEvent e) {
        users = new Users();
        service = new Service();
        msg = "";
        action(e);
    }

    public void saveUser() {
        try {
            if (UsersFacade.findByLogin(users.getLogin()).isEmpty()) {
                users.setPassword(((Integer) users.getPassword().hashCode()).toString());
                users.setIdservice(service);
                UsersFacade.create(users);
                logFile("Create a user", users.getName() + " " + users.getSurname());
                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_users').hide()");
            } else {
                msg = "This login has already been used, choose another one!";
                RequestContext.getCurrentInstance().execute("PF('wv_users').hide()");
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void modifyUser() {
        try {
            if (UsersFacade.findByLogin(users.getLogin()).isEmpty()) {
                users.setPassword(((Integer) users.getPassword().hashCode()).toString());

                users.setIdservice(service);
                UsersFacade.edit(users);
                logFile("Modify a user", users.getName() + " " + users.getSurname());

                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_users').hide()");
            } else {
                msg = "This login has already been used, choose another one!";
                RequestContext.getCurrentInstance().execute("PF('wv_users').hide()");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteUser() {
        try {

            UsersFacade.remove(users);
            logFile("Delete a user", users.getName() + " " + users.getSurname());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_users').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void logFile(String name, String target) {
        try {
            Logfile log = new Logfile();
            log.setDate(new Date(System.currentTimeMillis()));
            log.setIpadresse(((ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr());
            log.setName(name);
            log.setTarget(target);
            log.setTime(new Time(System.currentTimeMillis()));
            log.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            LogfileFacade.create(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void persist() {

        switch (operation) {

            case "add":
                saveUser();
                break;

            case "modify":
                modifyUser();
                break;

            case "delete":
                deleteUser();
                break;

        }
    }

    public UsersFacadeLocal getUsersFacade() {
        return UsersFacade;
    }

    public void setUsersFacade(UsersFacadeLocal UsersFacade) {
        this.UsersFacade = UsersFacade;
    }

    public List<Users> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Users> listUsers) {
        this.listUsers = listUsers;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ServiceFacadeLocal getServiceFacade() {
        return serviceFacade;
    }

    public void setServiceFacade(ServiceFacadeLocal serviceFacade) {
        this.serviceFacade = serviceFacade;
    }

    public List<Service> getListService() {
        return listService;
    }

    public void setListService(List<Service> listService) {
        this.listService = listService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

}
