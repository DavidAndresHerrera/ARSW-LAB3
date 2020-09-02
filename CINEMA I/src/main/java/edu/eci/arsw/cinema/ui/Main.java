package edu.eci.arsw.cinema.ui;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
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
        CinemaFunction funcionConjuro = new CinemaFunction(new Movie("El conjuro 3","Action"),functionDate);
        functions.add(funcionAvatar);
        functions.add(funcionConjuro);
        Cinema cinema=new Cinema("El eden",functions);
        insertarCinema(gc,cinema);
        comprarTicket(gc);
        filter(gc);


    }

    private static void filter(CinemaServices gc) {
        ArrayList<Movie> peliculas = gc.getBySeats("El eden","2019-12-22 15:30","Fiction",81);
        for (int i = 0; i < peliculas.size() ; i++){
            System.out.println(peliculas.get(i).getName());
        }
    }


    private static void comprarTicket(CinemaServices gc) {
        try {
            gc.buyTicket(1, 3, "El eden", "2019-12-22 15:30", "Avatar 2");
            gc.buyTicket(3, 3, "El eden", "2019-12-22 15:30", "Avatar 2");
            for (int i = 0; i < gc.getCinemaByName("El eden").getFunctions().get(0).getSeats().size(); i++) {
                System.out.println(gc.getCinemaByName("El eden").getFunctions().get(0).getSeats().get(i));
            }
        }catch (CinemaException e){
            e.printStackTrace();
        }
    }

    private static void insertarCinema(CinemaServices gc, Cinema cinema){
        gc.addCinema(cinema);
        try {
            for(int i = 0;i < gc.getCinemaByName("El eden").getFunctions().size();i++){
                System.out.println(gc.getCinemaByName("El eden").getFunctions().get(i).getMovie().getName());
            }
        } catch (CinemaException e) {
            e.printStackTrace();
        }
    }



}