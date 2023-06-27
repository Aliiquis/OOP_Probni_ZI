package hr.fer.oop.zi.zad3;

import java.util.*;

public class Position {
    private final String name;
    private List<RequiredSkill> requiredSkills = new ArrayList<>();
    private Set<Application> applications;

    public Position(String name) {
        this.name = name;
        this.applications = new TreeSet<>(Comparator.comparing(Application::getScore).reversed().thenComparing(a -> a.getApplicant().getId()));
    }

    public String getName() {
        return name;
    }

    public Position addRequiredSkill(RequiredSkill skill) {
        this.requiredSkills.add(skill);
        return this;
    }

    public void addApplication(Applicant applicant) {
        double score = 0;
        for (RequiredSkill skill : requiredSkills) {
            if (applicant.getRequiredSkillScore(skill.getName()) < skill.getMinimum()) {
                score = 0;
                break;
            }
            score += applicant.getRequiredSkillScore(skill.getName()) * skill.getMultiplier();
        }
        applications.add(new Application(applicant, score));
    }

    public Collection<Application> getApplications() {
        return applications;
    }

    public String summarizeApplications() {
        StringBuilder builder = new StringBuilder(this.name);
        builder.append(":\n");
        for (Application a : applications) {
            builder.append("\t").append(a.getApplicant().getName()).append(" - ");
            if (a.getScore() == 0.) builder.append("did not meet requirements\n");
            else builder.append(String.format("%.2f\n", a.getScore()));
        }
        return builder.toString();
    }

}