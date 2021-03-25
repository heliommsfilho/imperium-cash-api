package com.github.heliommsfilho.imperium_cash.api.resource;

import com.github.heliommsfilho.imperium_cash.api.helper.EntityDTOHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractResource {

    protected static ResponseEntity<List<?>> ok(List<?> objects, Class<?> destinationType) {
        List<?> dtoList = objects.stream().map(e -> EntityDTOHelper.getInstance().map(e, destinationType))
                                          .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    protected static ResponseEntity<?> okOrNoContent(Optional<?> object, Class<?> destinationType) {
        ResponseEntity<?> responseEntity = ResponseEntity.noContent().build();

        if (object.isPresent()) {
            responseEntity = ResponseEntity.ok(EntityDTOHelper.getInstance().map(object.get(), destinationType));
        }

        return responseEntity;
    }

    protected static ResponseEntity<?> created(Object object, Class<?> destinationType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(EntityDTOHelper.getInstance().map(object, destinationType));
    }

    protected static ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }
}
