package br.com.ellenmiranda.delivery_registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ellenmiranda.delivery_registration.entity.Delivery;
import br.com.ellenmiranda.delivery_registration.repository.DeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        Sort sort = Sort.by("dataLimiteEntrega").descending().and(
                Sort.by("nomeCliente").ascending());
        return deliveryRepository.findAll(sort);
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    delivery.setQtdPacotes(updatedDelivery.getQtdPacotes());
                    delivery.setDataLimiteEntrega(updatedDelivery.getDataLimiteEntrega());
                    delivery.setNomeCliente(updatedDelivery.getNomeCliente());
                    delivery.setCpfCliente(updatedDelivery.getCpfCliente());
                    delivery.setEndereco(updatedDelivery.getEndereco());
                    return deliveryRepository.save(delivery);
                })
                .orElseThrow(() -> new RuntimeException("Entrega não encontrada!"));
    }

    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
