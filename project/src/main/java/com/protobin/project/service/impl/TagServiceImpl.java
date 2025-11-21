package com.protobin.project.service.impl;

import com.protobin.project.dto.tag.TagCreateAllDto;
import com.protobin.project.dto.tag.TagResponseDto;
import com.protobin.project.entity.TagEntity;
import com.protobin.project.exception.NotFoundException;
import com.protobin.project.mapper.TagMapper;
import com.protobin.project.repository.ProjectRepository;
import com.protobin.project.repository.TagRepository;
import com.protobin.project.service.ProjectService;
import com.protobin.project.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    private final TagMapper tagMapper;

    @Override
    @Transactional
    public List<TagResponseDto> createAll(TagCreateAllDto createDto) {
        var foundProject = projectRepository.findById(createDto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project is not found"));

        var tagNames = removeDuplicates(createDto.getNames());

        List<TagEntity> toSave = new ArrayList<>();
        for (var tagName: tagNames) {
            var tag = new TagEntity();
            tag.setName(tagName);
            tag.setProject(foundProject);
            toSave.add(tag);
        }

        List<TagEntity> saved = tagRepository.saveAll(toSave);
        return saved.stream().map(tagMapper::mapToDto).toList();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var found = tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag is not found"));

        tagRepository.deleteById(found.getId());
    }

    private List<String> removeDuplicates(List<String> list) {
        Set<String> lowerCaseSet = new LinkedHashSet<>();
        List<String> result = new ArrayList<>();

        for (String s : list) {
            String lower = s.toLowerCase();
            if (!lowerCaseSet.contains(lower)) {
                lowerCaseSet.add(lower);
                result.add(s);
            }
        }
        return result;
    }
}
