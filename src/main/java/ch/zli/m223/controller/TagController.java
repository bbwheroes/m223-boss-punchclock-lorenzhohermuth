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

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;

@Path("/tags")
@org.eclipse.microprofile.openapi.annotations.tags.Tag(name = "Tags", description = "Handling of Tags")
public class TagController {

  @Inject
  TagService tagService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
  public List<Tag> index() {
    return tagService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
  public Tag create(Tag entry) {
    return tagService.createTag(entry);
  }

  @Path("/{id}")
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Deletes an entry.", description = "Deletes an entry by its id.")
  public void delete(@PathParam("id") Long id) {
    tagService.deleteTag(id);
  }

  @Path("/{id}")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates an entry.", description = "Updates an entry by its id.")
  public Tag update(@PathParam("id") Long id, Tag entry) throws NotSupportedException {
    return tagService.updateTag(id, entry);
  }
}