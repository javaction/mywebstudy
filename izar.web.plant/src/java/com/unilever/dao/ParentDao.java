/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tengguan
 */
public class ParentDao {
    @PersistenceContext(unitName = "TestPU")
    public EntityManager em; 
    
}
