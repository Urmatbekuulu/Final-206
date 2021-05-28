package views;

import  javax.swing.*;
import java.awt.*;

public class View extends  JFrame {

    public JButton readFromFileB = new JButton("Read from file");
    public JButton analyzeB = new JButton("Analyze");
    public JButton writeToFileB = new JButton("Write to File");



    public View(){
     execute();

    }
    public void execute(){
        this.setLayout(null);
        BarChart barChart = new BarChart();

        readFromFileB.setBounds(15,50,130,35);
        analyzeB     .setBounds(15,90,130,35);
        writeToFileB .setBounds(15,130,130,35);

        barChart.setBounds(150,0,1000,550);

        barChart.addHistogramColumn("A", 8);
        barChart.addHistogramColumn("B", 10);
        barChart.addHistogramColumn("C", 7);
        barChart.addHistogramColumn("D", 8);

        barChart.layoutHistogram();



        this.add(readFromFileB);
        this.add(analyzeB);
        this.add(writeToFileB);
        this.add(barChart);


        setSize(1200,600);
        setPreferredSize(getSize());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }








}
