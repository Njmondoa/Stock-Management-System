/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Logfile;
import entities.Menu;
import entities.Users;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.ServletRequest;
import org.primefaces.model.DualListModel;
import sessions.LogfileFacadeLocal;
import sessions.MenuFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class PrivilegesController implements Serializable, Converter{
    @EJB
    private LogfileFacadeLocal LogfileFacade;
    
    @EJB
    private UsersFacadeLocal UsersFacade;
    private List<Users> listUsers = new ArrayList<>();
    private Users users = new Users();
    private Integer idUsers;
    @EJB
    private MenuFacadeLocal menuFacade;
    private DualListModel<Menu> privileges = new DualListModel<>(new ArrayList<>(), new ArrayList<>());
    private String msg; 

    /**
     * Creates a new instance of PrivilegesController
     */
    public PrivilegesController() {
    }
    
    @PostConstruct
    public void init(){
        chargeListMenu();
        chargeListUsers();
    }
    
    public void chargeListMenu(){
        privileges.getSource().clear();
        privileges.getSource().addAll(menuFacade.findAll());
    }

    public void chargeListUsers(){
        listUsers.clear();
        listUsers.addAll(UsersFacade.findAll());
    }
    
    public void selectUsers(){
        users = UsersFacade.find(idUsers);
        chargeListMenu();
        privileges.getTarget().clear();
        privileges.getTarget().addAll(users.getMenuCollection());
        privileges.getSource().removeAll(privileges.getTarget());
    }
    
    public void savePrivileges(){
        try {
            logFile("Manage privileges", users.getName() + " " + users.getSurname());
            users.setMenuCollection(privileges.getTarget());
            
            UsersFacade.edit(users);
            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation Failed!";
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
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value){
        return menuFacade.find(Integer.valueOf(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value){
        return ((Menu) value).getIdmenu().toString();
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

    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    public MenuFacadeLocal getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacadeLocal menuFacade) {
        this.menuFacade = menuFacade;
    }

    public DualListModel<Menu> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(DualListModel<Menu> privileges) {
        this.privileges = privileges;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
