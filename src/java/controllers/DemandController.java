/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Attribution;
import entities.Demand;
import entities.Hardwarecons;
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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletRequest;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import sessions.AttributionFacadeLocal;
import sessions.DemandFacadeLocal;
import sessions.HardwareconsFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.SoftwareconsFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class DemandController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private DemandFacadeLocal DemandFacade;
    private List<Demand> listDemand = new ArrayList<>();
    private List<Demand> listmyDemand = new ArrayList<>();
    private List<Demand> listmyDemandHard = new ArrayList<>();
    private List<Demand> listmyDemandSoft = new ArrayList<>();

    private List<Demand> listToconfirm = new ArrayList<>();

    private List<Demand> listDemands = new ArrayList<>();
    private List<Demand> listDemandsh = new ArrayList<>();
    private List<Demand> listDemandnotconfirmed = new ArrayList<>();
    private List<Demand> listDemandconfirmed = new ArrayList<>();
    private List<Demand> listDemandattributed = new ArrayList<>();

    private List<Demand> listDemandtreated = new ArrayList<>();

    private List<Demand> listDemandhardvalidated = new ArrayList<>();
    private List<Demand> listDemandsoftvalidated = new ArrayList<>();

    private List<Demand> listDemandrejected = new ArrayList<>();
    private List<Demand> listDemandonhold = new ArrayList<>();

    private Demand demand = new Demand();
    private String operation;
    private String msg;

    @EJB
    private UsersFacadeLocal UsersFacade;
    private List<Users> listUsers = new ArrayList<>();
    private Users users = new Users();
    private Integer idUser;

    @EJB
    private HardwareconsFacadeLocal HardwareConsFacade;
    private List<Hardwarecons> listHardwarecons = new ArrayList<>();
    private Hardwarecons hardwarecons = new Hardwarecons();
    private Integer idsoftwareConsumable;
    private Hardwarecons hardwarecon = new Hardwarecons();
    @EJB
    private SoftwareconsFacadeLocal SoftwareConsFacade;
    private List<Softwarecons> listSoftwarecons = new ArrayList<>();
    private Softwarecons softwarecons = new Softwarecons();
    private Integer idhardwareConsumable;
    private Softwarecons softwarecon = new Softwarecons();
    @EJB
    private AttributionFacadeLocal AttributionFacade;
    private List<Attribution> listAttribution = new ArrayList<>();
    private Attribution attribution = new Attribution();

    /**
     * Creates a new instance of DemandController
     */
    public DemandController() {
    }

    @PostConstruct
    public void init() {
        listDemand.clear();
        listDemand.addAll(DemandFacade.findAll());

        listmyDemand.clear();
        listmyDemand.addAll(DemandFacade.listmydemand());

        listmyDemandHard.clear();
        listmyDemandHard.addAll(DemandFacade.listmydemandHard());

        listmyDemandSoft.clear();
        listmyDemandSoft.addAll(DemandFacade.listmydemandSoft());

        
        listAttribution.clear();
        listAttribution.addAll(AttributionFacade.findAll());

        hardwarecon = new Hardwarecons();
        softwarecon = new Softwarecons();

        hardwarecon = HardwareConsFacade.findByIdhardwarecons();
        softwarecon = SoftwareConsFacade.findByIdsoftwarecons();

        listDemands.clear();
        listDemands.addAll(DemandFacade.listdemand(hardwarecon, softwarecon));

        listDemandsh.clear();
        listDemandsh.addAll(DemandFacade.listdemands(hardwarecon, softwarecon));

        listDemandnotconfirmed.clear();
        listDemandnotconfirmed.addAll(DemandFacade.listdemandnotconfirmed());

        listDemandconfirmed.clear();
        listDemandconfirmed.addAll(DemandFacade.listdemandconfirmed());

        listDemandattributed.clear();
        listDemandattributed.addAll(DemandFacade.listdemandattributed());

        listDemandhardvalidated.clear();
        listDemandhardvalidated.addAll(DemandFacade.listdemandhardvalidated());

        listDemandsoftvalidated.clear();
        listDemandsoftvalidated.addAll(DemandFacade.listdemandsoftvalidated());

        listDemandrejected.clear();
        listDemandrejected.addAll(DemandFacade.listdemandrejected());

        listDemandonhold.clear();
        listDemandonhold.addAll(DemandFacade.listdemandonhold());

        listDemandtreated.clear();
        listDemandtreated.addAll(DemandFacade.listdemandtreated());

        listUsers.clear();
        listUsers.addAll(UsersFacade.findAll());

        listSoftwarecons.clear();
        listSoftwarecons.addAll(SoftwareConsFacade.findById());

        listHardwarecons.clear();
        listHardwarecons.addAll(HardwareConsFacade.findById());

    }
