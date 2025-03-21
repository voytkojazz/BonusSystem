package de.szut;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NormalEmployee implements Employee {

    private final String name;
    private final int yearsAtCompany;
    private final int performanceScore;
    private final int completedProjects;
    private final int absenceDays;

    @Override
    public boolean isTeamLeader() {
        return false;
    }
}
