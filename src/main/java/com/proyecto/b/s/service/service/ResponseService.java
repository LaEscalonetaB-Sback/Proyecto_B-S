package com.proyecto.b.s.service.service;

import com.proyecto.b.s.entity.Response;

public interface ResponseService {
    Response findByName(String name) throws Exception;
}