//    public List<Demand> insertList(){
//        hardwarecons = HardwareConsFacade.findByIdhardwarecons();
//        softwarecons = SoftwareConsFacade.findByIdsoftwarecons();
//       return DemandFacade.listdemand(hardwarecons, softwarecons);
//        
//    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

     public void selectUser() {
        users = UsersFacade.find(idUser);
    }


    public void selectSoftwarecons() {
        softwarecons = SoftwareConsFacade.find(idsoftwareConsumable);
    }

    public void selectHardwarecons() {
        hardwarecons = HardwareConsFacade.find(idhardwareConsumable);
    }

    public void prepareCreate(ActionEvent e) {
        demand = new Demand();
        softwarecons = new Softwarecons();
        hardwarecons = new Hardwarecons();
        users = new Users();

        msg = "";
        action(e);
    }

    public void confirmDemand() {
        try {

            demand.setState("confirmed");
            DemandFacade.edit(demand);
            logFile("Confirm a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void validateDemand() {
        try {

            demand.setState("validated");
            DemandFacade.edit(demand);
            logFile("Validate a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void rejectDemand() {
        try {

            demand.setState("rejected");
            DemandFacade.edit(demand);
            logFile("Reject a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void demandOnHold() {
        try {

            demand.setState("on hold");
            DemandFacade.edit(demand);
            logFile("Put on hold a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void demandAttributed() {
        try {

            demand.setState("Attributed");
            DemandFacade.edit(demand);
            logFile("Attribute a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void saveDemand() {
        try {
            if (demand.getColor() == null) {
                demand.setColor("/");

                hardwarecons = HardwareConsFacade.findByIdhardwarecons();
                demand.setIdhardwarecons(hardwarecons);
            } else {
                softwarecons = SoftwareConsFacade.findByIdsoftwarecons();
                demand.setIdsoftwarecons(softwarecons);
            }
            demand.setState("");
            demand.setIdsoftwarecons(softwarecons);
            demand.setIdhardwarecons(hardwarecons);
            demand.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            DemandFacade.create(demand);

            logFile("Make a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_demand').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();

        }
    }

    public void modifyDemand() {
        try {
            if (demand.getState() == "Attributed" || demand.getState() == "on hold" || demand.getState() == "rejected" || demand.getState() == "validated" || demand.getState() == "confirmed") {

                msg = " The demand has already been treated ";
                RequestContext.getCurrentInstance().execute("PF('wv_demand').hide()");
            } else {
                demand.setState("");
                demand.setIdsoftwarecons(softwarecons);
                demand.setIdhardwarecons(hardwarecons);
                demand.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
                DemandFacade.edit(demand);
                logFile("Modify a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_demand').hide()");

            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteDemand() {
        try {
            if (demand.getState() == "Attributed" || demand.getState() == "on hold" || demand.getState() == "rejected" || demand.getState() == "validated" || demand.getState() == "confirmed") {
                msg = " The demand has already been treated ";
                RequestContext.getCurrentInstance().execute("PF('wv_demand').hide()");

            } else {
                DemandFacade.remove(demand);
                logFile("Delete a demand", demand.getIdhardwarecons().getName() +" "+ demand.getIdsoftwarecons().getName());

                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_demand').hide()");
            }
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
                saveDemand();
                break;

            case "modify":
                modifyDemand();
                break;

            case "delete":
                deleteDemand();
                break;

        }
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public Integer getIdsoftwareConsumable() {
        return idsoftwareConsumable;
    }

    public void setIdsoftwareConsumable(Integer idsoftwareConsumable) {
        this.idsoftwareConsumable = idsoftwareConsumable;
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

    public Integer getIdhardwareConsumable() {
        return idhardwareConsumable;
    }

    public void setIdhardwareConsumable(Integer idhardwareConsumable) {
        this.idhardwareConsumable = idhardwareConsumable;
    }

    public List<Demand> getListDemands() {
        return listDemands;
    }

    public void setListDemands(List<Demand> listDemands) {
        this.listDemands = listDemands;
    }

    public List<Demand> getListDemandsh() {
        return listDemandsh;
    }

    public void setListDemandsh(List<Demand> listDemandsh) {
        this.listDemandsh = listDemandsh;
    }

    public List<Demand> getListDemandnotconfirmed() {
        return listDemandnotconfirmed;
    }

    public void setListDemandnotconfirmed(List<Demand> listDemandnotconfirmed) {
        this.listDemandnotconfirmed = listDemandnotconfirmed;
    }

    public List<Demand> getListDemandconfirmed() {
        return listDemandconfirmed;
    }

    public void setListDemandconfirmed(List<Demand> listDemandconfirmed) {
        this.listDemandconfirmed = listDemandconfirmed;
    }

    public List<Demand> getListDemandtreated() {
        return listDemandtreated;
    }

    public void setListDemandtreated(List<Demand> listDemandtreated) {
        this.listDemandtreated = listDemandtreated;
    }

    public List<Demand> getListDemandrejected() {
        return listDemandrejected;
    }

    public void setListDemandrejected(List<Demand> listDemandrejected) {
        this.listDemandrejected = listDemandrejected;
    }

    public List<Demand> getListDemandonhold() {
        return listDemandonhold;
    }

    public void setListDemandonhold(List<Demand> listDemandonhold) {
        this.listDemandonhold = listDemandonhold;
    }

    public List<Demand> getListDemandattributed() {
        return listDemandattributed;
    }

    public void setListDemandattributed(List<Demand> listDemandattributed) {
        this.listDemandattributed = listDemandattributed;
    }

    public List<Attribution> getListAttribution() {
        return listAttribution;
    }

    public void setListAttribution(List<Attribution> listAttribution) {
        this.listAttribution = listAttribution;
    }

    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    public List<Demand> getListmyDemand() {
        return listmyDemand;
    }

    public void setListmyDemand(List<Demand> listmyDemand) {
        this.listmyDemand = listmyDemand;
    }

    public List<Demand> getListDemandhardvalidated() {
        return listDemandhardvalidated;
    }

    public void setListDemandhardvalidated(List<Demand> listDemandhardvalidated) {
        this.listDemandhardvalidated = listDemandhardvalidated;
    }

    public List<Demand> getListDemandsoftvalidated() {
        return listDemandsoftvalidated;
    }

    public void setListDemandsoftvalidated(List<Demand> listDemandsoftvalidated) {
        this.listDemandsoftvalidated = listDemandsoftvalidated;
    }

    public AttributionFacadeLocal getAttributionFacade() {
        return AttributionFacade;
    }

    public void setAttributionFacade(AttributionFacadeLocal AttributionFacade) {
        this.AttributionFacade = AttributionFacade;
    }

    public List<Demand> getListmyDemandHard() {
        return listmyDemandHard;
    }

    public void setListmyDemandHard(List<Demand> listmyDemandHard) {
        this.listmyDemandHard = listmyDemandHard;
    }

    public List<Demand> getListmyDemandSoft() {
        return listmyDemandSoft;
    }

    public void setListmyDemandSoft(List<Demand> listmyDemandSoft) {
        this.listmyDemandSoft = listmyDemandSoft;
    }

    public List<Demand> getListToconfirm() {
        return listToconfirm;
    }

    public void setListToconfirm(List<Demand> listToconfirm) {
        this.listToconfirm = listToconfirm;
    }

    public Hardwarecons getHardwarecon() {
        return hardwarecon;
    }

    public void setHardwarecon(Hardwarecons hardwarecon) {
        this.hardwarecon = hardwarecon;
    }

    public Softwarecons getSoftwarecon() {
        return softwarecon;
    }

    public void setSoftwarecon(Softwarecons softwarecon) {
        this.softwarecon = softwarecon;
    }

}
