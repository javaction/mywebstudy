/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.web;

import com.unilever.job.DatabaseBackupTimeJobStarter;
import com.unilever.service.HysParameterService;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Web application lifecycle listener.
 *
 * @author tengguan
 */
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ApplicationListener.class.getName());
    @EJB
    private HysParameterService parameterService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("==========================服务程序开始启动================================");
        initDatabaseBackupTimer();//初始化数据库定时器
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("==========================服务程序开始关闭================================");
    }
    
    /**
     * 根据参数初始化数据库备份定时器
     */
    private void initDatabaseBackupTimer() {
        //查看数据库是否需要启动数据库定时备份
        if(parameterService.getDatabaseBackupSwitch())
        {
            try {
                DatabaseBackupTimeJobStarter.startJob(parameterService);//数据库定时备份job
            } catch (Exception ex) {
                LOG.severe("======================启动数据库备份定时器失败=========================================");
                LOG.severe(ex.getMessage());
            }
        }
        else
        {
            LOG.info("================不需要启动数据库备份定时器=========================");
        }
    }
}
