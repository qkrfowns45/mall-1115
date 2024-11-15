package mall.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import mall.DeliveryApplication;
import mall.domain.DeliveryStated;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long productId;

    private Integer qty;

    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveryStated deliveryStated = new DeliveryStated(this);
        deliveryStated.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void startDelivery(OrderPlaced orderPlaced) {
        
        Delivery delivery = new Delivery();

        delivery.setProductId(orderPlaced.getProductId());
        delivery.setQty(orderPlaced.getQty());
        delivery.setStatus(orderPlaced.getStatus());
        delivery.setUserId(orderPlaced.getUserId());
        delivery.setStatus(orderPlaced.getStatus());
        
        repository().save(delivery);

        DeliveryStated deliveryStated = new DeliveryStated(delivery);
        deliveryStated.publishAfterCommit();

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
