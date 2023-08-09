package com.joaolucas.newsjj.utils;

import com.joaolucas.newsjj.model.dto.CommentDTO;
import com.joaolucas.newsjj.model.dto.NewsDTO;
import com.joaolucas.newsjj.model.dto.TopicDTO;
import com.joaolucas.newsjj.model.dto.UserDTO;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Validation {
    public boolean isUserInfoValid(UserDTO request) {
        if(isAllFieldsNull(request)) return false;
        if(request.getFirstName() != null && request.getFirstName().isBlank() || request.getFirstName() != null && request.getFirstName().length() > 25) return false;
        if(request.getLastName() != null && request.getLastName().isBlank() || request.getLastName() != null && request.getLastName().length() > 25) return false;
        if(request.getUsername() != null && request.getUsername().isBlank() || request.getUsername() != null && request.getUsername().length() > 15) return false;
        if(request.getBio() != null && request.getBio().isBlank() || request.getBio() != null && request.getBio().length() > 160) return false;
        if(request.getBirthDate() != null && request.getBirthDate().isAfter(LocalDate.now().minusYears(12))) return false;
        if(request.getProfilePicUrl() != null && request.getProfilePicUrl().isBlank()) return false;

        return true;
    }

    public boolean isTopicInfoValid(TopicDTO request){
        if(isAllFieldsNull(request)) return false;
        if(request.getName() != null && request.getName().isBlank() || request.getName() != null && request.getName().length() > 25) return false;
        if(request.getDescription() != null && request.getDescription().isBlank() || request.getDescription() != null && request.getDescription().length() > 160) return false;
        return true;
    }

    public boolean isNewsInfoValid(NewsDTO request){
        if(isAllFieldsNull(request)) return false;
        if(request.getTitle() != null && request.getTitle().isBlank() || request.getTitle() != null && request.getTitle().length() > 65) return false;
        if(request.getText() != null && request.getText().isBlank() || request.getText() != null && request.getText().length() > 500) return false;
        return true;
    }

    public boolean isCommentInfoValid(CommentDTO request){
        if(isAllFieldsNull(request)) return false;
        if(request.getText() != null && request.getText().isBlank() || request.getText() != null && request.getText().length() > 280) return false;
        return true;
    }

    private boolean isAllFieldsNull(Object object)  {

        Field[] fields = object.getClass().getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);

            try {
                if(field.get(object) != null) return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }

}
