package com.protobin.project.service;

import com.protobin.project.core.ProjectEqualsUtils;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.mapper.ProjectMapper;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.protobin.project.core.ProjectDtoUtils.*;
import static com.protobin.project.core.ProjectEntityUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Spy
    private ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void testFindById_happyPath() {
        //given
        var idToSearch = UUID.randomUUID();
        var generatedProject = generateProject();
        generatedProject.setId(idToSearch);

        when(projectRepository.findById(idToSearch)).thenReturn(Optional.of(generatedProject));

        //when
        var responseDto = projectService.findById(idToSearch);

        //then
        ProjectEqualsUtils.equals(responseDto, generatedProject);
    }

    @Test
    void testFindById_whenNotFound() {
        //given
        var idToSearch = UUID.randomUUID();
        when(projectRepository.findById(idToSearch)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundException.class,
                () -> projectService.findById(idToSearch));

        //then
    }

    @Test
    void testCreate_happyPath() {
        //given
        var createDto = generateCreateDto();
        when(projectRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        //when
        var responseDto = projectService.create(createDto);

        //then
        ProjectEqualsUtils.equals(responseDto, createDto);
    }

    @Test
    void testUpdate_happyPath() {
        //given
        var idToUpdate = UUID.randomUUID();
        var generatedProject = generateProject();
        generatedProject.setId(idToUpdate);
        var updateDto = generateUpdateDto(idToUpdate);

        when(projectRepository.findById(idToUpdate)).thenReturn(Optional.of(generatedProject));
        when(projectRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        //when
        var responseDto = projectService.update(updateDto);

        //then
        ProjectEqualsUtils.equals(responseDto, updateDto);
    }

    @Test
    void testUpdate_whenNotFound() {
        //given
        var idToUpdate = UUID.randomUUID();
        var updateDto = generateUpdateDto(idToUpdate);

        when(projectRepository.findById(idToUpdate)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundException.class,
                () -> projectService.update(updateDto));

        //then
    }

    @Test
    void testDeleteById_happyPath() {
        //given
        var idToDelete = UUID.randomUUID();
        var generatedProject = generateProject();
        generatedProject.setId(idToDelete);

        when(projectRepository.findById(idToDelete)).thenReturn(Optional.of(generatedProject));

        //when
        projectService.deleteById(generatedProject.getId());

        //then
        verify(projectRepository).deleteById(generatedProject.getId());
    }

    @Test
    void testDeleteById_whenNotFound() {
        //given
        var idToDelete = UUID.randomUUID();
        when(projectRepository.findById(idToDelete)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundException.class,
                () -> projectService.deleteById(idToDelete));

        //then
    }
}
