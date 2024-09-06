package com.paulina.tg.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int quantity;
    private LocalDateTime restockDate;
    private String reason;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private OrderItem orderItem;

    @OneToOne()
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
