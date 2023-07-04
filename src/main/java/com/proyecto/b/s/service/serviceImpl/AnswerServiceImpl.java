package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.request.AnswerRequestDTO;
import com.proyecto.b.s.dto.request.eventRequestDTO.EventOptionForEventRequestDTO;
import com.proyecto.b.s.dto.response.eventResponseDTO.EventOptionForEventResponseDTO;
import com.proyecto.b.s.entity.Answer;
import com.proyecto.b.s.repository.AnswerRepository;
import com.proyecto.b.s.service.service.AnswerService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final ModelMapper modelMapper;

    public AnswerServiceImpl(AnswerRepository answerRepository, ModelMapper modelMapper) {
        this.answerRepository = answerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Answer findById(Long id) throws Exception {

        return answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer no encontrada con id: " + id));
    }

    @Override
    public boolean existById(Long id) {
        return answerRepository.existsById(id);
    }

    @Override
    public List<Answer> listAnswer() {
        List<Answer> answerList = answerRepository.findAll();
        HelperValidator.isEmptyList(answerList);

        return answerList;
    }

    @Override
    public Answer findByName(String name) {
        return answerRepository.findByName(name);
    }

    @Override
    public AnswerRequestDTO save(AnswerRequestDTO event) {
        Answer result = modelMapper.map(event,Answer.class);
        answerRepository.save(result);
        return modelMapper.map(result, AnswerRequestDTO.class);
    }
}
