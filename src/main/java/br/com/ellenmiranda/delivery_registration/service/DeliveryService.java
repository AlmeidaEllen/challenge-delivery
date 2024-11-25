package br.com.ellenmiranda.delivery_registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ellenmiranda.delivery_registration.entity.Delivery;
import br.com.ellenmiranda.delivery_registration.repository.DeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery createDelivery(Delivery delivery) {
        logger.info("Criando nova entrega: {}", delivery);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        logger.info("Entrega criada com sucesso: {}", savedDelivery);
        return savedDelivery;
    }

    public List<Delivery> getAllDeliveries() {
        logger.debug("Recuperando todas as entregas");
        Sort sort = Sort.by("dataLimiteEntrega").descending().and(
                Sort.by("nomeCliente").ascending());
        List<Delivery> deliveries = deliveryRepository.findAll(sort);
        logger.debug("Total de entregas recuperadas: {}", deliveries.size());
        return deliveries;
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        logger.info("Buscando entrega com ID: {}", id);
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            logger.info("Entrega encontrada: {}", delivery.get());
        } else {
            logger.warn("Entrega com ID {} não encontrada", id);
        }
        return delivery;
    }

    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        logger.info("Atualizando entrega com ID: {}", id);
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    delivery.setQtdPacotes(updatedDelivery.getQtdPacotes());
                    delivery.setDataLimiteEntrega(updatedDelivery.getDataLimiteEntrega());
                    delivery.setNomeCliente(updatedDelivery.getNomeCliente());
                    delivery.setCpfCliente(updatedDelivery.getCpfCliente());
                    delivery.setEndereco(updatedDelivery.getEndereco());
                    Delivery savedDelivery = deliveryRepository.save(delivery);
                    logger.info("Entrega atualizada com sucesso: {}", savedDelivery);
                    return savedDelivery;
                })
                .orElseThrow(() -> {
                    logger.error("Entrega com ID {} não encontrada", id);
                    return new RuntimeException("Entrega não encontrada!");
                });
    }

    public void deleteDelivery(Long id) {
        logger.warn("Excluindo entrega com ID: {}", id);
        deliveryRepository.deleteById(id);
        logger.info("Entrega com ID {} excluída com sucesso", id);
    }
}