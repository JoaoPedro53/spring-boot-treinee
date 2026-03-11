package developer.jota.service;

import developer.jota.domain.Producer;
import developer.jota.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository repository;

    public List<Producer> listAll(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Producer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));
    }

    public Producer save(Producer producer) {
        repository.save(producer);
        return producer;
    }

    public void delete(Long id) {
        var producer = findById(id);
        repository.delete(producer);
    }

    public void update(Producer producerToUpdate) {
        var producer = findById(producerToUpdate.getId());
        producerToUpdate.setCreatedAt(producer.getCreatedAt());
        repository.update(producerToUpdate);
    }
}
