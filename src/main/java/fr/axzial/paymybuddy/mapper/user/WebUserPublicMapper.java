package fr.axzial.paymybuddy.mapper.user;

import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.mapper.IMapper;
import fr.axzial.paymybuddy.model.user.WebUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebUserPublicMapper extends IMapper<WebUser, PublicWebUserDTO> {
}
