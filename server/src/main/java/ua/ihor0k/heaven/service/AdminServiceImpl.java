package ua.ihor0k.heaven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ihor0k.heaven.dto.OptionDTO;
import ua.ihor0k.heaven.dto.ParameterDTO;
import ua.ihor0k.heaven.entity.OptionEntity;
import ua.ihor0k.heaven.entity.ParameterEntity;
import ua.ihor0k.heaven.repository.ParameterRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService {
    private ParameterRepository parameterRepository;

    @Override
    public ParameterDTO createParameter(ParameterDTO parameter) {
        int order = (int) parameterRepository.count();
        ParameterEntity parameterEntity = parameterToEntity(parameter, order);
        parameterEntity = parameterRepository.save(parameterEntity);
        return parameterToDTO(parameterEntity);
    }

    @Override
    public List<ParameterDTO> getParameters() {
        return StreamSupport.stream(parameterRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingInt(ParameterEntity::getOrder))
                .map(this::parameterToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateParameter(Long id, ParameterDTO parameter) {
        ParameterEntity parameterEntity = getParameterById(id);
        parameterEntity.setName(parameter.getName());
        parameterEntity.setDescription(parameter.getDescription());
        syncOptions(parameterEntity, parameter.getOptions());
        parameterRepository.save(parameterEntity);
    }

    @Override
    public void deleteParameter(Long id) {
        ParameterEntity parameterEntity = getParameterById(id);
        parameterRepository.findByOrderGreaterThan(parameterEntity.getOrder()).stream()
                .peek(p -> p.setOrder(p.getOrder() - 1))
                .forEach(parameterRepository::save);
        parameterRepository.delete(parameterEntity);
    }

    @Override
    public void moveParameter(Long id, Integer position) {
        ParameterEntity parameterEntity = getParameterById(id);
        int currentPosition = parameterEntity.getOrder();
        if (currentPosition == position) return;
        if (currentPosition < position) {
            parameterRepository.findByOrderBetween(currentPosition + 1, position).stream()
                    .peek(p -> p.setOrder(p.getOrder() - 1))
                    .forEach(parameterRepository::save);
        } else {
            parameterRepository.findByOrderBetween(position, currentPosition - 1).stream()
                    .peek(p -> p.setOrder(p.getOrder() + 1))
                    .forEach(parameterRepository::save);
        }
        parameterEntity.setOrder(position);
        parameterRepository.save(parameterEntity);
    }

    @Override
    public OptionDTO createOption(Long parameterId, OptionDTO option) {
        ParameterEntity parameterEntity = getParameterById(parameterId);
        List<OptionEntity> optionEntities = parameterEntity.getOptions();
        OptionEntity optionEntity = optionToEntity(option, optionEntities.size(), parameterEntity);
        optionEntities.add(optionEntity);
        parameterRepository.save(parameterEntity);
        return optionToDTO(optionEntity);
    }

    @Override
    public void deleteOption(Long parameterId, Long optionId) {
        ParameterEntity parameterEntity = getParameterById(parameterId);
        OptionEntity optionEntity = getOptionById(parameterEntity, optionId);
        parameterEntity.getOptions().remove(optionEntity);
        parameterRepository.save(parameterEntity);
    }

    @Override
    public void moveOption(Long parameterId, Long optionId, Integer position) {
        ParameterEntity parameterEntity = getParameterById(parameterId);
        OptionEntity optionEntity = getOptionById(parameterEntity, optionId);
        int currentPosition = optionEntity.getOrder();
        if (currentPosition == position) return;
        List<OptionEntity> optionEntities = parameterEntity.getOptions();
        if (currentPosition < position) {
            optionEntities.stream()
                    .filter(o -> o.getOrder() > currentPosition && o.getOrder() <= position)
                    .forEach(o -> o.setOrder(o.getOrder() - 1));
        } else {
            optionEntities.stream()
                    .filter(o -> o.getOrder() >= position && o.getOrder() < currentPosition)
                    .forEach(o -> o.setOrder(o.getOrder() + 1));
        }
        optionEntity.setOrder(position);
        parameterRepository.save(parameterEntity);
    }

    private void syncOptions(ParameterEntity parameterEntity, List<OptionDTO> syncBy) {
        List<OptionEntity> options = parameterEntity.getOptions();
        Map<Long, OptionEntity> optionById = options.stream()
                .collect(Collectors.toMap(OptionEntity::getId, Function.identity()));

        Set<Long> idToKeep = syncBy.stream()
                .map(OptionDTO::getId)
                .collect(Collectors.toSet());

        options.removeIf(o -> !idToKeep.contains(o.getId()));

        for (int i = 0; i < syncBy.size(); i++) {
            OptionDTO option = syncBy.get(i);
            OptionEntity optionEntity = optionById.get(option.getId());
            if (optionEntity == null) {
                optionEntity = optionToEntity(option, i, parameterEntity);
                options.add(optionEntity);
            } else {
                optionEntity.setName(option.getName());
                optionEntity.setDescription(option.getDescription());
                optionEntity.setPrice(option.getPrice());
                optionEntity.setOrder(i);
                optionEntity.setImage(option.getImage());
            }
        }
    }

    private ParameterEntity parameterToEntity(ParameterDTO parameterDTO, int order) {
        List<OptionDTO> options = parameterDTO.getOptions();
        List<OptionEntity> optionEntities = new ArrayList<>(options.size());
        ParameterEntity parameterEntity = new ParameterEntity(parameterDTO.getName(), parameterDTO.getDescription(), order, optionEntities);
        for (int i = 0; i < options.size(); i++) {
            OptionDTO option = options.get(i);
            OptionEntity optionEntity = optionToEntity(option, i, parameterEntity);
            optionEntities.add(optionEntity);
        }
        return parameterEntity;
    }

    private OptionEntity optionToEntity(OptionDTO optionDTO, int order, ParameterEntity parameterEntity) {
        return new OptionEntity(optionDTO.getName(), optionDTO.getDescription(), optionDTO.getPrice(), order, optionDTO.getImage(), parameterEntity);
    }

    private ParameterDTO parameterToDTO(ParameterEntity parameterEntity) {
        List<OptionDTO> options = parameterEntity.getOptions().stream()
                .sorted(Comparator.comparingInt(OptionEntity::getOrder))
                .map(this::optionToDTO)
                .collect(Collectors.toList());
        return new ParameterDTO(parameterEntity.getId(), parameterEntity.getName(), parameterEntity.getDescription(), options);
    }

    private OptionDTO optionToDTO(OptionEntity optionEntity) {
        return new OptionDTO(optionEntity.getId(), optionEntity.getName(), optionEntity.getDescription(), optionEntity.getPrice(), optionEntity.getImage());
    }

    private ParameterEntity getParameterById(Long id) {
        return parameterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Parameter %d not found", id)));
    }

    private OptionEntity getOptionById(ParameterEntity parameterEntity, Long id) {
        List<OptionEntity> optionEntities = parameterEntity.getOptions();
        for (OptionEntity optionEntity : optionEntities) {
            if (optionEntity.getId().equals(id)) {
                return optionEntity;
            }
        }
        throw new EntityNotFoundException(String.format("Option %d not found in parameter %d", id, parameterEntity.getId()));
    }

    @Autowired
    public void setParameterRepository(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }
}
