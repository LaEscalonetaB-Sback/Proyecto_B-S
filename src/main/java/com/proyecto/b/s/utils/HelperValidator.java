package com.proyecto.b.s.utils;

import com.proyecto.b.s.exception.InvalidResourceException;

import java.util.List;

public class HelperValidator {

    public static List<?> isEmptyList(List<?> list) {
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new InvalidResourceException("La lista solicitada se encuentra vacia");
        }
    }
}
