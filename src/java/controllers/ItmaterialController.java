/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Itmaterial;
import entities.Logfile;
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
import sessions.ItmaterialFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class ItmaterialController {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private ItmaterialFacadeLocal ItmaterialFacade;
    private List<Itmaterial> listItmaterial = new ArrayList<>();
    private List<Itmaterial> listItmaterials = new ArrayList<>();

    private Itmaterial itmaterial = new Itmaterial();
    private String operation;
    private String msg;

    /**
     * Creates a new instance of ItmaterialController
     */
    public ItmaterialController() {
    }

    @PostConstruct
    public void init() {
        listItmaterial.clear();
        listItmaterial.addAll(ItmaterialFacade.findAll());

        listItmaterials.clear();
        listItmaterials.addAll(ItmaterialFacade.listitmaterials());
    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        itmaterial = new Itmaterial();

        msg = "";
        action(e);
    }

    public void saveItmaterial() {
        try {

            ItmaterialFacade.create(itmaterial);
            logFile("Create an IT material", itmaterial.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_itmaterial').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void modifyItmaterial() {
        try {

            ItmaterialFacade.edit(itmaterial);
            logFile("Edit an IT material", itmaterial.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_itmaterial').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteItmaterial() {
        try {

            ItmaterialFacade.remove(itmaterial);
            logFile("Delete an IT material", itmaterial.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_itmaterial').hide()");

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
                saveItmaterial();
                break;

            case "modify":
                modifyItmaterial();
                break;

            case "delete":
                deleteItmaterial();
                break;

        }
    }

    public ItmaterialFacadeLocal getItmaterialFacade() {
        return ItmaterialFacade;
    }

    public void setItmaterialFacade(ItmaterialFacadeLocal ItmaterialFacade) {
        this.ItmaterialFacade = ItmaterialFacade;
    }

    public List<Itmaterial> getListItmaterial() {
        return listItmaterial;
    }

    public void setListItmaterial(List<Itmaterial> listItmaterial) {
        this.listItmaterial = listItmaterial;
    }

    public Itmaterial getItmaterial() {
        return itmaterial;
    }

    public void setItmaterial(Itmaterial itmaterial) {
        this.itmaterial = itmaterial;
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

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

    public List<Itmaterial> getListItmaterials() {
        return listItmaterials;
    }

    public void setListItmaterials(List<Itmaterial> listItmaterials) {
        this.listItmaterials = listItmaterials;
    }

}
