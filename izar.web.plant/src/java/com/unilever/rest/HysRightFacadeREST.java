/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysRight;
import com.unilever.util.LogManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
@Path("hysright")
public class HysRightFacadeREST extends AbstractFacade<HysRight> {
    private static final Logger logger = LogManager.getLogger(HysRightFacadeREST.class); 
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysRightFacadeREST() {
        super(HysRight.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * 查询所有的权限----用于测试
     * @return 
     */
    @POST
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<HysRight> findAll(){
        logger.info("----findAll---");
        Query query = em.createNamedQuery("HysRight.findAll");
        List<HysRight> list = query.getResultList();
            
        return list;
    }
    
    
    
    
}
