package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.SearchRequestDTO;
import com.proyecto.b.s.dto.response.SearchResponseDTO;
import com.proyecto.b.s.entity.*;
import com.proyecto.b.s.repository.*;
import com.proyecto.b.s.service.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

}
