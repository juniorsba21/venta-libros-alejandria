package com.libreria.alejandria.repository;
import com.libreria.alejandria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {}