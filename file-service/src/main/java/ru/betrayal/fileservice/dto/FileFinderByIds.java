package ru.betrayal.fileservice.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class FileFinderByIds {
    private Set<UUID> ids;
}
