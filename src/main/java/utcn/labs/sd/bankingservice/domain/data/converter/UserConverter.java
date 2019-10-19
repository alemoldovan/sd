package utcn.labs.sd.bankingservice.domain.data.converter;

import java.util.ArrayList;
import java.util.List;


import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;

public class UserConverter {

	
	 private UserConverter() {
	    }

	    public static UserDTO toDto(User model) {
	        UserDTO dto = null;
	        if (model != null) {
	            dto = new UserDTO(model.getUserId(),model.getRole(), model.getUsername(), model.getPassword(), model.getName(), model.getEmail());
	        }
	        return dto;
	    }

	    public static List<UserDTO> toDto(List<User> models) {
	        List<UserDTO> userDtos = new ArrayList<>();
	        if ((models != null) && (!models.isEmpty())) {
	            for (User model : models) {
	                userDtos.add(toDto(model));
	            }
	        }
	        return userDtos;
	    }
}
