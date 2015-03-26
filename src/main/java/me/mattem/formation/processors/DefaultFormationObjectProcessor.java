package me.mattem.formation.processors;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.mattem.formation.annotations.FormationExclude;
import me.mattem.formation.annotations.FormationInclude;
import me.mattem.formation.annotations.FormationInterface;
import me.mattem.formation.annotations.FormationMap;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.cache.FormationObjectHolder.ObjectPropertyHolder;
import me.mattem.formation.cache.FormationObjectHolder.ObjectPropertyTypeDescriptor;
import me.mattem.formation.cache.objectdescriptors.EnumObjectPropertyDescriptor;
import me.mattem.formation.cache.objectdescriptors.InterfaceObjectPropertyDescriptor;
import me.mattem.formation.cache.objectdescriptors.MapObjectPropertyDescriptor;
import me.mattem.formation.scanners.FormationObjectScannerResult;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

public class DefaultFormationObjectProcessor extends AbstractFormationObjectProcessor {
	
	public DefaultFormationObjectProcessor(FormationObjectScannerResult scannerResult, FormationObjectCache objectCache) {
		super(scannerResult, objectCache);
	}
	
	@Override
	public boolean canProcessClass(Class<?> clazz) { return true; }

	@Override
	public FormationObjectHolder processClass(Class<?> clazz) {
		FormationObjectHolder objHolder;
		if(!clazz.isEnum()){
			objHolder = handleSimpleClass(clazz);
		}else{
			objHolder = handleEnumClass(clazz);
		}
		return objHolder;
	}
	
	@Override
	public boolean onStartProcessingResults() { return true; }

	@Override
	public boolean onDoneProcessingResults() { return true; }

	private FormationObjectHolder handleSimpleClass(Class<?> clazz){
		FormationInclude anno = AnnotationUtils.findAnnotation(clazz, FormationInclude.class);
		boolean searchHierarchy = anno.inherit();
		FormationObjectHolder objHolder = new FormationObjectHolder();
		
		String formationObjectName = resolveObjectName(clazz.getSimpleName(), anno);
		objHolder.setObjectName(formationObjectName);
		
		ReflectionUtils.doWithMethods(clazz, new ReflectionUtils.MethodCallback() {
			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				FormationExclude exclude = AnnotationUtils.findAnnotation(method, FormationExclude.class);
				if(exclude == null && isGetter(method)  && !ReflectionUtils.isObjectMethod(method)){
					if(!searchHierarchy && !clazz.equals(method.getDeclaringClass())){
						logger.debug("Ignoring method ["+method.getName()+"] on formation object ["+formationObjectName+"] as inherit is false - "
								+ "Found on ["+method.getDeclaringClass().getName()+"]");
					}else{
						ObjectPropertyHolder propHolder = processMethod(anno, clazz, method, formationObjectName);
						if(propHolder != null) objHolder.addPropertyHolder(propHolder);
					}
				}else{
					if(logger.isTraceEnabled())
						logger.trace("Ignoring method ["+method.getName()+"] on formation object ["+formationObjectName+"]");
				}
				
			}
		});
		
		for(String cat : anno.typeCategories()){
			objectCache.putCategory(cat, objHolder);
		}
		
