package me.mattem.formation.processors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import me.mattem.formation.EnableFormation;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.calibration.FormationStringCalibrationClass;
import me.mattem.formation.configuration.FormationContext;

import org.junit.Before;
import org.junit.Test;

public class DefaultFormationObjectProcessorTest {
	private DefaultFormationObjectProcessor objectProcessor;

	@Before
	public void setUp() throws Exception {
		objectProcessor = new DefaultFormationObjectProcessor();
		
		EnableFormation enableFormation = FormationEnabler.class.getAnnotation(EnableFormation.class);
		FormationContext formationContext = new FormationContext(enableFormation, FormationEnabler.class);
		objectProcessor.formationContext = formationContext;
		
		FormationObjectCache objectCache = mock(FormationObjectCache.class);
		when(objectCache.read()).thenReturn(new HashMap<String, FormationObjectHolder>());
		objectProcessor.objectCache = objectCache;
	}

	@Test
	public void testProcessStringCalibrationClass() {
		FormationObjectHolder holder = objectProcessor.processClass(FormationStringCalibrationClass.class);
		assertNotNull(holder);
	}
	
	@EnableFormation(basePackages="me.mattem.test")
	private static class FormationEnabler {}

}
