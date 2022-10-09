package fr.axzial.paymybuddy.mapper.user;

import fr.axzial.paymybuddy.dto.user.WebUserDTO;
import fr.axzial.paymybuddy.mapper.IMapper;
import fr.axzial.paymybuddy.model.user.WebUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WebUserMapper extends IMapper<WebUser, WebUserDTO> {
}
