package com.github.heliommsfilho.imperium_cash.api.resource;

import com.github.heliommsfilho.imperium_cash.api.helper.MapperHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractResource {

    protected static ResponseEntity<List<?>> ok(List<?> objects, Class<?> destinationType) {
        List<?> dtoList = objects.stream().map(e -> MapperHelper.getInstance().map(e, destinationType))
                                          .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    protected static ResponseEntity<?> okOrNoContent(Object object, Class<?> destinationType) {
        return Objects.nonNull(object) ? ResponseEntity.ok(MapperHelper.getInstance().map(object, destinationType)) :
                                         ResponseEntity.noContent().build();
    }

    protected static ResponseEntity<?> created(Object object, Class<?> destinationType) {
        Object dto = MapperHelper.getInstance().map(object, destinationType);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    protected static ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }

    protected static <T> T unwrapInput(Object object, Class<T> destinationType) {
        return MapperHelper.getInstance().map(object, destinationType);
    }
}
