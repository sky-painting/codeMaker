package com.tianhua.codemaker.app;

import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.enums.ModuleEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Description:
 * date: 2022/1/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class FunctionCodeAppWriteService {

    @Autowired
    private CommonWriteService writeService;

    /**
     * 第一级key:模块标示
     * 第二级key:代码元素标示
     * 第二级value:代码生成的lamda函数
     */
    private static Map<String,Map<String, Consumer<ClassContentBean>>> templateCodeWriteMap = new HashMap<>();

    /**
     *  初始化业务分派逻辑,代替了if-else部分
     *  key: 代码元素类型
     *  value: lambda表达式,写代码文件执行的方法
     */
    public void dispatcherInit(String appType){
        for (ModuleEnum moduleEnum : ModuleEnum.values()){
            if(moduleEnum.getAppName().equals(appType)){
                String simleModule = moduleEnum.getModuleName().replace(appType+"-","");
                Map<String, Consumer<ClassContentBean>> consumerMap = templateCodeWriteMap.get(simleModule);
                if(consumerMap == null){
                    consumerMap = new HashMap<>();
                }
                Map<String, Consumer<ClassContentBean>> finalConsumerMap = consumerMap;
                moduleEnum.getTemplateFileSet().forEach(templateCode-> {
                    if(templateCode.contains(TemplateFileEnum.POM.getTempFileName())){
                        finalConsumerMap.put(templateCode, classContentBean -> writeService.writePom(classContentBean));
                    } else if(templateCode.contains(TemplateFileEnum.MAPPER_XML.getTempFileName())){
                        finalConsumerMap.put(templateCode, classContentBean -> writeService.writeMapperXml(classContentBean));
                    }else if(templateCode.contains(TemplateFileEnum.TEST.getTempFileName())){
                        finalConsumerMap.put(templateCode, classContentBean -> writeService.writeClassTestFile(classContentBean));
                    } else {
                        finalConsumerMap.put(templateCode, classContentBean -> writeService.writeRoute(classContentBean));
                    }
                });
                templateCodeWriteMap.put(simleModule,consumerMap);
            }
        }
    }

    /**
     * 函数式代码元素文件写入方式
     * @param templateCode
     * @param moduleCode
     * @param classContentBean
     */
    public void write(String templateCode, String moduleCode, ClassContentBean classContentBean){
        //外部appwriteservice根据模块标示moduleCode 代码元素标示templateCode、路由并执行写入函数
        Consumer<ClassContentBean> result = templateCodeWriteMap.get(moduleCode).get(templateCode);
        if(result != null){
            //classContentBean 执行这段表达式进行代码文件写入
            result.accept(classContentBean);
            return;
        }
        System.out.println("找不到对应的代码元素处理函数--->moduleCode = "+moduleCode+",templateCode = "+templateCode);
    }


}
