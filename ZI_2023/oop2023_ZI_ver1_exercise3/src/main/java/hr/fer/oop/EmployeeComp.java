package hr.fer.oop;

import java.util.Comparator;

public class EmployeeComp implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        if (o1.getImplementedInnovations() != o2.getImplementedInnovations())
            return Integer.compare(o2.getImplementedInnovations(), o1.getImplementedInnovations());
        else if (o1.getProposedInnovations() != o2.getProposedInnovations())
            return Integer.compare(o2.getProposedInnovations(), o1.getProposedInnovations());
        else return o1.getLastName().compareTo(o2.getLastName());
    }
}
