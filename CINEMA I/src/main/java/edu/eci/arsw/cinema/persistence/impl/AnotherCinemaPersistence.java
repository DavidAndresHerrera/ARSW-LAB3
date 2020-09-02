package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("anotherCinema")
public class AnotherCinemaPersistence implements CinemaPersitence {
    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {

    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        return null;
    }

    @Override
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException {

    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        return null;
    }

    @Override
    public void addCinema(Cinema cinema) {

    }
}
