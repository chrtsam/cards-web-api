package com.chrtsam.cards.api.rest;

import com.chrtsam.cards.api.auth.AuthUser;
import com.chrtsam.cards.api.exception.EntityNotFoundException;
import com.chrtsam.cards.api.model.Task;
import com.chrtsam.cards.api.model.Status;
import com.chrtsam.cards.api.model.Task_;
import com.chrtsam.cards.api.model.external.TaskDTO;
import com.chrtsam.cards.api.model.external.ErrorResponse;
import com.chrtsam.cards.api.model.external.SearchRequest;
import com.chrtsam.cards.api.model.helper.Condition;
import com.chrtsam.cards.api.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Chris
 */
@Tag(name = "Task API", description = "Allows users to create and manage tasks in the form of cards")
@RestController
@RequestMapping("/card")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService cardService;
    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get a card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task exists",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Task not found"),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity getTask(@PathVariable("id") int id) {
        try {
            Task card = cardService.findById(id);
            TaskDTO cardResponse = modelMapper.map(card, TaskDTO.class);
            return ResponseEntity.ok(cardResponse);
        } catch (EntityNotFoundException exc) {
            return ResponseEntity.notFound().build();
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    @Operation(summary = "Add a new card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task succesfully created"),
        @ApiResponse(responseCode = "400", description = "Possibly a validation error. See response for more information"),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public HttpEntity addTask(@Valid @RequestBody TaskDTO cardRequest, @AuthenticationPrincipal AuthUser authenticatedUser) {
        try {
            Task c = new Task();
            c.setName(cardRequest.getName());
            c.setDescription(cardRequest.getDescription());
            c.setColor(cardRequest.getColor());
            Task savedTask = cardService.saveTask(c, authenticatedUser.getId());
            URI entityUri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedTask.getId())
                    .toUri();
            return ResponseEntity.created(entityUri).build();
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.badRequest().build();
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    @Operation(summary = "Update a card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task updated",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Task not found"),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public HttpEntity updateTask(@Valid @RequestBody TaskDTO cardRequest, @PathVariable("id") int id) {
        try {
            Task card = cardService.findById(id);
            card.setName(cardRequest.getName());
            card.setDescription(cardRequest.getDescription());
            card.setColor(cardRequest.getColor());
            card.setStatus(cardRequest.getStatus());
            Task savedTask = cardService.updateTask(card);
            TaskDTO cardResponse = modelMapper.map(savedTask, TaskDTO.class);
            return ResponseEntity.ok(cardResponse);
        } catch (EntityNotFoundException exc) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.badRequest().build();
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    @Operation(summary = "Change the status of a card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task exists",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Task not found"),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/{id}/status", method = RequestMethod.POST)
    public HttpEntity changeStatus(@Valid @RequestBody Status cardStatus, @PathVariable("id") int id) {
        try {
            cardService.changeTaskStatus(id, cardStatus);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException exc) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException exc) {
            return ResponseEntity.badRequest().build();
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    @Operation(summary = "Delete a card")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task deleted"),
        @ApiResponse(responseCode = "404", description = "Task could not be found"),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity deleteTask(@PathVariable("id") int id) {
        try {
            boolean deleted = cardService.deleteTask(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }

    @Operation(summary = "Search cards")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search returned results",
                content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))}),
        @ApiResponse(responseCode = "500", description = "Unknown error. Use the code provided in the message field for troubleshooting",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
    public HttpEntity searchTasks(@RequestBody SearchRequest searchRequest, Pageable page, @AuthenticationPrincipal AuthUser authenticatedUser) {
        try {
            List<Condition> entityConditions = TaskDTO.translateConditions(searchRequest.getConditions());
            if (!authenticatedUser.isAdmin()) {
                entityConditions.add(new Condition(Task_.OWNER, Condition.EQUALS, authenticatedUser.getId()));
            }
            Page<Task> resultPage = cardService.searchTasks(entityConditions, page);
            List<Task> cards = resultPage.getContent();
            List<TaskDTO> dtoTasks = new ArrayList<>(cards.size());
            for (Task card : cards) {
                dtoTasks.add(modelMapper.map(card, TaskDTO.class));
            }
            return ResponseEntity.ok(dtoTasks);
        } catch (Exception exc) {
            UUID errorTracer = UUID.randomUUID();
            logger.error("Error code: " + errorTracer, exc);
            return ResponseEntity.internalServerError().body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error code: " + errorTracer));
        }
    }
}
