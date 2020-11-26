package com.dudnyk;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args){
        String csvFile = "C:\\Users\\DVR\\Desktop\\acme_worksheet.csv";
        CSV csv = new CSV();

        List<Employee> employees = csv.getEmployees(csvFile);
        Collections.sort(employees, Comparator.comparing(Employee::getDate));

        csv.generateCSVFile("file", employees);
    }
}
