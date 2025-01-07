package com.example.pms1;

import com.example.pms1.dto.PlanCondition;
import com.example.pms1.dto.Priority;
import com.example.pms1.dto.Resp;
import com.example.pms1.entity.Plan;
import com.example.pms1.service.PlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author loself
 * @date 2024-07-04 9:29
 */

@SpringBootTest
public class PlanTests {
    @Autowired
    private PlanService planService;

    @Test
    void addNewplan(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyZTUxMjFlMzEyYzY0NDQxYjhkZGVhZGM5MjBhZWYwMSIsInN1YiI6InVzZXIyIiwiaXNzIjoieGwiLCJpYXQiOjE3MjAwNjMwNjgsImV4cCI6MTcyMDA2NjY2OH0.pJ7mnzyCosV8k7_ZMiCECTUr1HRF6xMtenHgno1ICfU";
        Date date = new Date();
        Plan plan = new Plan();
        plan.setProjectId(1);
        plan.setName("plan2");
        plan.setStartDate(date);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate plus = localDate.plus(Period.ofDays(15));
        Date endday = Date.from(plus.atStartOfDay(ZoneId.systemDefault()).toInstant());
        plan.setDueDate(endday);
        planService.addNewPlan(token,plan, 1);
    }

    @Test
    void getplans(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJkYTJkYTVmNjg3YmI0Mzk0OGU1OGRmMjVmZDIwODA0ZCIsInN1YiI6InVzZXIyIiwiaXNzIjoieGwiLCJpYXQiOjE3MjAwNTcwNjAsImV4cCI6MTcyMDA2MDY2MH0.dyFmyHBtlfN9sSd5OJa49Q_NYWWD8BceiVaZK8MdzqM";
        Resp plansOfProject = planService.getPlansOfProject(token, 1, 2, 1, PlanCondition.CREATION_TIME);
        List<Plan> list = (List<Plan>) plansOfProject.getData();
        list.forEach(System.out::println);
    }


    @Test
    void get(){
        planService.list().forEach(System.out::println);
    }


    @Test
    void getpriority(){
        System.out.println(Priority.HIGH.compareTo(Priority.LOW));
    }

}
