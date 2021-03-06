package action;

import macaca.client.MacacaClient;
import object.PageTemplate;
import util.Tools;

import java.util.Date;

public class LoginAction extends BaseAction {
    private PageTemplate page;

    /*
     * @description         登录的构造方法
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param driver        Macaca Driver
     * @param repoPath      对象仓库路径
     */
    public LoginAction(MacacaClient driver, String udid, String repoPath) {
        super(driver, udid, repoPath);
        page = new PageTemplate(driver, repoPath);
    }

    /*
    * @description: 欢迎页的等待方法
    * @author:      Griffin
    * @date:        2018/6/21
    * @time:        19:07
    * @param:
    * @return:      是否超时的Boolean值
    */
    public Boolean welcomePageAppear() {
        Date beginTime = new Date();
        while (!this.isElementExist("PageElementModule", "欢迎页", "欢迎首页")) {
            Tools.sleep(1000);              // 如果元素没有出现则暂停1秒
            Date endTime = new Date();
            long delayTime = endTime.getTime() - beginTime.getTime();
            if (delayTime / 1000 > 60)          // 如果1分钟没有出现则返回False
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /*
     * @description         登录操作的基础方法
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param userName      用户名
     * @param password      密码
     */
    private void login(String userName, String password) {
        page.setElementModule("PageElementModule", "登录页");
        page.clear("用户名");
        page.clear("密码");
        page.input("用户名", userName);
        page.input("密码", password);
        page.click("登录");
    }

    /*
     * @description         通过机会页登录
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param userName      用户名
     * @param password      密码
     */
    public void loginBySubscribeNow(String userName, String password) {
        page.setElementModule("PageElementModule", "机会页");
        page.click("立即订阅");
        this.login(userName, password);
        page.setElementModule("PageElementModule", "职位订阅");
        page.click("退出");
    }

    /*
     * @description         通过消息页登录
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param userName      用户名
     * @param password      密码
     */
    public void loginByMessage(String userName, String password) {
        page.setElementModule("PageElementModule", "基础页");
        page.click("消息");
        this.login(userName, password);
    }

    /*
     * @description         通过简历页登录
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param userName      用户名
     * @param password      密码
     */
    public void loginByResume(String userName, String password) {
        page.setElementModule("PageElementModule", "基础页");
        page.click("简历");
        this.login(userName, password);
    }

    /*
     * @description         通过我的页登录
     * @author:             Griffin
     * @date:               2018/5/4
     * @time:               10:46
     * @param userName      用户名
     * @param password      密码
     */
    public void loginByMyself(String userName, String password) {
        page.setElementModule("PageElementModule", "基础页");
        page.click("我的");
        page.setElementModule("PageElementModule", "我的页");
        page.click("立即登录");
        this.login(userName,password);
    }
}