package me.mattem.formation.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.scanners.FormationObjectScannerResult;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public abstract class AbstractFormationObjectProcessor implements FormationObjectProcessor {
	protected final Log logger = LogFactory.getLog(getClass());
	
	protected final FormationObjectScannerResult scannerResult; 
	protected final FormationObjectCache objectCache;
	
	public AbstractFormationObjectProcessor(FormationObjectScannerResult scannerResult, FormationObjectCache objectCache){
		this.scannerResult = scannerResult;
		this.objectCache = objectCache;
	}
	
	@PostConstruct
	private void init(){
		this.onStartProcessingResults();
		
		for(Class<?> clazz : scannerResult.getClasses()){
			logger.debug("Formation processing class ["+clazz.getName()+"]");
			Long start = System.currentTimeMillis();
			
			FormationObjectHolder objHolder;
			if(this.canProcessClass(clazz)){
				objHolder = this.processClass(clazz);
				
				objectCache.putObjectHolder(objHolder);

				Long taken = System.currentTimeMillis() - start;
				logger.debug("Done processing class ["+clazz.getName()+"] in ["+taken+"] ms "
					+ "- Created formation ["+objHolder.getObjectName()+"] with ["+objHolder.getPropertyHolders().size()+"] properties");
			}
		}
		
		this.onDoneProcessingResults();
	}
	
	protected boolean hasFormationTypeAnnotation(Method method){
		Assert.notNull(method, "Method to check for Formation Type annotations can not be null");
		Annotation[] annotations = method.getAnnotations();
		if(annotations.length == 0){
			return false;
		}else{
			for(Annotation a : annotations){
				if(a.annotationType().getName().startsWith("com.jarvis.formation.annotations"))
					return true;
			}
		}
		return false;
	}
	
	protected String resolveObjectName(String name, FormationInclude anno){
		if(anno != null && StringUtils.hasText(anno.name())) return anno.name();
		return name;
	}
	
	protected String resolveProperyName(String name){
		if (name.matches("^get[A-Z].*")){
			return WordUtils.capitalize(name.replaceFirst("get", ""));
		}else if(name.matches("^is[A-Z].*")){
			return WordUtils.capitalize(name.replaceFirst("is", ""));
		}
		return "";
	}
	
	protected boolean isGetter(Method method) {
	   if (method.getParameterTypes().length == 0) {
	         if (method.getName().matches("^get[A-Z].*") &&
	            !method.getReturnType().equals(void.class))
	               return true;
	         if (method.getName().matches("^is[A-Z].*") &&
	            method.getReturnType().equals(boolean.class))
	               return true;
	   }
	   return false;
	}
		
	protected boolean isSetter(Method method) {
	   return method.getReturnType().equals(void.class) &&
	         method.getParameterTypes().length == 1 &&
	            method.getName().matches("^set[A-Z].*");
	}
}
