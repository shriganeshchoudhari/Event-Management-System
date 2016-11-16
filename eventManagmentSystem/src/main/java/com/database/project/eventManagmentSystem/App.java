package com.database.project.eventManagmentSystem;

import com.database.project.eventManagmentSystem.user.Admin;
import com.database.project.eventManagmentSystem.user.Participant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	
    	ApplicationContext applicationContext = new FileSystemXmlApplicationContext("beans.xml");
    	
        Participant participant = (Participant) applicationContext.getBean("participant");
        
        Admin admin = (Admin) applicationContext.getBean("admin");
        
        System.out.println(participant);
        System.out.println(admin);

    }
}
