package com.dudnyk;

public class Main {
    public static void main(String[] args){
        String csvFile = "C:\\Users\\DVR\\Desktop\\acme_worksheet.csv";
        CSV csv = new CSV(csvFile);
        csv.generateCSVFile("file");
    }
}
