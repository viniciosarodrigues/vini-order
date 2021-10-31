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

import br.com.vini.orderapi.api.service.product.ProductService;
import br.com.vini.orderapi.api.service.product.protocol.ProductProtocol;
import br.com.vini.orderapi.api.service.product.repositories.custom.ProductFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = {"Produto"})
@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController<ProductService> {

    @ApiOperation(value = "Busca paginada", nickname = "find-page-product")
    @GetMapping
    public ResponseEntity<Page<ProductProtocol>> findPage(@ApiParam(value = "Filtros de busca e paginação") ProductFilter filters) {
        Pageable pageSettings = PageRequest.of(filters.getPage(), filters.getSize(), Direction.valueOf(filters.getDirection()),
                                               filters.getOrderBy());
        return ResponseEntity.ok(service.getPage(pageSettings, filters));
    }

    @ApiOperation(value = "Busca por identificador", nickname = "find-by-id-product")
    @GetMapping("/{id}")
    public ResponseEntity<ProductProtocol> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
