package controllers;

import entities.Demand;
import entities.Department;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import sessions.AttributionFacadeLocal;
import sessions.DemandFacadeLocal;

/**
 *
 * @author Mondoa Nelson
 */
public class chartController implements Serializable {

    @EJB
    private DemandFacadeLocal demandeFacade;
    private int numb = 0;
    private Date date, date1;

    private PieChartModel pieModel1;
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;

    @EJB
    private AttributionFacadeLocal attributionFacade;

    /**
     * Creates a new instance of chartController
     */
    public chartController() {
    }

    @PostConstruct
    public void init() {
        initBarModel();
        createAnimatedModels();
        createPieModels();

    }

    public void actualiser() {
        createAnimatedModels();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        int test2 = demandeFacade.countdemand2();
        int test3 = demandeFacade.countdemand3();
        int test4 = demandeFacade.countdemand4();
        int test5 = demandeFacade.countdemand5();
        int test6 = demandeFacade.countdemand6();

        pieModel1.set("DAAF", test2);
        pieModel1.set("DEL", test3);
        pieModel1.set("DIRE", test4);
        pieModel1.set("DTB", test5);
        pieModel1.set("DEP", test6);

        pieModel1.setTitle("Number of demands made by department");
        pieModel1.setLegendPosition("w");

    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Number of treated and un treated demands per year");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Number of demands and attributions made with time");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(50);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
//       
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Demands");

        int test = demandeFacade.countdemand(date, date1);

        boys.set("''", test);
        model.addSeries(boys);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Attributions");
        int test1 = attributionFacade.countdemand(date, date1);

        girls.set("attribution", test1);
        model.addSeries(girls);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Not Treated");
        int test = demandeFacade.countdemand(date, date1);
        series1.set(0.8, test);
//        series1.set(2, 1);
//        series1.set(3, 3);
//        series1.set(4, 6);
//        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Treated");
        int testi = demandeFacade.countdemand(date, date1);
        series2.set(0.4, testi);
//        series2.set(2, 3);
//        series2.set(3, 2);
//        series2.set(4, 7);
//        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public void setAnimatedModel1(LineChartModel animatedModel1) {
        this.animatedModel1 = animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public DemandFacadeLocal getDemandeFacade() {
        return demandeFacade;
    }

    public void setDemandeFacade(DemandFacadeLocal demandeFacade) {
        this.demandeFacade = demandeFacade;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public AttributionFacadeLocal getAttributionFacade() {
        return attributionFacade;
    }

    public void setAttributionFacade(AttributionFacadeLocal attributionFacade) {
        this.attributionFacade = attributionFacade;
    }

    private void createPieModels() {
        createPieModel1();
    }

}

