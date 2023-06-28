package hr.fer.oop;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int proposedInnovations;
    private int implementedInnovations;

    public Employee(String firstName, String lastName, int proposedInnovations, int implementedInnovations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.proposedInnovations = proposedInnovations;
        this.implementedInnovations = implementedInnovations;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getProposedInnovations() {
        return proposedInnovations;
    }

    public int getImplementedInnovations() {
        return implementedInnovations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName) + Objects.hash(lastName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee e) {
            return this.hashCode() == e.hashCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s %s [proposed: %d, implemented: %d]", firstName, lastName, proposedInnovations, implementedInnovations);
    }
}
