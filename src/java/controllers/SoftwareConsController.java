/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Itmaterial;
import entities.Logfile;
import entities.Softwarecons;
import entities.Users;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import sessions.ItmaterialFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.SoftwareconsFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class SoftwareConsController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private SoftwareconsFacadeLocal SoftwareConsFacade;
    private List<Softwarecons> listSoftwarecons = new ArrayList<>();
    private List<Softwarecons> listSoftwarecon = new ArrayList<>();
    private List<Softwarecons> listalert = new ArrayList<>();

    private Softwarecons softwarecons = new Softwarecons();
    private String operation;
    private String msg;

    @EJB
    private ItmaterialFacadeLocal ItmaterialFacade;
    private List<Itmaterial> listItmaterial = new ArrayList<>();
    private Itmaterial itmaterial = new Itmaterial();
    private Integer idMaterial;

    /**
     * Creates a new instance of SoftwareConsController
     */
    public SoftwareConsController() {
    }

    @PostConstruct
    public void init() {
        
        listSoftwarecons.clear();
        listSoftwarecons.addAll(SoftwareConsFacade.findAll());
        listSoftwarecon.clear();
        listSoftwarecon.addAll(SoftwareConsFacade.findById());

        listItmaterial.clear();
        listItmaterial.addAll(ItmaterialFacade.listitmaterials());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void selectItmaterial() {
        itmaterial = ItmaterialFacade.find(idMaterial);
    }

    public void prepareCreate(ActionEvent e) {
        softwarecons = new Softwarecons();
        itmaterial = new Itmaterial();

        msg = "";
        action(e);
    }

    public String alert() {
        listalert = SoftwareConsFacade.alert();
        for (Softwarecons listSoftwarecon1 : listalert) {
            //System.out.println(listHardwarecon1.getName() + " is below the treshold");
            //msg = " stock of " + listHardwarecon1.getName() + " is low ";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Stock " + listSoftwarecon1.getName() + " bas !", null));

        }

        return null;
    }

    public void saveSoftwareCons() {
        try {
            softwarecons.setIdsoftwarecons(SoftwareConsFacade.nextId());
            softwarecons.setIdmaterial(itmaterial);
            SoftwareConsFacade.create(softwarecons);
            logFile("Create a software consumable", softwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_softwarecons').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();

        }
    }

    public void modifySoftwareCons() {
        try {
            softwarecons.setIdmaterial(itmaterial);
            SoftwareConsFacade.edit(softwarecons);
            logFile("Edit a software consumable", softwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_softwarecons').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteSoftwareCons() {
        try {

            SoftwareConsFacade.remove(softwarecons);
            logFile("Delete a software consumable", softwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_softwarecons').hide()");

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
                saveSoftwareCons();
                break;

            case "modify":
                modifySoftwareCons();
                break;

            case "delete":
                deleteSoftwareCons();
                break;

        }
    }

    public SoftwareconsFacadeLocal getSoftwareConsFacade() {
        return SoftwareConsFacade;
    }

    public void setSoftwareConsFacade(SoftwareconsFacadeLocal SoftwareConsFacade) {
        this.SoftwareConsFacade = SoftwareConsFacade;
    }

    public List<Softwarecons> getListSoftwarecons() {
        return listSoftwarecons;
    }

    public void setListSoftwarecons(List<Softwarecons> listSoftwarecons) {
        this.listSoftwarecons = listSoftwarecons;
    }

    public Softwarecons getSoftwarecons() {
        return softwarecons;
    }

    public void setSoftwarecons(Softwarecons softwarecons) {
        this.softwarecons = softwarecons;
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

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public List<Softwarecons> getListalert() {
        return listalert;
    }

    public void setListalert(List<Softwarecons> listalert) {
        this.listalert = listalert;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

    public List<Softwarecons> getListSoftwarecon() {
        return listSoftwarecon;
    }

    public void setListSoftwarecon(List<Softwarecons> listSoftwarecon) {
        this.listSoftwarecon = listSoftwarecon;
    }

}
