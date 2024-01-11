package com.ei.math.runners;

import com.ei.math.entity.Convit;
import com.ei.math.entity.Group;
import com.ei.math.entity.Member;
import com.ei.math.entity.UserPeople;
import com.ei.math.enums.GenderEnum;
import com.ei.math.service.ConvitService;
import com.ei.math.service.GroupService;
import com.ei.math.service.MemberService;
import com.ei.math.service.UserService;
import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRunner implements ApplicationRunner{

    private final UserService userService;
    private final GroupService groupService;
    private final MemberService menberService;
    private final ConvitService convitService;
    
    public DataSourceRunner(
        UserService userService, 
        GroupService groupService,
        MemberService menberService,
        ConvitService convitService
    ) {
        this.userService = userService;
        this.groupService = groupService;
        this.menberService = menberService;
        this.convitService = convitService;
    }
    
    private void seed(){
        UserPeople personOne = userService.save(new UserPeople("Sedrac Calupeteca", "slcsedrac@gmail.com", "936269780", LocalDate.of(1998, Month.JANUARY, 31), GenderEnum.MALE, "sedrac", "12345678"));
        UserPeople personTwo = userService.save(new UserPeople("Lucas Calupeteca", "sedraccalupeteca@gmail.com", "958360876", LocalDate.of(1998, Month.JANUARY, 31), GenderEnum.MALE, "lucas", "12345678"));
        UserPeople personThree = userService.save(new UserPeople("Sandra Gomes", "sandragomes@yahoo.com", "923451234", LocalDate.of(2000, Month.MARCH, 20), GenderEnum.FEMALE, "sandra", "12345678"));
        UserPeople personFour = userService.save(new UserPeople("Bela López", "belalopez@gmail.com", "958960876", LocalDate.of(2001, Month.AUGUST, 11), GenderEnum.FEMALE, "bela", "12345678"));
        UserPeople personFive = userService.save(new UserPeople("Pedro Miguel", "pedromiguel@hotmail.com", "923981243", LocalDate.of(1999, Month.FEBRUARY, 13), GenderEnum.MALE, "pedro", "12345678"));
        UserPeople personSix = userService.save(new UserPeople("Maria Sousa", "mariasouza@gmail.com", "934360872", LocalDate.of(1999, Month.JULY, 10), GenderEnum.FEMALE, "maria", "12345678"));
                        
        Group grupOne = groupService.save(new Group("Grupo arithmética fácil",personOne));
        Group grupTwo = groupService.save(new Group("Grupo teoria de radical",personOne));
        Group grupThree = groupService.save(new Group("Grupo de resolução de equação",personOne));
        Group grupFour = groupService.save(new Group("Grupo de resolução de radical",personTwo));
        groupService.save(new Group("Grupo de resolução de equação II",personTwo));
        
        menberService.save(new Member(personThree,grupOne));
        menberService.save(new Member(personFive,grupOne));
        menberService.save(new Member(personSix,grupOne));
        
        menberService.save(new Member(personFour,grupTwo));
        menberService.save(new Member(personTwo,grupTwo));
        menberService.save(new Member(personOne,grupTwo));
        
        menberService.save(new Member(personFive,grupThree));
        menberService.save(new Member(personSix,grupThree));
        
        menberService.save(new Member(personSix,grupFour));
        
        convitService.save(new Convit(personOne,grupFour));
        convitService.save(new Convit(personTwo,grupThree));
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
       seed();
    }
    
}
