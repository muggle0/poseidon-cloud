package com.muggle.code;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @program: poseidon-generator
 * @description:
 * @author: muggle
 * @create: 2019-12-05
 **/

public abstract class CodeGeneratorTemplate {

    private TableMessage message;

    public CodeGeneratorTemplate(TableMessage message) {
        this.message = message;
    }

    abstract void connect();

    abstract DataSourceConfig configDesc(TableMessage dsc);

    abstract GlobalConfig configGlobal(TableMessage message);

    abstract PackageConfig configPc(TableMessage message);

    abstract InjectionConfig config(TableMessage message);

    abstract TemplateConfig configTemp(TableMessage message);

    abstract StrategyConfig configStrategy(TableMessage message);

    public void createCode(){
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        DataSourceConfig dataSourceConfig = configDesc(this.message);
        GlobalConfig globalConfig = configGlobal(this.message);
        PackageConfig packageConfig = configPc(this.message);
        InjectionConfig config = config(this.message);
        mpg.setPackageInfo(packageConfig);
        mpg.setDataSource(dataSourceConfig);
        mpg.setGlobalConfig(globalConfig);
        mpg.setCfg(config);
        TemplateConfig templateConfig = configTemp(this.message);
        mpg.setTemplate(templateConfig);
        StrategyConfig strategyConfig = configStrategy(message);
        mpg.setStrategy(strategyConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
