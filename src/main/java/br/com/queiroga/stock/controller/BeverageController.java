package br.com.queiroga.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.queiroga.stock.exception.NotFoundException;
import br.com.queiroga.stock.model.Beverage;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.dto.BeverageStockDTO;
import br.com.queiroga.stock.model.dto.TotalVolumeDTO;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;
import br.com.queiroga.stock.model.enums.SortEnum;
import br.com.queiroga.stock.service.BeverageService;
import br.com.queiroga.stock.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/beverage", produces = MediaType.APPLICATION_JSON_VALUE)
@Api
public class BeverageController {

	@Autowired
	private BeverageService beverageService;
	
	@Autowired
	private SectionService sectionService;
	
	@ApiOperation(value = "Creates an beverage entry in stock")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad request", responseContainer ="Map", response=java.lang.String.class),
			@ApiResponse(code = 422, message = "Section not found")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Beverage> create(@Valid @RequestBody(required = true) BeverageStockDTO beverage){
		
		Beverage beverageStock = beverageService.create(convertToEntity(beverage));
		return ResponseEntity.status(HttpStatus.CREATED).body(beverageStock);
		
	}

	@ApiOperation(value = "List all beverages")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Beverage>> getAll(@RequestParam(name = "dateSorting", defaultValue = "DESC") SortEnum  dateSorting,
			@RequestParam(name = "sectionSorting", defaultValue = "ASC")SortEnum sectionSorting){
		
		List<Beverage> beverages = beverageService.listAll(dateSorting,sectionSorting);
		
		if(beverages.isEmpty()) {
			throw new NotFoundException("No beverages registered");
		}
		
		return ResponseEntity.ok(beverages);
		
	}
	
	
	@ApiOperation(value = "List all beverages by type")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok"),
			@ApiResponse(code = 404, message = "Not found")
	})
	@GetMapping(value = "/type/{type}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Beverage>> listByType(@PathVariable(name = "type") BeverageTypeEnum type,
			@RequestParam(name = "dateSorting", defaultValue = "DESC") SortEnum  dateSorting,
			@RequestParam(name = "sectionSorting", defaultValue = "ASC")SortEnum sectionSorting){
		
		List<Beverage> beverages = beverageService.findByType(type, dateSorting, sectionSorting);
		if(beverages.isEmpty()) {
			throw new NotFoundException("Beverages not found for the informed type");
		}
		return ResponseEntity.ok(beverages);
		
	}
		
	@ApiOperation(value = "List total volume by type")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok")
	})
	@GetMapping(value = "/type/{type}/volume" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TotalVolumeDTO> listTotalVolumeByType(@PathVariable(name = "type") BeverageTypeEnum type){
		
		Integer total = beverageService.getTotalVolumeByType(type);

		return ResponseEntity.ok(TotalVolumeDTO.builder().totalVolume(total).build());
		
	}
	
	
	@ApiOperation(value = "List total volume by section")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok")
	})
	@GetMapping(value = "/section/{section}/volume" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TotalVolumeDTO> listTotalVolumeBySection(@PathVariable(name = "sectionId") Long sectionId){
		
		Integer total = beverageService.getTotalVolumeBySection(sectionId);
	
		return ResponseEntity.ok(TotalVolumeDTO.builder().totalVolume(total).build());
		
	}
	
	
	@ApiOperation(value = "Search storage locations by type")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok")
	})
	@GetMapping(value = "/type/{type}/storage" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Section>> listStorageLocationsByType(@PathVariable(name = "type") BeverageTypeEnum type){
		
		List<Section> sections = sectionService.findStorageLocationsByType(type);
	
		return ResponseEntity.ok(sections);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	private Beverage convertToEntity(BeverageStockDTO beverageDTO) {
		Beverage beverage = new Beverage();
		BeanUtils.copyProperties(beverageDTO, beverage);
		beverage.setSection(Section.builder().id(beverageDTO.getSection().getId()).build());
		return beverage;
	}

}
