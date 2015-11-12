/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysValue;
import com.unilever.util.LogManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 
 * @author Administrators.
 */
@Stateless
@Path("hysvalue")
public class HysValueFacadeREST extends AbstractFacade<HysValue> {
    private static final Logger logger = LogManager.getLogger(HysValueFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysValueFacadeREST() {
        super(HysValue.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    
    
    
}
