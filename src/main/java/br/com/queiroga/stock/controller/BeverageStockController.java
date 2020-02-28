package br.com.queiroga.stock.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.queiroga.stock.model.BeverageStock;
import br.com.queiroga.stock.model.dto.BeverageStockDTO;
import br.com.queiroga.stock.service.BeverageStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
@Api
public class BeverageStockController {

	@Autowired
	private BeverageStockService beverageService;
	
	@ApiOperation(value = "Creates an entry in stock")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created")
	})
	@PostMapping
	public ResponseEntity<BeverageStock> create(@ApiParam(required = true) @RequestBody BeverageStockDTO beverage){
		
		BeverageStock beverageStock = beverageService.create(createEntity(beverage));
		return ResponseEntity.status(HttpStatus.CREATED).body(beverageStock);
		
	}

	private BeverageStock createEntity(BeverageStockDTO beverage) {
		BeverageStock beverageStock = new BeverageStock();
		BeanUtils.copyProperties(beverage, beverageStock);
		return beverageStock;
	}
	
}