		return objHolder;
	}
	
	private FormationObjectHolder handleEnumClass(Class<?> clazz) {
		FormationInclude anno = AnnotationUtils.findAnnotation(clazz, FormationInclude.class);
		FormationObjectHolder objHolder = new FormationObjectHolder();		
		String formationObjectName = resolveObjectName(clazz.getSimpleName(), anno);
		objHolder.setObjectName(formationObjectName);
		
		try{
			Method toString = clazz.getMethod("toString");
			ObjectPropertyHolder propHolder = new ObjectPropertyHolder();
			propHolder.setPropertyGeneralType("Enum");
			propHolder.setProperyName(formationObjectName);
			
			EnumObjectPropertyDescriptor eopd = new EnumObjectPropertyDescriptor();
			for(Object o : clazz.getEnumConstants()){
				eopd.addValue((String) toString.invoke(o));
			}
			propHolder.setObjectPropertyDescriptor(eopd);
			
			objHolder.addPropertyHolder(propHolder);
		}catch(NoSuchMethodException nsme){
			logger.error("Error getting values from Formation enum ["+formationObjectName+"] "
					+ "Enum types must implement the 'toString()' method to be used in Formation. "
					+ "This wont stop Formation from displaying this in the form, but it's values will not be available.");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("Error invoking 'toString()' on Formation enum ["+formationObjectName+"]. "
					+ "Nested exception is: ", e);
		}
		
		return objHolder;
	}
	
	private ObjectPropertyHolder processMethod(FormationInclude classInclude, Class<?> clazz, Method method, String formationObjectName){
		
		Class<?> returnType = method.getReturnType();
		if(isTypeListAnnotationNeeded(returnType) && !hasFormationTypeAnnotation(method)){
			logger.error("A non annotated method returning an abstract class or interface was found on Formation object ["+formationObjectName+"]"
					+ " This ("+returnType.getSimpleName()+") will be ignored and not included in the form. If you require it there, add the appropiate @Formation type "
					+ "annotation and provide a list of suitable candidate classes");
			return null; // Don't bother
		}
	
		ObjectPropertyHolder propHolder = new ObjectPropertyHolder();
		propHolder.setProperyName(resolveProperyName(method.getName()));
		
		propHolder.setPropertyGeneralType(returnType.getSimpleName());
		propHolder.setPropertyTypeDescriptor(this.resolvePropertyTypeDescriptor(method));
			
		if(returnType.isAssignableFrom(Map.class)){
			propHolder.setObjectPropertyDescriptor(mapDescriptor(clazz, method));
		}
		
		if(isTypeListAnnotationNeeded(returnType)){
			propHolder.setObjectPropertyDescriptor(interfaceDescriptor(clazz, method));
			// Override the general type
			propHolder.setPropertyGeneralType("Interface");
		}
		
		logger.debug("Adding property ["+propHolder.getProperyName()+"] of type ["+propHolder.getPropertyGeneralType()+"] "
				+ "to formation object ["+formationObjectName+"]");
		
		return propHolder;
	}
	
	private boolean isTypeListAnnotationNeeded(Class<?> clazz){
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
	
	private MapObjectPropertyDescriptor mapDescriptor(Class<?> clazz, Method method){
		MapObjectPropertyDescriptor mapDescriptor = new MapObjectPropertyDescriptor();
		
		FormationMap mapAnno = AnnotationUtils.findAnnotation(method, FormationMap.class);
		if(mapAnno != null){
			mapDescriptor.setMapKeyLabel(mapAnno.keyLabel());
			mapDescriptor.setMapValueLabel(mapAnno.valueLabel());
		}
		return mapDescriptor;
	}
	
	private InterfaceObjectPropertyDescriptor interfaceDescriptor(Class<?> clazz, Method method){
		InterfaceObjectPropertyDescriptor ifaceDescriptor = new InterfaceObjectPropertyDescriptor();
		FormationInterface formationIface = AnnotationUtils.findAnnotation(method, FormationInterface.class);
		if(formationIface != null){
			ifaceDescriptor.setTypeCategory(formationIface.typeCategory());
		}
		
		return ifaceDescriptor;
	}
	
	private ObjectPropertyTypeDescriptor resolvePropertyTypeDescriptor(Method method){
		Type returnType = method.getGenericReturnType();
		return analyseType(returnType);
	}
	
	private ObjectPropertyTypeDescriptor analyseType(Type type){
		ObjectPropertyTypeDescriptor typeDescriptor = new ObjectPropertyTypeDescriptor();
		
		if (type instanceof Class){
			typeDescriptor.addGeneralType(((Class<?>) type).getSimpleName());
		
		} else if (type instanceof ParameterizedType){
		    ParameterizedType pt = (ParameterizedType)type;
		    Class<?> c = (Class<?>)pt.getRawType();
		    typeDescriptor.addGeneralType(c.getSimpleName());
		    Type o = pt.getOwnerType();
		    if (o != null) { 
		    	typeDescriptor.addInnerType(this.analyseType(o)); 
		    }
		    
		    for (Type  t : pt.getActualTypeArguments()){
		    	typeDescriptor.addInnerType(this.analyseType(t));
		    }

		    
		} else if (type instanceof TypeVariable<?>) {
		    TypeVariable<?> v = (TypeVariable<?>)type;
		    typeDescriptor.addGeneralType(v.getName());
		
		} else if (type instanceof GenericArrayType) {
		    GenericArrayType gat = (GenericArrayType)type;
		    typeDescriptor.addInnerType(this.analyseType(gat.getGenericComponentType()));
		
		} else if (type instanceof WildcardType) {
		    WildcardType w = (WildcardType)type;
		    Type[] upper = w.getUpperBounds();
		    Type[] lower = w.getLowerBounds();
		    if (upper.length==1 && lower.length==0) {
		    	typeDescriptor.addInnerType(this.analyseType(upper[0]));
		    } else if (upper.length==0 && lower.length==1) {
				typeDescriptor.addInnerType(this.analyseType(lower[0]));
		    }
		} else {
			typeDescriptor.setUnknownType(true);
		}
		return typeDescriptor;
	}
	
}
