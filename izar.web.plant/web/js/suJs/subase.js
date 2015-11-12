/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    
    /**
     * get some path of the porject
     */
    function getRootPath(){
       //1.获取当前网址 ，如http://localhost:8088/proj/menu
       var curWwwPath = window.document.location.href;
       
       //2.获取主机地址之后的目录，如：proj/menu.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        
        //3.获取主机地址，如：http:localhost:8088
        var localhostPath = curWwwPath.substring(0, pos);
        
        //4.获取带“/”的项目名，如： /proj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);
        
        return {
            curWwwPath :curWwwPath,
            pathName : pathName,
            pos : pos,
            localhostPath :localhostPath,
            projectName : projectName
            
        }
        
    }
    
    /**
     * get the url parm
     */
    function getRequestParm(){
        
        
        
    }