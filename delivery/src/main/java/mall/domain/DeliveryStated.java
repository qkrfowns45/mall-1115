package mall.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import mall.domain.*;
import mall.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryStated extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private Integer qty;

    public DeliveryStated(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryStated() {
        super();
    }
}
//>>> DDD / Domain Event
