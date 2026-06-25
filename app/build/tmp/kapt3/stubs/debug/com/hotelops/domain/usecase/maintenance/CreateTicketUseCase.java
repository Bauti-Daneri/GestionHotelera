package com.hotelops.domain.usecase.maintenance;

import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.repository.MaintenanceRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JY\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/hotelops/domain/usecase/maintenance/CreateTicketUseCase;", "", "maintenanceRepository", "Lcom/hotelops/domain/repository/MaintenanceRepository;", "(Lcom/hotelops/domain/repository/MaintenanceRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/MaintenanceTicket;", "hotelId", "", "roomId", "title", "description", "category", "Lcom/hotelops/domain/model/TicketCategory;", "priority", "reportedBy", "imageUrl", "app_debug"})
public final class CreateTicketUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.MaintenanceRepository maintenanceRepository = null;
    
    @javax.inject.Inject()
    public CreateTicketUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.MaintenanceRepository maintenanceRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.MaintenanceTicket>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String priority, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedBy, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl) {
        return null;
    }
}