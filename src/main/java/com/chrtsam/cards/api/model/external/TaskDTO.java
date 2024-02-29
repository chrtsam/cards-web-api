package com.chrtsam.cards.api.model.external;

import com.chrtsam.cards.api.model.Task;
import com.chrtsam.cards.api.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.sql.Date;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

/**
 *
 * @author Chris
 */
@Schema(name = "Task", description = "A task assigned, and managed by a user")
public class TaskDTO {

    @Schema(description = "Tasks identifier. Used only as a reference for the update and find")
    private Integer id;

    @Schema(description = "The name of the task")
    @NotBlank(message = "name is required")
    private String name;

    @Schema(description = "A short description of the task")
    private String description;

    @Schema(description = "The color code of the task. The value should be a right color code. e.g. #H47FQE")
    @Pattern(regexp = "^#[a-zA-Z0-9]{6}$")
    private String color;

    @Schema(description = "The current status of the task.")
    private Status status;

    @Schema(description = "The date the task was created")
    private Date dateCreated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonIgnore
    public static final void addMappings(ModelMapper modelMapper) {
        TypeMap<Task, TaskDTO> cardDtoTypeMap = modelMapper.createTypeMap(Task.class, TaskDTO.class);
        cardDtoTypeMap.addMappings(mapper -> {
            mapper.map(src -> src.getId(), TaskDTO::setId);
            mapper.map(src -> src.getName(), TaskDTO::setName);
            mapper.map(src -> src.getDescription(), TaskDTO::setDescription);
            mapper.map(src -> src.getColor(), TaskDTO::setColor);
            mapper.map(src -> src.getStatus(), TaskDTO::setStatus);
            mapper.map(src -> src.getDateCreated(), TaskDTO::setDateCreated);
        });
    }
}
