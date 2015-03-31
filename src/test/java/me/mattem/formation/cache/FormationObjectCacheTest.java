package me.mattem.formation.cache;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FormationObjectCacheTest {
	FormationObjectCache objectCache;

	@Before
	public void setUp() throws Exception {
		objectCache = new FormationObjectCache();
	}

	@After
	public void tearDown() throws Exception {
		objectCache = null;
	}

	@Test
	public void testPutObjectHolder() {
		FormationObjectHolder foh = new FormationObjectHolder();
		foh.setObjectName("Test Object");
		foh.setClassName(this.getClass().getName());
		
		objectCache.putObjectHolder(foh);
		
		FormationObjectHolder cachedHolder = objectCache.getObjectHolder("Test Object");
		
		assertEquals(foh, cachedHolder);
	}
	
	@Test
	public void testGetObjectHolderByObjectName() {
		FormationObjectHolder foh = new FormationObjectHolder();
		foh.setObjectName("Test Object");
		foh.setClassName(this.getClass().getName());
		
		objectCache.putObjectHolder(foh);
		
		FormationObjectHolder cachedHolder = objectCache.getObjectHolder("Test Object");
		
		assertEquals(foh.getObjectName(), cachedHolder.getObjectName());
	}
	
	@Test
	public void testGetObjectHolderByClassName() {
		FormationObjectHolder foh = new FormationObjectHolder();
		foh.setObjectName("Test Object");
		foh.setClassName(this.getClass().getName());
		
		objectCache.putObjectHolder(foh);
		
		FormationObjectHolder cachedHolder = objectCache.getObjectHolder(this.getClass().getName());
		
		assertEquals(foh.getObjectName(), cachedHolder.getObjectName());
		assertEquals(foh.getClassName(), cachedHolder.getClassName());
	}
	
	@Test
	public void testPutCatagry() {
		FormationObjectHolder foh = new FormationObjectHolder();
		foh.setObjectName("Test Object");
		FormationObjectHolder foh2 = new FormationObjectHolder();
		foh.setObjectName("Test Object 2");
		

		objectCache.putCategory("Test Cat", foh);
		objectCache.putCategory("Test Cat", foh2);
		
		List<FormationObjectHolder> catList = objectCache.getCategory("Test Cat");
		assertNotNull(catList);
		assertEquals(2, catList.size());
	}

}
