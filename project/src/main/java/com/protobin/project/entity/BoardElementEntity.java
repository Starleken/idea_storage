package com.protobin.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.protobin.project.entity.aware.CreatedAtTimestampAware;
import com.protobin.project.entity.aware.UpdateAtTimestampAware;
import com.protobin.project.entity.enums.BoardElementEntityType;
import com.protobin.project.entity.listener.HistoryListener;
import com.protobin.project.entity.listener.TimestampListener;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Data
@Table(name = "board_elements")
@EntityListeners({TimestampListener.class, HistoryListener.class})
public class BoardElementEntity implements CreatedAtTimestampAware, UpdateAtTimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Column(name = "entity_type", nullable = false, columnDefinition = "board_element_entity_type_enum")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private BoardElementEntityType type;

    @Column(name = "coordinate_x", nullable = false)
    private float coordinateX;

    @Column(name = "coordinate_y", nullable = false)
    private float coordinateY;

    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @Column(name = "payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode payload;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;

    @Column(name = "updated_at", nullable = true)
    private Long updatedAt;
}
