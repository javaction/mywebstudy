/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.job;

import com.unilever.service.HysParameterService;
import com.unilever.util.LogManager;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Ussopp.Su
 */
public class DatabaseBackupTimeJobStarter {
    
    private static Logger logger = LogManager.getLogger(DatabaseBackupTimeJobStarter.class);
    private static Scheduler scheduler;
    private static boolean isStarted = false;
    private static final Object MUTEX = new Object();
    @EJB
    private static HysParameterService hysParameterService;

    /**
     * 打开数据库备份定时器，如果没有打开则打开，如果已经打开则重启即先关闭在打开
     *
     * @param hysParameterService
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void startJob(HysParameterService hysParameterService) throws SchedulerException, ParseException {
        logger.info("------定时备份数据库的startJob()------");
        synchronized (MUTEX) {
            if (isStarted) {
                stopJob(hysParameterService);
            }
            String cronExpression = hysParameterService.getCronExpression();
            logger.info("数据库备份的时间表达式：---" + cronExpression);
            //如果时间表达式存在，执行
            if (cronExpression != null && !"".equals(cronExpression)) {
                StdSchedulerFactory sf = new StdSchedulerFactory();
                Properties props = new Properties();
                props.put("org.quartz.scheduler.instanceName", "DatabaseBackupTimeScheduler");
                props.put("org.quartz.threadPool.threadCount", "10");
                sf.initialize(props);
                scheduler = sf.getScheduler();
                logger.info("DatabaseBackupTimeJobStarter.SchedulerName:" + scheduler.getSchedulerName());
                final JobDetail job = newJob(DatabaseBackupTimeJob.class).withIdentity("databaseBackupIntegration", "group3").build();//定时任务的具体实现
                job.getJobDataMap().put("hysParameterService", hysParameterService);//由于JOB类无法加载EJB对象，则传递下去
                final CronTrigger trigger = newTrigger().withIdentity("databaseBackupIntegration", "group3").withSchedule(cronSchedule(cronExpression)).build();//定时任务的执行时间//由cronExpression时间表达式控制

                scheduler.scheduleJob(job, trigger);//Scheduler /Quartz独立的运行容器。JobDetail和Trigger(包含两种trigger)
                scheduler.start();
                isStarted = true;
            }

        }
    }
      
    public static void stopJob(HysParameterService hysParameterService) throws SchedulerException {
        logger.info("-----------DatabaseBackupTimeJobStarter.stopJob()-----isStarted------" + isStarted);
        synchronized (MUTEX) {
            if (isStarted) {
                scheduler.shutdown(true);
            }
            isStarted = false;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.severe(e.getMessage());
        }
    }
    
    
    private DatabaseBackupTimeJobStarter() {
    }
}
