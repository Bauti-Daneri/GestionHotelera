package com.hotelops.domain.usecase.roomservice;

import com.hotelops.domain.model.OrderStatus;
import com.hotelops.domain.model.RoomServiceOrder;
import com.hotelops.domain.repository.RoomServiceRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/hotelops/domain/usecase/roomservice/UpdateOrderStatusUseCase;", "", "roomServiceRepository", "Lcom/hotelops/domain/repository/RoomServiceRepository;", "(Lcom/hotelops/domain/repository/RoomServiceRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/RoomServiceOrder;", "orderId", "", "status", "Lcom/hotelops/domain/model/OrderStatus;", "app_debug"})
public final class UpdateOrderStatusUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.RoomServiceRepository roomServiceRepository = null;
    
    @javax.inject.Inject()
    public UpdateOrderStatusUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.RoomServiceRepository roomServiceRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.RoomServiceOrder>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus status) {
        return null;
    }
}