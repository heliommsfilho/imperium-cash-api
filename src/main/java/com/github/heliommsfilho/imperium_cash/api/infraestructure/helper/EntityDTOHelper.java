package com.github.heliommsfilho.imperium_cash.api.infraestructure.helper;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import java.util.List;
import java.util.Optional;

public class EntityDTOHelper {

    private static ModelMapper mapper;

    /**
     * Private not-instantiable constructor.
     * */
    private EntityDTOHelper() {
        throw new IllegalStateException("EntityDTOHelper is static and should not be instantiated");
    }

    /**
     * Returns {@link EntityDTOHelper#mapper} instance.
     *
     * @return {@link EntityDTOHelper#mapper}.
     * */
    public static ModelMapper getInstance() {
        if (mapper == null) {
            create();
        }

        return mapper;
    }

    /**
     * Returns a {@code Optional} wrapped value for a {@code BaseEntity} instance.
     *
     * @param entity {@code BaseEntity} instance.
     * @return {@code Optional<T>}*/
    public static <T> Optional<T> getOptional(T entity) {
        return Optional.ofNullable(entity);
    }

    /**
     * Initialize {@link EntityDTOHelper#mapper}.
     * */
    private static void create() {
        mapper =  new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(EntityDTOHelper::avoidLazyInitializationExceptionCondition);
    }



    /**
     * Instruct ModelMapper to avoid to map a not-initialized {@code PersistentCollection}.
     *
     * @param context ModelMapperContext.
     *
     * @return {@code true} if the {@code PersistentCollection} can be safely mapped, or {@code false} otherwise.
     * */
    private static boolean avoidLazyInitializationExceptionCondition(MappingContext<Object, Object> context) {
        boolean canMap = true;

        if (context.getSource() instanceof PersistentCollection) {
            PersistentCollection persistentCollection = (PersistentCollection) context.getSource();

            if (!persistentCollection.wasInitialized()) {
                canMap = false;
            }
        }

        return canMap;
    }

    /**
     * Returns a {@code Optional} first value from a {@code List}. if not empty.
     *
     * @param list List of values.
     *
     * @return {@code Optional} first value from a {@code List} or {@link Optional#empty()} otherwise.
     * */
    public static <T> Optional<T> getSingleResult(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }
}
