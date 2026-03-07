package developer.jota.service;

import developer.jota.domain.Producer;
import developer.jota.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository repository;

    public List<Producer> listAll(String name) {
        return name == null ? repository.findAll() : repository.findByName(name);
    }

    public Producer findById(Long id) {
        return repository.findById(id);
    }

    public Producer save(Producer producer) {
        repository.save(producer);
        return producer;
    }

    public void delete(Long id) {
        var producer = repository.findById(id);
        repository.delete(producer);
    }

    public void update(Producer producerToUpdate) {
        var producer = repository.findById(producerToUpdate.getId());
        producerToUpdate.setCreatedAt(producer.getCreatedAt());
        repository.update(producerToUpdate);
    }
}
