package com.zackma.config;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.zackma.controller.IndexController;
import com.zackma.entity.JfUsers;

public class AppConfig extends JFinalConfig{

    /**
     * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
     * @param pro 生产环境配置文件
     * @param dev 开发环境配置文件
     */
    public void loadProp(String pro, String dev) {
        try {
            PropKit.use(pro);
        }
        catch (Exception e) {
            PropKit.use(dev);
        }
    }

    @Override
    public void configConstant(Constants constants) {
        loadProp("config_pro.properties", "config.properties");
        constants.setViewType(ViewType.JSP);
        constants.setDevMode(true);
    }

    @Override
    public void configRoute(Routes routes) {
        routes.setBaseViewPath("/WEB-INF/view/");
        routes.add("/", IndexController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        loadProp("config_pro.properties", "config.properties");
        C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"),
                PropKit.get("username"),
                PropKit.get("password"),
                PropKit.get("driver"));
        plugins.add(c3p0Plugin);//加载连接池

        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        arp.setDialect(new PostgreSqlDialect());
        arp.addMapping(JfUsers.tableName, JfUsers.class);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

}
