package me.mattem.formation.processors;

import static org.junit.Assert.*;
import static me.mattem.formation.FormationTestHelper.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import me.mattem.formation.EnableFormation;
import me.mattem.formation.cache.FormationObjectCache;
import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.cache.FormationObjectHolder.ObjectPropertyHolder;
import me.mattem.formation.calibration.FormationBooleanCalibrationClass;
import me.mattem.formation.calibration.FormationEnumCalibration;
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
		
		assertEquals("Formation String Calibration Class", holder.getObjectName());
		assertEquals(FormationStringCalibrationClass.class.getName(), holder.getClassName());
		
		assertEquals(2, holder.getPropertyHolders().size());
		assertTrue(hasPropertyOfType(holder.getPropertyHolders(), "SimpleString", "String"));
		assertTrue(hasPropertyOfType(holder.getPropertyHolders(), "SimpleStringTwo", "String"));
	}
	
	@Test
	public void testProcessBooleanCalibrationClass() {
		FormationObjectHolder holder = objectProcessor.processClass(FormationBooleanCalibrationClass.class);
		assertNotNull(holder);
		
		assertEquals("Formation Boolean Calibration Class", holder.getObjectName());
		assertEquals(FormationBooleanCalibrationClass.class.getName(), holder.getClassName());
		
		assertEquals(2, holder.getPropertyHolders().size());
		assertTrue(hasPropertyOfType(holder.getPropertyHolders(), "Something", "Boolean"));
		assertTrue(hasPropertyOfType(holder.getPropertyHolders(), "IsSomethingBoxed", "Boolean"));
	}
	
	@Test
	public void testProcessEnumCalibrationClass() {
		FormationObjectHolder holder = objectProcessor.processClass(FormationEnumCalibration.class);
		assertNotNull(holder);
		
		assertEquals("Formation Enum Calibration", holder.getObjectName());
		assertEquals(FormationEnumCalibration.class.getName(), holder.getClassName());
		
		assertEquals(1, holder.getPropertyHolders().size());
		assertTrue(hasPropertyOfType(holder.getPropertyHolders(), "Formation Enum Calibration", "Enum"));
		
		List<ObjectPropertyHolder> propertyHolders = holder.getPropertyHolders();
		assertNotNull(propertyHolders);
		assertTrue(hasEnumValues(propertyHolders.get(0), "One", "Two", "Three"));
	}
	
	@EnableFormation(basePackages="me.mattem.test", uniqueClassNames=true)
	private static class FormationEnabler {}

}
