package com.tianhua.codemaker.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

/**
 * description: FreemarkerUtils <br>
 * date: 2020/7/6 23:13 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
public class FreemarkerUtils {
    public static String parseTpl(String viewName, Map<String, Object> params) {
        Configuration cfg = SpringContextHolder.getBean(Configuration.class);
        String html = null;
        Template t = null;
        try {
            t = cfg.getTemplate(viewName + ".ftl");
            html = FreeMarkerTemplateUtils.processTemplateIntoString(t, params);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return html;
    }


}
