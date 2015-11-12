/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.job;

import com.unilever.service.HysParameterService;
import com.unilever.util.LogManager;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *
 * @author Ussopp.Su
 */
public class DatabaseBackupTimeJob implements Job{
    private static final Logger logger =LogManager.getLogger(DatabaseBackupTimeJob.class);
    
    /**
     * 执行备份数据工作
     * @param jec
     * @throws JobExecutionException 
     */
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        logger.info("================数据库备份的.execute()==================");
        JobDataMap data = jec.getJobDetail().getJobDataMap();
        //先查询数据库参数自动备份是否启动   HysParameterService hysParameterService   HysParameter hysParameter = new HysParameter();
        HysParameterService hysParameterService = (HysParameterService) data.get("hysParameterService");
        //获取数据库备份路径
        String backPath = hysParameterService.getBackPath();
        boolean result = hysParameterService.backupDatabase(backPath);
        if(result) logger.info("-------数据库定时备份job成功-------");
        else logger.info("-------数据库定时备份job成功-------");
    }
    
    
    
    
}
