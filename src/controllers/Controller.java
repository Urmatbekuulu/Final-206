package controllers;

import models.Model;
import views.BarChart;
import views.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public Model model;
    public View view;

    public  void execute(){
        this.model = new Model();
        this.view = new View();


        view.readFromFileB.addActionListener((l)->{
            readFromFileActionPerformed();
        });
        view.analyzeB.addActionListener((l)->{
            analyzeActionPerformed();
        });
        view.writeToFileB.addActionListener((l)->{
            writeToFileActionPerformed();
        });
    }

    private void writeToFileActionPerformed() {

    }

    private void analyzeActionPerformed() {
    }

    private void readFromFileActionPerformed() {

        List<String> fileNames=model.filNames;
        List<String> markerNames = model.markerNames;
        List<Integer> points = model.points;

        List<String> averageListName = new ArrayList<>();
        List<Integer> averagePoint = new ArrayList<>();

        try(FileInputStream fileInputStream = new FileInputStream("src/"+"input.txt"); // if you work with intelIj then
            // add this "src/" to file path FileInputStream("src/"+modelCountChars.getFilePath)
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader))
        {
            String string;
            while ((string = bufferedReader.readLine()) != null){
                int posInitial=-1;
                int posEnd=-1;
                int numStart=-1;
                int numEnd=-1;
                boolean isNameFound=false;
                boolean isNumFound=false;
                for(int i=0;i<string.length();i++){

                    if(!Character.isSpaceChar(string.charAt(i))){
                        posInitial = i;
                    }

                    if(Character.isSpaceChar(string.charAt(i)) && i>0 && posInitial !=-1){

                        posEnd=i;
                        isNameFound=true;
                        break;


                    }
                }
                boolean numStarted=false;
                for(int i=string.length()-1;i>=0;i--){

                    if(Character.isDigit(string.charAt(i)) && !numStarted){
                        numStarted=true;
                        numEnd=i+1;

                    }

                    if(Character.isSpaceChar(string.charAt(i)) && numStarted){

                        numStart = i+1;
                        isNumFound = true;
                        break;

                    }
                }


                if (isNameFound && isNumFound && numStart>posEnd+1 ) {

                    fileNames.add(string.substring(posEnd+1,numStart));
                    markerNames.add(string.substring(posInitial,posEnd));
                    points.add(Integer.parseInt(string.substring(numStart,numEnd)));

                }

            }



        }
        catch (IOException e) {
            e.printStackTrace();
        }


        boolean hasAdded[] = new boolean[fileNames.size()];
        for(int i=0;i<fileNames.size();i++){
            hasAdded[i]=false;
        }


        for(int i=0;i<fileNames.size();i++){
            int sum=0;
            int count=0;
            if(!hasAdded[i]) {
                for (int j = 0; j < fileNames.size(); j++) {

                    if(fileNames.get(i).equals( fileNames.get(j) )){
                        sum+=points.get(j);
                        count++;
                        hasAdded[j]=true;

                    }

                }
                hasAdded[i]=true;
                averageListName.add(fileNames.get(i));
                averagePoint.add(Math.round((float) sum/count));
            }



        }
        BarChart barChart = new BarChart();
        for(int i=0;i<averageListName.size();i++)
        {
            barChart.addHistogramColumn(averageListName.get(i),averagePoint.get(i));

            System.out.println(averageListName.get(i)+" "+averagePoint.get(i));
        }
        barChart.setBounds(150,0,1000,550);
        barChart.layoutHistogram();

        view.add(barChart);




    }

}
