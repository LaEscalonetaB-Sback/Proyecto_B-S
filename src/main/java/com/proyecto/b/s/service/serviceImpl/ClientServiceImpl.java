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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapperInterface modelMapperInterface;

    @Autowired
    private ModelMapper modelMapper;

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
    public void deleteClient(Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new InvalidResourceException("Client not found -" + this.getClass().getName()));

        client.setActive(false);

        clientRepository.save(client);

        modelMapperInterface.clientToClientResponseDTO(client);
    }
}
