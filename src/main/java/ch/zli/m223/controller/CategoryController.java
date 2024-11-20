package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.resource.NotSupportedException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Category;
import ch.zli.m223.service.CategoryService;

@Path("/category")
@Tag(name = "Category", description = "Handling of categorys")
public class CategoryController {

  @Inject
  CategoryService categoryService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all Categorys.", description = "Returns a list of all Categorys.")
  public List<Category> index() {
    return categoryService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new category.", description = "Creates a new category and returns the newly added category.")
  public Category create(Category category) {
    return categoryService.createCategory(category);
  }

  @Path("/{id}")
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Deletes an category.", description = "Deletes an category by its id.")
  public void delete(@PathParam("id") Long id) {
    categoryService.deleteCategory(id);
  }

  @Path("/{id}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates an category.", description = "Updates an category by its id.")
  public Category update(@PathParam("id") Long id, Category category) throws NotSupportedException {
    return categoryService.updateCategory(id, category);
  }
}
