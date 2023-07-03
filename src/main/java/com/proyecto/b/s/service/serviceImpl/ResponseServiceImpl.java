package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.entity.Response;
import com.proyecto.b.s.repository.ResponseRespository;
import com.proyecto.b.s.service.service.ResponseService;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRespository responseRespository;

    public ResponseServiceImpl(ResponseRespository responseRespository) {
        this.responseRespository = responseRespository;
    }

    @Override
    public Response findByName(String name) throws Exception {
        return responseRespository.findByName(name);
    }
}
