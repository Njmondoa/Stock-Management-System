/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
import javax.servlet.ServletRequest;
import org.primefaces.context.RequestContext;
import sessions.LogfileFacadeLocal;
import sessions.UsersFacadeLocal;

/**
 *
 * @author Corei_5
 */
public class LogfileController implements Serializable {

    @EJB
    private LogfileFacadeLocal LogfileFacade;
    private List<Logfile> listLogfile = new ArrayList<>();
    private Logfile logfile = new Logfile();
    private String operation;
    private String msg;

    /**
     * Creates a new instance of LogfileController
     */
    public LogfileController() {
    }

    @PostConstruct
    public void init() {
        listLogfile.clear();
        listLogfile.addAll(LogfileFacade.findAll());

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

    public void deleteAll() {
        try {
            for(Logfile l : listLogfile){
            LogfileFacade.remove(l);
            }
            logFile("Erase all the Log files", "");

            msg = "Operation successfull!";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Operation failed!";
        } finally {
            init();
        }
    }

    public LogfileFacadeLocal getLogfileFacade() {
        return LogfileFacade;
    }

    public void setLogfileFacade(LogfileFacadeLocal LogfileFacade) {
        this.LogfileFacade = LogfileFacade;
    }

    public List<Logfile> getListLogfile() {
        return listLogfile;
    }

    public void setListLogfile(List<Logfile> listLogfile) {
        this.listLogfile = listLogfile;
    }

    public Logfile getLogfile() {
        return logfile;
    }

    public void setLogfile(Logfile logfile) {
        this.logfile = logfile;
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

}
