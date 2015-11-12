/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.util.LogManager;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
public class HysRightService {
    private static final Logger logger = LogManager.getLogger(HysRightService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    
    
    
    
}
