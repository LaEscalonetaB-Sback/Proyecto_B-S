package com.proyecto.b.s.service.serviceImpl;

import com.proyecto.b.s.dto.modelMapper.ModelMapperInterface;
import com.proyecto.b.s.dto.request.ClientRequestDTO;
import com.proyecto.b.s.dto.response.ClientResponseDTO;
import com.proyecto.b.s.entity.Client;
import com.proyecto.b.s.exception.InvalidResourceException;
import com.proyecto.b.s.repository.ClientRepository;
import com.proyecto.b.s.service.service.ClientService;
import com.proyecto.b.s.utils.HelperValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapperInterface modelMapperInterface;
    private final ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapperInterface modelMapperInterface, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapperInterface = modelMapperInterface;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientResponseDTO> searchClient(String name, Integer cuit) {
        if (name == null && cuit == null) {
            List<Client> clientList = clientRepository.findAll();
            HelperValidator.isEmptyList(clientList);

            return clientList.stream()
                    .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                    .collect(Collectors.toList());
        } else {
            List<Client> clientList = clientRepository.searchBy(name, cuit);
            HelperValidator.isEmptyList(clientList);

            return clientList.stream()
                    .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Client saveClient(ClientRequestDTO clientRequestDTO) {
        Client client = modelMapperInterface.clientReqDTOToClient(clientRequestDTO);

        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) throws Exception {

        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Client findByName(String name) {

        return Optional.ofNullable(clientRepository.findByName(name))
                .orElseThrow(() -> new InvalidResourceException("Cliente no encontrado con el nombre" + name));
    }

    @Override
    public void deleteClient(Long id) throws Exception {
        Client client = findById(id);
        client.setActive(false);
        clientRepository.save(client);
        modelMapperInterface.clientToClientResponseDTO(client);
    }
}
