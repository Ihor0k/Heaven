package ua.ihor0k.heaven.service;

import ua.ihor0k.heaven.dto.OptionDTO;
import ua.ihor0k.heaven.dto.ParameterDTO;

import java.util.List;

public interface AdminService {
    ParameterDTO createParameter(ParameterDTO parameter);

    List<ParameterDTO> getParameters();

    void updateParameter(Long id, ParameterDTO parameter);

    void deleteParameter(Long id);

    void moveParameter(Long id, Integer position);

    OptionDTO createOption(Long parameterId, OptionDTO option);

    void deleteOption(Long parameterId, Long optionId);

    void moveOption(Long parameterId, Long optionId, Integer position);
}
