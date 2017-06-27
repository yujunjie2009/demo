package com.newtiming.finance.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerUtil {
	/** 
	* 获取freemarker可使用的bean 
	* @param clz 类型 
	* @return 
	*/  
	@SuppressWarnings("rawtypes")  
	public static TemplateModel getStaticModel(Class clz) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			return wrapper.getStaticModels().get(clz.getName());
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		return null;
	}
}

