/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Department;
import entities.Logfile;
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
import sessions.DepartmentFacadeLocal;
import sessions.LogfileFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class DepartmentController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;

    

    @EJB
    private DepartmentFacadeLocal DepartmentFacade;
    private List<Department> listDepartment = new ArrayList<>();
    private Department department = new Department();
    private String operation;
    private String msg;

    /**
     * Creates a new instance of DepartmentController
     */
    public DepartmentController() {
    }

    @PostConstruct
    public void init() {
        listDepartment.clear();
        listDepartment.addAll(DepartmentFacade.findAll());

    }

    public void action(ActionEvent e) {
        CommandButton btn = (CommandButton) e.getComponent();
        operation = btn.getWidgetVar();
        msg = "";
    }

    public void prepareCreate(ActionEvent e) {
        department = new Department();

        msg = "";
        action(e);
    }

    public void saveDepartment() {
        try {

            DepartmentFacade.create(department);
            logFile("Create a department", department.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_department').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void modifyDepartment() {
        try {

            DepartmentFacade.edit(department);
            logFile("Edit a department", department.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_department').hide()");

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public void deleteDepartment() {
        try {

            DepartmentFacade.remove(department);
            logFile("Delete a department", department.getName());

            msg = "Operation successfull!";
            RequestContext.getCurrentInstance().execute("PF('wv_department').hide()");

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
                saveDepartment();
                break;

            case "modify":
                modifyDepartment();
                break;

            case "delete":
                deleteDepartment();
                break;

        }
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

}
