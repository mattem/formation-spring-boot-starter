package me.mattem.formation.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.configuration.FormationContext;
import me.mattem.formation.scanners.FormationObjectScannerResult;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public abstract class AbstractFormationObjectProcessor implements FormationObjectProcessor {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired protected FormationContext formationContext;
	@Autowired protected FormationObjectScannerResult scannerResult; 
	@Autowired protected FormationObjectCache objectCache;
	
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
				if(a.annotationType().getName().startsWith("me.mattem.formation.annotations"))
					return true;
			}
		}
		return false;
	}
	
	protected String resolveObjectName(Class<?> clazz, FormationInclude anno){
		if(anno != null && StringUtils.hasText(anno.name())) return anno.name();
		if(formationContext.isGuaranteedUniqueClassNames()){
			return splitCamelCase(clazz.getSimpleName());
		}else{
			return clazz.getName();
		}
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
	
	protected boolean isTypeListAnnotationNeeded(Class<?> clazz){
		if(ClassUtils.isPrimitiveOrWrapper(clazz) ||
			clazz.isAssignableFrom(List.class) || 
			clazz.isAssignableFrom(Collection.class) || 
			clazz.isAssignableFrom(Map.class) ||
			clazz.isAssignableFrom(Set.class)){
			return false;
		}else if(Modifier.isAbstract(clazz.getModifiers()) 
				|| Modifier.isInterface(clazz.getModifiers())){
			return true;
		}
		return false;
	}
	
	protected String resolveFormationClassName(Class<?> clazz){
		String formationClassName = "";
		if(ClassUtils.isPrimitiveOrWrapper(clazz)){
			formationClassName = getWrapper(clazz).getSimpleName();
		}else if(clazz == Object.class){
			formationClassName = "Object"; // Special case for Object
		}else if(clazz == String.class){
			formationClassName = "String"; // Special case
		}else if(clazz.isAssignableFrom(List.class)){
			formationClassName = "List";
		}else if(clazz.isAssignableFrom(Map.class)){
			formationClassName = "Map";
		}else if(clazz.isAssignableFrom(Collection.class)){
			formationClassName = "Collection";
		}else if(clazz.isAssignableFrom(Set.class)){
			formationClassName = "Set";
		}else{
			formationClassName = clazz.getName();
		}
		return formationClassName;
	}
	
	protected Class<?> getWrapper(Class<?> clazz){
        if(clazz == int.class) {
            return Integer.class; 
		}else if(clazz == boolean.class){ 
            return Boolean.class; 
        }else if(clazz == long.class){
            return Long.class; 
        }else if(clazz == float.class){ 
            return Float.class; 
        }else if(clazz == double.class){ 
            return Double.class; 
		}else if(clazz == short.class){ 
            return Short.class; 
		}else if(clazz == char.class){ 
            return Character.class; 
		}else if (clazz == byte.class){
            return Byte.class; 
		}
        return clazz;
	}
	
	protected String splitCamelCase(String s) {
		   return s.replaceAll(
		      String.format("%s|%s|%s",
		         "(?<=[A-Z])(?=[A-Z][a-z])",
		         "(?<=[^A-Z])(?=[A-Z])",
		         "(?<=[A-Za-z])(?=[^A-Za-z])"
		      ),
		      " "
		   );
		}
}
