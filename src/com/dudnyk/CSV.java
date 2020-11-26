package com.dudnyk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.stream.Collectors;
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

    public void generateCSVFile(String fileName, List<Employee> data) {
    try (FileWriter csvWriter = new FileWriter(fileName + ".csv")) {

      List<Date> dates = data.stream()
              .map((e) -> e.getDate())
              .distinct()
              .collect(Collectors.toList());

      writeTitles(csvWriter, dates);
      writeData(csvWriter, dates, data);

      csvWriter.append("\n");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeTitles(FileWriter fileWriter, List<Date> dates) throws IOException {
    fileWriter.append("Name / Date");

    for (Date date : dates) {
      fileWriter.append(",");
      fileWriter.append(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    fileWriter.append("\n");
  }

  private  void writeData(FileWriter fileWriter, List<Date> dates, List<Employee> employees) throws IOException {
    // map of names and list of corresponding objects
    Map<String, List<Employee>> employeesDict = getDictOfNames(employees);

    for (String name : employeesDict.keySet()) {
      List<Employee> employeesList = employeesDict.get(name);
      fileWriter.append(name);

      // write work hours
      for (Date date : dates) {
        fileWriter.append(",");
        Employee employee = this.getEmployeeByDate(employeesList, date);

        if (employee != null ){
          fileWriter.append(String.valueOf(employee.getWorkHours()));
        }
      }
      fileWriter.append("\n");
    }
  }

  private Employee getEmployeeByDate(List<Employee> employees, Date date) {
    for (Employee employee : employees) {
      if (employee.getDate().equals(date)) {
        return employee;
      }
    }
    return null;
  }

  private Map<String, List<Employee>> getDictOfNames(List<Employee> employees) {
    Map<String, List<Employee>> employeesDict = new HashMap<>();

    for (Employee employee : employees) {
      if (!employeesDict.containsKey(employee.getName())) {
        employeesDict.put(employee.getName(), new LinkedList<>());
      }
      employeesDict.get(employee.getName()).add(employee);
    }

    return employeesDict;
  }
}
