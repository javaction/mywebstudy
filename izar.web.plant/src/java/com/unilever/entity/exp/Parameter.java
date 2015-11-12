/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 公共传参对象
 * @author Administrator
 */
@XmlRootElement
public class Parameter {
    
    //编号，多个以逗号“,”分割
    private String ids;
    
    private String databaseBackupPath;//立即备份数据库路径
        
    private String flag;//操作标志 //可以用于数据库备份开关的传参
    
    private String disk;//盘符
    
    private String oldPassword;//用户修改密码中的原密码
    
    private String newPassword;//用户修改密码中的新密码
    
    private String confirmPassword;//用户修改密码中的确认密码

    /**
     * @return the ids
     */
    public String getIds() {
        return ids;
    }

    /**
     * @param ids the ids to set
     */
    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * @return the databaseBackupPath
     */
    public String getDatabaseBackupPath() {
        return databaseBackupPath;
    }

    /**
     * @param databaseBackupPath the databaseBackupPath to set
     */
    public void setDatabaseBackupPath(String databaseBackupPath) {
        this.databaseBackupPath = databaseBackupPath;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the disk
     */
    public String getDisk() {
        return disk;
    }

    /**
     * @param disk the disk to set
     */
    public void setDisk(String disk) {
        this.disk = disk;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
