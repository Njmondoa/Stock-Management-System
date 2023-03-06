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
public class AttributionController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private DemandFacadeLocal DemandFacade;
    private List<Demand> listDemand = new ArrayList<>();
    private List<Demand> listDemandattributed = new ArrayList<>();
    private List<Demand> listDemandvalidated = new ArrayList<>();
    private Demand demand = new Demand();
    private Integer iddemand;

    @EJB
    private AttributionFacadeLocal AttributionFacade;
    private List<Attribution> listAttribution = new ArrayList<>();
    private List<Attribution> listmyAttribution = new ArrayList<>();

    private Attribution attribution = new Attribution();
    private int stock;
    private int stock1;
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

    /**
     * Creates a new instance of AttributionController
     */
    public AttributionController() {
    }

    @PostConstruct
    public void init() {

        listDemand.clear();
        listDemand.addAll(DemandFacade.findAll());

        listDemandattributed.clear();
        listDemandattributed.addAll(DemandFacade.findAll());

        listDemandvalidated.clear();
        listDemandvalidated.addAll(DemandFacade.findAll());

        hardwarecon = new Hardwarecons();
        softwarecon = new Softwarecons();

        hardwarecon = HardwareConsFacade.findByIdhardwarecons();
        softwarecon = SoftwareConsFacade.findByIdsoftwarecons();

        listAttribution.clear();
        listAttribution.addAll(AttributionFacade.findAll());

        listmyAttribution.clear();
        listmyAttribution.addAll(AttributionFacade.listmyattribution());

        
        listUsers.clear();
        listUsers.addAll(UsersFacade.findAll());

        listSoftwarecons.clear();
        listSoftwarecons.addAll(SoftwareConsFacade.findAll());

        listHardwarecons.clear();
        listHardwarecons.addAll(HardwareConsFacade.findAll());

    }


    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void selectUser() {
        users = UsersFacade.find(idUser);
    }

    public void selectdemand() {
        demand = DemandFacade.find(iddemand);
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
        attribution = new Attribution();
        msg = "";
        action(e);
    }

    public void validateDemand() {
        try {

            demand.setState("validated");
            DemandFacade.edit(demand);
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
            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void Stock() {
        try {
            hardwarecons = demand.getIdhardwarecons();
            stock = HardwareConsFacade.findByStockquantity(demand.getIdhardwarecons().getName());

            stock = stock - attribution.getQtyattributed();
            
            hardwarecons.setStockquantity(stock);
            HardwareConsFacade.edit(hardwarecons);
            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }

    }

    public void Stock2() {
        try {
            softwarecon = demand.getIdsoftwarecons();
            stock = SoftwareConsFacade.findByStockquantity(demand.getIdsoftwarecons().getName());
            stock = stock - attribution.getQtyattributed();

            softwarecon.setStockquantity(stock);
            SoftwareConsFacade.edit(softwarecon);
            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }

    }

    public void saveAttribution() {
        try {
            if (attribution.getQtyattributed() > demand.getQtydemanded()) {
                msg = "Attributed quantity should be less than or equals to quantity demanded";
                RequestContext.getCurrentInstance().execute("PF('wv_attribution').hide()");

            } else {
                attribution.setIddemand(demand);

                attribution.setIdhardwarecons(demand.getIdhardwarecons());
                attribution.setIdsoftwarecons(demand.getIdsoftwarecons());
                attribution.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));

                AttributionFacade.create(attribution);
                demandAttributed();
                Stock();
                logFile("Make an attribution", attribution.getIdhardwarecons().getName() +" "+ attribution.getIdsoftwarecons().getName());
                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_attribution').hide()");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void saveAttribution2() {
        try {
            if (attribution.getQtyattributed() > demand.getQtydemanded()) {
                msg = "Attributed quantity should be less than or equals to quantity demanded";
                RequestContext.getCurrentInstance().execute("PF('wv_attribution').hide()");

            } else {
                attribution.setIddemand(demand);
                attribution.setIdhardwarecons(demand.getIdhardwarecons());
                attribution.setIdsoftwarecons(demand.getIdsoftwarecons());
                attribution.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
                AttributionFacade.create(attribution);
                demandAttributed();
                Stock2();
                logFile("Make an attribution", "");
                msg = "Operation successfull!";
                RequestContext.getCurrentInstance().execute("PF('wv_attribution').hide()");
            }
            }catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        }finally {
            init();
        }
        }

    

    public void modifyAttribution() {
        try {

            attribution.setIdusers((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser"));
            AttributionFacade.edit(attribution);
            logFile("modify a demand", "");

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_attribution').hide()");

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
                saveAttribution();
                break;

            case "modify":
                modifyAttribution();
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

    public AttributionFacadeLocal getAttributionFacade() {
        return AttributionFacade;
    }

    public void setAttributionFacade(AttributionFacadeLocal AttributionFacade) {
        this.AttributionFacade = AttributionFacade;
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

    public Hardwarecons getHardwarecon() {
        return hardwarecon;
    }

    public List<Attribution> getListmyAttribution() {
        return listmyAttribution;
    }

    public void setListmyAttribution(List<Attribution> listmyAttribution) {
        this.listmyAttribution = listmyAttribution;
    }

  
 
    public void setHardwarecon(Hardwarecons hardwarecon) {
        this.hardwarecon = hardwarecon;
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

    public Softwarecons getSoftwarecon() {
        return softwarecon;
    }

    public void setSoftwarecon(Softwarecons softwarecon) {
        this.softwarecon = softwarecon;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Demand> getListDemandattributed() {
        return listDemandattributed;
    }

    public void setListDemandattributed(List<Demand> listDemandattributed) {
        this.listDemandattributed = listDemandattributed;
    }

    public List<Demand> getListDemandvalidated() {
        return listDemandvalidated;
    }

    public void setListDemandvalidated(List<Demand> listDemandvalidated) {
        this.listDemandvalidated = listDemandvalidated;
    }

    public Integer getIddemand() {
        return iddemand;
    }

    public void setIddemand(Integer iddemand) {
        this.iddemand = iddemand;
    }

    public int getStock1() {
        return stock1;
    }

    public void setStock1(int stock1) {
        this.stock1 = stock1;
    }

}
