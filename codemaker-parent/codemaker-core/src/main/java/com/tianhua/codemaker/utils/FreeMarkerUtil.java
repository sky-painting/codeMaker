package com.tianhua.codemaker.utils;

import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.Map;

public class FreeMarkerUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerUtil.class);
	private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
//	private static final BeansWrapper beansWrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_26).build();
	static {
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setClassicCompatible(true);
		loadCommonStaticClass(cfg);
	}

	public static Configuration getConfiguration(Class<?> clazz, String templatePath){
		cfg.setClassForTemplateLoading(clazz, templatePath);
		return cfg;
	}
	public static Configuration getConfiguration(ServletContext servletContext){
		return getConfiguration(servletContext, "template");
	}
	public static Configuration getConfiguration(ServletContext servletContext, String templatePath){
		cfg.setServletContextForTemplateLoading(servletContext, templatePath);
		return cfg;
	}
	
	private static void loadCommonStaticClass(Configuration cfg) {
		try {
//			TemplateHashModel staticModels = beansWrapper.getStaticModels();
//			cfg.setSharedVariable("FreeMarkerUtil", (TemplateHashModel) staticModels.get("com.daojia.codegenerator.util.FreeMarkerUtil"));
//			cfg.setSharedVariable("StringUtils", (TemplateHashModel) staticModels.get("org.apache.commons.lang.StringUtils"));
//			cfg.setSharedVariable("Math", (TemplateHashModel) staticModels.get("java.lang.Math"));
		} catch (Exception e) {
			LOGGER.error("loadCommonStaticClass异常",e);
		}
	}
	public static Map<Object,Object> simpleHash2Map(SimpleHash sh){
		if (sh == null) {
			return null;
		}
		Map<Object,Object> map = null;
		try {
			map = sh.toMap();
		} catch (Exception e) {
			LOGGER.error("simpleHash2Map ## trans to map error", e);
			map = Maps.newHashMap();
		}
		return map;
	}
}
