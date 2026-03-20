package developer.jota.repository;

import developer.jota.domain.Producer;
import external.dependency.Connection;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Log4j2
public class ProducerRepository {
    @Qualifier(value = "connMongoDB")
    private final Connection connection;
    private final ProducerData producerData;

    public List<Producer> findAll() {
        return producerData.getProducers();
    }

    public List<Producer> findByName(String name) {
        log.info(connection);
        return producerData.getProducers().stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }

    public Optional<Producer> findById(Long id) {
        return producerData.getProducers().stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }

    public Producer save(Producer producer) {
            producerData.getProducers().add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        producerData.getProducers().remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }


}
