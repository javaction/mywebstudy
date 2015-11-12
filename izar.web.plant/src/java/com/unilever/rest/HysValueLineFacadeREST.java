/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysValueLine;
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
@Path("hysvalueline")
public class HysValueLineFacadeREST extends AbstractFacade<HysValueLine> {
    private static final Logger logger = LogManager.getLogger(HysValueLineFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysValueLineFacadeREST() {
        super(HysValueLine.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    
    
    
    
}
