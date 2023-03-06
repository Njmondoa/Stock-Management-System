/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Department;
import entities.Logfile;
import entities.Service;
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
import sessions.DepartmentFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.ServiceFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class ServiceController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;
    private List<Service> listService = new ArrayList<>();
    private Service service = new Service();
    private String operation;
    private String msg;

    @EJB
    private DepartmentFacadeLocal DepartmentFacade;
    private List<Department> listDepartment = new ArrayList<>();
    private Department department = new Department();
    private Integer idDepartment;

    /**
     * Creates a new instance of ServiceController
     */
    public ServiceController() {
    }

    @PostConstruct
    public void init() {

        listService.clear();
        listService.addAll(serviceFacade.findAll());
        listDepartment.clear();
        listDepartment.addAll(DepartmentFacade.findAll());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void selectDepartment() {
        department = DepartmentFacade.find(idDepartment);
    }

    public void prepareCreate(ActionEvent e) {
        service = new Service();
        department = new Department();

        msg = "";
        action(e);
    }

    public void saveService() {
        try {

            service.setIddepartment(department);
            serviceFacade.create(service);
            logFile("Create a service", service.getName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation Successfull.",null));
            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_service').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation failed.", null));

            msg = "Operation failed!";
        } finally {
            init();

        }
    }

    public void modifyService() {
        try {
            service.setIddepartment(department);
            serviceFacade.edit(service);
            logFile("Edit a service", service.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_service').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteService() {
        try {

            serviceFacade.remove(service);
            logFile("Delete a service", service.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_service').hide()");

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
                saveService();
                break;

            case "modify":
                modifyService();
                break;

            case "delete":
                deleteService();
                break;

        }
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

    public DepartmentFacadeLocal getDepartmentFacade() {
        return DepartmentFacade;
    }

    public void setDepartmentFacade(DepartmentFacadeLocal DepartmentFacade) {
        this.DepartmentFacade = DepartmentFacade;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

}
