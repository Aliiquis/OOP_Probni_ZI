package hr.fer.oop;

import java.util.ArrayList;
import java.util.List;

public class InnovationCompetition {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String firstName, String lastName, int proposedInnovations, int implementedInnovations) {
        employees.add(new Employee(firstName, lastName, proposedInnovations, implementedInnovations));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void removeEmployees(List<String> removalNames) {
        for (String name : removalNames) {
            employees.removeIf(e -> e.getLastName().equals(name));
        }
    }

    public void rankEmployees() {
        employees.sort(new EmployeeComp());
    }
}
