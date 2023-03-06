/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Corei_5
 */
import entities.Demand;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class ChartView implements Serializable {

    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    private PieChartModel pieModel1;

    @PostConstruct
    public void init() {
        createAnimatedModels();
        createPieModels();
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Number of attributions made by departments");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(250);
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("DAAF", 52);
        pieModel1.set("DIRE", 100);
        pieModel1.set("DEL", 60);
        pieModel1.set("DTB", 150);
        pieModel1.set("DEP", 50);

        pieModel1.setTitle("Number of demands made per department");
        pieModel1.setLegendPosition("w");
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        Demand demand = new Demand();
        ChartSeries DAAF = new ChartSeries();
        DAAF.setLabel("DAAF");
        DAAF.set("JANUARY", 50);
        DAAF.set("FEBRUARY", 80);
        DAAF.set("MARCH", 44);
        DAAF.set("APRIL", 135);
        DAAF.set("MAY", 25);
        DAAF.set("JUNE", 50);
        DAAF.set("JULY", 80);
        DAAF.set("AUGUST", 44);
        DAAF.set("SEPTEMBER", 135);
        DAAF.set("OCTOBER", 25);
        DAAF.set("NOVEMBER", 135);
        DAAF.set("DECEMBER", 25);

        ChartSeries DIRE = new ChartSeries();
        DIRE.setLabel("DIRE");
        DIRE.set("JANUARY", 50);
        DIRE.set("FEBRUARY", 80);
        DIRE.set("MARCH", 44);
        DIRE.set("APRIL", 135);
        DIRE.set("MAY", 25);
        DIRE.set("JUNE", 50);
        DIRE.set("JULY", 80);
        DIRE.set("AUGUST", 44);
        DIRE.set("SEPTEMBER", 135);
        DIRE.set("OCTOBER", 25);
        DIRE.set("NOVEMBER", 135);
        DIRE.set("DECEMBER", 25);
        
        ChartSeries DEL = new ChartSeries();
        DEL.setLabel("DEL");
        DEL.set("JANUARY", 50);
        DEL.set("FEBRUARY", 80);
        DEL.set("MARCH", 44);
        DEL.set("APRIL", 135);
        DEL.set("MAY", 25);
        DEL.set("JUNE", 50);
        DEL.set("JULY", 80);
        DEL.set("AUGUST", 44);
        DEL.set("SEPTEMBER", 135);
        DEL.set("OCTOBER", 25);
        DEL.set("NOVEMBER", 135);
        DEL.set("DECEMBER", 25);
        
        ChartSeries DTB = new ChartSeries();
        DTB.setLabel("DTB");
        DTB.set("JANUARY", 50);
        DTB.set("FEBRUARY", 80);
        DTB.set("MARCH", 44);
        DTB.set("APRIL", 135);
        DTB.set("MAY", 25);
        DTB.set("JUNE", 50);
        DTB.set("JULY", 80);
        DTB.set("AUGUST", 44);
        DTB.set("SEPTEMBER", 135);
        DTB.set("OCTOBER", 25);
        DTB.set("NOVEMBER", 135);
        DTB.set("DECEMBER", 25);
        
        ChartSeries DEP = new ChartSeries();
        DEP.setLabel("DEP");
        DEP.set("JANUARY", 50);
        DEP.set("FEBRUARY", 80);
        DEP.set("MARCH", 44);
        DEP.set("APRIL", 135);
        DEP.set("MAY", 25);
        DEP.set("JUNE", 50);
        DEP.set("JULY", 80);
        DEP.set("AUGUST", 44);
        DEP.set("SEPTEMBER", 135);
        DEP.set("OCTOBER", 25);
        DEP.set("NOVEMBER", 135);
        DEP.set("DECEMBER", 25);

        model.addSeries(DAAF);
        model.addSeries(DIRE);
        model.addSeries(DEL);
        model.addSeries(DTB);
        model.addSeries(DEP);

        return model;
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    private void createPieModels() {
        createPieModel1();
    }

}
