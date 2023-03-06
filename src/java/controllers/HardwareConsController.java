/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Demand;
import entities.Hardwarecons;
import entities.Itmaterial;
import entities.Logfile;
import entities.Softwarecons;
import entities.Users;
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
import sessions.DemandFacadeLocal;
import sessions.HardwareconsFacadeLocal;
import sessions.ItmaterialFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.SoftwareconsFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class HardwareConsController {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private HardwareconsFacadeLocal HardwareConsFacade;
    private List<Hardwarecons> listHardwarecons = new ArrayList<>();
    private List<Hardwarecons> listalert = new ArrayList<>();

    private List<Hardwarecons> listHardwarecon = new ArrayList<>();
    private Hardwarecons hardwarecons = new Hardwarecons();
    private String operation;
    private String msg;
    public String a;

    @EJB
    private DemandFacadeLocal DemandFacade;
    private List<Demand> listDemand = new ArrayList<>();
    private Demand demand = new Demand();

    @EJB
    private SoftwareconsFacadeLocal SoftwareConsFacade;
    private List<Softwarecons> listSoftwarecons = new ArrayList<>();
    private Softwarecons softwarecons = new Softwarecons();
    private Integer idSoftwarecons;
   

    @EJB
    private ItmaterialFacadeLocal ItmaterialFacade;
    private List<Itmaterial> listItmaterial = new ArrayList<>();
    private Itmaterial itmaterial = new Itmaterial();
    private Integer idMaterial;

    /**
     * Creates a new instance of HardwareConsController
     */
    public HardwareConsController() {
    }

    @PostConstruct
    
    public void init() {
       
        listHardwarecons.clear();
        listHardwarecons.addAll(HardwareConsFacade.findAll());

        listHardwarecon.clear();
        listHardwarecon.addAll(HardwareConsFacade.findById());
        listSoftwarecons.clear();
        listSoftwarecons.addAll(SoftwareConsFacade.findById());
        listDemand.clear();
        listDemand.addAll(DemandFacade.findAll());
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

    public void selectSoftwarecons() {
        softwarecons = SoftwareConsFacade.find(idSoftwarecons);
    }

    public void prepareCreate(ActionEvent e) {
        hardwarecons = new Hardwarecons();
        softwarecons = new Softwarecons();
        itmaterial = new Itmaterial();

        msg = "";
        action(e);
    }

    public String alert() {
        listalert = HardwareConsFacade.alert();
        for (Hardwarecons listHardwarecon1 : listalert) {
            //System.out.println(listHardwarecon1.getName() + " is below the treshold");
            //msg = " stock of " + listHardwarecon1.getName() + " is low ";
            
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Stock " + listHardwarecon1.getName() + " bas !", null));

        }

        return null;
    }

    public void saveHardwareCons() {
        try {
            hardwarecons.setIdhardwarecons(HardwareConsFacade.nextId());
            hardwarecons.setIdmaterial(itmaterial);
            HardwareConsFacade.create(hardwarecons);
            logFile("Create a hardware consumable", hardwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_hardwarecons').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();

        }
    }

    public void modifyHardwareCons() {
        try {
            hardwarecons.setIdmaterial(itmaterial);
            HardwareConsFacade.edit(hardwarecons);
            logFile("Edit a hardware consumable", hardwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_hardwarecons').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteHardwareCons() {
        try {

            HardwareConsFacade.remove(hardwarecons);
            logFile("Delete a hardware consumable", hardwarecons.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_hardwarecons').hide()");

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
                saveHardwareCons();
                break;

            case "modify":
                modifyHardwareCons();
                break;

            case "delete":
                deleteHardwareCons();
                break;

        }
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

    public HardwareconsFacadeLocal getHardwareConsFacade() {
        return HardwareConsFacade;
    }

    public void setHardwareConsFacade(HardwareconsFacadeLocal HardwareConsFacade) {
        this.HardwareConsFacade = HardwareConsFacade;
    }

    public List<Hardwarecons> getListHardwarecons() {
        return listHardwarecons;
    }

    public void setListHardwarecons(List<Hardwarecons> listHardwarecons) {
        this.listHardwarecons = listHardwarecons;
    }

    public Hardwarecons getHardwarecons() {
        return hardwarecons;
    }

    public void setHardwarecons(Hardwarecons hardwarecons) {
        this.hardwarecons = hardwarecons;
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

    public List<Hardwarecons> getListHardwarecon() {
        return listHardwarecon;
    }

    public void setListHardwarecon(List<Hardwarecons> listHardwarecon) {
        this.listHardwarecon = listHardwarecon;
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

    public Integer getIdSoftwarecons() {
        return idSoftwarecons;
    }

    public void setIdSoftwarecons(Integer idSoftwarecons) {
        this.idSoftwarecons = idSoftwarecons;
    }

    public DemandFacadeLocal getDemandFacade() {
        return DemandFacade;
    }

    public void setDemandFacade(DemandFacadeLocal DemandFacade) {
        this.DemandFacade = DemandFacade;
    }

    public List<Demand> getListDemand() {
        return listDemand;
    }

    public void setListDemand(List<Demand> listDemand) {
        this.listDemand = listDemand;
    }

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public List<Hardwarecons> getListalert() {
        return listalert;
    }

    public void setListalert(List<Hardwarecons> listalert) {
        this.listalert = listalert;
    }


}
