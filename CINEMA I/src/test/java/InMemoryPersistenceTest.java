
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.ByGender;
import edu.eci.arsw.cinema.persistence.impl.BySeat;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws CinemaPersistenceException{
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        ipct.saveCinema(c);
        
        assertNotNull("Loading a previously stored cinema returned null.",ipct.getCinema(c.getName()));
        assertEquals("Loading a previously stored cinema returned a different cinema.",ipct.getCinema(c.getName()), c);
    }


    @Test
    public void saveExistingCinemaTest() {
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3","Action"),functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2=new Cinema("Movies Bogotá",functions2);
        try{
            ipct.saveCinema(c2);
        }
        catch (CinemaPersistenceException ex){
            fail("An exception was expected after saving a second cinema with the same name");
        }

    }

    @Test
    public void buyTickets(){
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        try {
            ipct.buyTicket(1,1,"cinemaX","2018-12-18 15:30","The Night");
        } catch (CinemaException e) {
            fail("No se puedo comprar el ticket");
        }


    }

    @Test
    public void getFunctionsByCinemaAndDate(){
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        ipct.getFunctionsbyCinemaAndDate("cinemaX","2018-12-18 15:30").get(0).getMovie().equals("The Night");
    }

    @Test
    public void getMoviesByGender(){
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        ByGender bg = new ByGender();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("El eden",functions);

        try {
            ipct.saveCinema(c);
            ArrayList<Movie> finals = bg.filter("El eden","2018-12-18 15:30","Horror",20,ipct);
            assertEquals(finals.size(),1);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed getting movies by gender");
        }

    }


    @Test
    public void getMoviesBySeats(){
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        BySeat bg = new BySeat();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Horror"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("El eden",functions);

        try {
            ipct.saveCinema(c);
            ipct.buyTicket(1, 3, "El eden","2018-12-18 15:30","The Night 2");
            ipct.buyTicket(3, 3, "El eden","2018-12-18 15:30","The Night 2");
            ArrayList<Movie> finals = bg.filter("El eden","2018-12-18 15:30","Horror",83,ipct);
            assertEquals(finals.get(0).getName(), "SuperHeroes Movie 2");
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed getting movies by gender");
        } catch (CinemaException e) {
            e.printStackTrace();
        }

    }


}
