package vip.bblog.cunadmin;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 *
 * @author zhuyc
 * @since 2019-03-20
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + File.separator + "system" + File.separator + "src" + File.separator + "main" + File.separator + "java");
        gc.setFileOverride(true);
        gc.setAuthor("yy");
        gc.setEnableCache(false);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setEntityName("%s");
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/cun_admin?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        sc.setEntityTableFieldAnnotationEnable(true);
        //去除表前缀
        //sc.setTablePrefix("sys_");
        sc.setInclude("sys_user,sys_role,sys_menu,sys_user_role,sys_role_menu,sys_biz_tenant");
        mpg.setStrategy(sc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("vip.bblog.cunadmin");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        pc.setMapper("dao");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 自定义配置

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！

        /*mpg.setTemplateEngine(new FreemarkerTemplateEngine());*/
        mpg.execute();
    }

}
