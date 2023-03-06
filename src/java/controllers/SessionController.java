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
import java.util.Date;
import javafx.scene.control.Alert;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import org.jboss.weld.context.http.HttpSessionContext;
import sessions.LogfileFacade;
import sessions.LogfileFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class SessionController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private UsersFacadeLocal UsersFacade;
    private Users currentUser = new Users();
    private byte Empreintes;
    private String language = "en";
    public String msg = "";
    private Boolean file = false;
    private Boolean treatment = false;
    private Boolean analysis = false;
    private Boolean users = false;
    private Boolean department = false;
    private Boolean service = false;
    public Users Empreinte;
    private Boolean itmaterial = false;
    private Boolean consumable = false;
    private Boolean privileges = false;
    private Boolean mydemands = false;
    private Boolean myattributions = false;
    private Boolean demands = false;
    private Boolean attributions = false;
    private Boolean informer = false;
    private Boolean statistics = false;
    private Boolean toconfirm = false;
    private Boolean totreat = false;
    private Boolean treateddemands = false;

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }

    public void watchOut() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("currentUser")) {
                ((FacesContext.getCurrentInstance()).getExternalContext()).redirect("authenticate.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String authenticate() {
        try {
            currentUser = UsersFacade.findByLoginPassword(currentUser.getLogin(), currentUser.getPassword());
            if (currentUser != null) {
                msg = "";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", currentUser);
                logFile("Login", "System");
                //configuration of menu access

                Empreinte = new Users();
                Empreinte = UsersFacade.findByLoginPassword(currentUser.getLogin(), ((Integer) currentUser.getPassword().hashCode()).toString());

                file = currentUser.getMenuCollection().contains(new Menu("file"));
                treatment = currentUser.getMenuCollection().contains(new Menu("treatment"));
                analysis = currentUser.getMenuCollection().contains(new Menu("analysis"));
                users = currentUser.getMenuCollection().contains(new Menu("users"));
                department = currentUser.getMenuCollection().contains(new Menu("department"));
                service = currentUser.getMenuCollection().contains(new Menu("service"));
                itmaterial = currentUser.getMenuCollection().contains(new Menu("itmaterial"));
                consumable = currentUser.getMenuCollection().contains(new Menu("consumable"));
                privileges = currentUser.getMenuCollection().contains(new Menu("privileges"));
                demands = currentUser.getMenuCollection().contains(new Menu("demands"));
                mydemands = currentUser.getMenuCollection().contains(new Menu("mydemands"));
                myattributions = currentUser.getMenuCollection().contains(new Menu("myattributions"));
                attributions = currentUser.getMenuCollection().contains(new Menu("attributions"));
                informer = currentUser.getMenuCollection().contains(new Menu("informer"));
                statistics = currentUser.getMenuCollection().contains(new Menu("statistics"));
                toconfirm = currentUser.getMenuCollection().contains(new Menu("toconfirm"));
                totreat = currentUser.getMenuCollection().contains(new Menu("totreat"));
                treateddemands = currentUser.getMenuCollection().contains(new Menu("treateddemands"));

                return "homepage.xhtml?faces-redirect=true";

            } else {
                msg = "Login or password incorrect, please try again...";

                currentUser = new Users();
                return "authenticate.xhtml?faces-redirect=true";
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Login or password incorrect, please try again...";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Login or password incorrect, please try again...", null));

            currentUser = new Users();
            return "authenticate.xhtml?faces-redirect=true";
        }
    }

    public String logOut() {
        try {
            msg = "";
            logFile("Logout", "System");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "authenticate.xhtml?faces-redirect=true";
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

    public UsersFacadeLocal getUsersFacade() {
        return UsersFacade;
    }

    public void setUsersFacade(UsersFacadeLocal UsersFacade) {
        this.UsersFacade = UsersFacade;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public String english() {
        language = "en";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String french() {
        language = "fr";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String chinese() {
        language = "cn";
        return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo() + "?faces-redirect=true";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

    public Boolean getFile() {
        return file;
    }

    public void setFile(Boolean file) {
        this.file = file;
    }

    public Boolean getTreatment() {
        return treatment;
    }

    public void setTreatment(Boolean treatment) {
        this.treatment = treatment;
    }

    public Boolean getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Boolean analysis) {
        this.analysis = analysis;
    }

    public Boolean getUsers() {
        return users;
    }

    public void setUsers(Boolean users) {
        this.users = users;
    }

    public Boolean getDepartment() {
        return department;
    }

    public void setDepartment(Boolean department) {
        this.department = department;
    }

    public Boolean getService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public Boolean getItmaterial() {
        return itmaterial;
    }

    public void setItmaterial(Boolean itmaterial) {
        this.itmaterial = itmaterial;
    }

    public Boolean getConsumable() {
        return consumable;
    }

    public void setConsumable(Boolean consumable) {
        this.consumable = consumable;
    }

    public Boolean getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Boolean privileges) {
        this.privileges = privileges;
    }

    public Boolean getDemands() {
        return demands;
    }

    public void setDemands(Boolean demands) {
        this.demands = demands;
    }

    public Boolean getAttributions() {
        return attributions;
    }

    public void setAttributions(Boolean attributions) {
        this.attributions = attributions;
    }

    public Boolean getInformer() {
        return informer;
    }

    public void setInformer(Boolean informer) {
        this.informer = informer;
    }

    public Boolean getStatistics() {
        return statistics;
    }

    public void setStatistics(Boolean statistics) {
        this.statistics = statistics;
    }

    public Boolean getToconfirm() {
        return toconfirm;
    }

    public void setToconfirm(Boolean toconfirm) {
        this.toconfirm = toconfirm;
    }

    public Boolean getTotreat() {
        return totreat;
    }

    public void setTotreat(Boolean totreat) {
        this.totreat = totreat;
    }

    public Boolean getTreateddemands() {
        return treateddemands;
    }

    public void setTreateddemands(Boolean treateddemands) {
        this.treateddemands = treateddemands;
    }

    public Boolean getMydemands() {
        return mydemands;
    }

    public void setMydemands(Boolean mydemands) {
        this.mydemands = mydemands;
    }

    public Boolean getMyattributions() {
        return myattributions;
    }

    public void setMyattributions(Boolean myattributions) {
        this.myattributions = myattributions;
    }

    /**
     * Creates a new instance of SessionController
     */
}
