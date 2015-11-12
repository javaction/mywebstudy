/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.dao.HysParameterDao;
import com.unilever.entity.HysParameter;
import com.unilever.entity.exp.PageBaseExp;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.job.DatabaseBackupTimeJobStarter;
import com.unilever.util.Constant;
import com.unilever.util.LogManager;
import com.unilever.util.StringUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
public class HysParameterService {
    private static final Logger logger = LogManager.getLogger(HysParameterService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    @EJB
    private HysParameterDao hysParameterDao;
    
    //也可以在下面单独写一个方法，让上面的execute来调用。。
    public boolean backupDatabase(String backPath)
    {
     //数据库用户默认密码2
        String datebacePassword = Constant.DATABACE_PASSWORD;                        
        //需要备份的数据库地址3 //localhost
        String databaceAddress = Constant.DATABACE_ADDRESS;
        //需要备份的数据库名称4
        String databaceName = Constant.DATABACE_NAME;
        //需要备份的数据库的默认用户5
        String databaceUser = Constant.DATABACE_USER;
         //默认端口号6
        String databacePortNum = Constant.DATABACE_PORT_NUM;
        try
        {
            //生成临时脚本文件名称        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateStr = sdf.format(new Date());
            File file ;
            File fileBat;
            File fileBackPath ;
            boolean b1 = false;
            boolean b2 = false;
            boolean b3 = false;
            file  = new File("c:\\linshiwenjian");//脚本临时文件夹
            fileBat = new File("c:\\linshiwenjian\\out"+dateStr+".bat");
            fileBackPath = new File(backPath);
            b1 = file.exists();
            b2 = fileBat.exists();
            b3 = fileBackPath.exists();
            if(file.exists()&&fileBat.exists()&&fileBackPath.exists()){
                logger.info("文件夹和文件存在");
            }else{
                logger.info("文件夹不存在,需要创建文件夹和文件");
                b1 = file.mkdir();               
                b2 = fileBat.createNewFile();
                b3 = fileBackPath.mkdir();
            } 

            String eoff = "@echo off";
            String setPass = "set PGPASSWORD="+datebacePassword;
            String pgexe = "pg_dump.exe --host "+databaceAddress+" --port "+databacePortNum+" --username "+databaceUser+" --format custom --blobs --verbose"
                    + " --file \""+backPath+"\\%date:~0,4%-%date:~5,2%-%date:~8,2%-%time:~0,2%-%time:~3,2%-%time:~6,2%.dump\" "+databaceName;
            String pa = "exit";
            logger.info("临时脚本的路径：---"+fileBat.getAbsolutePath());
        
            FileWriter fw = new FileWriter(fileBat.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(eoff);
            bw.newLine();
            bw.write(setPass);
            bw.newLine();
            bw.write(pgexe);
            bw.newLine();
            bw.write(pa);

            bw.flush();
            bw.close();
            fw.close();
            
            Process process = null;
            process = Runtime.getRuntime().exec(fileBat.getAbsolutePath()); // fileBat.getAbsolutePath()
            process.waitFor();  
            process.destroy();
            
            //删除临时文件夹和临时脚本文件
            if(fileBat.exists()){
                fileBat.delete();
            }
            if(file.exists()){
                file.delete();
            }  

        }
        catch(Exception e)
        {
            logger.severe(e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * 数据库立即备份
     * @return 
     */ 
    public Result postgresBackup(Parameter param){  //Parameter param
        logger.info("--------plant.HysParameterService.postgresBackup------------");
        String result = "successed";

        HysParameter hysParameter = new HysParameter();
        //备份路径，先获取默认设置的。
        String backPath = Constant.DATABACE_BACKUP_PATH;           
        if(param.getDatabaseBackupPath()!=null&&!"".equals(param.getDatabaseBackupPath())){           
            backPath = param.getDatabaseBackupPath();
        }else{             
            backPath = this.getBackPath();
        }  
        if(!backupDatabase(backPath)) result = "fail";           
        return new Result(result);
    }
    
    
    
    
    
    
    
   /**
    * 数据库备份功能的开关方法
    * @param parameter
    * @return 
    */
    public Result databaseBackupSwitch(Parameter parameter){      
        logger.info("---------plant.HysParameterService.databaseBackupSwitch--------");
        String result = "successed";
        try{
            String state = "open";
            if(parameter.getFlag()!=null && "close".equals(parameter.getFlag())){
                state = "close";
            }
            //先处理参数
            //需要记录一下数据库databaseBackupState参数，先查询，有则更新，没有则新增
            HysParameter hysParameter = new HysParameter();       
            hysParameter.setHysParameterCode("databaseBackupState");
            hysParameter.setHysParameterType("state");
            hysParameter = hysParameterDao.findByParamCodeAndType(hysParameter);
            if(hysParameter!=null&&hysParameter.getHysParameterValue()!=null){
               hysParameter.setHysParameterValue(state);
               //更新数据库信息
               this.editSysParameter(hysParameter);//更新
            }        
            else{
                hysParameter.setHysParameterCode("databaseBackupState");
                hysParameter.setHysParameterType("state");
                hysParameter.setHysParameterValue(state);
                hysParameter = hysParameterDao.findByParamCodeAndType(hysParameter);
                this.addSysParameter(hysParameter);    
            }
            //再处理定时任务
            if(parameter.getFlag()!=null && "close".equals(parameter.getFlag())){
                //关闭自动备份数据库
                DatabaseBackupTimeJobStarter.stopJob(this);
            }else{
                //开启自动备份数据库
                DatabaseBackupTimeJobStarter.startJob(this);
            }
        }catch(Exception e){
            result = "failed";
            logger.severe("[databaseBackupSwitch Exception]: "+e.getMessage());
        }
        return new Result(result);
    }
    /**
    * 数据库备份功能的开关方法
    * @param parameter
    * @return 
    */
    public boolean getDatabaseBackupSwitch(){
        HysParameter hysParameter = hysParameterDao.findByParamCodeAndType("databaseBackupState","state");
        if(null == hysParameter){
            return false;
        }else
        {
            String value = hysParameter.getHysParameterValue();
            if("open".equals(value)){
                return true;
            }else
            {
                return false;
            }
        }
        
    }
    
     /**
     * 编辑单个参数值，根据传入的这个对象进行的修改
     * @param hySysParameter
     * @return 
     */
    public Result editSysParameter(HysParameter hysParameter) {
        logger.info("------plant.HysParameterService.editSysParameter----");
        String result = "successed";
        try{
            em.merge(hysParameter);
        }
        catch(Exception e){
            logger.info("HysParameterService.editSysParameter exception:"+e.getMessage());
            result = "failed";
            e.printStackTrace();
        }
        return new Result(result);
    }
    
    /**
     * 新增单个参数值
     * @param hySysParameter
     * @return 
     */
    public Result addSysParameter(HysParameter hysParameter) {
        logger.info("------plant.HysParameterService.addSysParameter-----");
        String result = "successed";
        try{
            em.persist(hysParameter);
        }
        catch(Exception e){
            logger.info("HysParameterService.addSysParameter exception:"+e.getMessage());
            result = "failed";
            e.printStackTrace();
        }
        return new Result(result);
    }
    
    
    /**
     * 检查盘符是否存在
     * @param parameter
     * @return 
     */
    public Result checkDisk(Parameter parameter) {
        logger.info("------plant.HysParameterService.checkDisk-------");
        String result = "exist";
        String disk = parameter.getFlag();
        try{
            if(disk!=null && !"".equals(disk) && disk.indexOf(":")!=-1){
                File f = new File(disk.substring(0, disk.indexOf(":")+1));
                if(!f.exists()){
                    result = "notExist";
                }
            }
            else{
                result = "notExist";
            }
        }
        catch(Exception e){
            result = "notExist";
            logger.severe("checkDisk exception:"+e.getMessage());
        }
        return new Result(result);
    } 
    
    /**
     * 获取数据库定时备份频率
     *
     * @return
     */
    public String getCronExpression() {
        String cronExpression = "";//备份时间
        HysParameter hysParameter = new HysParameter();
        hysParameter = new HysParameter();
        hysParameter.setHysParameterCode("databaseBackupInterval");
        hysParameter.setHysParameterType("interval");
        hysParameter = hysParameterDao.findByParamCodeAndType(hysParameter);
        if (hysParameter != null && hysParameter.getHysParameterValue() != null) {
            cronExpression = hysParameter.getHysParameterValue();
        }
        return cronExpression;
    }

    
    /**
     * 编辑参数 //现在只是编辑数据库备份路径和备份间隔时间
     * @param parameter
     * @return 
     */
    public Result editHySysParameter(Parameter parameter){
        logger.info("-------plant.HysParameterService.editHySysParameter-------");
        String result = "successed";
        String ids =parameter.getIds();
        logger.info("ids:--"+ids);
        //取备份时间间隔参数，需要判断一下这个参数是否改变了//返回此值，在其调用方法中判断//用于判断是否是否需要调用数据库备份的start()：true,调用；false,不调用。
        String isChange = "false";
        try {
            String databaseBackupIntervalOld = "";
            HysParameter hysParameter = new HysParameter();
            hysParameter.setHysParameterCode("databaseBackupInterval");
            hysParameter.setHysParameterType("interval");
            hysParameter = hysParameterDao.findByParamCodeAndType(hysParameter);
            if(hysParameter!=null&&hysParameter.getHysParameterValue()!=null){
                databaseBackupIntervalOld = hysParameter.getHysParameterValue();
            }
            
            String strs[] = ids.split(",");
            for(String str:strs){
                String st[] = str.split(";");  
                String value =null;
                if(st[0]!=null&&!"".equals(st[0])){
                    //根据参数中传来的id查询，这一整行的数据...
                   // "ids":    "100023;open,     100016;c:\\databackup,     100022;monthly;01;02"   //前台出来的ids式样
                    HysParameter hysParameter2 = em.getReference(HysParameter.class,Long.valueOf(st[0]));
                    if(st.length==2){
                        value = st[1];
                        if("databaseBackupPath".equals(hysParameter2.getHysParameterCode())){
                            value = StringUtil.dealPathDouble(value);
                        }
                        if("databaseBackupState".equals(hysParameter2.getHysParameterCode())){
                            value = value;
                        }
                        hysParameter2.setHysParameterValue(value);
                        em.merge(hysParameter2);                       
                    }else if(st.length>=3){
                        //需要处理传来的数据库备份时间间隔..///暂时先用以前的，再考虑看看有什么好的方式来实现
                        StringBuilder databaseBackupInterval = new StringBuilder("0 0 ");
                        logger.info(parameter+"==parameter===data="+st.toString());
                        
                        if("weekly".equals(st[1])){
                            databaseBackupInterval.append(Integer.parseInt(st[3])).append(" ? * ");
                            if("07".equals(st[2])){//周日
                                databaseBackupInterval.append("1");
                            }
                            else{
                                databaseBackupInterval.append(Integer.parseInt(st[2])+1);
                            }
                        }
                        else if("monthly".equals(st[1])){
                            databaseBackupInterval.append(Integer.parseInt(st[3])).append(" ").append(Integer.parseInt(st[2])).append(" * ?");
                        }
                        else if("yearly".equals(st[1])){
                            databaseBackupInterval.append(Integer.parseInt(st[4])).append(" ").append(Integer.parseInt(st[3])).append(" ").append(Integer.parseInt(st[2])).append(" ?");
                        }
                        hysParameter2.setHysParameterValue(databaseBackupInterval.toString());
                        if(!databaseBackupIntervalOld.equals(databaseBackupInterval.toString())){
                            isChange = "true";
                        }  
                        em.merge(hysParameter2);
                        
                    }else if(st.length==1){
                        hysParameter2.setHysParameterValue(null);
                    }                  
                } 
            }          
        } catch (Exception e) {
            result = "failed";
            logger.severe("[editHySysParameter Exception]: "+e.getMessage());
        }
        return new Result(result,isChange);
    }
    
    
    /**
     * 查询所有参数，并对参数重新处理（如：数据库备份路径，定时备份的时间设置..）//////单纯返回list,暂时先不用。。
     * @return 
     */
    public List<HysParameter> findAllParametersTest(){
        logger.info("-----------plant.HysParameterService.findAllParametersTest-------------");
        Query query = em.createNamedQuery("HysParameter.findAllPara");     
        List<HysParameter> list = query.getResultList();
        List<HysParameter> paraList = new ArrayList<HysParameter>();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            while(it.hasNext()){
                 HysParameter hysParameter = (HysParameter)it.next();
                 HysParameter parameter = new HysParameter();
                 if(hysParameter!=null){
                     String value = hysParameter.getHysParameterValue();                  
                     if("databaseBackupInterval".equals(hysParameter.getHysParameterCode())){       
                         //将时间进行处理前台使用的式样
                         value = StringUtil.analyseInterval(value);                        
                         parameter.setHysParameterValue(value);
                     }
                     if("databaseBackupPath".equals(hysParameter.getHysParameterCode())){  
                         //处理成前台使用的样式
                         value = StringUtil.dealPathSingle(value);
                         parameter.setHysParameterValue(value);
                     } 
                     parameter.setHysStorageId(hysParameter.getHysStorageId());
                     parameter.setHysParameterCode(hysParameter.getHysParameterCode());
                     parameter.setHysParameterType(hysParameter.getHysParameterType());
                     parameter.setHysRemark(hysParameter.getHysRemark());
                     parameter.setHysParameterValue(value);
                 }
                 paraList.add(parameter); 
            }         
        }       
        return paraList;
    }
    
    /**
     * 查询所有参数，并对参数重新处理（如：数据库备份路径，定时备份的时间设置..）
     * @return 
     */
    public PageBaseExp<HysParameter> findAllParameters(){
        logger.info("-----------plant.HysParameterService.findAllParameters-------------");

        PageBaseExp<HysParameter> page = new PageBaseExp<HysParameter>();
        page.setCurrpage(1);
        page.setTotalpages(1);
        Query query = em.createNamedQuery("HysParameter.findAllPara");     
        List<HysParameter> list = query.getResultList();
        if(list!=null&&list.size()>0){
            String databaseBackupState = "";//用于接收数据库备份开关的状态信息 //open / close
            Iterator it = list.iterator();
            while(it.hasNext()){
                 HysParameter hysParameter = (HysParameter)it.next();
                 HysParameter parameter = new HysParameter();
                 if(hysParameter!=null){
                     String value = hysParameter.getHysParameterValue();
                     
                     if("databaseBackupInterval".equals(hysParameter.getHysParameterCode())){       
                         //将时间进行处理前台使用的式样
                         value = StringUtil.analyseInterval(value);                        
                         parameter.setHysParameterValue(value);
                     }
                     if("databaseBackupPath".equals(hysParameter.getHysParameterCode())){  
                         //处理成前台使用的样式
                         value = StringUtil.dealPathSingle(value);
                         parameter.setHysParameterValue(value);
                     } 
                     parameter.setHysStorageId(hysParameter.getHysStorageId());
                     parameter.setHysParameterCode(hysParameter.getHysParameterCode());
                     parameter.setHysParameterType(hysParameter.getHysParameterType());
                     parameter.setHysRemark(hysParameter.getHysRemark());
                     parameter.setHysParameterValue(value);
                     if("databaseBackupState".equals(parameter.getHysParameterCode())&&"state".equals(parameter.getHysParameterType())){
                         databaseBackupState = parameter.getHysParameterValue();
                     }                    
                 }
                 page.getHysParameterRows().add(parameter);
            }
            
            if(databaseBackupState==null||"".equals(databaseBackupState)){
                databaseBackupState = "close";
            }
            page.setDatabaseBackupState(databaseBackupState);
        }       
              
        return page;
    }
    
    /**
     * 获取数据库备份地址
     * @return 
     */
    public String getBackPath() {
        String backPath = Constant.DATABACE_BACKUP_PATH;           
        HysParameter hysParameter = new HysParameter();
        //从数据库获取参数路径1
        hysParameter.setHysParameterCode("databaseBackupPath");
        hysParameter.setHysParameterType("path");
        hysParameter = hysParameterDao.findByParamCodeAndType(hysParameter);
        if(hysParameter!=null&&hysParameter.getHysParameterValue()!=null){
            backPath = hysParameter.getHysParameterValue();
        }
        return backPath;
    }
    
    
    
}
