package com.ghostappi.backend.controller;

import java.util.List;
import java.util.Objects;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import com.ghostappi.backend.service.IngredientService;
import com.ghostappi.backend.model.Ingredient;

@RestController
@Validated
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*", methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.DELETE,
                RequestMethod.PUT
})
@Tag(name = "Ingredients", description = "Provides methods for managing ingredients")

public class IngredientController {

        @Autowired
        private IngredientService ingredientService;

        @Operation(summary = "Get all ingredients", description = "Return a list of all ingredients with pagination")
        @ApiResponse(responseCode = "200", description = "Success", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
        })
        @GetMapping(params = { "page", "size" })
        public List<Ingredient> getAll(
                        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                        @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
                List<Ingredient> ingredients = ingredientService.getAll(page, size);
                return ingredients;
        }

        @Operation(summary = "Get ingredient by id", description = "Get ingredient by id from the database")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))
                        }),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
        })
        @GetMapping("/{idIngredient}")
        public ResponseEntity <Ingredient> getById(@PathVariable Integer idIngredient) {
                return new ResponseEntity<Ingredient>(ingredientService.getById(idIngredient), HttpStatus.OK);
        }

        @Operation(summary = "Save ingredient", description = "Save a new ingredient")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
        })
        @PostMapping
        public ResponseEntity<String> save(@Valid @RequestBody Ingredient ingredient) {
                ingredientService.save(ingredient);
                return ResponseEntity.ok("Ingredient saved");
        }

        @Operation(summary = "Update an existing ingredient", description = "Update an existing ingredient")
        @ApiResponse(responseCode = "200", description = "Success", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Ingredient.class)))
        })
        @PutMapping("{idIngredient}")
        public ResponseEntity<Ingredient> update(@Valid @RequestBody Ingredient ingredient,
                        @PathVariable @Min(value = 1 , message="The required request parameter idIngredient is negative our missing.") Integer idIngredient) {
                if (!Objects.equals(ingredient.getIdIngredient(), idIngredient))
                        throw new IllegalIdentifierException(" The provider idetinfier do not match ");
                Ingredient ingredientToUpdate = ingredientService.getById(idIngredient);
                ingredientToUpdate.setName(ingredient.getName());
                ingredientService.save(ingredientToUpdate);
                return new ResponseEntity<>(ingredientToUpdate, HttpStatus.OK);
        }

}
