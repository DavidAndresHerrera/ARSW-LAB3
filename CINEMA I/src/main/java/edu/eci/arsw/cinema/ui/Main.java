package edu.eci.arsw.cinema.ui;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CinemaServices gc = ac.getBean(CinemaServices.class);


        String functionDate = "2019-12-22 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funcionAvatar = new CinemaFunction(new Movie("Avatar 2","Fiction"),functionDate);
        CinemaFunction funcionConjuro = new CinemaFunction(new Movie("El conjuro 3","Horror"),functionDate);
        functions.add(funcionAvatar);
        functions.add(funcionConjuro);
        Cinema cinema=new Cinema("El eden",functions);

        gc.addCinema(cinema);

        try {
            System.out.println(gc.getCinemaByName("El eden"));
        } catch (CinemaException e) {
            e.printStackTrace();
        }

    }




}