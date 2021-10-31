package br.com.vini.orderapi.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vini.orderapi.api.service.fiscalcategory.FiscalCategoryService;
import br.com.vini.orderapi.api.service.fiscalcategory.protocol.FiscalCategoryProtocol;
import br.com.vini.orderapi.api.service.fiscalcategory.repositories.custom.FiscalCategoryFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"Categoria (Fiscal)"})
@RestController
@RequestMapping(value = "/category")
public class FiscalCategoryController extends BaseController<FiscalCategoryService> {

    @ApiOperation(value = "Busca paginada")
    @GetMapping
    public ResponseEntity<Page<FiscalCategoryProtocol>> findPage(@ApiParam(value = "Filtros de busca e paginação") FiscalCategoryFilter filters) {
        Pageable pageSettings = PageRequest.of(filters.getPage(), filters.getSize(), Direction.valueOf(filters.getDirection()),
                                               filters.getOrderBy());
        return ResponseEntity.ok(service.getPage(pageSettings, filters));
    }

    @ApiOperation(value = "Busca por identificador")
    @GetMapping("/{id}")
    public ResponseEntity<FiscalCategoryProtocol> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
