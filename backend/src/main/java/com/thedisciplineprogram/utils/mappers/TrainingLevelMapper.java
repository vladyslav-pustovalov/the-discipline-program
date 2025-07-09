package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.entities.TrainingLevel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingLevelMapper {

    TrainingLevelDTO toDTO(TrainingLevel trainingLevel);

    TrainingLevel toEntity(TrainingLevelDTO trainingLevelDTO);
}
