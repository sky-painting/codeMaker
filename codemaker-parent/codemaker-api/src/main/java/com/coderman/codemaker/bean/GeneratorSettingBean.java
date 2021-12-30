package com.coderman.codemaker.bean;


/**
 * @Description:类配置信息，后期存储在数据库或配置文件中
 * @Author：coderman
 * @CreateTime：2018年8月28日下午11:19:41
 * @version v1.0
 */
@Deprecated
public class GeneratorSettingBean {

	private String ftlFilePath;
	private String javaFileSubPackage;
	private String javaFileSuffix;
	private String javaFileName;
	private String fileKey;


	public GeneratorSettingBean() {}
	public GeneratorSettingBean(String ftlFilePath, String javaFileSubPackage, String javaFileName) {
		this.ftlFilePath = ftlFilePath;
		this.javaFileSubPackage = javaFileSubPackage;
		this.javaFileName = javaFileName;
	}

	public String getFtlFilePath() {
		return ftlFilePath;
	}

	public void setFtlFilePath(String ftlFilePath) {
		this.ftlFilePath = ftlFilePath;
	}

	public String getJavaFileSubPackage() {
		return javaFileSubPackage;
	}

	public void setJavaFileSubPackage(String javaFileSubPackage) {
		this.javaFileSubPackage = javaFileSubPackage;
	}

	public String getJavaFileSuffix() {
		return javaFileSuffix;
	}

	public void setJavaFileSuffix(String javaFileSuffix) {
		this.javaFileSuffix = javaFileSuffix;
	}

	public String getJavaFileName() {
		return javaFileName;
	}

	public void setJavaFileName(String javaFileName) {
		this.javaFileName = javaFileName;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
}
