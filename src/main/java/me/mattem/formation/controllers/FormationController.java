package me.mattem.formation.controllers;

import java.util.List;
import java.util.Set;

import me.mattem.formation.cache.FormationObjectHolder;
import me.mattem.formation.services.FormationService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@ConditionalOnWebApplication
public class FormationController {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired private FormationService formation;
	
	@RequestMapping(value="/formation/describe", method=RequestMethod.GET)
	public @ResponseBody FormationObjectHolder runDescribe(@RequestParam(value="object") String object){
		logger.debug("Formation describing object ["+object+"]");
		return formation.getObject(object);
	}
	
	@RequestMapping(value="/formation/category", method=RequestMethod.GET)
	public @ResponseBody List<FormationObjectHolder> runType(@RequestParam(value="category") String category){
		logger.debug("Formation getting types for category object ["+category+"]");
		return formation.getObjectsForCategory(category);
	}
	
	@RequestMapping(value="/formation/list", method=RequestMethod.GET)
	public @ResponseBody Set<String> runList(){
		logger.debug("Formation getting known object list");
		return formation.getKnownObjectNames();
	}
	
	@RequestMapping(value="/formation/rebuild", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public void runRebuild(){
		formation.rebuild();
	}
	
}
