package com.dudnyk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSV {

  private String csvSplitBy = ",";

  public void setCvsSplitBy(String cvsSplitBy) {
    this.csvSplitBy = cvsSplitBy;
  }

  public List<Employee> getEmployees(String filePath) {
    List<Employee> employees =  new LinkedList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      // skip first title line
      br.readLine();
      // split and add data to the employee list
      while ((line = br.readLine()) != null) {
        String[] data = line.split(this.csvSplitBy);
        employees.add(createEmployee(data));
      }

    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
    return employees;
  }

  private static Employee createEmployee(String[] data) throws ParseException {
    Date date = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH).parse(data[1]);
    return  new Employee(data[0], Float.parseFloat(data[2]), date);
  }
}
