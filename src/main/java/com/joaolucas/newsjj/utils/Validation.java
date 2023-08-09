package com.joaolucas.newsjj.utils;

import com.joaolucas.newsjj.model.dto.UserDTO;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Validation {
    public boolean isUserInfoValid(UserDTO request) throws IllegalAccessException {
        if(isAllFieldsNull(request)) return false;
        if(request.getFirstName() != null && request.getFirstName().isBlank() || request.getFirstName() != null && request.getFirstName().length() > 25) return false;
        if(request.getLastName() != null && request.getLastName().isBlank() || request.getLastName() != null && request.getLastName().length() > 25) return false;
        if(request.getUsername() != null && request.getUsername().isBlank() || request.getUsername() != null && request.getUsername().length() > 15) return false;
        if(request.getBio() != null && request.getBio().isBlank() || request.getBio() != null && request.getBio().length() > 160) return false;
        if(request.getBirthDate() != null && request.getBirthDate().isAfter(LocalDate.now().minusYears(12))) return false;
        if(request.getProfilePicUrl() != null && request.getProfilePicUrl().isBlank()) return false;

        return true;
    }



    private boolean isAllFieldsNull(Object object) throws IllegalAccessException {

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);

            if(field.get(object) != null) return false;
        }

        return true;
    }

}
