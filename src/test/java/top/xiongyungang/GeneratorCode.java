package top.xiongyungang;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Arrays;

/**
 * 自动生成代码
 */
public class GeneratorCode {
    public static void main(String[] args) {
        // 生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        // 配置策略
        // 1.全局策略
        GlobalConfig config = new GlobalConfig();
        // 设置生成目录
        String userDir = System.getProperty("user.dir"); // 获取工作目录
        config.setOutputDir(userDir+"/src/main/java");
        config.setAuthor("xiongyungang");
        config.setFileOverride(false); // 覆盖原文件
        config.setOpen(false); // 资源管理器中打开
        config.setServiceName("%sService"); // 默认Service前缀I，使用正则去掉I前缀
        config.setIdType(IdType.AUTO); // 默认主键策略，雪花
        config.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(config);

        // 2.设置数据源
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setUrl("jdbc:mysql://localhost:3306/nobitan?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        sourceConfig.setDriverName("com.mysql.jdbc.Driver");
        sourceConfig.setPassword("root");
        sourceConfig.setUsername("root");
        sourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(sourceConfig);

        // 3. 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setModuleName("nobitan");
        packageConfig.setService("service");
        packageConfig.setParent("top.xiongyungang");
        autoGenerator.setPackageInfo(packageConfig);

        // 4.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("content"); // 设置的表名
        strategyConfig.setEntityLombokModel(true); // 自动添加Lombok
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // _转驼峰
        strategyConfig.setLogicDeleteFieldName("deleted"); // 逻辑删除
        // 自动填充
        TableFill createTimeFill = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTimeFill = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        strategyConfig.setTableFillList(Arrays.asList(createTimeFill, updateTimeFill));
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true); // localhost/get_id_2
        autoGenerator.setStrategy(strategyConfig);


        // 执行生成器
        autoGenerator.execute();
    }
}
