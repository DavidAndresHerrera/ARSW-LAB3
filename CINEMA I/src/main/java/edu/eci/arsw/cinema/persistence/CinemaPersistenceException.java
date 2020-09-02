/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */

public class CinemaPersistenceException extends Exception{

    public CinemaPersistenceException(String message) {
        super(message);
    }

    public CinemaPersistenceException(String message, Throwable cause) {
        super(message, cause);
    } 
    
}
